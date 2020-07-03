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
        var store:String
){
    fun productDtoToProduct() = Product(
            id = 0,
            imageUrl = imageUrl,
            productName = name,
            price = price,
            brand =  brand
    )
}











































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































