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
class ProductService{

    @Autowired
    lateinit var repository:ProductRepository
    lateinit var storeRepository: StoreRepository

    @Throws(StoreDoesntExistException::class)
    fun addProduct(idStore:Long, productDto: ProductDto): Product{
        storeRepository.findById(idStore).orElseThrow {
            throw StoreDoesntExistException("The store does not exist.")
        }

        var aProduct: Product = productDto.productDtoToProduct()
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

    fun findAll():Iterable<Product>{
        return this.repository.findAll()
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

}