package services

import doobie._
import doobie.implicits._
import cats._
import cats.effect._
import cats.implicits._
import scala.concurrent.ExecutionContext


class DoobieService {


  // We need a ContextShift[IO] before we can construct a Transactor[IO]. The passed ExecutionContext
  // is where nonblocking operations will be executed.
  implicit val cs = IO.contextShift(ExecutionContext.global)

  // A transactor that gets connections from java.sql.DriverManager and excutes blocking operations
  // on an unbounded pool of daemon threads. See the chapter on connection handling for more info.
  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", // driver classname
    "jdbc:postgresql:world", // connect URL (driver-specific)
    "postgres",              // user
    "password"                       // password
  )

  val program1: doobie.ConnectionIO[Int] = 42.pure[ConnectionIO]

  val io: IO[Int] = program1.transact(xa)

  val program2 = sql"select 42".query[Int].unique

  val io2 = program2.transact(xa)

  val countries: IO[List[String]] = sql"select name from country".query[String].to[List].transact(xa)

}
