package com.kurtlogan.life

import scala.language.higherKinds

object GameOfLife {

  import CompassFunctor.ops._

  def nextGeneration[F[_]: CompassFunctor](cells: F[Cell]): F[Cell] =
    cells.cmap(Dead) { c =>
      c.neighbours match {
        case TwoNeighbours()   => c.point
        case ThreeNeighbours() => Alive
        case _                 => Dead
      }
    }

  private object TwoNeighbours {
    def unapply(cells: Seq[Cell]): Boolean = cells.count(_ == Alive) == 2
  }

  private object ThreeNeighbours {
    def unapply(cells: Seq[Cell]): Boolean = cells.count(_ == Alive) == 3
  }
}
