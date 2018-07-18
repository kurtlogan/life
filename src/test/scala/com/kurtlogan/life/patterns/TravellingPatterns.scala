package com.kurtlogan.life.patterns

import com.kurtlogan.life.Cell
import com.kurtlogan.life.GameMap.GameMap

object TravellingPatterns {
   val glider: List[GameMap[Cell]] =
     PatternBuilder.stack(
       """*..
         |.**
         |**.
       """.stripMargin,
       """.*.
         |..*
         |***
       """.stripMargin,
       """...
         |*.*
         |.**
         |.*.
       """.stripMargin,
       """...
         |..*
         |*.*
         |.**
       """.stripMargin,
       """....
         |.*..
         |..**
         |.**.
       """.stripMargin
     )

  val lightSpaceship: List[GameMap[Cell]] =
    PatternBuilder.stack(
      """*..*.
        |....*
        |*...*
        |.****
      """.stripMargin,
      """......
        |...**.
        |.**.**
        |.****.
        |..**..
      """.stripMargin,
      """......
        |..****
        |.*...*
        |.....*
        |.*..*.
      """.stripMargin,
      """...**..
        |..****.
        |..**.**
        |....**.
        |.......
      """.stripMargin,
      """..*..*.
        |......*
        |..*...*
        |...****
      """.stripMargin
    )
}