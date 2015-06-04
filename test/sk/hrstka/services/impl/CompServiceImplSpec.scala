package sk.hrstka.services.impl

import models.db
import models.domain.TechSpec
import models.domain.{Comp, CompSpec, Handle, TechSpec}
import org.mockito.ArgumentCaptor
import org.mockito.Matchers._
import org.mockito.Mockito._
import reactivemongo.bson.BSONObjectID
import repositories.CompRepository
import sk.hrstka.services.{LocationService, TechService}
import test.BaseSpec

import scala.concurrent.Future

class CompServiceImplSpec extends BaseSpec {
  import models.domain.CompSpec._

  behavior of "upsert"

  it should "map user to DB and invoke repository" in new TestScope {
    // Prepare
    val userId = BSONObjectID.generate.stringify
    val compId = BSONObjectID.generate
    when(compRepository.upsert(any[db.Comp])).thenReturn(Future.successful(compId))

    // Execute
    assert(compService.upsert(
      comp        = avitech,
      techHandles = avitech.techRatings.map(_.tech.handle),
      userId      = db.CompSpec.avitech.authorId.stringify
    ).futureValue == compId.stringify)

    // Verify
    val compCaptor = ArgumentCaptor.forClass(classOf[db.Comp])
    verify(compRepository).upsert(compCaptor.capture())
    verifyNoMore()
    val comp = compCaptor.getValue

    // Assert
    assert(comp == db.CompSpec.avitech)
  }

  behavior of "all"

  it should "return all companies if no city or tech is provided" in new TestScope {
    // Prepare
    when(compRepository.all(None, None))
      .thenReturn(Future.successful(Seq(db.CompSpec.avitech, db.CompSpec.borci)))
    when(techService.allRatings())
      .thenReturn(Future.successful(TechSpec.allRatings))
    when(locationService.get(Handle(db.CompSpec.avitech.city)))
      .thenReturn(Future.successful(CompSpec.avitech.city))
    when(locationService.get(Handle(db.CompSpec.borci.city)))
      .thenReturn(Future.successful(CompSpec.borci.city))

    // Execute
    val result = compService.all(None, None).futureValue.toSet
    assertComp(avitech, result.find(_.id == avitech.id).get)
    assertComp(borci, result.find(_.id == borci.id).get)
    assertResult(Set(avitech, borci))(result)

    // Verify
    verify(compRepository).all(None, None)
    verify(techService).allRatings()
    verify(locationService).get(Handle(db.CompSpec.avitech.city))
    verify(locationService).get(Handle(db.CompSpec.borci.city))
    verifyNoMore()
  }

  it should "return all companies in Bratislava" in new TestScope {
    // Prepare
    when(compRepository.all(city = Some(avitech.city.handle.value), None))
      .thenReturn(Future.successful(Seq(db.CompSpec.avitech)))
    when(techService.allRatings())
      .thenReturn(Future.successful(TechSpec.allRatings))
    when(locationService.get(Handle(db.CompSpec.avitech.city)))
      .thenReturn(Future.successful(CompSpec.avitech.city))

    // Execute
    val result = futureValue(compService.all(city = Some(avitech.city.handle), None)).toSet
    assertComp(avitech, result.find(_.id == avitech.id).get)
    assertResult(Set(avitech))(result)

    // Verify
    verify(compRepository).all(city = Some(avitech.city.handle.value), None)
    verify(techService).allRatings()
    verify(locationService).get(Handle(db.CompSpec.avitech.city))
    verifyNoMore()
  }

  it should "return all companies that use PHP" in new TestScope {
    // Prepare
    when(compRepository.all(None, tech = Some(TechSpec.phpRating.tech.handle.value)))
      .thenReturn(Future.successful(Seq(db.CompSpec.borci)))
    when(techService.allRatings())
      .thenReturn(Future.successful(TechSpec.allRatings))
    when(locationService.get(Handle(db.CompSpec.borci.city)))
      .thenReturn(Future.successful(CompSpec.borci.city))

    // Execute
    val result = futureValue(compService.all(None, tech = Some(TechSpec.phpRating.tech.handle))).toSet
    assertComp(borci, result.find(_.id == borci.id).get)
    assertResult(Set(borci))(result)

    // Verify
    verify(compRepository).all(None, tech = Some(TechSpec.phpRating.tech.handle.value))
    verify(techService).allRatings()
    verify(locationService).get(Handle(db.CompSpec.borci.city))
    verifyNoMore()
  }

  it should "return all companies in Bratislava that use PHP" in new TestScope {
    // Prepare
    when(compRepository.all(city = Some(avitech.city.handle.value), tech = Some(TechSpec.phpRating.tech.handle.value)))
      .thenReturn(Future.successful(Seq.empty))
    when(techService.allRatings())
      .thenReturn(Future.successful(TechSpec.allRatings))

    // Execute
    val result = futureValue(compService.all(city = Some(avitech.city.handle), tech = Some(TechSpec.phpRating.tech.handle))).toSet
    assertResult(Set.empty)(result)

    // Verify
    verify(compRepository).all(city = Some(avitech.city.handle.value), tech = Some(TechSpec.phpRating.tech.handle.value))
    verify(techService).allRatings()
    verifyNoMore()
  }

  behavior of "get"

  it should "return a company" in new TestScope {
    // Prepare
    when(techService.allRatings())
      .thenReturn(Future.successful(TechSpec.allRatings))
    when(compRepository.get(db.CompSpec.avitech._id))
      .thenReturn(Future.successful(db.CompSpec.avitech))
    when(locationService.get(Handle(db.CompSpec.avitech.city)))
      .thenReturn(Future.successful(CompSpec.avitech.city))

    // Execute
    val result = futureValue(compService.get(avitech.id))
    assertResult(avitech)(result)

    // Verify
    verify(techService).allRatings()
    verify(compRepository).get(db.CompSpec.avitech._id)
    verify(locationService).get(Handle(db.CompSpec.avitech.city))
    verifyNoMore()
  }

  behavior of "topWomen"

  it should "return sorted list of companies with the most female programmers" in new TestScope {
    val noCodersSetComp = db.CompSpec.avitech.copy(codersCount = None)
    val noCodersComp = db.CompSpec.avitech.copy(codersCount = Some(0))
    val noFemaleCodersSetComp = db.CompSpec.avitech.copy(femaleCodersCount = None)
    val noFemaleCodersComp = db.CompSpec.avitech.copy(femaleCodersCount = Some(0))

    // Prepare
    when(compRepository.all(None, None))
      .thenReturn(Future.successful(Seq(
        db.CompSpec.borci,
        noCodersSetComp,
        noCodersComp,
        db.CompSpec.avitech,
        noFemaleCodersSetComp,
        noFemaleCodersComp)))
    when(techService.allRatings())
      .thenReturn(Future.successful(TechSpec.allRatings))
    when(locationService.get(Handle(db.CompSpec.avitech.city)))
      .thenReturn(Future.successful(CompSpec.avitech.city))
    when(locationService.get(Handle(db.CompSpec.borci.city)))
      .thenReturn(Future.successful(CompSpec.borci.city))

    // Execute
    assertResult(Seq(borci, avitech)) {
      futureValue(compService.topWomen())
    }

    // Verify
    verify(compRepository).all(None, None)
    verify(techService).allRatings()
    verify(locationService, times(5)).get(Handle(db.CompSpec.avitech.city))
    verify(locationService).get(Handle(db.CompSpec.borci.city))
    verifyNoMore()
  }

  private def assertComp(expected: Comp, actual: Comp): Unit = {
    // Partial assertions
    assertUnapplied(
      Comp.unapply(expected).get.productIterator.toSeq,
      Comp.unapply(actual).get.productIterator.toSeq)

    // Double-check
    assertResult(expected)(actual)
  }

  private def assertUnapplied(expected: Seq[Any], actual: Seq[Any]): Unit =
    expected.zip(actual).foreach { pair =>
      assertResult(pair._1)(pair._2)
  }

  private class TestScope {
    val compRepository = mock[CompRepository]
    val techService = mock[TechService]
    val locationService = mock[LocationService]
    val compService = new CompServiceImpl(compRepository, techService, locationService)

    def verifyNoMore(): Unit = {
      verifyNoMoreInteractions(compRepository)
      verifyNoMoreInteractions(techService)
      verifyNoMoreInteractions(locationService)
    }
  }
}