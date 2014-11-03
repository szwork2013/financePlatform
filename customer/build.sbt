name := "customer"

version := "1.0"

val springVersion = "4.1.1.RELEASE"

libraryDependencies ++= Seq(
  javaCore,
  javaJdbc,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  cache,
  javaWs,
  "cn.com.nciic" % "sfxxrz" %"1.0"
)