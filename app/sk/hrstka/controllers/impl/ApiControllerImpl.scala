package sk.hrstka.controllers.impl

import com.google.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, Controller}
import sk.hrstka.common.Logging
import sk.hrstka.controllers.ApiController
import sk.hrstka.models.api.{CompFactory, JsonFormats, TechFactory}
import sk.hrstka.services.{CompService, LocationService, TechService}

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
final class ApiControllerImpl @Inject() (compService: CompService,
                                         techService: TechService,
                                         locationService: LocationService)
  extends Controller with ApiController with Logging {
  import JsonFormats._

  override def comps() = Action.async { implicit request =>
    compService.all(None, None).map { comps =>
      Ok(Json.toJson(comps.map { comp =>
        CompFactory.fromDomain(comp, sk.hrstka.controllers.routes.CompController.get(comp.id.value).absoluteURL())
      }))
    }
  }

  override def techs(): Action[AnyContent] = Action.async { implicit request =>
    techService.allRatings().map { techRatings =>
      Ok(Json.toJson(techRatings.map(TechFactory.fromDomain)))
    }
  }

  override def cities(): Action[AnyContent] = Action.async { implicit request =>
    locationService.all().map { cities =>
      Ok(Json.toJson(cities.map { city =>
        Json.obj(
          "handle" -> city.handle.value,
          "sk" -> city.sk
        )
      }))
    }
  }
}

private object ApiControllerImpl {
  val compsCacheKey = "api.companies"
}