package de.moso.entity

/**
 * Created by sandro on 26.05.15.
 */

trait ModuleBind[T]  {
  def moduleType: ModuleTypes[T]
  def module: Module
}

case class LogicBuilder[+T](module: Module, prop: java.util.List[IoTPropertyBase])  {
  def build = {
    prop.toString
//    if (prop.propertyName == "ValueType")

  }
}
