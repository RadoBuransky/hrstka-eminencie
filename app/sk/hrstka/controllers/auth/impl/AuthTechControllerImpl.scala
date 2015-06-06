package sk.hrstka.controllers.auth.impl

import java.net.URL

import com.google.inject.{Inject, Singleton}
import jp.t2v.lab.play2.auth.AuthElement
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.Application
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.MessagesApi
import play.api.mvc.{Action, AnyContent}
import sk.hrstka
import sk.hrstka.controllers.auth.{AddTechForm, AuthTechController}
import sk.hrstka.controllers.impl.{BaseController, MainModelProvider}
import sk.hrstka.models.domain._
import sk.hrstka.models.ui.TechCategoryFactory
import sk.hrstka.services.{AuthService, LocationService, TechService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class AuthTechControllerImpl @Inject() (protected val authService: AuthService,
                                        protected val locationService: LocationService,
                                        protected val techService: TechService,
                                        protected val application: Application,
                                        val messagesApi: MessagesApi)
  extends BaseController with AuthTechController with MainModelProvider with HrstkaAuthConfig with AuthElement {
  import AuthTechControllerImpl._

  override def all: Action[AnyContent] = AsyncStack(AuthorityKey -> Eminent) { implicit request =>
    val serviceResult = for {
      allRatings <- techService.allRatings()
      allCategories <- techService.allCategories()
      userVotes <- techService.votesFor(loggedIn.id)
    } yield (allRatings, allCategories, userVotes)

    serviceResult.flatMap {
      case (techRatings, allCategories, userVotes) =>
        withMainModel(None, None, Some(loggedIn)) { implicit mainModel =>
          Ok(sk.hrstka.views.html.techs(
            None,
            techRatings.map(hrstka.models.ui.TechRatingFactory.apply),
            userVotes.map(uv => uv.techId -> uv.value).toMap,
            // TODO: Order categories somehow
            allCategories.toSeq.map(TechCategoryFactory.apply)))
        }
    }
  }

  override def add: Action[AnyContent] = AsyncStack(AuthorityKey -> hrstka.models.domain.Eminent) { implicit request =>
    withForm(addTechForm) { form =>
      techService.upsert(Tech(
        id        = Identifiable.empty,
        handle    = HandleFactory.fromHumanName(form.name),
        category  = TechCategory(form.categoryHandle),
        name      = form.name,
        website   = new URL(form.website)
      )).map { techId =>
        Redirect(sk.hrstka.controllers.auth.routes.AuthTechController.all())
      }
    }
  }

  override def voteUp(id: String) = AsyncStack(AuthorityKey -> hrstka.models.domain.Eminent) { implicit request =>
    vote(techService.voteUp(id, loggedIn.id))
  }

  override def voteDown(id: String) = AsyncStack(AuthorityKey -> hrstka.models.domain.Eminent) { implicit request =>
    vote(techService.voteDown(id, loggedIn.id))
  }

  private def vote[A](action: Future[Unit])(implicit request: RequestWithAttributes[A]) =
    action.map { Unit =>
      Redirect(sk.hrstka.controllers.auth.routes.AuthTechController.all())
    }
}

object AuthTechControllerImpl {
  val addTechForm = Form(
    mapping(
      "categoryHandle" -> text,
      "name" -> text,
      "website" -> text
    )(AddTechForm.apply)(AddTechForm.unapply)
  )
}