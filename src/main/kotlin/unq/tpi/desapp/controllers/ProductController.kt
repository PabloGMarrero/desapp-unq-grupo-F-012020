package unq.tpi.desapp.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.model.Product
import unq.tpi.desapp.service.ProductService
import org.springframework.http.ResponseEntity
import unq.tpi.desapp.aspects.ExceptionAspect
import unq.tpi.desapp.aspects.LoggingAspect
import unq.tpi.desapp.builders.ProductBuilder
import unq.tpi.desapp.dto.ProductDto
import java.util.*


@RestController
@RequestMapping("/products")
@CrossOrigin(origins = ["http://localhost:3000","http://localhost:8080", "https://buyingfromhome.herokuapp.com/"])

class ProductController{

    @Autowired
    private var productService: ProductService = ProductService()

    @LoggingAspect
    @GetMapping("/")
    fun getAllProducts():ResponseEntity<Iterable<ProductDto>> {
        var list:Iterable<ProductDto> = this.productService.findAll()
        if (list.toList().isNotEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(list)
        }else{
            return ResponseEntity.noContent().build()
        }
    }

    @LoggingAspect
    @GetMapping("/get")
    fun getProductById(@RequestParam("productId") productId:Long):ResponseEntity<Product>{
        var product:Optional<Product>  = this.productService.findById(productId)
        if(product.isPresent) {
            return ResponseEntity.status(HttpStatus.OK).body(product.get())
        }else{
            return ResponseEntity.notFound().build()
        }
    }

    @LoggingAspect
    @GetMapping("/get/name")
    fun getProductsByName(@RequestParam("productName") nameProduct:String):ResponseEntity<Iterable<Product>>{
        var products:Iterable<Product>  = this.productService.getByName(nameProduct)
        if(products.toList().isNotEmpty()) {
            return ResponseEntity.ok().body(products)
        }else{
            return ResponseEntity.notFound().build()
        }
    }

    @LoggingAspect
    @ExceptionAspect
    @PostMapping("/{idStore}/addProduct")
    fun addProduct(@PathVariable("idStore") idStore:Long,
                   @RequestBody aProductDto: ProductDto):ResponseEntity<Product>{

        var productSaved: Product = this.productService.addProduct(idStore, aProductDto)
        return ResponseEntity.ok().body(productSaved)

    }

    @LoggingAspect
    @ExceptionAspect
    @PutMapping("/update")
    fun updateProduct(@RequestParam("id") id: Long, @RequestBody product: ProductDto):ResponseEntity<Product>{
        return ResponseEntity.ok().body(this.productService.updateProduct(product))
    }

    @LoggingAspect
    @ExceptionAspect
    @DeleteMapping("/delete")
    fun deleteProduct(@RequestParam("id") id: Long):ResponseEntity<Product>{
        this.productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

}