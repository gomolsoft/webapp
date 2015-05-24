package de.moso.entity

/**
 * Created by sandro on 19.05.15.
 */

trait ModuleTypes[T] {
  def value: T
}

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

case class ModuleNumber(var value: Number) extends ModuleTypes[Number]

case class ModuleBool(var value: Boolean) extends ModuleTypes[Boolean]

//val b = new ModuleBool(true);
//val n = ModuleNumber(5.9)

trait LogicEspression {
  def and(expression: LogicEspression): LogicEspression

  def or(expression: LogicEspression): LogicEspression
}

class LogicEspressionBlock(logicOperator: LogicOperator) extends LogicEspression {
  def and(expression: LogicEspression): LogicEspression

  :
  {

  }

  def or(expression: LogicEspression): LogicEspression

  :
  {
    null
  }

}

class LogicOperator[M: ModuleTypes] {
  def >(operator: LogicOperator[M]): LogicOperator[M]

  def <(operator: LogicOperator[M]): LogicOperator[M]

  def ==(operator: LogicOperator[M]): LogicOperator[M]
}

