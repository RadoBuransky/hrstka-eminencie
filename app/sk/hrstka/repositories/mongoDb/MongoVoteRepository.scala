package sk.hrstka.repositories.mongoDb

import com.google.inject.{Inject, Singleton}
import play.api.libs.json.{Json, OFormat}
import play.modules.reactivemongo.ReactiveMongoApi
import play.modules.reactivemongo.json.ImplicitBSONHandlers._
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.bson.{BSONFindAndModifyImplicits, BSONFindAndModifyCommand}
import reactivemongo.bson.{BSONDocument, _}
import sk.hrstka.common.{HrstkaCache, HrstkaException}
import sk.hrstka.models.db.Identifiable.Id
import sk.hrstka.models.db._
import sk.hrstka.repositories.{CompVoteRepository, TechVoteRepository, VoteRepository}
import sk.hrstka.models.db.JsonFormats._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.ClassTag

sealed class MongoVoteRepository[TEntity <: Vote : ClassTag](hrstkaCache: HrstkaCache,
                                                             coll: MongoCollection,
                                                             protected val reactiveMongoApi: ReactiveMongoApi)
                                                            (implicit oFormat: OFormat[TEntity])
  extends BaseMongoRepository[TEntity](coll) with VoteRepository[TEntity] {
  import MongoVoteRepository._

  override def vote(entityId: Id, userId: Id, value: Int): Future[Boolean] = {
    import BSONFindAndModifyCommand._
    import BSONFindAndModifyImplicits._

    val findAndModify = FindAndModify(
      query       = BSONDocument(entityIdField -> entityId, userIdField -> userId),
      modify      = Update(BSONDocument(
        entityIdField -> entityId,
        userIdField -> userId,
        valueField  -> value), fetchNewObject = false),
      upsert      = true,
      sort        = Some(BSONDocument("_id" -> 1)),
      fields      = None)

    val result = db.collection[BSONCollection](coll.name).runCommand(findAndModify).map {
      case FindAndModifyResult(Some(UpdateLastError(_, _, _, Some(error))), _) => throw new HrstkaException(s"Vote error! [$error]")
      case FindAndModifyResult(_, Some(document)) => document.getAs[Int](valueField).getOrElse(value + 1) != value
      case FindAndModifyResult(_, _) => true
    }

    // Invalidate cache if/when vote is stored
    hrstkaCache.invalidateOnSuccess(result)

    result
  }

  override def findValue(entityId: Id, userId: Id): Future[Option[Int]] =
    find(Json.obj(
      entityIdField -> entityId,
      userIdField -> userId))
      .map(_.headOption.map(_.value))

  override def all(userId: Option[Id]): Future[Traversable[TEntity]] =
    userId match {
      case Some(id) => find(Json.obj(userIdField -> userId))
      case None => all()
    }
}

private object MongoVoteRepository {
  val entityIdField = "entityId"
  val userIdField = "userId"
  val valueField = "value"
}

@Singleton
final class MongoTechVoteRepository @Inject()(hrstkaCache: HrstkaCache,
                                              reactiveMongoApi: ReactiveMongoApi)
  extends MongoVoteRepository[TechVote](hrstkaCache, TechVoteCollection, reactiveMongoApi) with TechVoteRepository

@Singleton
final class MongoCompVoteRepository @Inject()(hrstkaCache: HrstkaCache,
                                              reactiveMongoApi: ReactiveMongoApi)
  extends MongoVoteRepository[CompVote](hrstkaCache, CompVoteCollection, reactiveMongoApi) with CompVoteRepository