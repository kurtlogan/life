package com.kurtlogan.life

import org.scalacheck.Gen

trait BlockGen {

  private val blockSize = 9
  private val neighbourSize = blockSize - 1

  val aliveGen: Gen[Cell] = Gen.const(Alive)
  val deadGen: Gen[Cell] = Gen.const(Dead)
  val cellGen: Gen[Cell] = Gen.oneOf(Alive, Dead)

  val aliveCellsGen: Int => Gen[List[Cell]] = Gen.listOfN(_, aliveGen)
  val deadCellsGen: Int => Gen[List[Cell]] = Gen.listOfN(_, deadGen)

  val aliveBlock: (Int, Int) => Gen[List[Cell]] = blockGen(Alive)(_, _)
  val deadBlock: (Int, Int) => Gen[List[Cell]] = blockGen(Dead)(_, _)

  def schrodingersBlock(n: Int): Gen[List[Cell]] = schrodingersBlock(n, n)
  def schrodingersBlock(min: Int, max: Int): Gen[List[Cell]] = cellGen.flatMap(blockGen(_)(min, max))

  private def blockGen(primary: Cell)(min: Int, max: Int): Gen[List[Cell]] =
    for {
      n <- Gen.chooseNum(min, max)
      alive <- aliveCellsGen(n)
      dead <- deadCellsGen(neighbourSize - n)
      rndOrder <- Gen.pick(neighbourSize, alive ++ dead)
      (fst, snd) = rndOrder.toList.splitAt(neighbourSize / 2)
    } yield {
      (fst :+ primary) ++ snd
    }

}