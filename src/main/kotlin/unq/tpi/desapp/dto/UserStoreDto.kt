package unq.tpi.desapp.dto

import com.fasterxml.jackson.annotation.JsonCreator

data class UserStoreDto @JsonCreator constructor(
        var userDto: UserDto,
        var storeDto: StoreDto
){
}