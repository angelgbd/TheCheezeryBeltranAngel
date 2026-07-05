package beltran.angel.thecheezery.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import beltran.angel.thecheezery.data.CheezeryContract.ProductEntry

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_NAME = "cheezery.db"
        private const val DATABASE_VERSION = 2
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("PRAGMA foreign_keys = ON;")

        db.execSQL(
            """
                CREATE TABLE ${ProductEntry.TABLE_NAME}(
                ${ProductEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${ProductEntry.COLUMN_NAME} TEXT NOT NULL,
                ${ProductEntry.COLUMN_PRICE} REAL NOT NULL,
                ${ProductEntry.COLUMN_IMAGE} TEXT,
                ${ProductEntry.COLUMN_DESCRIPTION} TEXT,
                ${ProductEntry.COLUMN_TYPE} TEXT NOT NULL
                )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db.execSQL(
                """
                    ALTER TABLE ${ProductEntry.TABLE_NAME}
                    ADD COLUMN ${ProductEntry.COLUMN_TYPE} TEXT NOT NULL DEFAULT 'HOT_DRINKS'
                """.trimIndent()
            )
        }
    }
}
