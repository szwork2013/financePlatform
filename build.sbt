name := "financePlatform"

version := "1.0"

scalaVersion := "2.10.0"

val springVersion = "4.1.1.RELEASE"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  cache,
  javaWs,
  "org.springframework" % "spring-context" % springVersion,
  "org.springframework" % "spring-test" % springVersion % "test"
)