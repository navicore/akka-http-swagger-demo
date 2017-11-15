package my.org.myswagger

import com.github.swagger.akka.SwaggerHttpService
import com.github.swagger.akka.model.{Contact, Info}
import io.swagger.models.ExternalDocs

object DocService extends SwaggerHttpService with ErrorSupport {

  override val apiClasses: Set[Class[_]] = Set(classOf[MessageService])
  override val host : String = "http://localhost:8080" //the url of your api, not swagger's json endpoint
  override val basePath = s"/api" //the basePath for the API you are exposing
  override val apiDocsPath = "api-docs" //where you want the swagger.json and swagger.yaml served from
  override val info = Info(
    description= "Get waittime information for mobile order processing based on ML",
    version="0.1.0",
    title="BITS Waittime API",
    contact=Some(Contact("BITS", "https://bits.starbucks.com/waittime", "bits@starbucks.com"))
  )
  override val externalDocs = Some(
    new ExternalDocs("wait-time-api github",
                     "https://github.com/sbbits/wait-time-api"))
}
