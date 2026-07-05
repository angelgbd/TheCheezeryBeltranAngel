package beltran.angel.thecheezery.data.database.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import beltran.angel.thecheezery.data.database.entity.ComboEntity
import beltran.angel.thecheezery.data.database.entity.ProductComboEntity
import beltran.angel.thecheezery.data.database.entity.ProductEntity

data class ComboWithProducts(
    @Embedded val combo: ComboEntity,
    @Relation(
        parentColumn = "idCombo",
        entityColumn = "idProduct",
        associateBy = Junction(
            value = ProductComboEntity::class,
            parentColumn = "idCombo",
            entityColumn = "idProduct"
        )
    )
    val products: List<ProductEntity>
)
