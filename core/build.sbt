name := "core"

version := "1.0"

scalaVersion := "2.10.0"

val springVersion = "4.1.1.RELEASE"

libraryDependencies ++= Seq(
  javaCore,
  javaJpa,
  "org.springframework" % "spring-context" % springVersion,
  "javax.inject" % "javax.inject" % "1",
  "org.springframework.data" % "spring-data-jpa" % "1.3.2.RELEASE",
  "org.springframework" % "spring-expression" % springVersion,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.6.Final",
  "org.mockito" % "mockito-core" % "1.9.5" % "test"
)
