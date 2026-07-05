package beltran.angel.thecheezery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import beltran.angel.thecheezery.domain.ProductType
import beltran.angel.thecheezery.ui.screens.AddProductScreen
import beltran.angel.thecheezery.ui.screens.MenuScreen
import beltran.angel.thecheezery.ui.screens.ShowProducts
import beltran.angel.thecheezery.ui.screens.WelcomeScreen
import beltran.angel.thecheezery.viewModel.ProductViewModel

@Composable
fun CheezeryNavHost(
    navController: NavHostController,
    viewModel: ProductViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.WELCOME
    ) {
        composable(Routes.WELCOME) {
            WelcomeScreen(
                onGetStarted = {
                    navController.navigate(Routes.MENU) {
                        popUpTo(Routes.WELCOME) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.MENU) {
            MenuScreen(
                onHotDrinksClick = {
                    navController.navigate(Routes.productsRoute(ProductType.HOT_DRINKS))
                },
                onColdDrinksClick = {
                    navController.navigate(Routes.productsRoute(ProductType.COLD_DRINKS))
                },
                onSaltiesClick = {
                    navController.navigate(Routes.productsRoute(ProductType.SALTIES))
                },
                onSweetsClick = {
                    navController.navigate(Routes.productsRoute(ProductType.SWEETS))
                },
                onCombosClick = {},
                onAddProductClick = {
                    navController.navigate(Routes.ADD_PRODUCT)
                }
            )
        }

        composable(
            route = Routes.PRODUCTS,
            arguments = listOf(
                navArgument("productType") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val typeName = backStackEntry.arguments?.getString("productType")
                ?: ProductType.HOT_DRINKS.name
            val productType = ProductType.valueOf(typeName)
            ShowProducts(
                productType = productType,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.ADD_PRODUCT) {
            AddProductScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
