import cats.effect.IO
import io.circe.Json
import io.circe.generic.auto._
import io.circe.syntax._
import io.finch._
import io.finch.circe._
import models.{Locale, Time}
import org.scalatest.{FlatSpec, Matchers}

class TimeSpec extends FlatSpec with Matchers with TestHelper {

  behavior of "the time endpoint"

  it should "return time according to locale" in {

    val locale: Json = Locale("de", "DE").asJson
    val input = Input
      .post("/time")
      .withBody[Application.Json](locale)
    val res = app.time(input)
    res.awaitValueUnsafe() shouldBe Some(
      Time(Locale("de", "DE"),
           app.timeService.currentTime(new java.util.Locale("de", "DE"))))
  }

}
