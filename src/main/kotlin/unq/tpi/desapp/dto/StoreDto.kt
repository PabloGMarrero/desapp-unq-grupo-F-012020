package unq.tpi.desapp.dto

import com.fasterxml.jackson.annotation.JsonCreator

data class StoreDto @JsonCreator constructor(
        var name:String,
        var activity:String,
        var street:String,
        var number:Long,
        var locality:String,
        var latitude:Double,
        var longitude:Double,
        var covDistance:Double
)