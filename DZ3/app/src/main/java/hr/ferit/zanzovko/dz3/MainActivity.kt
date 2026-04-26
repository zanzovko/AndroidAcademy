package hr.ferit.zanzovko.dz3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.ferit.zanzovko.dz3.DZ3.Note
import hr.ferit.zanzovko.dz3.DZ3.NoteEditScreen
import hr.ferit.zanzovko.dz3.DZ3.NotesListScreen
import hr.ferit.zanzovko.dz3.predavanje_3.DetailScreen
import hr.ferit.zanzovko.dz3.predavanje_3.ListScreen
import hr.ferit.zanzovko.dz3.predavanje_3.MyData
import hr.ferit.zanzovko.dz3.ui.theme.DZ3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DZ3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    var dataList by remember {
        mutableStateOf(
            listOf(
                MyData(1, "Kotlin", "Kotlin je moderni programski jezik koji radi na JVM-u.", topic = "Programming"),
                MyData(2, "Jetpack Compose", "Deklarativni UI framework za Android razvoj.", topic = "Android"),
                MyData(3, "Android Studio", "Službeni IDE za razvoj Android aplikacija.", topic = "Tools"),
                MyData(4, "Material Design", "Google-ov dizajn sistem za moderna korisnička sučelja.", topic = "Design"),
                MyData(5, "Coroutines", "Kotlin-ov način asinkronog programiranja.", topic = "Programming"),
                MyData(6, "Navigation", "Compose Navigation komponenta za prelazak između ekrana.", topic = "Android")
            )
        )
    }

    NavHost(
        navController = navController,
        startDestination = "list_screen"
    ) {
        composable(route = "list_screen") {
            ListScreen(
                items = dataList,
                onShuffleClick = { dataList = dataList.shuffled() },
                onItemClick = { id ->
                    navController.navigate("detail_screen/$id")
                }
            )
        }

        composable(
            route = "detail_screen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            val item = dataList.find { it.id == id }

            DetailScreen(
                item = item,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}


@Composable
fun NotesApp() {
    val navController = rememberNavController()

    // STATE - lista bilješki
    var notes by remember {
        mutableStateOf(
            listOf(
                Note(id = 1, title = "note 1", description = "Prva bilješka"),
                Note(id = 2, title = "note 2", description = "Druga bilješka")
            )
        )
    }

    NavHost(
        navController = navController,
        startDestination = "notes_list"
    ) {
        // Zaslon 1 - lista bilješki
        composable(route = "notes_list") {
            NotesListScreen(
                notes = notes,
                onAddClick = {
                    // navigacija na zaslon 2 bez ID-a (nova bilješka)
                    navController.navigate("note_edit/-1")
                },
                onNoteClick = { noteId ->
                    // navigacija na zaslon 2 s ID-em postojeće bilješke
                    navController.navigate("note_edit/$noteId")
                }
            )
        }

        // Zaslon 2 - unos/uređivanje bilješke
        composable(
            route = "note_edit/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            val existingNote = if (noteId == -1) null else notes.find { it.id == noteId }

            NoteEditScreen(
                existingNote = existingNote,
                onBackClick = { navController.popBackStack() },
                onSaveClick = { title, description ->
                    notes = if (existingNote == null) {
                        // dodaj novu bilješku
                        val newId = (notes.maxOfOrNull { it.id } ?: 0) + 1
                        notes + Note(id = newId, title = title, description = description)
                    } else {
                        // ažuriraj postojeću bilješku
                        notes.map { note ->
                            if (note.id == existingNote.id) {
                                note.copy(title = title, description = description)
                            } else {
                                note
                            }
                        }
                    }
                    navController.popBackStack()
                }
            )
        }
    }
}