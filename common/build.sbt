name := "common"

version := "1.0"

resolvers ++= Seq(
  "Sunlights 3rd party" at "http://192.168.1.97:8081/nexus/content/repositories/thirdparty",
  "Sunlights snapshots" at "http://192.168.1.97:8081/nexus/content/repositories/snapshots/",
  "Sunlights releases" at "http://192.168.1.97:8081/nexus/content/repositories/releases/"
)

libraryDependencies ++= Seq(
  javaCore,
  javaJdbc,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  cache,
  javaWs,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.6.Final",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "commons-beanutils" % "commons-beanutils" % "1.9.2",
  "commons-net" % "commons-net" % "1.4.1",
  "org.apache.ws.commons.axiom" % "axiom-api" % "1.2.13",
  "org.apache.ws.commons.axiom" % "axiom-impl" % "1.2.13",
  "org.apache.axis2" % "axis2-adb" % "1.6.1",
  "org.apache.axis2" % "axis2-kernel" % "1.6.2",
  "org.apache.axis2" % "axis2-transport-http" % "1.6.2",
  "org.apache.axis2" % "axis2-transport-local" % "1.6.2",
  "commons-codec" % "commons-codec" % "1.9",
  "commons-httpclient" % "commons-httpclient" % "3.1",
  "commons-logging" % "commons-logging" % "1.1.3",
  "commons-beanutils" % "commons-beanutils" % "1.9.2",
  "org.quartz-scheduler" % "quartz" % "2.1.7",
  "commons-io" % "commons-io" % "2.4",
  "commons-net" % "commons-net" % "1.4.1",
  "com.google.guava" % "guava" % "18.0",
  "rapid" % "xsqlbuider" % "1.0.4"
)

sources in(Compile, doc) := Seq.empty

publishArtifact in(Compile, packageDoc) := false

