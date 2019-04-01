name := "finch-simple-app"

version := "0.1"

scalaVersion := "2.12.8"

lazy val finchVersion = "0.27.0"
lazy val circeVersion = "0.9.0"
lazy val twitterServerVersion = "19.1.0"

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finchx-core" % finchVersion,
  "com.github.finagle" %% "finchx-circe" % finchVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "com.twitter" %% "twitter-server" % twitterServerVersion,
  "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test",
)

addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
)

enablePlugins(JavaServerAppPackaging)
