package com.kurtlogan.life

sealed trait Cell
case object Alive extends Cell
case object Dead  extends Cell
