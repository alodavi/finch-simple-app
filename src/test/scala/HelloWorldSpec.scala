import Main.helloWorld
import io.finch.Input
import models.Message
import org.scalatest.{FlatSpec, Matchers}

class HelloWorldSpec extends FlatSpec with Matchers {

  behavior of "the hello endpoint"

  it should "get 'Hello, world!'" in {
    helloWorld(Input.get("/hello")).awaitValueUnsafe() shouldBe Some(
      Message("Hello, world!"))
  }

}
