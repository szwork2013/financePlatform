name := "operationPlatform"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.0"

resolvers ++= Seq(
  "Sunlights 3rd party" at "http://nexus.sunlights.me/nexus/content/repositories/thirdparty",
  "Sunlights snapshots" at "http://nexus.sunlights.me/nexus/content/repositories/snapshots/",
  "Sunlights releases" at "http://nexus.sunlights.me/nexus/content/repositories/releases/"
)

libraryDependencies ++= Seq(
  javaCore,
  javaJdbc,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  cache,
  javaWs,
  filters,
  "common" % "common_2.10" % "1.4-SNAPSHOT",
  "thirdpart" % "thirdpart_2.10" % "1.1-SNAPSHOT",
  "ch.ethz.ganymed" % "ganymed-ssh2" % "build210",
  "shumicrawler" % "shumicrawler_2.10" % "1.0-SNAPSHOT" exclude("common", "common_2.10"),
  "net.sourceforge.jexcelapi" % "jxl" % "2.6.10",
  "org.apache.poi" % "poi" % "3.10.1",
  "org.apache.poi" % "poi-ooxml" % "3.10.1",
  "com.typesafe.play.plugins" %% "play-plugins-mailer" % "2.3.6"
)

sources in (Compile,doc) := Seq.empty

publishArtifact in (Compile, packageDoc) := false




