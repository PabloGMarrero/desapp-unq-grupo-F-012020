package unq.tpi.desapp.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.model.Purchase
import unq.tpi.desapp.model.User
import unq.tpi.desapp.service.UserService
import java.util.*

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = ["*"])

class UserController {

    @Autowired
    val userService:UserService = UserService()

    @GetMapping("/get/")
    fun getUserById(@RequestParam("id") id:Long):ResponseEntity<User>{
        var user: Optional<User> =  userService.findByID(id)
        if (user.isPresent){
            return ResponseEntity.status(HttpStatus.OK).body(user.get())
        }else{
            return ResponseEntity.noContent().build()
        }
    }

    @GetMapping("/get/{id}/orders")
    fun getUserOrders(@PathVariable("id") id:Long):ResponseEntity<List<Purchase>>{
        var orders = userService.getUserOrders(id)

        if (! orders.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(orders)
        }else{
            return ResponseEntity.noContent().build()
        }

    }

}