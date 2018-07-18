package com.kurtlogan.life

import org.scalacheck.Gen
import GameBoard._

trait PatternGen {

  import patterns.StaticPatterns._
  import patterns.OscillatingPatterns._
  import patterns.TravellingPatterns._

  val staticGen: Gen[GameMap[Cell]]            = Gen.oneOf(block, boat, loaf, beehive)
  val oscillatingGen: Gen[List[GameMap[Cell]]] = Gen.oneOf(blinker, beacon, toad)
  val travellingGen: Gen[List[GameMap[Cell]]]  = Gen.oneOf(glider, lightSpaceship)
}
