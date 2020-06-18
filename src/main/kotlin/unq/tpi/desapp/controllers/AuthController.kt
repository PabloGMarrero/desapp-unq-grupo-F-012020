package unq.tpi.desapp.controllers

import com.fasterxml.jackson.module.kotlin.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.builders.UserBuilder
import unq.tpi.desapp.dto.UserDto
import unq.tpi.desapp.User
import unq.tpi.desapp.service.UserService
import java.net.URI
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/authusers")
@CrossOrigin(origins = ["*"])
class AuthController {

    @Autowired
    val userService: UserService = UserService()

    @PostMapping("/signin", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    fun login(@RequestBody userDTO: UserDto):ResponseEntity<UserDto>{
        var anUser= userService.getUserByEmailAndPass(userDTO.email, userDTO.password)
        if (anUser.isPresent){
            var anUserDto:UserDto = anUser.get().toUserDTO()
            return ResponseEntity.ok().body(anUserDto)
        }else{
            return ResponseEntity.notFound().build()
        }

    }

    @PostMapping("/signup", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    fun register(@RequestBody userDTO: UserDto):ResponseEntity<UserDto>{
        var userExist = userService.findByEmail(userDTO.email)
        if (! userExist.isPresent) {
            var anUser = UserBuilder.anUser().
            withEmail(userDTO.email).withName(userDTO.name).withPass(userDTO.password).build()
            var anUserDTO = userService.create(anUser).toUserDTO()
            return ResponseEntity.ok().body(anUserDTO)
        }

        //TODO ver de devolver esto o una exception
        return ResponseEntity.notFound().build()
    }
}