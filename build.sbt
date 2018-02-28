name := "prime"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += "net.logstash.logback" % "logstash-logback-encoder" % "4.9"
libraryDependencies += "org.apache.commons" % "commons-math3" % "3.6.1"

lazy val root = project.in(file(".")).enablePlugins(PlayScala)