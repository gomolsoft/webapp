package de.moso.entity

import scala.beans.BeanProperty

package finding {

  /**
   * Created by Sandro on 20.07.15 for webapp.
   */
  case class Tag(@BeanProperty tagName: String)

}


package naming {

  case class Description(@BeanProperty long: String, @BeanProperty short: String)

  object Description {
    def apply(short: String) = new Description("", short)
  }

}