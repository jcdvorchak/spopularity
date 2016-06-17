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
