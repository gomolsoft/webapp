import java.time._

import scala.math.ScalaNumber

/**
 * Created by sandro on 19.05.15.
 */

sealed trait LogicOperator

case object < extends LogicOperator

case object > extends LogicOperator

case object eq extends LogicOperator

trait ModuleTypes[T] {
  def genericComparator[T](logicOperator: Char, moduleTypes1: T, moduleTypes2: T): Boolean =
    (logicOperator, moduleTypes1, moduleTypes2) match {
      case ('>', b1: Boolean, b2: Boolean) => b1 > b2
      case ('>', n1: Number, n2: Number) => n1.doubleValue() > n2.doubleValue()
      case ('>', d1: LocalDate, d2: LocalDate) => d1.compareTo(d2) > 0

      case ('<', b1: Boolean, b2: Boolean) => b1 < b2
      case ('<', n1: Number, n2: Number) => n1.doubleValue() < n2.doubleValue()
      case ('<', d1: LocalDate, d2: LocalDate) => d1.compareTo(d2) < 0

      case ('=', b1: Boolean, b2: Boolean) => b1 == b2
      case ('=', n1: Number, n2: Number) => n1.doubleValue() == n2.doubleValue()
      case ('=', d1: LocalDate, d2: LocalDate) => d1.compareTo(d2) == 0

      case _ => throw new RuntimeException
    }

  def value: T

  def <(moduleType: ModuleTypes[T]): Boolean = genericComparator('<', value, moduleType.value)

  def >(moduleType: ModuleTypes[T]): Boolean = genericComparator('>', value, moduleType.value)

  def eq(moduleType: ModuleTypes[T]): Boolean = genericComparator('=', value, moduleType.value)

}

case class ModuleDate(var value: LocalDate) extends ModuleTypes[LocalDate]

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
      case < => valueExpressionLeft < valueExpressionRight
      case > => valueExpressionLeft > valueExpressionRight
      case eq => valueExpressionLeft eq valueExpressionRight
    }
  }
}

class LogicBox(expLeft: Logic, exprRight: Logic) {
  def and: Logic with AnyRef = {
    new Logic {
      override def analyze = expLeft.analyze & exprRight.analyze
    }
  }

  def or: Logic with AnyRef = {
    new Logic {
      override def analyze = expLeft.analyze | exprRight.analyze
    }
  }
}

////////////////////////////////////////////////////////////////////////////////////////////
val mi1 = ModuleDouble(99.99999)
val mi2 = ModuleDouble(99.99998)

val d1 = ModuleDate(LocalDate.of(2014, 3, 15))
val d2 = ModuleDate(LocalDate.of(2014, 3, 14))

val loA1 = new LogicAnalyzer(mi1, <, mi2)
val loA2 = new LogicAnalyzer(mi1, >, mi2)
val loA3 = new LogicAnalyzer(mi1, eq, mi2)

val loA4 = new LogicAnalyzer(d1, eq, d2)

val lb1 = new LogicBox(loA1, loA3)
val lb2 = new LogicBox(loA4, lb1.or)


loA1.analyze
loA2.analyze
loA3.analyze

