import play.PlayJava
import sbt._
import Keys._

object FinancePlatformBuild extends Build {

  lazy val common = Project(id = "common",base = file("common"))
    .settings(scalaVersion := "2.10.0")
    .enablePlugins(PlayJava)

  lazy val trade = Project(id="trade", base= file("trade"))
    .settings(scalaVersion := "2.10.0")
    .dependsOn(account)
    .dependsOn(customer)
    .dependsOn(common).enablePlugins(PlayJava)

  lazy val core = Project(id="core", base= file("core"))
    .settings(scalaVersion := "2.10.0")
    .dependsOn(customer)
    .dependsOn(common).enablePlugins(PlayJava)

  lazy val customer = Project(id="customer", base= file("customer"))
    .settings(scalaVersion := "2.10.0")
    .dependsOn(common).enablePlugins(PlayJava)

  lazy val account = Project(id="account", base= file("account"))
    .settings(scalaVersion := "2.10.0")
    .dependsOn(customer)
    .dependsOn(common).enablePlugins(PlayJava)

  lazy val financePlatform = Project(id = "financePlatform", base = file("."))
    .dependsOn(common)
    .dependsOn(trade)
    .dependsOn(core)
    .dependsOn(customer)
    .dependsOn(account)
    .aggregate(common, trade, core, customer, account)
    .settings(scalaVersion := "2.10.0")
    .enablePlugins(PlayJava)
}
