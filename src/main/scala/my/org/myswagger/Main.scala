package my.org.myswagger

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import my.org.myswagger.models.JsonSupport
import my.org.myswagger.routes.ApiRoute

import scala.concurrent.ExecutionContextExecutor

object Main extends LazyLogging with JsonSupport with ErrorSupport {

  def main(args: Array[String]) {

    implicit val system: ActorSystem = ActorSystem("MySwagger-system")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val route =
      ApiRoute.apply ~
        DocService.routes

    Http().bindAndHandle(route, "0.0.0.0", port)
  }
}
