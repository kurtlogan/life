package com.kurtlogan.life.patterns

import com.kurtlogan.life.Cell
import com.kurtlogan.life.GameBoard.GameMap

object StaticPatterns {

  val block: GameMap[Cell] = PatternBuilder(
    """**
      |**
    """.stripMargin
  )

  val boat: GameMap[Cell] = PatternBuilder(
    """**.
      |*.*
      |.*.
    """.stripMargin
  )

  val loaf: GameMap[Cell] = PatternBuilder(
    """.**.
      |*..*
      |.*.*
      |..*.
    """.stripMargin
  )

  val beehive: GameMap[Cell] = PatternBuilder(
    """.**.
      |*..*
      |.**.
    """.stripMargin
  )
}
