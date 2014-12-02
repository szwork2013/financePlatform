name := "financePlatform"

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
  "org.webjars" % "jquery" % "1.11.1",
  "org.webjars" % "bootstrap" % "3.1.1-2" exclude("org.webjars", "jquery"),
  "org.webjars" % "angularjs" % "1.2.18" exclude("org.webjars", "jquery"),
  "org.webjars" % "angular-ui-router" % "0.2.11-1",
  "org.webjars" % "nervgh-angular-file-upload" % "1.1.5" ,
  "org.webjars" % "angular-ui" % "0.4.0-3",
  "org.webjars" % "ng-grid" % "2.0.14",
  "org.webjars" % "angular-ui-bootstrap" % "0.11.2",
  "org.webjars" % "angular-ui-date" % "0.0.5"
)

sources in(Compile, doc) := Seq.empty

publishArtifact in(Compile, packageDoc) := false
