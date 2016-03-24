/**
  * Created by libing2 on 2016/3/22.
  */
class YesIam {
}

trait Logged {
  def log(msg: String) {}

}

class Account {
  var balance: Double = 100.9D
}

class SavingsAccount extends Account with Logged {
  def withdraw(amount: Double) {
    if (amount > balance) {
      log("Insufficient funds")
    } else {

    }
  }
}

trait ConsoleLogger extends Logged {
  override def log(msg: String) {
    println(msg)
  }
}

object ForRun{
  def main(args: Array[String]) {
    val acct = new SavingsAccount with ConsoleLogger
    acct.log("dddddd")
    print("我特么的就打印了一句话，咋这么happy")
  }
}
