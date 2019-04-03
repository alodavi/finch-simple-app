import doobie.util.transactor.Transactor
import doobie.scalatest._
import models.Country
import org.scalatest._
import doobie._
import doobie.implicits._
import cats._
import cats.data._
import cats.effect.IO

import scala.concurrent.ExecutionContext

class AnalysisTestScalaCheck extends FunSuite with Matchers with IOChecker {

  implicit val cs = IO.contextShift(ExecutionContext.global)

  val transactor = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql:worldtest",
    "postgres",
    "password"
  )

  val allCountries =
    sql"select code, name, population, gnp from country".query[Country]

  test("allCountries") { check(allCountries) }
  //   test("biggerThan") { check(biggerThan(0))  }
  //   test("update")     { check(update("", "")) }

}
