package hr.ferit.zanzovko.dz3.predavanje_3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(
    item: MyData?,
    onBackClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Nazad"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (item != null) {
            TitleText(text = item.title)
            Spacer(modifier = Modifier.height(8.dp))
            DescriptionText(text = item.description)
        } else {
            Text(text = "Element nije pronađen")
        }
    }
}