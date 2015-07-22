package de.moso.logic

import de.moso.entity.{IoTPropertyBase, Module}

/**
 * Created by Sandro on 20.07.15 for Default (Template) Project.
 */
case class LogicBuilder[+T](module: Module, prop: java.util.List[IoTPropertyBase])  {
  def build = {
    prop.toString
//    if (prop.propertyName == "ValueType")

  }
}
