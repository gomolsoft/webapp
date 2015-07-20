package de.moso.entity

import java.util

import org.springframework.data.annotation.Id

import scala.beans.BeanProperty

/**
 * Created by sandro on 18.05.15.
 */


case class Description(@BeanProperty long: String, @BeanProperty short: String)
object Description {
  def apply(short:String) = new Description("", short)
}

trait Module {
  var serialNo: String
  var description: Description
  var name: String
  var active: Boolean
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

case class SensorModule(@BeanProperty var name: String,
                        @BeanProperty var serialNo: String,
                        @BeanProperty var description: Description,
                        @BeanProperty var active: Boolean
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