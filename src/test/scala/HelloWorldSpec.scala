import cats.effect.IO
import io.finch.Input
import models.Message
import org.scalatest.{FlatSpec, Matchers}

class HelloWorldSpec extends FlatSpec with Matchers with TestHelper {

  behavior of "the hello endpoint"

  it should "get 'Hello, world!'" in {
    app.helloWorld(Input.get("/hello")).awaitValueUnsafe() shouldBe Some(
      Message("Hello, world!"))
  }

}
