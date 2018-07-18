package com.kurtlogan.life.patterns

import com.kurtlogan.life._
import com.kurtlogan.life.GameMap.{ Coord, GameMap }

object PatternBuilder {
  def apply(s: String): GameMap[Cell] = {
    def loop(s: String, c: Coord, m: Map[Coord, Cell]): Map[Coord, Cell] = s.headOption match {
      case None       => m
      case Some('.')  => loop(s.tail, Coord(c.x + 1, c.y), m ++ Map(c -> Dead))
      case Some('*')  => loop(s.tail, Coord(c.x + 1, c.y), m ++ Map(c -> Alive))
      case Some('\n') => loop(s.tail, Coord(0, c.y + 1), m)
      case Some(' ')  => loop(s.tail, Coord(c.x + 1, c.y), m)
      case _          => loop(s.tail, c, m)
    }

    loop(s, Coord(0, 0), Map())
  }

  def stack(s: String*): List[GameMap[Cell]] =
    List(s.map(PatternBuilder(_)): _*)
}
