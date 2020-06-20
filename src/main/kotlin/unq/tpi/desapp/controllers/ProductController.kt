package unq.tpi.desapp.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.model.Product
import unq.tpi.desapp.service.ProductService
import org.springframework.http.ResponseEntity
import java.util.*


@RestController
@RequestMapping("/products")
@CrossOrigin(origins = ["*"])

class ProductController{

    @Autowired
    var productService: ProductService = ProductService()

    @GetMapping("/")
    fun getAllProducts():ResponseEntity<Iterable<Product>> {
        var list:Iterable<Product> = this.productService.findAll()
        if (list.toList().isNotEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(list)
        }else{
            return ResponseEntity.noContent().build()
        }
    }

    @GetMapping("/get")
    fun getProductById(@RequestParam("productId") productId:Long):ResponseEntity<Product>{
        var product:Optional<Product>  = this.productService.findById(productId)
        if(product.isPresent) {
            return ResponseEntity.status(HttpStatus.OK).body(product.get())
        }else{
            return ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/get/name")
    fun getProductsByName(@RequestParam("productName") nameProduct:String):ResponseEntity<Iterable<Product>>{
        var products:Iterable<Product>  = this.productService.getByName(nameProduct)
        if(products.toList().isNotEmpty()) {
            return ResponseEntity.ok().body(products)
        }else{
            return ResponseEntity.notFound().build()
        }
    }


    @PostMapping("/add")
    fun addProduct(@RequestBody aProduct: Product):ResponseEntity<Product>{
        try {
            var productSaved: Product = this.productService.save(aProduct)
            return ResponseEntity.ok().body(productSaved)
        }catch(ex:Exception) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null)
        }

    }

    @PutMapping("/add")
    fun updateProduct(@RequestParam("id") id: Long, @RequestBody product: Product):ResponseEntity<Product>{
        return ResponseEntity.ok().body(this.productService.updateProduct(product))
    }

    @DeleteMapping("/delete")
    fun deleteProduct(@RequestParam("id") id: Long):ResponseEntity<Product>{
        try {
            this.productService.deleteProduct(id)
            return ResponseEntity.noContent().build()
        }catch(ex:Exception) {
            return ResponseEntity.notFound().build()
        }
    }

}