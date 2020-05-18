package unq.tpi.desapp.controllers


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.service.StoreService

@RestController
@RequestMapping("/stores")

class StoreController {

    @Autowired
    val storeService: StoreService = StoreService()

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    @ResponseBody
    fun getStore(@PathVariable("id") id: Long): ResponseEntity<*>? {
        storeService?.findByID(id)
        return ResponseEntity.ok().build<Any>()
    }

}



