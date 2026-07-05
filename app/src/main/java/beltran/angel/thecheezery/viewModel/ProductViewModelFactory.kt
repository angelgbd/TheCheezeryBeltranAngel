package beltran.angel.thecheezery.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import beltran.angel.thecheezery.data.database.AppDatabase
import beltran.angel.thecheezery.data.repository.CheezeryRepository

class ProductViewModelFactory(
    private val context: Context,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            val database = AppDatabase.getInstance(context.applicationContext)
            val repository = CheezeryRepository(database)
            return ProductViewModel(repository, context.applicationContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
