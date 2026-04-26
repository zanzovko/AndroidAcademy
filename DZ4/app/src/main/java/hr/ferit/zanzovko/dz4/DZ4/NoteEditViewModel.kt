package hr.ferit.zanzovko.dz4.DZ4

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NoteEditViewModel(
    private val repository: NotesRepository,
    private val noteId: Int
) : ViewModel() {

    // existingNote = null znači da se kreira nova bilješka
    val existingNote: Note? = if (noteId == -1) null else repository.getNoteById(noteId)

    var title by mutableStateOf(existingNote?.title ?: "")
        private set

    var description by mutableStateOf(existingNote?.description ?: "")
        private set

    val date: String = existingNote?.date ?: ""

    fun onTitleChange(newTitle: String) {
        title = newTitle
    }

    fun onDescriptionChange(newDescription: String) {
        description = newDescription
    }

    fun saveNote() {
        if (title.isBlank()) return
        if (existingNote == null) {
            repository.addNote(title, description)
        } else {
            repository.updateNote(existingNote.id, title, description)
        }
    }
}

class NoteEditViewModelFactory(
    private val repository: NotesRepository,
    private val noteId: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteEditViewModel(repository, noteId) as T
    }
}