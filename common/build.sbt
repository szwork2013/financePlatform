name := "common"

version := "1.0"

libraryDependencies ++= Seq(
  javaCore,
  javaJdbc,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  cache,
  javaWs,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.6.Final",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "commons-codec" % "commons-codec" % "1.9",
  "commons-httpclient" % "commons-httpclient" % "3.1",
  "commons-logging" % "commons-logging" % "1.1.3",
  "commons-beanutils" % "commons-beanutils" % "1.9.2",
  "org.quartz-scheduler" % "quartz" % "2.1.7",
  "commons-io" % "commons-io" % "2.4",
  "commons-net" % "commons-net" % "1.4.1",
  "rapid" % "xsqlbuider" % "1.0.4"
)
