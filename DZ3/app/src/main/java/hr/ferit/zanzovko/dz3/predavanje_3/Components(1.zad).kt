package hr.ferit.zanzovko.dz3.predavanje_3

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1A237E),
        modifier = modifier.padding(8.dp)
    )
}

@Composable
fun DescriptionText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 14.sp,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis, // vlastita stilska izmjena - "..." na kraju
        color = Color.DarkGray,
        modifier = modifier.padding(4.dp)
    )
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6200EE),
            contentColor = Color.White
        ),
        modifier = modifier.padding(4.dp)
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun ComponentsPreview() {
    androidx.compose.foundation.layout.Column {
        TitleText(text = "Naslov")
        DescriptionText(text = "Ovo je neki opis koji će se prikazati u maksimalno tri linije teksta.")
        CustomButton(text = "Klikni me")
    }
}