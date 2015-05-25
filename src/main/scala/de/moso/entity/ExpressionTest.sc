import scala.math.ScalaNumber

val mi1 = ModuleInt(3)
val mi2 = ModuleInt(5)
val loA = new LogicAnalyzer[Int](mi1, <, mi2)
val loA2 = new LogicAnalyzer[Int](mi1, >, mi2)
val lb = new LogicBox[Int]()

//case object eq extends LogicOperator

sealed trait LogicOperator

/**
 * Created by sandro on 19.05.15.
 */

trait ModuleTypes[T] {
  def value: T
  def <(moduleType: ModuleTypes[T]): Boolean
  def >(moduleType: ModuleTypes[T]): Boolean
}

case class ModuleNumber(var value: ScalaNumber) extends ModuleTypes[ScalaNumber] {
  override def <(moduleType: ModuleTypes[ScalaNumber]): Boolean = this.value.doubleValue() < value.doubleValue()
  override def >(moduleType: ModuleTypes[ScalaNumber]): Boolean = this.value.doubleValue() > value.doubleValue()
}

case class ModuleBool(var value: Boolean) extends ModuleTypes[Boolean] {
  override def <(moduleType: ModuleTypes[Boolean]): Boolean = this.value < value
  override def >(moduleType: ModuleTypes[Boolean]): Boolean = this.value > value
}

case class ModuleInt(var value: Int) extends ModuleTypes[Int] {
  implicit def intToNumb(moduleInt: ModuleInt): ModuleNumber = {
    ModuleNumber(BigInt(moduleInt.value))
  }

  override def <(moduleType: ModuleTypes[Int]): Boolean = value < moduleType.value
  override def >(moduleType: ModuleTypes[Int]): Boolean = value > moduleType.value
}

case class ModuleDouble(var value: Double) extends ModuleTypes[Double] {
  implicit def floatToNumb(moduleFloat: ModuleDouble): ModuleNumber = {
    ModuleNumber(BigDecimal(moduleFloat.value))
  }

  override def <(moduleType: ModuleTypes[Double]): Boolean = value < moduleType.value
  override def >(moduleType: ModuleTypes[Double]): Boolean = value > moduleType.value
}

class LogicAnalyzer[T](valueExpressionLeft: ModuleTypes[T], logicOperator: LogicOperator, valueExpressionRight: ModuleTypes[T]) {
  def analyze: Boolean = {
    logicOperator match {
      case < => valueExpressionLeft < valueExpressionRight
      case > => valueExpressionLeft > valueExpressionRight
      //    case eq => ???
    }
  }
}

class LogicBox[T] {
  def and(expLeft: LogicAnalyzer[T], exprRight: LogicAnalyzer[T]): Boolean = {
    expLeft.analyze & exprRight.analyze
  }

  def or(expLeft: LogicAnalyzer[T], exprRight: LogicAnalyzer[T]): Boolean = {
    expLeft.analyze | exprRight.analyze
  }
}
case object < extends LogicOperator

case object > extends LogicOperator

loA.analyze
loA2.analyze

lb.or(loA, loA2)
