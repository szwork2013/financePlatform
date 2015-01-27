name := "thirdpart"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.0"

resolvers ++= Seq(
  "Sunlights 3rd party" at "http://192.168.0.97:8081/nexus/content/repositories/thirdparty",
  "Sunlights snapshots" at "http://192.168.0.97:8081/nexus/content/repositories/snapshots/",
  "Sunlights releases" at "http://192.168.0.97:8081/nexus/content/repositories/releases/"
)

libraryDependencies ++= Seq(
  javaCore,
  javaJdbc,
  cache,
  javaWs,
  "commons-httpclient" % "commons-httpclient" % "3.1",
  "com.google.code.gson" % "gson" % "2.3.1",
  "com.sunlights" % "jpush-client" % "3.2.3",
  "c3p0" % "c3p0" % "0.9.0.4",
  "org.hibernate" % "hibernate-c3p0" % "4.3.6.Final"
)


sources in(Compile, doc) := Seq.empty

publishArtifact in(Compile, packageDoc) := false

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishTo <<= version { v: String =>
  val nexus = "http://192.168.0.97:8081/nexus/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "content/repositories/releases")
}