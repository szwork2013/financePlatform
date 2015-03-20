name := "thirdpart"

version := "1.1-SNAPSHOT"

scalaVersion := "2.10.0"


resolvers ++= Seq(
  "Sunlights 3rd party" at "http://nexus.sunlights.me/nexus/content/repositories/thirdparty",
  "Sunlights snapshots" at "http://nexus.sunlights.me/nexus/content/repositories/snapshots/",
  "Sunlights releases" at "http://nexus.sunlights.me/nexus/content/repositories/releases/"
)


libraryDependencies ++= Seq(
  javaCore,
  javaJdbc,
  cache,
  javaWs,
  "commons-httpclient" % "commons-httpclient" % "3.1",
  "com.sunlights" % "jpush-client" % "3.2.3",
  "com.google.code.gson" % "gson" % "2.3.1"
)


sources in(Compile, doc) := Seq.empty

publishArtifact in(Compile, packageDoc) := false

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishTo <<= version { v: String =>
  val nexus = "http://nexus.sunlights.me/nexus/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "content/repositories/releases")
}