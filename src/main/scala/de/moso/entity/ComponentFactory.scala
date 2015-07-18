package de.moso.entity

import scala.collection.JavaConversions._

/**
 * Created by sandro on 29.05.15.
 */
package object factory {
  trait ModuleFiller {

    def createPropertyType (key: String, propertyType: String)
                           (propVal: String, value: String)
                           (properties: java.util.Map[String,java.util.List[IoTPropertyBase]]): Unit = {
      if (properties.get( key ) == null)
        properties.put( key, new java.util.ArrayList() )

      val iotPropList = properties.get(key)
      if (iotPropList isEmpty)
        iotPropList add( IoTPropertyBase(propertyType) )

      def assign: Boolean = {
        var found = false
        for (t <- iotPropList) {
          t match {
            case iot: IoTPropertyBase if (iot.propertyName.equals(propertyType)) => {
              iot.add(propVal, value)
              found = true
            }
            case _ =>
          }
        }
        found
      }

      if (!assign) {
        iotPropList add( IoTPropertyBase(propertyType) )
        assign
      }
    }

  }

  class ComponentFactory {

  }

}

