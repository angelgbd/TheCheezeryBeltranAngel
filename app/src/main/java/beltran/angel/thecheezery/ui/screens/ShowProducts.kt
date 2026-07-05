package beltran.angel.thecheezery.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import beltran.angel.thecheezery.R
import beltran.angel.thecheezery.domain.Product
import beltran.angel.thecheezery.domain.ProductType
import beltran.angel.thecheezery.viewModel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowProducts(
    productType: ProductType,
    viewModel: ProductViewModel,
    onBack: () -> Unit,
) {
    LaunchedEffect(productType) {
        viewModel.getProductsByType(productType)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = productType.label) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (viewModel.productsListState.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(text = "No products found for ${productType.label}")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(viewModel.productsListState) { product ->
                    ProductItem(product)
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.dolar),
            contentDescription = "Product image"
        )
        Column(modifier = Modifier.fillMaxWidth(fraction = 0.7f)) {
            Text(text = product.name)
            Text(text = product.description.orEmpty())
        }
        Text(text = "${product.price}")
    }
}
