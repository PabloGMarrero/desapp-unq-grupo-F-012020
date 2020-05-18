package unq.tpi.desapp.controllers


import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.service.StoreService

@RestController
@RequestMapping("/stores")

class StoreController() {
    private val storeService: StoreService? = null

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    @ResponseBody
    fun getStore(@PathVariable("id") id: String?): ResponseEntity<*>? {
        storeService?.findByID(id)
        return ResponseEntity.ok().build<Any>()
    }

}



