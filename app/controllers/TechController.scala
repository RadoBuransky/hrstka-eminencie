package controllers

import common.SupportedLang
import models._
import play.api.data.Forms._
import play.api.data._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import services.TechService

import scala.concurrent.Future

case class AddTechForm(techName: String)

trait TechController {
  def insert: Action[AnyContent]
  def all: Action[AnyContent]
  def voteUp(id: String): Action[AnyContent]
  def voteDown(id: String): Action[AnyContent]
}

object TechController {
  val insertTechForm = Form(
    mapping(
      "techName" -> text
    )(AddTechForm.apply)(AddTechForm.unapply)
  )

  def apply(techService: TechService): TechController = new TechControllerImpl(techService)
}

private class TechControllerImpl(techService: TechService) extends BaseController with TechController {
  import controllers.TechController._

  override def insert: Action[AnyContent] = Action.async { implicit request =>
    val form = insertTechForm.bindFromRequest().get
    techService.insert(form.techName, userId).map { Unit =>
      Redirect(AppLoader.routes.techController.all())
    }
  }

  override def all: Action[AnyContent] = Action.async { implicit request =>
    techService.all().map { techs =>
      val uiTechs = techs.map(ui.Tech(_))
      Ok(views.html.technologies(SupportedLang.defaultLang, None, uiTechs))
    }
  }

  override def voteUp(id: String) = vote(techService.voteUp(id, userId))
  override def voteDown(id: String) = vote(techService.voteDown(id, userId))

  private def vote(action: Future[Unit]) = Action.async { implicit request =>
    action.map { Unit =>
      Redirect(AppLoader.routes.techController.all())
    }
  }
}