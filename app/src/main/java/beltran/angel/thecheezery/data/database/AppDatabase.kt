package beltran.angel.thecheezery.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import beltran.angel.thecheezery.data.database.converter.ProductTypeConverter
import beltran.angel.thecheezery.data.database.dao.ComboDao
import beltran.angel.thecheezery.data.database.dao.ProductComboDao
import beltran.angel.thecheezery.data.database.dao.ProductDao
import beltran.angel.thecheezery.data.database.entity.ComboEntity
import beltran.angel.thecheezery.data.database.entity.ProductComboEntity
import beltran.angel.thecheezery.data.database.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, ComboEntity::class, ProductComboEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(ProductTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun comboDao(): ComboDao
    abstract fun productComboDao(): ProductComboDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "cheezery.db"

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
