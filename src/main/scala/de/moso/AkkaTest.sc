import akka.actor.{Actor, ActorSystem, Props}

import scala.annotation.tailrec
import scala.collection.immutable.List
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
//
val x1 = for(i <- 1 to 2)  yield Random.nextPrintableChar()
val x2 = for {
  i <- x1
  n = Random.nextPrintableChar()
  s = Random.nextString(n)
  n2 = Random.nextInt(3)

  s2 = List.fill(n2)(Random.nextPrintableChar()).mkString

} yield s2
val x3 = for {
  i <- x1
  j <- x2

  if ( j.contains(i) )

} yield (i,j)
//
var optNumbers = List(Some(1), Some(-1), None, Some(2), None, Some(3))  ++:  List(Some(99))
optNumbers = optNumbers ++: List(Some(12), Some(12), None, Some(22), None, Some(32))
val n3 = for {
  optNumber <- optNumbers
  value <- optNumber
} yield value

//
/*
case class FutureO[+A](future: Future[Option[A]]) extends AnyVal {
  def flatMap[B](f: A => FutureO[B])(implicit ec: ExecutionContext): FutureO[B] = {
    val newFuture = future.flatMap{
      case Some(a) => f(a).future
      case None => Future.successful(None)
    }
    FutureO(newFuture)
  }

  def map[B](f: A => B)(implicit ec: ExecutionContext): FutureO[B] = {
    FutureO(future.map(option => option map f))
  }
}
*/