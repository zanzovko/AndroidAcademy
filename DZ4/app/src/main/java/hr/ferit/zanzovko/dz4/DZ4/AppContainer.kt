package hr.ferit.zanzovko.dz4.DZ4

object AppContainer {
    val notesRepository: NotesRepository by lazy { NotesRepository() }
}