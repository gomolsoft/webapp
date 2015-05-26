package de.moso.entity

import scala.math.ScalaNumber

/**
 * Created by sandro on 19.05.15.
 */

sealed trait LogicOperator
case object < extends LogicOperator
case object > extends LogicOperator
case object eq extends LogicOperator

trait ModuleTypes[T] {
  def genericComparator[T](logicOperator: Char, moduleTypes1: T, moduleTypes2: T) : Boolean =
    (logicOperator, moduleTypes1, moduleTypes2) match {
      case ('>', b1:Boolean, b2:Boolean) => b1 > b2
      case ('>', n1:Number, n2:Number)   => n1.doubleValue() > n2.doubleValue()

      case ('<', b1:Boolean, b2:Boolean) => b1 < b2
      case ('<', n1:Number, n2:Number)   => n1.doubleValue() < n2.doubleValue()

      case ('=', b1:Boolean, b2:Boolean) => b1 == b2
      case ('=', n1:Number, n2:Number)   => n1.doubleValue() == n2.doubleValue()

      case _ => false
    }

  def value: T
  def <(moduleType: ModuleTypes[T]): Boolean = genericComparator('<', value, moduleType.value)
  def >(moduleType: ModuleTypes[T]): Boolean = genericComparator('>', value, moduleType.value)
  def eq(moduleType: ModuleTypes[T]): Boolean = genericComparator('=', value, moduleType.value)

}

case class ModuleNumber(var value: ScalaNumber) extends ModuleTypes[ScalaNumber]
case class ModuleBool(var value: Boolean) extends ModuleTypes[Boolean]

case class ModuleInt(var value: Int) extends ModuleTypes[Int] {
  implicit def intToNumb(moduleInt: ModuleInt): ModuleNumber = {
    ModuleNumber(BigInt(moduleInt.value))
  }
}
case class ModuleDouble(var value: Double) extends ModuleTypes[Double] {
  implicit def floatToNumb(moduleFloat: ModuleDouble): ModuleNumber = {
    ModuleNumber(BigDecimal(moduleFloat.value))
  }
}

trait Logic {
  def analyze: Boolean
}

class LogicAnalyzer[+T](valueExpressionLeft: ModuleTypes[T], logicOperator: LogicOperator, valueExpressionRight: ModuleTypes[T]) extends Logic {
  def analyze: Boolean = {
    logicOperator match {
      case <  => valueExpressionLeft < valueExpressionRight
      case >  => valueExpressionLeft > valueExpressionRight
      case eq => valueExpressionLeft eq valueExpressionRight
    }
  }
}

class LogicBox(expLeft: Logic, exprRight: Logic) {

  def and: Logic with Any = {
    new Logic {
      override def analyze: Boolean = expLeft.analyze & exprRight.analyze
    }
  }

  def or: Logic with Any = {
    new Logic {
      override def analyze: Boolean = expLeft.analyze | exprRight.analyze
    }
  }
}
