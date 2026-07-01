package beltran.angel.thecheezery.data

import android.content.ContentValues
import beltran.angel.thecheezery.domain.Product
import kotlin.apply

class ProductsDAO(private val dbHelper: DatabaseHelper){

    fun insertProduct(product: Product){
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply{
            put(CheezeryContract.ProductEntry.COLUMN_NAME, product.name)
            put(CheezeryContract.ProductEntry.COLUMN_PRICE, product.price)
            put(CheezeryContract.ProductEntry.COLUMN_IMAGE, product.image)
            put(CheezeryContract.ProductEntry.COLUMN_DESCRIPTION, product.description)
        }
        db.insert(CheezeryContract.ProductEntry.TABLE_NAME, null, values)
    }

    fun getAllProducts(): List<Product>{

    }

    fun getProductById(productId: Int): Product?{

    }
}