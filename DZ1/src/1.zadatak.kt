fun main() {

    val firstName = "Ivan"
    val lastName = "Horvat"

    var email: String? = null

    var age: Int? = 25

    println("Duljina emaila: ${email?.length}")
    println("Duljina emaila: ${email?.length ?: "Email nije postavljen."}")

    email = "ivan.horvat@gmail.com"
    println("Duljina emaila: ${email?.length}")
}