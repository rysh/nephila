import Dependencies._
import sbtcrossproject.CrossType
import sbtcrossproject.CrossPlugin.autoImport.crossProject

ThisBuild / scalaVersion     := "2.12.6"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "rysh"
ThisBuild / organizationName := "rysh"

lazy val nephila = (project in file("application/server")).settings(commonSettings).settings(
  scalaJSProjects := Seq(client),
  pipelineStages in Assets := Seq(scalaJSPipeline),
  pipelineStages := Seq(digest, gzip),
  // triggers scalaJSPipeline when using compile or continuous compilation
  compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
  libraryDependencies ++= Seq(
    "com.vmunier" %% "scalajs-scripts" % "1.1.2",
    guice,
    specs2 % Test,
    scalaTest % Test
  ),
  // Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
  EclipseKeys.preTasks := Seq(compile in Compile),
  organization := "rysh",
  dockerUsername := Some("rysh"),
  version := "latest",
  dockerBaseImage := "amazoncorretto:8u202",
  javaOptions in Universal ++= Seq(
    "-Dpidfile.path=/dev/null"
  ),
  coverageExcludedPackages := """controllers\..*Reverse.*;router.Routes.*;.*Reverse.*"""
).enablePlugins(PlayScala, JavaAppPackaging, DockerPlugin)
  .dependsOn(sharedJvm)

lazy val client = (project in file("application/client")).settings(commonSettings).settings(
  scalaJSUseMainModuleInitializer := true,
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.5"
  )
).enablePlugins(ScalaJSPlugin, ScalaJSWeb)
  .dependsOn(sharedJs)

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("application/shared"))
  .settings(commonSettings)
lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

lazy val commonSettings = Seq(
  scalaVersion := "2.12.6",
  organization := "rysh"
)

// loads the server project at sbt startup
onLoad in Global := (onLoad in Global).value andThen {s: State => "project nephila" :: s}
