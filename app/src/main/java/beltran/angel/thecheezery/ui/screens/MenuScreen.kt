package beltran.angel.thecheezery.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import beltran.angel.thecheezery.R
import beltran.angel.thecheezery.ui.theme.Brighter_Pink
import beltran.angel.thecheezery.ui.theme.Less_Purple
import beltran.angel.thecheezery.ui.theme.Pinky
import beltran.angel.thecheezery.ui.theme.TheCheezeryTheme
import beltran.angel.thecheezery.ui.theme.Very_purple

val firstGradient = Brush.verticalGradient(listOf(Brighter_Pink, Pinky))
val secondGradient = Brush.verticalGradient(listOf(Pinky, Less_Purple))
val thirdGradient = Brush.verticalGradient(listOf(Less_Purple, Very_purple))

@Composable
fun MenuScreen(
    onHotDrinksClick: () -> Unit = {},
    onColdDrinksClick: () -> Unit = {},
    onSaltiesClick: () -> Unit = {},
    onSweetsClick: () -> Unit = {},
    onCombosClick: () -> Unit = {},
    onAddProductClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.grupo2),
            contentDescription = "The Cheezery logo",
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .padding(top = 32.dp, bottom = 24.dp),
            contentScale = ContentScale.Fit
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            MenuRow(
                leftLabel = "Hot drinks",
                rightLabel = "Cold drinks",
                gradient = firstGradient,
                onLeftClick = onHotDrinksClick,
                onRightClick = onColdDrinksClick
            )
            MenuRow(
                leftLabel = "Salties",
                rightLabel = "Sweets",
                gradient = secondGradient,
                onLeftClick = onSaltiesClick,
                onRightClick = onSweetsClick
            )
            MenuRow(
                leftLabel = "Combos",
                rightLabel = "Add new product",
                gradient = thirdGradient,
                onLeftClick = onCombosClick,
                onRightClick = onAddProductClick
            )
        }
    }
}

@Composable
private fun MenuRow(
    leftLabel: String,
    rightLabel: String,
    gradient: Brush,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        MenuCategoryButton(
            text = leftLabel,
            gradient = gradient,
            onClick = onLeftClick,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        MenuCategoryButton(
            text = rightLabel,
            gradient = gradient,
            onClick = onRightClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun MenuCategoryButton(
    text: String,
    gradient: Brush,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(115.dp)
            .background(brush = gradient)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    TheCheezeryTheme {
        MenuScreen()
    }
}
