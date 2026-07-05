package beltran.angel.thecheezery.navigation

import beltran.angel.thecheezery.domain.ProductType

object Routes {
    const val WELCOME = "welcome"
    const val MENU = "menu"
    const val PRODUCTS = "products/{productType}"
    const val ADD_PRODUCT = "add_product"

    fun productsRoute(type: ProductType) = "products/${type.name}"
}
