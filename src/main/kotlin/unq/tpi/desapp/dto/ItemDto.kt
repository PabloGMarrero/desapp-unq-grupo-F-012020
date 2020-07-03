package unq.tpi.desapp.dto

import com.fasterxml.jackson.annotation.JsonCreator

data class ItemDto @JsonCreator constructor(
    var id:Long,
    var quantity: Long,
    var storeId:Long
)