package hr.ferit.zanzovko.dz4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.ferit.zanzovko.dz4.DZ4.NoteEditScreen
import hr.ferit.zanzovko.dz4.DZ4.NotesListScreen
import hr.ferit.zanzovko.dz4.ui.theme.DZ3Theme

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
fun NotesApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "notes_list"
    ) {
        composable(route = "notes_list") {
            NotesListScreen(
                onAddClick = {
                    navController.navigate("note_edit/-1")
                },
                onNoteClick = { noteId ->
                    navController.navigate("note_edit/$noteId")
                }
            )
        }

        composable(
            route = "note_edit/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1

            NoteEditScreen(
                noteId = noteId,
                onBackClick = { navController.popBackStack() },
                onSaveSuccess = { navController.popBackStack() }
            )
        }
    }
}