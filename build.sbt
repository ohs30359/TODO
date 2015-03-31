import com.github.play2war.plugin._

name := "PlayApp"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "com.google.code.gson" % "gson" % "2.2.4"
)     

play.Project.playJavaSettings



Play2WarPlugin.play2WarSettings
 
Play2WarKeys.servletVersion := "3.0"