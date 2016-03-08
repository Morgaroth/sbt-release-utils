package io.github.morgaroth.sbt

import com.typesafe.sbt.pgp.PgpKeys._
import sbt._
import Keys._
import sbtrelease.ReleaseStep
import sbtrelease.Utilities._
import xerial.sbt.Sonatype.SonatypeKeys._


trait SbtReleaseHelpers {

  def oneTaskReleaseStep(task: TaskKey[_]) = ReleaseStep(action = (st: State) => {
    val extracted = st.extract
    //    println("extracted project "+extracted)
    val ref = extracted.get(thisProjectRef)
    println("ref project " + ref)
    val key: TaskKey[_] = task in ThisScope in ref
    println("key " + key)
    extracted.runAggregated(key, st)
  })

  val publishArtifactsLocally = oneTaskReleaseStep(publishLocal)
  val publishArtifactsSigned = oneTaskReleaseStep(publishSigned)
  val finishReleaseAtSonatype = oneTaskReleaseStep(sonatypeReleaseAll)
  val clearTarget = oneTaskReleaseStep(clean)
}

object SbtReleaseUtils extends AutoPlugin {

  object autoImport extends SbtReleaseHelpers

}

