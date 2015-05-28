package de.moso.entity

import java.util

import org.springframework.data.annotation.Id

import scala.beans.BeanProperty

/**
 * Created by sandro on 18.05.15.
 */

trait ModuleFiller {

}

trait Module {
  var serialNo: String
  var description: String


//  protected def addProperty(properties: java.util.Map[String,java.util.List[IoTPropertyBase]])(key: String, propertyType: String)(propVal: String, value: String): Unit = {
//    if (properties.get( key ) == null)
//      properties.put( key, new util.ArrayList() )
//
//    val iotPropList = properties.get(key)
//    if (iotPropList isEmpty)
//      iotPropList add( IoTPropertyBase(propertyType) )
//
//    def assign:Boolean = {
//      var found = false
//      for (t <- iotPropList) {
//        t match {
//          case iot: IoTPropertyBase if (iot.propertyName.equals(propertyType)) => {
//            iot.add(propVal, value)
//            found = true
//          }
//          case _ =>
//        }
//      }
//      found
//    }
//
//    if (!assign) {
//      iotPropList add( IoTPropertyBase(propertyType) )
//      assign
//    }
//  }
//}

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

  @Id @BeanProperty var id: java.lang.String = _

  @BeanProperty var properties: java.util.Map[String,java.util.List[IoTPropertyBase]] = _

  @BeanProperty var tags: java.util.List[Tag] = _

  //def addProperty( f:(key: String, propertyType: String)(propVal: String, value: String)=>Unit ): Unit = {
  def addProperty( addProperty:(java.util.Map[String,java.util.List[IoTPropertyBase]]) => Unit ): Unit = {
    if ( properties == null )
      properties = new util.HashMap()

    addProperty(properties)//(key,propertyType)(propVal,value)
  }

  def addTags(tag: Tag): Unit = {
    if (tags == null)
      tags = new util.ArrayList[Tag]();
    tags.add(tag)
  }
}

/*
case class TestClass(@Id @BeanProperty myValue: String, f:String => String) extends ComponentBase {
  @BeanProperty var serialNo: String = {f(":TestClass:")}
}
*/
/*
class MyComponent(f:String => String) extends ComponentBase {
  @Id @BeanProperty var name: java.lang.String = _
  @BeanProperty var serialNo: String = {f("MySerial") + math.random}
}
*/

/**
 * Represents an application user.
 */
/*
case class User(@Id @BeanProperty val username: String, @BeanProperty val password: String, @BeanProperty val firstName: String, @BeanProperty val lastName: String) extends Ordered[User] {
  require(firstName != null && firstName.trim != "", "First name must not be null or blank.")
  require(lastName != null && lastName.trim != "", "Last name must not be null or blank.")
  require(username != null && username.trim != "", "Username must not be null or blank.")
  require(password != null && password.trim != "", "Password name must not be null or blank.")

  /**
   * {@inheritDoc}
   */
  def compare(that: User): Int = {
    if (this.firstName != that.firstName) this.firstName.compare(that.firstName)
    else if (this.lastName != that.lastName) this.lastName.compare(that.lastName)
    else 0
  }

}
*/