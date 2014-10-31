name := "common"

version := "1.0"

val springVersion = "4.1.1.RELEASE"

libraryDependencies ++= Seq(
  javaCore,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  cache,
  javaWs,
  "com.h2database" % "h2" % "1.4.181",
  "org.springframework" % "spring-context" % springVersion,
  "org.springframework" % "spring-orm" % springVersion,
  "org.springframework" % "spring-jdbc" % springVersion,
  "org.springframework" % "spring-tx" % springVersion,
  "org.springframework" % "spring-expression" % springVersion,
  "org.springframework" % "spring-aop" % springVersion,
  "org.springframework" % "spring-test" % springVersion % "test",
  "org.springframework.data" % "spring-data-jpa" % "1.3.2.RELEASE",
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
  "org.apache.httpcomponents" % "httpcore" % "4.3.2",
  "jdom" % "jdom" % "1.1",
  "javax.mail" % "mail" % "1.4",
  "org.apache.neethi" % "neethi" % "3.0.3",
  "wsdl4j" % "wsdl4j" % "1.6.2",
  "org.apache.ws.commons.schema" % "XmlSchema" % "1.4.2",
  "commons-io" % "commons-io" % "2.2",
  "rapid" % "xsqlbuider" % "1.0.4",
  "cn.com.nciic" % "sfxxrz" %"1.0",
  "org.codehaus.xfire" % "xfire-all" % "1.2.6"
)
