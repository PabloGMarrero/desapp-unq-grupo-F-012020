package unq.tpi.desapp.service

import unq.tpi.desapp.builders.ProductBuilder
import unq.tpi.desapp.model.Product
import java.util.*

class ProductRepository {

    var products:MutableList<Product>  = mutableListOf()

    fun getById(id:Long):Product{
        val product :Optional<Product> = products.stream().filter { prod -> prod.id ==id }.findFirst()

        if (product.isPresent) {
            return product.get()
        }
        return ProductBuilder.aProduct().build() //refactor, must return using the id not return a new Product
    }
    fun getAll():MutableList<Product>{
        return products
    }
    fun saveProduct(aProduct:Product, storeId:Long){
        products.add(aProduct)
    }

    fun deleteProduct(aProduct:Product, storeId:Long){
        var product :Optional<Product> = products.stream().filter { prod -> prod.id == aProduct.id }.findFirst()

        if (product.isPresent){
            products.remove(product.get())
        }
    }

    fun modifyProduct(aProduct:Product, storeId:Long){

    }


}