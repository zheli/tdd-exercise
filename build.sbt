lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "tech.minna",
      scalaVersion := "2.13.1"
    )),
    name := "tdd-exercise"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
