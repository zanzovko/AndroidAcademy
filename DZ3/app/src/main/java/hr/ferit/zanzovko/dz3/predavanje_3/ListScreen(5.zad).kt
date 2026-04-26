package hr.ferit.zanzovko.dz3.predavanje_3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListScreen(
    items: List<MyData>,
    onShuffleClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredItems = if (searchQuery.isBlank()) {
        items
    } else {
        items.filter { it.topic.contains(searchQuery, ignoreCase = true) }
    }

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Pretraži po topicu") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        CustomButton(
            text = "Promiješaj",
            onClick = onShuffleClick
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredItems, key = { it.id }) { item ->
                ItemCard(
                    data = item,
                    modifier = Modifier.clickable { onItemClick(item.id) }
                )
            }
        }
    }
}