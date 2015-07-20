package de.moso.entity

import org.springframework.data.annotation.Id

import scala.beans.BeanProperty

/**
 * Created by sandro on 17.07.15.
 */
case class Location( @BeanProperty var name: String ) {
  @Id
  @BeanProperty var id: java.lang.String = _
}