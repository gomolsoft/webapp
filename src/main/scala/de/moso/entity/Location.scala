package de.moso.entity

import java.util

import org.springframework.data.annotation.Id

import scala.beans.BeanProperty

/**
 * Created by sandro on 17.07.15.
 */
//TODO
case class Location(@BeanProperty var name: String,
                        @BeanProperty var serialNo: String,
                        @BeanProperty var description: String
                         ) {

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