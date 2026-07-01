package beltran.angel.thecheezery.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import beltran.angel.thecheezery.data.CheezeryContract.ProductEntry
import beltran.angel.thecheezery.domain.Product

class DatabaseHelper(context: Context): SQLiteOpenHelper (
   context,
   DATABASE_NAME,
    null,
    DATABASE_VERSION
){

    companion object{
        private val DATABASE_NAME = "cheezery.db"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase){
        db.execSQL("PRAGMA foreign_keys = ON;")

        //tabla de productos
        db.execSQL(
            """
                CREATE TABLE ${
                CheezeryContract.ProductEntry.TABLE_NAME}(
                ${
                ProductEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${
                ProductEntry.COLUMN_NAME} TEXT NOT NULL,
                ${
                ProductEntry.COLUMN_PRICE} REAL NOT NULL,
                ${ProductEntry.COLUMN_IMAGE} TEXT,
                ${
                ProductEntry.COLUMN_DESCRIPTION} TEXT
                )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${ProductEntry.TABLE_NAME}")

        onCreate(db)
    }

}