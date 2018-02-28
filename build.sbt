name := "prime"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(guice,
  "net.logstash.logback" % "logstash-logback-encoder" % "4.9",
  "org.apache.commons" % "commons-math3" % "3.6.1",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % "test")

lazy val root = project.in(file(".")).enablePlugins(PlayScala)