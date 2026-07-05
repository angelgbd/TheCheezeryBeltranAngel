package beltran.angel.thecheezery.domain

data class Product(
    val id: Int = 0,
    val name: String,
    val price: Float,
    val type: ProductType,
    val image: String? = null,
    val description: String? = null,
)
