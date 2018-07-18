package com.kurtlogan.life

import org.scalacheck._
import org.scalacheck.Prop.forAllNoShrink
import GameOfLife._

class RulesSpec extends Properties("Rule") with BlockGen {

  implicit val listCompassFunctor: CompassFunctor[List] = new CompassFunctor[List] {
    override def cmap[A, B](fa: List[A])(z: => A)(fc: Compass[A] => B): List[B] = fa match {
      case a :: b :: c :: d :: middle :: e :: f :: g :: h :: Nil =>
        List(fc(Compass(middle, List(a, b, c, d, e, f, g, h))))
      case _ => Nil
    }
  }

  implicit class blockOps(val l: List[Cell]) {
    def shouldBe(c: Cell): Boolean = l match {
      case h :: _ => h == c
      case _      => false
    }
  }

  property("underpopulated - dies") = forAllNoShrink(schrodingersBlock(0, 1))(nextGeneration(_) shouldBe Dead)

  property("population 2 - no change") =
    forAllNoShrink(schrodingersBlock(2))(block => nextGeneration(block) shouldBe block(4))

  property("population 3 - always alive") = forAllNoShrink(schrodingersBlock(3))(nextGeneration(_) shouldBe Alive)

  property("overpopulated - dies") = forAllNoShrink(schrodingersBlock(4, 8))(nextGeneration(_) shouldBe Dead)
}
