import SbtReleaseHelpers._
import sbt.Keys.{publishTo => publishToKey, _}
import sbt._
import sbtrelease.ReleaseStateTransformations._
import sbtrelease.ReleaseStep

sbtPlugin := true

name := "sbt-release-utils"

organization := "io.github.morgaroth"

addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.8.5")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "0.2.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-pgp" % "0.8.3")

enablePlugins(SbtSonatypeUtils)

releaseSettings

sonatypeSettings

ReleaseKeys.releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies, // : ReleaseStep
  inquireVersions, // : ReleaseStep
  clearTarget,
  runTest, // : ReleaseStep
  setReleaseVersion, // : ReleaseStep
  publishArtifactsSigned,
  finishReleaseAtSonatype,
  commitReleaseVersion, // : ReleaseStep, performs the initial git checks
  tagRelease, // : ReleaseStep
  setNextVersion, // : ReleaseStep
  commitNextVersion, // : ReleaseStep
  pushChanges // : ReleaseStep, also checks that an upstream branch is properly configured
)

pomExtra := githubPom(name.value, "Mateusz Jaje", "Morgaroth")

publishToKey := publishRepoForVersion(version.value)

publishArtifact in Test := false

pomPostProcess := PackagingHelpers.removeTestOrSourceDependencies