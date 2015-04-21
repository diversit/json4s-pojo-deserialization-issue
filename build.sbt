name := "json4s pojo deserialize issue"

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
	"org.json4s"         %% "json4s-jackson" % "3.2.11",
	"org.apache.commons" %  "commons-lang3"  % "3.3.2",
	"org.scalatest"      %% "scalatest"      % "2.2.1" % "test",
  "junit"              %  "junit"          % "4.12"  % "test"
)

