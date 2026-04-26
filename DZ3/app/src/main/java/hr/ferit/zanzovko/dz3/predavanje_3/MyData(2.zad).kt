package hr.ferit.zanzovko.dz3.predavanje_3

data class MyData(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String = "", // opcionalno, za Coil
    val topic: String = "" // za bonus u Tasku 5
)