package de.moso.entity

/**
 * Created by sandro on 26.05.15.
 */


trait ModuleBind[T]  {
  def moduleType: ModuleTypes[T]
  def module: Module
}

class LogicBuilder[+T](module: Module, prop: IoTPropertyBase)  {

}
