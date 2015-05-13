package repositories.mongoDb

import models.db.Identifiable._
import models.db.JsonFormats._
import models.db.{Comp, Identifiable}
import play.api.libs.json.Json
import reactivemongo.bson.BSONObjectID
import repositories.CompRepository

import scala.concurrent.Future

class MongoCompRepository extends BaseMongoRepository(CompCollection) with CompRepository {
  override def get(compId: Id): Future[Comp] = get[Comp](compId)
  override def all(city: Option[Handle] = None): Future[Seq[Comp]] = {
    val cityQuery = city.map { cityHandle => Json.obj("city" -> cityHandle) }
    find[Comp](cityQuery.getOrElse(Json.obj()))
  }
  override def upsert(comp: Comp): Future[Id] = {
    val compToUpsert = if (comp._id == Identifiable.empty) comp.copy(_id = BSONObjectID.generate) else comp
    super[BaseMongoRepository].upsert(compToUpsert)
  }
}
