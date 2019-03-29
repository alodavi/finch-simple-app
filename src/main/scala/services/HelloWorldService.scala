package services

import com.twitter.util.Future
import models.Message

class HelloWorldService {

  val message = Message("Hello, world!")

  def getMessage(): Future[Message] = {
    Future.value(message)
  }
}
