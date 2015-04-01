name := "identitymap"

description := "An immutable Map based on identity"

scalaVersion := "2.11.6"

///////////////////////////////////////////////////////////////////////////////////////////////////

lazy val project = FDProject(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

///////////////////////////////////////////////////////////////////////////////////////////////////

scalacOptions += "-feature"

unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value)

unmanagedSourceDirectories in Test := Seq((scalaSource in Test).value)