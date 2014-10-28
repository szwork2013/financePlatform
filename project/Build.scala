import play.PlayJava
import sbt._
import Keys._

object FinancePlatformBuild extends Build {
  lazy val root = Project(id = "financePlatform", base = file(".")).dependsOn(common).aggregate(common).enablePlugins(PlayJava)

  lazy val common = Project(id = "common",base = file("common")).settings(scalaVersion := "2.10.0")
}
