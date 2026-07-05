package beltran.angel.thecheezery.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import beltran.angel.thecheezery.data.DatabaseHelper
import beltran.angel.thecheezery.data.ProductsDAO

class ProductViewModelFactory(
    private val context: Context,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            val dbHelper = DatabaseHelper(context.applicationContext)
            val dao = ProductsDAO(dbHelper)
            return ProductViewModel(dao, context.applicationContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
