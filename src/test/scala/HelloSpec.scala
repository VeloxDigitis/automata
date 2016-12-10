import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
  
  val hello = "Hello, world!"
  
  "Hello World" should "contain two words" in {
  
    hello.split(" ").size should be(2)
  
  }
}