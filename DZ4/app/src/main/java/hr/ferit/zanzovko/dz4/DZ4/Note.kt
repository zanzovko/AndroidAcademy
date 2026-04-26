package hr.ferit.zanzovko.dz4.DZ4

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Note(
    val id: Int,
    val title: String,
    val description: String,
    val date: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
)
