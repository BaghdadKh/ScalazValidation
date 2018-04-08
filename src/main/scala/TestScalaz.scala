import scala.util.{Failure, Success, Try}
import scalaz.Validation
import scalaz.syntax.validation._

object TestScalaz {
  def main(args: Array[String]): Unit = {
    val result : Try[Int] = add(6,0 ) // get exception message
    val result1 : Try[Int] = add(6,2 ) // normally get 3
    //normal match case for Success , Failure
    result match {
      case Success(res) => println(res)
      case Failure(ex) => println(ex)
    }
    println("/***********************************************************************************/")
    val e = "error".failure[Int]
    println("error  " + e) //scalaz.Validation[String,Int] = Failure(error)
    val s = 1.success[String]
    println("1.success  " +s) //scalaz.Validation[String,Int] = Success(1)
    /** Return the success value of this validation or the given default if failure.*/
    println("e.getOrElse(2)   " + e.getOrElse(2))
    println(e | 2) //  Alias for `getOrElse`
    //Run the first given function if failure, otherwise, the second given function.
    println(s.fold(_ => 2, _ * 6))
    println(e.fold(_ => 2, _ * 6))
    //error :None , Some if one element
    println(e.toOption)
    // Map on the success of this validation Success()
    println(s.map(_ * 100))
    val error = Validation.failure[String, Int]("error")
    println(error) // Failure(_)
    val success = Validation.success[String, Int](1)
    println(success) // Success(_)
    println("/***********************************************************************************/")
    println("error".failureNel)
    println("error".failure.toValidationNel)
    println("ok".successNel)
    println("ok".success.toValidationNel)
  }
  def add(x:Int,y:Int) : Try[Int] = {
    Try(x/y)
  }
}
