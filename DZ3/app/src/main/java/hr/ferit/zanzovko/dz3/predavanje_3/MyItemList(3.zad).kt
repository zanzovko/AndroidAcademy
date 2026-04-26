package hr.ferit.zanzovko.dz3.predavanje_3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyItemList(
    items: List<MyData>,
    onShuffleClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        CustomButton(
            text = "Promiješaj",
            onClick = onShuffleClick,
            modifier = Modifier.padding(8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp), // razmak između kartica
            contentPadding = androidx.compose.foundation.layout.PaddingValues(8.dp)
        ) {
            items(items, key = { it.id }) { item ->
                ItemCard(data = item)
            }
        }
    }
}