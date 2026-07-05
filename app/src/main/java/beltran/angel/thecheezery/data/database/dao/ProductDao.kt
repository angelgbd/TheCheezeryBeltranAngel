package beltran.angel.thecheezery.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import beltran.angel.thecheezery.data.database.entity.ProductEntity

@Dao
interface ProductDao {
    @Insert
    suspend fun insert(product: ProductEntity): Long

    @Query("SELECT * FROM Products")
    suspend fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM Products WHERE idProduct = :id")
    suspend fun getById(id: Int): ProductEntity?

    @Query("SELECT * FROM Products WHERE typeProduct = :type")
    suspend fun getByType(type: String): List<ProductEntity>
}
