package repositories.mongoDb

import models.db.{JsonFormats, User}
import play.api.libs.json.Json
import reactivemongo.core.commands.LastError
import repositories.UserRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class MongoUserRepository extends BaseMongoRepository(UserCollection) with UserRepository {
  import JsonFormats._

  override def insert(user: User): Future[LastError] = super.insert[User](user)
  override def findByEmail(email: String): Future[Option[User]] = find[User](Json.obj("email" -> email)).map(_.headOption)
}