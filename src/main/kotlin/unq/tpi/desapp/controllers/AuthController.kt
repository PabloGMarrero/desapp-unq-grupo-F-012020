package unq.tpi.desapp.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import unq.tpi.desapp.aspects.ExceptionAspect
import unq.tpi.desapp.aspects.LoggingAspect
import unq.tpi.desapp.builders.UserBuilder
import unq.tpi.desapp.dto.UserDto
import unq.tpi.desapp.exceptions.InvalidEmailOrPasswordException
import unq.tpi.desapp.exceptions.UserAlreadyExistsException
import unq.tpi.desapp.model.User
import unq.tpi.desapp.service.UserService

@RestController
@RequestMapping("/authusers")
@CrossOrigin(origins = ["*"])

class AuthController {

    @Autowired
    private val userService: UserService = UserService()

    @LoggingAspect
    @ExceptionAspect
    @PostMapping("/signin", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    fun login(@RequestBody userDTO: UserDto):ResponseEntity<UserDto>{
        var anUser= userService.getUserByEmailAndPass(userDTO.email, userDTO.password)
        return ResponseEntity.ok().body(anUser.toUserDTO())
    }

    @LoggingAspect
    @ExceptionAspect
    @PostMapping("/signup", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    fun register(@RequestBody userDTO: UserDto):ResponseEntity<UserDto>{
        var user:User = userService.registerUser(userDTO)
        return ResponseEntity.ok().body(user.toUserDTO())
    }

    @LoggingAspect
    @ExceptionAspect
    @PostMapping("/authsignin")
    fun authsignin(@RequestBody userDTO: UserDto): ResponseEntity<UserDto>{
        var user:User = userService.authSignin(userDTO)
        return ResponseEntity.ok().body(user.toUserDTO())
    }

}