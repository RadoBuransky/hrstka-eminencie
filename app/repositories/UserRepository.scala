package repositories

import com.google.inject.ImplementedBy
import models.db.User
import reactivemongo.core.commands.LastError
import repositories.mongoDb.MongoUserRepository

import scala.concurrent.Future

@ImplementedBy(classOf[MongoUserRepository])
trait UserRepository {
  def insert(user: User): Future[LastError]
  def findByEmail(email: String): Future[Option[User]]
}
