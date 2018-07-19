package com.kurtlogan.life

import scala.language.higherKinds

case class Compass[A](point: A, neighbours: Seq[A])

trait CompassMapper[F[_]] {
  def cmap[A, B](fa: F[A])(z: => A)(f: Compass[A] => B): F[B]
}

object CompassMapper {

  def apply[F[_]: CompassMapper]: CompassMapper[F] = implicitly[CompassMapper[F]]

  object ops {
    implicit class CompassMapperOps[A, F[_]: CompassMapper](fa: F[A]) {
      def cmap[B](z: => A)(f: Compass[A] => B): F[B] =
        CompassMapper[F].cmap(fa)(z)(f)
    }
  }
}
