package unq.tpi.desapp.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.aspects.LoggingAspect
import unq.tpi.desapp.dto.UserDto
import unq.tpi.desapp.model.Purchase
import unq.tpi.desapp.model.User
import unq.tpi.desapp.service.UserService
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = ["*"])
class UserController {

    @Autowired
    private val userService:UserService = UserService()

    @LoggingAspect
    @GetMapping("/")
    fun getAllUsers(): ResponseEntity<Iterable<User>> {
        var list:Iterable<User> = this.userService.getAll()

        if (list.toList().isNotEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(list)
        }else{
            return ResponseEntity.noContent().build()
        }
    }

    @LoggingAspect
    @GetMapping("/get/")
    fun getUserById(@RequestParam("userid") id:Long):ResponseEntity<UserDto>{
        var user: Optional<User> =  userService.findByID(id)
        if (user.isPresent){
            return ResponseEntity.status(HttpStatus.OK).body(user.get().toUserDTO())
        }else{
            return ResponseEntity.noContent().build()
        }
    }

    @LoggingAspect
    @GetMapping("/get/{id}/orders")
    fun getUserOrders(@PathVariable("id") id:Long):ResponseEntity<List<Purchase>>{
        var orders = userService.getUserOrders(id)

        if (orders.isNotEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(orders)
        }else{
            return ResponseEntity.noContent().build()
        }

    }

    @LoggingAspect
    @PutMapping("/updateuser")
    fun updateUser(@RequestBody userDTO: @Valid UserDto):ResponseEntity<User> {

        var anUser:User = userService.update(userDTO.userDtoToUser())

        return ResponseEntity.ok().body(anUser)
    }

}