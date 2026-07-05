package beltran.angel.thecheezery.data

import android.content.ContentValues
import android.database.Cursor
import beltran.angel.thecheezery.domain.Product
import beltran.angel.thecheezery.domain.ProductType

class ProductsDAO(private val dbHelper: DatabaseHelper) {

    fun insertProduct(product: Product): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(CheezeryContract.ProductEntry.COLUMN_NAME, product.name)
            put(CheezeryContract.ProductEntry.COLUMN_PRICE, product.price)
            put(CheezeryContract.ProductEntry.COLUMN_IMAGE, product.image)
            put(CheezeryContract.ProductEntry.COLUMN_DESCRIPTION, product.description)
            put(CheezeryContract.ProductEntry.COLUMN_TYPE, product.type.name)
        }
        return db.insert(CheezeryContract.ProductEntry.TABLE_NAME, null, values)
    }

    fun getAllProducts(): List<Product> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            CheezeryContract.ProductEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        return cursor.use { readProductsFromCursor(it) }
    }

    fun getProductById(productId: Int): Product? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            CheezeryContract.ProductEntry.TABLE_NAME,
            null,
            "${CheezeryContract.ProductEntry.COLUMN_ID} = ?",
            arrayOf(productId.toString()),
            null,
            null,
            null
        )
        return cursor.use {
            if (it.moveToFirst()) cursorToProduct(it) else null
        }
    }

    fun getProductsByType(type: ProductType): List<Product> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            CheezeryContract.ProductEntry.TABLE_NAME,
            null,
            "${CheezeryContract.ProductEntry.COLUMN_TYPE} = ?",
            arrayOf(type.name),
            null,
            null,
            null
        )
        return cursor.use { readProductsFromCursor(it) }
    }

    private fun readProductsFromCursor(cursor: Cursor): List<Product> {
        val products = mutableListOf<Product>()
        while (cursor.moveToNext()) {
            products.add(cursorToProduct(cursor))
        }
        return products
    }

    private fun cursorToProduct(cursor: Cursor): Product {
        val typeName = cursor.getString(
            cursor.getColumnIndexOrThrow(CheezeryContract.ProductEntry.COLUMN_TYPE)
        )
        return Product(
            id = cursor.getInt(cursor.getColumnIndexOrThrow(CheezeryContract.ProductEntry.COLUMN_ID)),
            name = cursor.getString(cursor.getColumnIndexOrThrow(CheezeryContract.ProductEntry.COLUMN_NAME)),
            price = cursor.getFloat(cursor.getColumnIndexOrThrow(CheezeryContract.ProductEntry.COLUMN_PRICE)),
            type = ProductType.valueOf(typeName),
            image = cursor.getString(cursor.getColumnIndexOrThrow(CheezeryContract.ProductEntry.COLUMN_IMAGE)),
            description = cursor.getString(cursor.getColumnIndexOrThrow(CheezeryContract.ProductEntry.COLUMN_DESCRIPTION))
        )
    }
}
