package de.moso.entity

import org.springframework.data.annotation.Id

import scala.beans.BeanProperty

/**
 * Created by sandro on 17.07.15.
 */


trait Locationable {
  var room: Room
  var location: String

}

case class Room( @BeanProperty var name: String ) {
  @Id
  @BeanProperty var id: java.lang.String = _

}

case class Location( @BeanProperty var room: Room, @BeanProperty var location: String ) extends Locationable {
  @Id
  @BeanProperty var id: java.lang.String = _

}
