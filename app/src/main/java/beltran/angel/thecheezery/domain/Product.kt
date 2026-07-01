package beltran.angel.thecheezery.domain

data class Product (
    val id: Int = 0,
    val name: String,
    val price: Float,
    val image: String? = null, //URL o nombre del recurso
    val description: String? = null

)