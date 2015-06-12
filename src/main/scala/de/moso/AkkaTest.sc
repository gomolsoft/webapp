import akka.actor.{Actor, ActorSystem, Props}

import scala.annotation.tailrec
import scala.util.Random


class HelloActor extends Actor {
  def receive = {
    case "hello" => println("hello back at you")
    case _       => println("huh?")
  }
}

class AkkaTest {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  helloActor ! "hello"
  helloActor ! "buenos dias"
}

//val t = new AkkaTest
val mainList = List(0 to 10000:_*)

@tailrec
def check(ints: List[Int], cnts: Int = 0):Boolean = {
  ints match {
    case Nil => false

    case x :: tail => {

      if (x == 5000) {
        println(s"value= $x, after count= $cnts")
        return true
      }
      check(tail, cnts+1)

    }
  }
}

check(Random.shuffle(mainList))
