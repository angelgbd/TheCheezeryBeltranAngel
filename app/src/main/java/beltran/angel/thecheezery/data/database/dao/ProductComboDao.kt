package beltran.angel.thecheezery.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import beltran.angel.thecheezery.data.database.entity.ProductComboEntity

@Dao
interface ProductComboDao {
    @Insert
    suspend fun insert(link: ProductComboEntity): Long

    @Query("DELETE FROM ProductsCombo WHERE idCombo = :comboId AND idProduct = :productId")
    suspend fun removeLink(comboId: Int, productId: Int)
}
