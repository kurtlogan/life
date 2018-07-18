import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.kurtlogan",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "life",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0" % Test,
    libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.25"
  )
