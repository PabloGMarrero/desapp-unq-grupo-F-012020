package unq.tpi.desapp.controllers


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.model.Store
import unq.tpi.desapp.service.StoreService
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/stores")

class StoreController {

    @Autowired
    val storeService: StoreService = StoreService()

    @GetMapping("/{id}")
    fun getStore(@PathVariable("id") id: Long): ResponseEntity<Store> {
        var store: Optional<Store> =  storeService.findByID(id)
        if (store.isPresent){
            return ResponseEntity.status(HttpStatus.OK).body(store.get())
        }else{
            return ResponseEntity.noContent().build()
        }

    }

    @GetMapping("/")
    fun getAllStores(): ResponseEntity<Iterable<Store>> {

        var list:Iterable<Store> = this.storeService.getAll()

        if (list.toList().isNotEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(list)
        }else{
            return ResponseEntity.noContent().build()
        }
    }

    @PostMapping("/store")
    fun createStore(@RequestBody aStore: @Valid Store): ResponseEntity<Store> {
        return ResponseEntity.ok().body(storeService.save(aStore))
    }

    @PutMapping("/store")
    fun updateStore(@RequestParam("id") id: Long, @RequestBody aStore: @Valid Store):ResponseEntity<Store> {
        return ResponseEntity.ok().body(this.storeService.updateStore(aStore))
    }

}



