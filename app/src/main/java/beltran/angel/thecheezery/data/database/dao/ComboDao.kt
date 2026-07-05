package beltran.angel.thecheezery.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import beltran.angel.thecheezery.data.database.entity.ComboEntity
import beltran.angel.thecheezery.data.database.relation.ComboWithProducts

@Dao
interface ComboDao {
    @Insert
    suspend fun insert(combo: ComboEntity): Long

    @Query("SELECT * FROM Combos")
    suspend fun getAll(): List<ComboEntity>

    @Query("SELECT * FROM Combos WHERE idCombo = :id")
    suspend fun getById(id: Int): ComboEntity?

    @Transaction
    @Query("SELECT * FROM Combos")
    suspend fun getCombosWithProducts(): List<ComboWithProducts>
}
