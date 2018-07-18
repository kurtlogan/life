package com.kurtlogan.life

import scala.language.higherKinds

object GameOfLife {

  import CompassFunctor.ops._

  def nextGeneration[F[_]: CompassFunctor](cells: F[Cell]): F[Cell] =
    cells.cmap(Dead) {
      case c @ TwoNeighbours() => c.point
      case ThreeNeighbours()   => Alive
      case _                   => Dead
    }

  private object TwoNeighbours {
    def unapply(c: Compass[Cell]): Boolean = c.neighbours.count(_ == Alive) == 2
  }

  private object ThreeNeighbours {
    def unapply(c: Compass[Cell]): Boolean = c.neighbours.count(_ == Alive) == 3
  }
}
