package beltran.angel.thecheezery.data.database.converter

import androidx.room.TypeConverter
import beltran.angel.thecheezery.domain.ProductType

class ProductTypeConverter {
    @TypeConverter
    fun fromProductType(type: ProductType): String = type.name

    @TypeConverter
    fun toProductType(value: String): ProductType = ProductType.valueOf(value)
}
