package models

import io.circe.Encoder
import io.circe.generic.JsonCodec

case class Locale(language: String, country: String)
