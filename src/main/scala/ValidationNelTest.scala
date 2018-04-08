import scalaz.syntax.validation._
import scalaz.{Applicative, NonEmptyList, Validation}

object ValidationNelTest {
  type ValidOrError[X] = Validation[NonEmptyList[String], X]
  def main(args: Array[String]): Unit = {
    // divide by zero get invalid dividor
    val validDividor = validateDivision(2)
    val num = validateDivision(8)
    val invalidDividor = validateDivision(0)
    val divideValidated = Applicative[ValidOrError].lift2(division)
    println( num +" / " + validDividor.toString +"   =   " + divideValidated(num.toValidationNel,validDividor.toValidationNel))
    println( num +" / " + invalidDividor +"   =   " + divideValidated(num.toValidationNel,invalidDividor.toValidationNel))
  }
  def division(x: Int, y: Int) = x / y
  def validateDivision(n: Int) = if (n != 0) n.success else "invalid num".failure
}
