package sk.hrstka.services.impl.cache

import com.google.inject.{Inject, Singleton}
import sk.hrstka.common.HrstkaCache
import sk.hrstka.models.domain._
import sk.hrstka.repositories.{CompRepository, TechRepository, TechVoteRepository}
import sk.hrstka.services.TechService
import sk.hrstka.services.impl.TechServiceImpl

@Singleton
final class CachedTechServiceImpl @Inject() (hrstkaCache: HrstkaCache,
                                             techRepository: TechRepository,
                                             techVoteRepository: TechVoteRepository,
                                             compRepository: CompRepository) extends TechService {
  protected val underlying: TechService = new TechServiceImpl(techRepository, techVoteRepository, compRepository)

  override def upsert(tech: Tech) = underlying.upsert(tech)
  override def voteUp(handle: Handle, userId: Id) = underlying.voteUp(handle, userId)
  override def getByHandle(handle: Handle) = underlying.getByHandle(handle)
  override def voteDown(handle: Handle, userId: Id) = underlying.voteDown(handle, userId)
  override def allCategories() = hrstkaCache.cacheSuccess("CachedTechServiceImpl.allCategories", underlying.allCategories())
  override def allRatings() = hrstkaCache.cacheSuccess("CachedTechServiceImpl.allRatings", underlying.allRatings())
  override def remove(handle: Handle) = underlying.remove(handle)
  override def allUsedRatings(cityHandle: Option[Handle]) =
    hrstkaCache.cacheSuccess(s"CachedTechServiceImpl.allUsedRatings($cityHandle)", underlying.allUsedRatings(cityHandle))
  override def votesFor(userId: Id) = underlying.votesFor(userId)
}
