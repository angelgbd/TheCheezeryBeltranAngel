package beltran.angel.thecheezery.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import beltran.angel.thecheezery.R
import beltran.angel.thecheezery.ui.theme.CheezeryPink
import beltran.angel.thecheezery.ui.theme.CheezeryPlum
import beltran.angel.thecheezery.ui.theme.TheCheezeryTheme

@Composable
fun WelcomeScreen(onGetStarted: () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.the_cheezery),
            contentDescription = "The Cheezery neon sign",
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .background(CheezeryPlum)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Welcome to The Cheezery",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Home of the most wonderful desserts ever seen (and tasted) by the human being.",
                color = Color.White,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = onGetStarted,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CheezeryPink,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Get started!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    TheCheezeryTheme {
        WelcomeScreen()
    }
}
