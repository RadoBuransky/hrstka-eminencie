package sk.hrstka.controllers

import com.google.inject.ImplementedBy
import play.api.mvc._
import sk.hrstka.controllers.impl.CompControllerImpl

/**
 * Company controller.
 */
@ImplementedBy(classOf[CompControllerImpl])
trait CompController {
  /**
   * Gets HTML view of a company for the provided business number.
   *
   * @param businessNumber Company business number.
   * @return HTML view.
   */
  def get(businessNumber: String): Action[AnyContent]

  /**
   * Gets HTML view for companies with the most women.
   *
   * @return HTML view.
   */
  def women: EssentialAction

  /**
   * Gets HTML view containing all companies.
   *
   * @return HTML view.
   */
  def search: EssentialAction

  /**
   * Gets HTML view containing companies for the provided city and tech handles.
   *
   * @param cityHandle City handle.
   * @param techHandle Tech handle.
   * @return HTML view.
   */
  def cityTech(cityHandle: String, techHandle: String): EssentialAction
}

