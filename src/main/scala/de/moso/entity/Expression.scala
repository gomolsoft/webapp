package de.moso.entity

/**
 * Created by sandro on 19.05.15.
 */

trait ModuleTypes[T] {
  def value: T
}

case class ModuleNumber[T](var value: BigInt[T]) extends ModuleTypes[Number[T]] {
}

case class ModuleBool(var value: Boolean) extends ModuleTypes[Boolean] {
}


//val b = new ModuleBool(true);
//val n = ModuleNumber(5.9)
