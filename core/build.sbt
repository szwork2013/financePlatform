name := "core"

version := "1.0"

scalaVersion := "2.10.0"

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
  "cn.com.nciic" % "sfxxrz" % "1.0"
)

sources in(Compile, doc) := Seq.empty

publishArtifact in(Compile, packageDoc) := false