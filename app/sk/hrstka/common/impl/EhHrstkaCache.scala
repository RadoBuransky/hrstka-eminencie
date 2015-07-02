package sk.hrstka.common.impl

import com.google.inject.{ Inject, Singleton }
import net.sf.ehcache.{CacheManager, Ehcache}
import play.api.cache.{CacheApi, EhCacheApi}
import play.api.inject.ApplicationLifecycle
import sk.hrstka.common.{HrstkaCache, Logging}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.Duration
import scala.reflect.ClassTag

@Singleton
final class DefaultEhHrstkaCache @Inject() (applicationLifecycle: ApplicationLifecycle)
  extends EhHrstkaCache(applicationLifecycle, EhHrstkaCache.defaultCacheName)

@Singleton
final class HrstkaEhHrstkaCache @Inject() (applicationLifecycle: ApplicationLifecycle)
  extends EhHrstkaCache(applicationLifecycle, EhHrstkaCache.hrsktaCacheName)

abstract class EhHrstkaCache(applicationLifecycle: ApplicationLifecycle,
                                     cacheName: String)
  extends HrstkaCache with CacheApi with Logging {
  import EhHrstkaCache._

  override def set(key: String, value: Any, expiration: Duration): Unit = playEhCacheApi.set(key, value, expiration)
  override def get[T: ClassTag](key: String): Option[T] = playEhCacheApi.get[T](key)
  override def getOrElse[A: ClassTag](key: String, expiration: Duration)(orElse: => A): A = playEhCacheApi.getOrElse[A](key, expiration)(orElse)
  override def remove(key: String): Unit = playEhCacheApi.remove(key)

  override def cacheSuccess[T: ClassTag](key: String)(value: => Future[T]): Future[T] = {
    val prefixedKey = prefixKey(key)
    get[T](prefixedKey) match {
      case Some(result) =>
        logger.info(s"Getting from cache. [$prefixedKey]")
        Future.successful(result)
      case None =>
        value.onSuccess {
          case result =>
            logger.info(s"Putting to cache. [$prefixedKey]")
            set(key, result)
        }
        value
    }
  }

  override def invalidateOnSuccess[T](action: => Future[T]): Future[T] = {
    action.onSuccess {
      case _ => invalidate()
    }
    action
  }

  override def invalidate(): Unit = {
    cache.removeAll()
    logger.info("Cache invalidated.")
  }

  private def prefixKey(key: String) = prefix + key

  private lazy val cacheManager = {
    val result = CacheManager.create()
    logger.info("CacheManager created.")

    applicationLifecycle.addStopHook { () =>
      Future.successful {
        result.shutdown()
        logger.info("CacheManager shut down.")
      }
    }

    result
  }
  private lazy val playEhCacheApi = new EhCacheApi(cache)
  private lazy val cache: Ehcache = {
    logger.info("Ehcache created")
    cacheManager.addCache(cacheName)
    cacheManager.getEhcache(cacheName)
  }
}

private object EhHrstkaCache {
  val defaultCacheName = "defaultCache"
  val hrsktaCacheName = "hrstkaCache"
  val prefix = "sk.hrstka."
}