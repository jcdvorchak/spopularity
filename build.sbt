enablePlugins(ScalaJSPlugin)

name := "spotify-hipster-analysis"

version := "1.0"

scalaVersion := "2.11.8"

lazy val copyjs = TaskKey[Unit]("copyjs", "Copy javascript files to target directory")
copyjs := {
  val outDir = baseDirectory.value / "client/js/"
  val inDir = baseDirectory.value / "target/scala-2.11"
  val files = Seq("spotify-hipster-analysis-fastopt.js", "spotify-hipster-analysis-fastopt.js.map", "spotify-hipster-analysis-jsdeps.js") map { p =>   (inDir / p, outDir / p) }
  IO.copy(files, true)
}

addCommandAlias("buildJS", ";fastOptJS;copyjs")

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.4" % "test",
  "com.google.code.gson" % "gson" % "2.3.1",
//  "org.springframework.boot" % "spring-boot-starter" % "1.3.5.RELEASE",
  "org.springframework" % "spring-web" % "4.3.1.RELEASE"
)
