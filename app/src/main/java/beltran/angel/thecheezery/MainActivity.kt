package beltran.angel.thecheezery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import beltran.angel.thecheezery.navigation.CheezeryNavHost
import beltran.angel.thecheezery.ui.theme.TheCheezeryTheme
import beltran.angel.thecheezery.viewModel.ProductViewModel
import beltran.angel.thecheezery.viewModel.ProductViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheCheezeryTheme {
                val navController = rememberNavController()
                val viewModel: ProductViewModel = viewModel(
                    factory = ProductViewModelFactory(context = applicationContext)
                )
                CheezeryNavHost(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}
