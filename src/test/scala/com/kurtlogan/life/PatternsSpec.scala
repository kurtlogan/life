package com.kurtlogan.life

import org.scalacheck.Properties
import org.scalacheck.Prop.forAllNoShrink
import GameOfLife.nextGeneration
import GameMap._

class PatternsSpec extends Properties("Pattern") with PatternGen {

  implicit class GameMapOps[A](map: GameMap[A]) {
    def aliveShouldMatch(that: GameMap[A]): Boolean =
      map.filter(_._2 == Alive) == that.filter(_._2 == Alive)
  }

  property("statics - do not change") = forAllNoShrink(staticGen) { map =>
    nextGeneration(nextGeneration(nextGeneration(map))) aliveShouldMatch map
  }

  property("oscillators - repeat the same 2 patterns") = forAllNoShrink(oscillatingGen) {
    case head :: next :: Nil =>
      (nextGeneration(head) aliveShouldMatch next) &&
        (nextGeneration(next) aliveShouldMatch head)
    case _ => false
  }

  property("travellers - next gen should match the next pattern") = forAllNoShrink(travellingGen) { list =>
    def loop(xs: List[GameMap[Cell]]): Boolean = xs match {
      case head :: next :: tail =>
        (nextGeneration(head) aliveShouldMatch next) && loop(next :: tail)

      case _ :: Nil | Nil => true
    }

    loop(list)
  }
}
