package hr.ferit.zanzovko.dz4.DZ4

class NotesRepository {
    private val notes = mutableListOf(
        Note(id = 1, title = "note 1", description = "Prva bilješka"),
        Note(id = 2, title = "note 2", description = "Druga bilješka")
    )

    fun getAllNotes(): List<Note> = notes.toList()

    fun getNoteById(id: Int): Note? = notes.find { it.id == id }

    fun addNote(title: String, description: String) {
        val newId = (notes.maxOfOrNull { it.id } ?: 0) + 1
        notes.add(Note(id = newId, title = title, description = description))
    }

    fun updateNote(id: Int, title: String, description: String) {
        val index = notes.indexOfFirst { it.id == id }
        if (index != -1) {
            notes[index] = notes[index].copy(title = title, description = description)
        }
    }
}