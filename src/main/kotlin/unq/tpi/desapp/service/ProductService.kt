package unq.tpi.desapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unq.tpi.desapp.dto.ProductDto
import unq.tpi.desapp.exceptions.StoreDoesntExistException
import unq.tpi.desapp.model.Product
import unq.tpi.desapp.repository.ProductRepository
import unq.tpi.desapp.repository.StoreRepository
import java.util.*

@Service
@Transactional
class ProductService {

    @Autowired
    lateinit var repository:ProductRepository

    @Autowired
    lateinit var storeRepository: StoreRepository

    fun addProduct(idStore:Long, productDto: ProductDto): Product{
        var store = storeRepository.findById(idStore).orElseThrow {
            throw StoreDoesntExistException("The store does not exist.")
        }

        var aProduct: Product = productDto.productDtoToProduct()
        store.addProduct(aProduct)

        return this.repository.save(aProduct)
    }

    fun save(aProduct: ProductDto): Product {
        var productValidated = aProduct.productDtoToProduct()
        productValidated.validated()
        return this.repository.save(productValidated)
    }

    fun findById(id:Long):Optional<Product>{
        return this.repository.findById(id)
    }

    fun findAll():Iterable<ProductDto>{
        var products = this.repository.findAll()
        var productsDto = mutableListOf<ProductDto>()

        products.forEach { product -> productsDto.add(product.toProductDto()) }

        return productsDto
    }

    fun updateProduct(aProduct: ProductDto): Product {
        return this.save(aProduct)
    }

    fun deleteProduct(id: Long){
        this.repository.deleteById(id)
    }

    fun getByName(productName: String):Iterable<Product>{
        var listProducts: Iterable<Product> = this.repository.findAll()
        listProducts = listProducts.filter { product -> product.productName == productName }
        return listProducts
    }

    fun addProductsInBatch(idStore: Long, productList: List<ProductDto>): List<ProductDto>? {
        var store = storeRepository.findById(idStore).orElseThrow {
            throw StoreDoesntExistException("The store does not exist.")
        }

        for(productDto in productList){
            var aProduct: Product = productDto.productDtoToProduct()
            store.addProduct(aProduct)
            this.repository.save(aProduct)
        }

        return productList
    }

}