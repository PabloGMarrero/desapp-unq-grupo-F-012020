package unq.tpi.desapp.dto

import com.fasterxml.jackson.annotation.JsonCreator
import unq.tpi.desapp.model.Product

/**
 *
 */
data class ProductDto @JsonCreator constructor(
        var name:String,
        var brand:String,
        var imageUrl:String,
        var price:Double,
        var store:String,
        var id:Long
){
    fun productDtoToProduct() = Product(
            id = id,
            imageUrl = imageUrl,
            productName = name,
            price = price,
            brand =  brand
    )
}











































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































