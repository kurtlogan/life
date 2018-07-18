package com.kurtlogan.life

import scala.annotation.implicitNotFound
import scala.language.higherKinds

case class Compass[A](point: A, neighbours: Seq[A])

@implicitNotFound("Could not find instance for CompassFunctor")
trait CompassFunctor[F[_]] {
  def cmap[A, B](fa: F[A])(z: => A)(f: Compass[A] => B): F[B]
}

object CompassFunctor {

  def apply[F[_]: CompassFunctor]: CompassFunctor[F] = implicitly[CompassFunctor[F]]

  object ops {
    implicit class CompassFunctorOps[A, F[_]: CompassFunctor](fa: F[A]) {
      def cmap[B](z: => A)(f: Compass[A] => B): F[B] =
        CompassFunctor[F].cmap(fa)(z)(f)
    }
  }
}
