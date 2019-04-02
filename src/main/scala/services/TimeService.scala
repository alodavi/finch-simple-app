package services

import cats.effect.IO
import models.{Locale, Time}

class TimeService {

  def currentTime(l: java.util.Locale): String =
    java.util.Calendar.getInstance(l).getTime.toString

  def getCurrentTime(locale: Locale): IO[Time] = {
    IO.pure(
      Time(locale,
           currentTime(new java.util.Locale(locale.language, locale.country))))
  }

}
