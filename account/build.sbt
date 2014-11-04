name := "account"

version := "1.0"

libraryDependencies ++= Seq(
  javaCore,
  javaJdbc,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  cache,
  javaWs
)

sources in (Compile,doc) := Seq.empty

publishArtifact in (Compile, packageDoc) := false