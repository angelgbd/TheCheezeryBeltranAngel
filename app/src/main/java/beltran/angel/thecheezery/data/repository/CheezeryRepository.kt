package beltran.angel.thecheezery.data.repository

import beltran.angel.thecheezery.data.database.AppDatabase
import beltran.angel.thecheezery.data.database.entity.ComboEntity
import beltran.angel.thecheezery.data.database.entity.ProductComboEntity
import beltran.angel.thecheezery.data.database.entity.ProductEntity
import beltran.angel.thecheezery.data.database.relation.ComboWithProducts
import beltran.angel.thecheezery.domain.Combo
import beltran.angel.thecheezery.domain.Product
import beltran.angel.thecheezery.domain.ProductType

class CheezeryRepository(private val database: AppDatabase) {

    private val productDao = database.productDao()
    private val comboDao = database.comboDao()
    private val productComboDao = database.productComboDao()

    suspend fun insertProduct(product: Product): Long {
        return productDao.insert(product.toEntity())
    }

    suspend fun getAllProducts(): List<Product> {
        return productDao.getAll().map { it.toDomain() }
    }

    suspend fun getProductById(id: Int): Product? {
        return productDao.getById(id)?.toDomain()
    }

    suspend fun getProductsByType(type: ProductType): List<Product> {
        return productDao.getByType(type.name).map { it.toDomain() }
    }

    suspend fun insertCombo(combo: Combo): Long {
        return comboDao.insert(combo.toEntity())
    }

    suspend fun getCombosWithProducts(): List<ComboWithProducts> {
        return comboDao.getCombosWithProducts()
    }

    suspend fun linkProductToCombo(productId: Int, comboId: Int): Long {
        return productComboDao.insert(
            ProductComboEntity(
                productId = productId,
                comboId = comboId
            )
        )
    }

    private fun ProductEntity.toDomain(): Product {
        return Product(
            id = id,
            name = name,
            price = price,
            type = type,
            image = image,
            description = description
        )
    }

    private fun Product.toEntity(): ProductEntity {
        return ProductEntity(
            id = id,
            name = name,
            price = price,
            type = type,
            image = image,
            description = description
        )
    }

    private fun ComboEntity.toDomain(): Combo {
        return Combo(
            id = id,
            name = name,
            price = price
        )
    }

    private fun Combo.toEntity(): ComboEntity {
        return ComboEntity(
            id = id,
            name = name,
            price = price
        )
    }
}
