package unq.tpi.desapp.controllers

import com.fasterxml.jackson.annotation.JsonCreator
import unq.tpi.desapp.model.Product
import unq.tpi.desapp.model.Store

data class ProductListDto @JsonCreator constructor(
        var product:Product,
        var store: String

)