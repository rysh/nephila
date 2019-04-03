lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "rysh",
      scalaVersion := "2.12.7"
    )),
    name := "nephila-test"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "3.141.59"
