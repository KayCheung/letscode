/**
  * Created by libing2 on 2016/3/23.
  */
class HiObject {

}


object KnowObject extends App {
  Console.print("The whole class body becomes the main method")
}

class Account {
  val id = Account.newUniqueNumber()
  private var balance = 0.0D

  def deposit(amount: Double) {
    balance += amount
  }
}

object Account {
  private var lastNumber = 0

  def newUniqueNumber() = {
    lastNumber += 1
    lastNumber
  }
}

