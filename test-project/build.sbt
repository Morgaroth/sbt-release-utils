import sbt.Keys._
import sbtrelease.ReleaseStep

name := "test-sbt-release-utils-proj"

version := "0.1.0"

enablePlugins(SbtReleaseUtils)

releaseSettings

ReleaseKeys.releaseProcess := Seq[ReleaseStep](
  oneTaskReleaseStep(publishLocal),
  clearTarget,
  oneTaskReleaseStep(test)
)