package de.moso.entity

import java.util

import org.springframework.data.annotation.Id

import scala.beans.BeanProperty
import scala.collection.JavaConversions._

/**
 * Created by sandro on 18.05.15.
 */

trait ModuleFiller {
  def createPropertyType(key: String, propertyType: String)(propVal: String, value: String)(properties: java.util.Map[String,java.util.List[IoTPropertyBase]]): Unit = {
    if (properties.get( key ) == null)
      properties.put( key, new java.util.ArrayList() )

    val iotPropList = properties.get(key)
    if (iotPropList isEmpty)
      iotPropList add( IoTPropertyBase(propertyType) )

    def assign:Boolean = {
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

trait Module {
  var serialNo: String
  var description: String
}

case class Tag(@BeanProperty tagName: String)

case class IoTPropertyBase(@BeanProperty propertyName: String) {
  @BeanProperty var properties: java.util.Map[String, String] = _

  def add(name: String, value: String): Boolean = {
    if (properties == null) {
      properties = new java.util.HashMap()
    }
    null != properties.put(name, value)
  }
}

case class SensorModule(@BeanProperty var serialNo: String,
                        @BeanProperty var description: String
                         ) extends Module {

  @Id
  @BeanProperty var id: java.lang.String = _

  @BeanProperty var properties: java.util.Map[String, java.util.List[IoTPropertyBase]] = _

  @BeanProperty var tags: java.util.List[Tag] = _

  def addProperty(addProperty: (java.util.Map[String, java.util.List[IoTPropertyBase]]) => Unit): Unit = {
    if (properties == null)
      properties = new util.HashMap()

    addProperty(properties)
  }

  def addTags(tag: Tag): Unit = {
    if (tags == null)
      tags = new util.ArrayList[Tag]()
    tags.add(tag)
  }
}