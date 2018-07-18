package com.kurtlogan.life

object GameMap {

  case class Coord(x: Int, y: Int)

  type GameMap[A] = Map[Coord, A]

  private def neighbours[A](fa: GameMap[A])(z: => A)(coord: Coord): Map[Coord, A] = {
    def get(x: Int, y: Int): Map[Coord, A] = Map(Coord(x, y) -> fa.getOrElse(Coord(x, y), z))

    get(coord.x - 1, coord.y - 1) ++
      get(coord.x, coord.y - 1) ++
      get(coord.x + 1, coord.y - 1) ++
      get(coord.x - 1, coord.y) ++
      get(coord.x + 1, coord.y) ++
      get(coord.x - 1, coord.y + 1) ++
      get(coord.x, coord.y + 1) ++
      get(coord.x + 1, coord.y + 1)
  }

  private def growBorders[A](fa: GameMap[A])(z: => A): GameMap[A] =
    fa.filter(_._2 == Alive).flatMap(x => neighbours(fa)(z)(x._1)) ++ fa

  implicit val mapCompassFunctor: CompassFunctor[GameMap] = new CompassFunctor[GameMap] {

    override def cmap[A, B](fa: GameMap[A])(z: => A)(f: Compass[A] => B): GameMap[B] =
      growBorders(fa)(z).flatMap {
        case (coord, item) =>
          Map(coord -> f(Compass(item, neighbours(fa)(z)(coord).values.toList)))
      }
  }
}
