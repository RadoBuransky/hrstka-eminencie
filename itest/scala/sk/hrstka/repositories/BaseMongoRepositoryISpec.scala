package sk.hrstka.repositories

import org.scalatest.{DoNotDiscover, Suites}
import reactivemongo.bson.BSONObjectID
import sk.hrstka.common.HrstkaException
import sk.hrstka.itest.TestApplication
import sk.hrstka.models.db.{CitySpec, Identifiable}
import sk.hrstka.repositories.itest.BaseRepositoryISpec
import sk.hrstka.repositories.mongoDb.{CityCollection, MongoCityRepository}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@DoNotDiscover
class StandaloneBaseMongoRepositoryISpec extends Suites with TestApplication {
  override val nestedSuites = Vector(new BaseMongoRepositoryISpec(this))
}

@DoNotDiscover
class BaseMongoRepositoryISpec(testApplication: TestApplication)
  extends BaseRepositoryISpec[MongoCityRepository](testApplication, CityCollection) {
  import CitySpec._

  behavior of "insert"

  it should "fail if _id is already set" in { baseMongoRepository =>
    intercept[HrstkaException] { baseMongoRepository.insert(kosice.copy(_id = BSONObjectID.generate)) }
  }

  behavior of "insert and get"

  it should "generate _id and insert entity" in { baseMongoRepository =>
    val result = baseMongoRepository.insert(kosice)
    val id = result.futureValue
    assert(id != Identifiable.empty)
    assert(baseMongoRepository.get(id).futureValue.copy(_id = Identifiable.empty) == kosice)
  }

  behavior of "upsert"

  it should "insert entity if _id is not set" in { baseMongoRepository =>
    val result = baseMongoRepository.upsert(kosice)
    val id = result.futureValue
    assert(id != Identifiable.empty)
    assert(baseMongoRepository.get(id).futureValue.copy(_id = Identifiable.empty) == kosice)
  }

  it should "update entity if _id is set" in { baseMongoRepository =>
    val result = baseMongoRepository.upsert(kosice).flatMap { kosiceId =>
      baseMongoRepository.upsert(kosice.copy(
        _id = kosiceId,
        name  = "abc"
      ))
    }
    val id = result.futureValue
    assert(id != Identifiable.empty)

    val updatedCity = baseMongoRepository.get(id).futureValue
    assert(updatedCity._id == id)
    assert(updatedCity.handle == kosice.handle)
    assert(updatedCity.name == "abc")
  }

  behavior of "getByHandle"

  it should "fail if entity with such handle does not exist" in { baseMongoRepository =>
    val result = baseMongoRepository.getByHandle("x")
    whenReady(result.failed) { ex =>
      assert(ex.isInstanceOf[HrstkaException])
    }
  }

  it should "return entity for the handle" in { baseMongoRepository =>
    val result = insertAll(baseMongoRepository).flatMap { _ =>
      baseMongoRepository.getByHandle(kosice.handle)
    }
    assert(result.futureValue.copy(_id = Identifiable.empty) == kosice)
  }

  behavior of "findByHandle"

  it should "not fail if entity with such handle does not exist" in { baseMongoRepository =>
    val result = baseMongoRepository.findByHandle("x")
    assert(result.futureValue.isEmpty)
  }

  it should "return entity for the handle" in { baseMongoRepository =>
    val result = insertAll(baseMongoRepository).flatMap { _ =>
      baseMongoRepository.findByHandle(kosice.handle)
    }
    assert(result.futureValue.map(_.copy(_id = Identifiable.empty)).contains(kosice))
  }

  behavior of "all"

  it should "return all entities" in { cityRepository =>
    val result = insertAll(cityRepository).flatMap { _ =>
      cityRepository.all()
    }
    assert(result.futureValue.map(_.copy(_id = Identifiable.empty)).toSet == Set(kosice, noveZamky))
  }

  behavior of "remove"

  it should "remove entity by _id" in { cityRepository =>
    val result = for {
      kosiceId <- cityRepository.insert(kosice)
      noveZamkyFuture <- cityRepository.insert(noveZamky)
      _ <- cityRepository.remove(kosiceId)
      allCities <- cityRepository.all()
    } yield allCities

    assert(result.futureValue.map(_.copy(_id = Identifiable.empty)).toSet == Set(noveZamky))
  }


  private def insertAll(cityRepository: CityRepository): Future[Seq[Identifiable.Id]] = for {
      kosiceFuture <- cityRepository.insert(kosice)
      noveZamkyFuture <- cityRepository.insert(noveZamky)
    } yield Seq(kosiceFuture, noveZamkyFuture)
}