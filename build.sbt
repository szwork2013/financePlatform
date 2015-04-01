name := "financePlatform"

version := "2.0"

resolvers ++= Seq(
  "Sunlights 3rd party" at "http://nexus.sunlights.me/nexus/content/repositories/thirdparty",
  "Sunlights snapshots" at "http://nexus.sunlights.me/nexus/content/repositories/snapshots/",
  "Sunlights releases" at "http://nexus.sunlights.me/nexus/content/repositories/releases/"
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
  "org.webjars" % "angular-ui-date" % "0.0.5",
  "org.javassist" % "javassist" % "3.18.2-GA",
  "org.dbunit" % "dbunit" % "2.4.9"
)

sources in(Compile, doc) := Seq.empty

publishArtifact in(Compile, packageDoc) := false

com.etsy.sbt.Checkstyle.checkstyleSettings

com.etsy.sbt.Checkstyle.CheckstyleTasks.checkstyleConfig := file("checkstyle/checkstyle.xml")


