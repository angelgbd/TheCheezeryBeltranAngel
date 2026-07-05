package beltran.angel.thecheezery.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import beltran.angel.thecheezery.data.repository.CheezeryRepository
import beltran.angel.thecheezery.domain.Product
import beltran.angel.thecheezery.domain.ProductType
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: CheezeryRepository,
    private val context: Context,
) : ViewModel() {

    var productsListState by mutableStateOf(listOf<Product>())

    init {
        viewModelScope.launch {
            getAllProducts()
        }
    }

    fun saveProduct(product: Product) {
        viewModelScope.launch {
            val newProductId = repository.insertProduct(product)
            if (newProductId != -1L) {
                Toast.makeText(context, "Producto guardado", Toast.LENGTH_SHORT).show()
                getAllProducts()
            } else {
                Toast.makeText(context, "Hubo un error al guardar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getAllProducts() {
        viewModelScope.launch {
            productsListState = repository.getAllProducts()
        }
    }

    fun getProductsByType(type: ProductType) {
        viewModelScope.launch {
            productsListState = repository.getProductsByType(type)
        }
    }
}
