package services

import com.twitter.util.Future
import models.{Locale, Time}

class TimeService {

  def currentTime(l: java.util.Locale): String =
    java.util.Calendar.getInstance(l).getTime.toString

  def getCurrentTime(locale: Locale): Future[Time] = {
    Future.value(
      Time(locale,
           currentTime(new java.util.Locale(locale.language, locale.country))))
  }

}
