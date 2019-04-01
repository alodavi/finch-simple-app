import cats.effect.IO

import scala.concurrent.ExecutionContext

trait TestHelper {

  implicit val ctx = IO.contextShift(ExecutionContext.global)
  val app = new App()

}
