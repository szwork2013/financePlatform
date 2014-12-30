name := "customer"

version := "1.0"

resolvers ++= Seq(
  "Sunlights 3rd party" at "http://192.168.0.97:8081/nexus/content/repositories/thirdparty",
  "Sunlights snapshots" at "http://192.168.0.97:8081/nexus/content/repositories/snapshots/",
  "Sunlights releases" at "http://192.168.0.97:8081/nexus/content/repositories/releases/"
)

libraryDependencies ++= Seq(
  javaCore,
  javaJdbc,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  cache,
  javaWs,
  "org.apache.ws.commons.axiom" % "axiom-api" % "1.2.13",
  "org.apache.ws.commons.axiom" % "axiom-impl" % "1.2.13",
  "org.apache.axis2" % "axis2-adb" % "1.6.1",
  "org.apache.axis2" % "axis2-kernel" % "1.6.2",
  "org.apache.axis2" % "axis2-transport-http" % "1.6.2",
  "org.apache.axis2" % "axis2-transport-local" % "1.6.2",
  "commons-httpclient" % "commons-httpclient" % "3.1"
)

sources in(Compile, doc) := Seq.empty

publishArtifact in(Compile, packageDoc) := false