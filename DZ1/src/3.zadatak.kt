fun main(){

    val steps = listOf(4500, 12000, 8000, 15000, 3000, 11000, 9500)

    var total = 0

    for(i in steps){
        total += i
    }
    println("Ukupan broj koraka u tjednu je $total.")

    var index=0
    while(index < steps.size){
        if(steps[index] > 10000){
            println("Prvi dan kad je korisnik premašio 10k koraka je: ${index+1}. dan")
            break
        }
        index++
    }
}