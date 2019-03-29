import com.twitter.app.Flag
import com.twitter.finagle.Http
import com.twitter.util.Await
import io.finch._
import io.finch.circe._
import io.finch.syntax._
import io.circe.generic.auto._
import models.{Locale, Message, Time}
import services.{HelloWorldService, TimeService}
import com.twitter.server.TwitterServer
import com.twitter.util.logging.Logger

object Main extends TwitterServer {

  private val port: Flag[Int] = flag("port", 8081, "TCP port for HTTP server")
  private val logging = Logger("finch-example-new")

  val helloWorldService = new HelloWorldService
  val timeService = new TimeService

  val helloWorld: Endpoint[Message] = get("hello") {
    helloWorldService.getMessage().map(Ok)
  }

  val time: Endpoint[Time] =
    post("time" :: jsonBody[Locale]) { l: Locale =>
      timeService.getCurrentTime(l).map(Ok)
    }

  val api = (helloWorld :+: time).handle {
    case e: Exception => BadRequest(e)
  }

  def main(): Unit = {
    logging.info(s"Serving the application on port ${port()}")

    Await.ready(Http.server.serve(":8081", api.toService))
  }
}
