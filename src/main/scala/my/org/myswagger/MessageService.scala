package my.org.myswagger

import javax.ws.rs.Path

import io.swagger.annotations._
import my.org.myswagger.models.Message

@Path("/api") // @Path annotation required for Swagger
@Api(value = "/api")
trait MessageService {
  @Path("/message")
  @ApiOperation(
    value = "get order service time for order token",
    httpMethod = "GET",
    produces = "Application/json"
  )
  @ApiResponses(
    Array(
      new ApiResponse(code = 200, message = "My Messsage", response = classOf[Message]),
      new ApiResponse(code = 400, message = "malformed request"),
      new ApiResponse(code = 401, message = "not allowed"),
      new ApiResponse(code = 500, message = "internal error"),
    ))
  def getMessage(txt: String): Message = Message(s"""you said "$txt"""")

}
