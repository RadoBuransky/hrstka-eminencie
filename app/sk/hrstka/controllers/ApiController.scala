package sk.hrstka.controllers

import com.google.inject._
import play.api.mvc._
import sk.hrstka.controllers.impl.ApiControllerImpl
import sk.hrstka.controllers.impl.cache.CachedApiControllerImpl

/**
 * API controller.
 */
@ImplementedBy(classOf[CachedApiControllerImpl])
trait ApiController {
  /**
   * Gets JSON array with all companies.
   *
   * @return JSON array.
   */
  def comps(): EssentialAction

  /**
   * Gets JSON representation of company for the business number.
   *
   * @param businessNumber Company business number.
   * @return JSON object.
   */
  def comp(businessNumber: String): Action[AnyContent]

  /**
   * Gets JSON array with all technologies.
   *
   * @return JSON array.
   */
  def techs(): EssentialAction

  /**
   * Gets JSON representation of the technology.
   *
   * @param handle Technology handle.
   * @return JSON object.
   */
  def tech(handle: String): Action[AnyContent]

  /**
   * Gets JSON array with all cities.
   *
   * @return JSON array.
   */
  def cities(): EssentialAction
}