package de.moso.logic

import de.moso.entity.{Module, ModuleTypes}

/**
 * Created by sandro on 26.05.15.
 */

trait ModuleBind[T]  {
  def moduleType: ModuleTypes[T]
  def module: Module
}
