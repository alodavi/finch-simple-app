import io.circe.Json
import io.finch._
import models.{Locale, Message, Time}
import org.scalatest.{FlatSpec, Matchers}
import io.finch.circe._
import io.circe.generic.auto._
import io.circe.syntax._
import services.TimeService

class TimeSpec extends FlatSpec with Matchers {

  import Main._

  val timeServiceInstance = new TimeService

  behavior of "the hello endpoint"

  it should "get 'Hello, world!'" in {
    helloWorld(Input.get("/hello")).awaitValueUnsafe() shouldBe Some(
      Message("Hello, world!"))
  }

  behavior of "the time endpoint"

  it should "return time according to locale" in {

    val locale: Json = Locale("de", "DE").asJson
    val input = Input
      .post("/time")
      .withBody[Application.Json](locale)
    val res = time(input)
    res.awaitValueUnsafe() shouldBe Some(
      Time(Locale("de", "DE"),
           timeServiceInstance.currentTime(new java.util.Locale("de", "DE"))))
  }

}
