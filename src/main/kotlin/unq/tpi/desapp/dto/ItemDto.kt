package unq.tpi.desapp.dto

import com.fasterxml.jackson.annotation.JsonCreator

data class ItemDto @JsonCreator constructor(
//    var id:Long,
//    var quantity: Long,
//    var storeId:Long
        var brand:String,
        var id:Long,
        var imageUrl:String,
        var price:Double,
        var productName:String,
        var quantity: Long,
        var stock:Long
)