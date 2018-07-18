package com.kurtlogan.life.patterns

import com.kurtlogan.life.Cell
import com.kurtlogan.life.GameMap.GameMap

object OscillatingPatterns {
  val blinker: List[GameMap[Cell]] =
    PatternBuilder.stack(
      """
        |***
        |""".stripMargin,
      """.*.
        |.*.
        |.*.
      """.stripMargin
    )

  val beacon: List[GameMap[Cell]] =
    PatternBuilder.stack(
      """**..
        |**..
        |..**
        |..**
      """.stripMargin,
      """**..
        |*...
        |...*
        |..**
      """.stripMargin
    )

  val toad: List[GameMap[Cell]] =
    PatternBuilder.stack(
      """
        |.***
        |***.
      """.stripMargin,
      """..*.
        |*..*
        |*..*
        |.*..
      """.stripMargin
    )
}
