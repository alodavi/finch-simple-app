import cats.effect.{ContextShift, IO}
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import io.finch._
import io.finch.circe._
import io.circe.generic.auto._
import models.{Locale, Message, Time}
import services.{HelloWorldService, TimeService}
import com.twitter.util.logging.Logger

class App(implicit S: ContextShift[IO]) extends Endpoint.Module[IO] {

  val helloWorldService = new HelloWorldService
  val timeService = new TimeService

  val helloWorld: Endpoint[IO, Message] = get("hello") {
    helloWorldService.getMessage().map(Ok)
  }

  val time: Endpoint[IO, Time] =
    post("time" :: jsonBody[Locale]) { l: Locale =>
      timeService.getCurrentTime(l).map(Ok)
    }

  val api = (helloWorld :+: time).handle {
    case e: Exception => BadRequest(e)
  }

  final def toService: Service[Request, Response] =
    Bootstrap
      .serve[Application.Json](api)
      .toService

}
