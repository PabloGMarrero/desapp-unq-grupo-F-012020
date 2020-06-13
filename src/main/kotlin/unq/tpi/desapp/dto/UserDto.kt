package unq.tpi.desapp.dto

import com.fasterxml.jackson.annotation.JsonCreator

data class UserDto @JsonCreator constructor(
        var name:String,
        var email:String,
        var password:String
)