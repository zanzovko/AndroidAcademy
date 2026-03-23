fun prepareUsername(username: String): String {
    return username.trim().lowercase()
}

fun isUsernameValid(username: String): Boolean {
    if (username.isBlank()) return false
    if (username.length < 5 || username.length > 15) return false
    if (!username[0].isLetter()) return false
    if (username.contains(" ")) return false
    if (!username.all { it.isLetterOrDigit() || it == '_' }) return false
    return true
}

fun main() {
    val testUsernames = listOf(" John_doe123 ", "ab", "valid_user1", "123invalid", "has space", "perfect15")

    for (raw in testUsernames) {
        val prepared = prepareUsername(raw)
        val valid = isUsernameValid(prepared)
        println("Originalno: \"$raw\" → Obrađeno: \"$prepared\" → Valjano: $valid")
    }
}