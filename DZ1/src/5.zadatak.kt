object TransactionLogger {
    fun log(message: String) {
        println("[LOG] $message")
    }
}

class BankAccount(val accountNumber: String) {

    var balance: Double = 0.0
        private set

    init {
        totalAccounts++
    }

    fun deposit(amount: Double) {
        if (amount <= 0) {
            TransactionLogger.log("Neuspješna uplata na račun $accountNumber: iznos mora biti pozitivan.")
            return
        }
        balance += amount
        TransactionLogger.log("Uplata $amount € na račun $accountNumber. Novo stanje: $balance €")
    }

    fun withdraw(amount: Double) {
        if (amount <= 0) {
            TransactionLogger.log("Neuspješna isplata s računa $accountNumber: iznos mora biti pozitivan.")
            return
        }
        if (amount > balance) {
            TransactionLogger.log("Neuspješna isplata s računa $accountNumber: nedovoljno sredstava.")
            return
        }
        balance -= amount
        TransactionLogger.log("Isplata $amount € s računa $accountNumber. Novo stanje: $balance €")
    }

    companion object {
        var totalAccounts: Int = 0
            private set
    }
}

fun main() {
    val acc1 = BankAccount("HR001")
    val acc2 = BankAccount("HR002")
    val acc3 = BankAccount("HR003")

    acc1.deposit(500.0)
    acc1.withdraw(200.0)
    acc1.withdraw(400.0) // Nedovoljno sredstava

    acc2.deposit(1000.0)
    acc2.withdraw(250.0)

    println("\nStanje računa ${acc1.accountNumber}: ${acc1.balance} €")
    println("Stanje računa ${acc2.accountNumber}: ${acc2.balance} €")
    println("Stanje računa ${acc3.accountNumber}: ${acc3.balance} €")
    println("\nUkupno kreiranih računa: ${BankAccount.totalAccounts}")
}