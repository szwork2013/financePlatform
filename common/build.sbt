name := "common"

version := "1.0"

val springVersion = "4.1.1.RELEASE"

libraryDependencies ++= Seq(
  "org.springframework" % "spring-context" % springVersion,
  "org.springframework" % "spring-tx" % springVersion,
  "commons-logging" % "commons-logging" % "1.1.3",
  "commons-beanutils" % "commons-beanutils" % "1.9.2",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.6.Final",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "rapid" % "xsqlbuider" % "1.0.4"
)