package com.kurtlogan.life

import org.scalacheck._
import org.scalacheck.Prop.{forAll, forAllNoShrink}
import GameOfLife._

class RulesSpec extends Properties("Rule") with BlockGen {

  implicit val listCompassFunctor: CompassFunctor[List] = new CompassFunctor[List] {
    override def cmap[A, B](fa: List[A])(z: => A)(f: Compass[A] => B): List[B] = fa match {
      case tl :: t :: tr :: ml :: m :: mr :: bl :: b :: br :: Nil =>
        List(f(Compass(m, List(tl, t, tr, ml, mr, bl, b, br))))
      case _ => Nil
    }
  }

  implicit class blockOps(val l: List[Cell]) {
    def shouldBe(c: Cell): Boolean = l match {
      case h :: _ => h == c
      case _ => false
    }
  }

  property("underpopulated - dies") =
    forAllNoShrink(schrodingersBlock(0, 1))(nextGeneration(_) shouldBe Dead)

  property("population 2 - no change") =
    forAllNoShrink(schrodingersBlock(2))(list => nextGeneration(list) shouldBe list(4))

  property("population 3 - always alive") =
    forAllNoShrink(schrodingersBlock(3))(nextGeneration(_) shouldBe Alive)

  property("overpopulated - dies") =
    forAllNoShrink(schrodingersBlock(4, 8))(nextGeneration(_) shouldBe Dead)
}