package sk.hrstka.services

import com.google.inject.ImplementedBy
import sk.hrstka.models.domain.{City, Country, Handle, Iso3166}
import sk.hrstka.services.impl.LocationServiceImpl
import sk.hrstka.services.impl.cache.CachedLocationServiceImpl

import scala.concurrent.Future

/**
 * Geographical location service. Currently cities only.
 */
@ImplementedBy(classOf[CachedLocationServiceImpl])
trait LocationService {
  /**
   * Inserts or updates a city.
   *
   * @param city City to insert or update.
   * @return City handle.
   */
  def upsert(city: City): Future[Handle]

  /**
   * Removes a city only if it is not used. Fails otherwise.
   *
   * @param handle City handle.
   * @return Handle of the removed city.
   */
  def remove(handle: Handle): Future[Handle]

  /**
   * Gets all countries ordered by number of companies.
   *
   * @return All countries ordered.
   */
  def countries(): Future[Seq[Country]]

  /**
   * Gets country by the ISO 3166 code. Fails if no such country exists.
   *
   * @param code Country code.
   * @return Country.
   */
  def getCountryByCode(code: Iso3166): Future[Country]

  /**
   * Gets only used cities ordered by number of companies.
   * 
   * @return All used cities ordered.
   */
  def usedCities(): Future[Seq[City]]

  /**
   * Gets all cities unordered.
   *
   * @return All cities unordered.
   */
  def allCities(): Future[Traversable[City]]

  /**
   * Gets city for the handle if exists, fails otherwise.
   * 
   * @param handle Handle to get the city for.
   * @return The city.
   */
  def city(handle: Handle): Future[City]
}
