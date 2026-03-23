fun main(){

    val productCode = 2
    val price = 1.50
    val insertedMoney = 2

    val drinkName = when(productCode){
        1 -> "Voda"
        2 -> "Cola"
        3 -> "Sok"
        4 -> "Kava"
        else -> "Nepoznato piće"
    }

    if(insertedMoney >= price){
        val change = insertedMoney-price
        println("Točim: $drinkName. Ostatak: $change eura.")
    }else{
        val missing = price-insertedMoney
        println("Nedostaje još: ${missing} eura za ${drinkName}.")
    }
}