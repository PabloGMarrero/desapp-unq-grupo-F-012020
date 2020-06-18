package unq.tpi.desapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unq.tpi.desapp.Product
import unq.tpi.desapp.repository.ProductRepository
import java.util.*

@Service
@Transactional
class ProductService{

    @Autowired
    lateinit var repository:ProductRepository

    fun save(aProduct: Product): Product {
        return this.repository.save(aProduct)
    }

    fun findById(id:Long):Optional<Product>{
        return this.repository.findById(id)
    }

    fun findAll():Iterable<Product>{
        return this.repository.findAll()
    }

    fun updateProduct(aProduct: Product): Product {
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