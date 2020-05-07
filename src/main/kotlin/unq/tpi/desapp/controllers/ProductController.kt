package unq.tpi.desapp.controllers

import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.builders.ProductBuilder
import unq.tpi.desapp.model.Product
import unq.tpi.desapp.service.ProductRepository
import javax.annotation.PostConstruct

@RestController
@RequestMapping("/products")

class ProductController() {
    var productRepository:ProductRepository = ProductRepository()

    @PostConstruct
    fun initial(){
        var coke = ProductBuilder.aProduct().withBrand("Coca Cola").withName("Coca Cola Regular").withPrice(125.50).withId(1).build()
        var chocolate = ProductBuilder.aProduct().withBrand("Coffler").withName("chocolate 200grs").withPrice(190.5).withId(2).build()

        this.productRepository.saveProduct(coke, 1)
        this.productRepository.saveProduct(chocolate, 1)
    }

    @GetMapping("/")
    fun getProducts():MutableList<Product>{
        return this.productRepository.getAll()
    }

    @GetMapping("/get")
    fun getProductById(@RequestParam("productId") productId:Long):Product{
        return this.productRepository.getById(productId)
    }

    @PostMapping("/add")
    fun addProduct(@RequestBody aProduct: Product){
        this.productRepository.saveProduct(aProduct, 1)
    }

    @PostMapping("/add{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody product: Product){
        var product: Product = this.productRepository.getById(id)
        if ( product === null){
            //doesnt exist so must return an exception or whatever we want
        }else{
            this.productRepository.modifyProduct(product, 1)
        }
    }

    @DeleteMapping("delete/{id}")
    fun deleteProduct(@PathVariable id: Long){
        var storeId:Long = 100
        var product: Product = this.productRepository.getById(id)
        if (product != null){
            this.productRepository.deleteProduct(product, storeId)
        }else{
            //could return an exception saying that the product doesnt exist or whatever we want
        }
    }
}