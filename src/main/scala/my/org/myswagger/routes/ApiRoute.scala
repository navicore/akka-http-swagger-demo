package my.org.myswagger.routes

import spray.json._
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.{Directives, Route}
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import com.typesafe.scalalogging.LazyLogging
import my.org.myswagger.{ErrorSupport, MessageService}
import my.org.myswagger.models.JsonSupport

object ApiRoute
    extends JsonSupport
    with LazyLogging
    with Directives
    with MessageService
    with ErrorSupport {

  def apply: Route =
    path(urlpath) {
      logRequest(urlpath) {
        handleErrors {
          cors(corsSettings) {
            get {
              logger.debug(s"get $urlpath")
              val message = getMessage("hi")
              complete(
                HttpEntity(ContentTypes.`application/json`,
                  message.toJson.prettyPrint))
            }
          }
        }
      }
    }
}
