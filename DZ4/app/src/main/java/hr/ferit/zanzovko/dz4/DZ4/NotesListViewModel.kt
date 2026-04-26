package hr.ferit.zanzovko.dz4.DZ4

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NotesListViewModel(
    private val repository: NotesRepository
) : ViewModel() {

    var notes by mutableStateOf(repository.getAllNotes())
        private set

    fun refreshNotes() {
        notes = repository.getAllNotes()
    }
}

class NotesListViewModelFactory(
    private val repository: NotesRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesListViewModel(repository) as T
    }
}