package unq.tpi.desapp.dto

import com.fasterxml.jackson.annotation.JsonCreator
import unq.tpi.desapp.model.User

data class UserDto @JsonCreator constructor(
        var name:String,
        var email:String,
        var password:String,
        var id:Long
){
        /**
         * Convert dto to User
         */
        fun userDtoToUser() = User(
            name = name,
            email = email,
            password = password
        )
}