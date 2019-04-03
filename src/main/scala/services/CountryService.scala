package services

import doobie._
import doobie.implicits._
import cats._
import cats.effect._
import cats.implicits._
import models.Country

import scala.concurrent.ExecutionContext

class CountryService {

  // We need a ContextShift[IO] before we can construct a Transactor[IO]. The passed ExecutionContext
  // is where nonblocking operations will be executed.
  implicit val cs = IO.contextShift(ExecutionContext.global)

  // A transactor that gets connections from java.sql.DriverManager and excutes blocking operations
  // on an unbounded pool of daemon threads. See the chapter on connection handling for more info.
  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", // driver classname
    "jdbc:postgresql:world", // connect URL (driver-specific)
    "postgres", // user
    "password" // password
  )

  val countries: IO[List[Country]] =
    sql"select code, name, population, gnp from country"
      .query[Country]
      .to[List]
      .transact(xa)

}
