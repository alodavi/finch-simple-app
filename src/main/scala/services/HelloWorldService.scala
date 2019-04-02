package services

import cats.effect.IO
import models.Message

class HelloWorldService {

  val message = Message("Hello, world!")

  def getMessage(): IO[Message] = {
    IO.pure(message)
  }
}
