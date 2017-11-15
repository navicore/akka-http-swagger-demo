package my.org.myswagger

import java.io.IOException

import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.{HttpOrigin, HttpOriginRange}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{Directive, ExceptionHandler, RejectionHandler}
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings
import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging

import scala.collection.JavaConverters._

trait ErrorSupport extends LazyLogging {

  val conf: Config = ConfigFactory.load()
  val corsOriginList: List[HttpOrigin] = conf
    .getStringList("main.corsOrigin")
    .asScala
    .iterator
    .toList
    .map(origin => HttpOrigin(origin))
  val urlpath: String = conf.getString("main.path")
  val port: Int = conf.getInt("main.port")

  val corsSettings: CorsSettings.Default = CorsSettings.defaultSettings.copy(
    allowedOrigins = HttpOriginRange(corsOriginList: _*))

  val rejectionHandler
    : RejectionHandler = corsRejectionHandler withFallback RejectionHandler.default

  val exceptionHandler: ExceptionHandler = ExceptionHandler {
    case e: NoSuchElementException =>
      complete(StatusCodes.NotFound -> e.getMessage)
    case e: IllegalArgumentException =>
      logger.warn(s"IllegalArgument $e")
      complete(StatusCodes.BadRequest -> e.getMessage)
    case e: IOException =>
      logger.warn(s"IOException $e")
      complete(StatusCodes.Forbidden -> e.getMessage)
  }

  val handleErrors
    : Directive[Unit] = handleRejections(rejectionHandler) & handleExceptions(
    exceptionHandler)

}
