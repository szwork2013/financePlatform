import play.PlayJava
import sbt._
import Keys._

object FinancePlatformBuild extends Build {
  lazy val root = Project(id = "financePlatform", base = file("."))
    .dependsOn(common)
    .dependsOn(trade)
    .dependsOn(core)
    .aggregate(common, trade, core)
    .settings(scalaVersion := "2.10.0")
    .enablePlugins(PlayJava)

  lazy val common = Project(id = "common",base = file("common"))
    .settings(scalaVersion := "2.10.0")
    .enablePlugins(PlayJava)

  lazy val trade = Project(id="trade", base= file("trade"))
    .settings(scalaVersion := "2.10.0")
    .dependsOn(common).enablePlugins(PlayJava)

  lazy val core = Project(id="core", base= file("core"))
    .settings(scalaVersion := "2.10.0")
    .dependsOn(common).enablePlugins(PlayJava)
}
