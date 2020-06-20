package unq.tpi.desapp.controllers


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.dto.StoreDto
import unq.tpi.desapp.model.Address
import unq.tpi.desapp.model.GeographicMap
import unq.tpi.desapp.model.Product
import unq.tpi.desapp.model.Store
import unq.tpi.desapp.service.StoreService
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/stores")
@CrossOrigin(origins = ["*"])

class StoreController {

    @Autowired
    val storeService: StoreService = StoreService()

    @GetMapping("/")
    fun getAllStores(): ResponseEntity<Iterable<Store>> {

        var list:Iterable<Store> = this.storeService.getAll()

        if (list.toList().isNotEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(list)
        }else{
            return ResponseEntity.noContent().build()
        }
    }

    @GetMapping("get/{id}")
    fun getStore(@PathVariable("id") id: Long): ResponseEntity<Store> {
        var store: Optional<Store> =  storeService.findByID(id)
        if (store.isPresent){
            return ResponseEntity.status(HttpStatus.OK).body(store.get())
        }else{
            return ResponseEntity.noContent().build()
        }

    }

    @GetMapping("/get/name")
    fun getStoresByName(@RequestParam("storeName") nameStore:String):ResponseEntity<Iterable<Store>>{
        var stores:Iterable<Store>  = this.storeService.getByName(nameStore)
        if(stores.toList().isNotEmpty()) {
            return ResponseEntity.ok().body(stores)
        }else{
            return ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/addstore", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    fun addStore(@RequestBody storeDTO: StoreDto):ResponseEntity<Store>{

        var geoZone= GeographicMap(storeDTO.latitude, storeDTO.longitude)
        var anAddress= Address(storeDTO.locality, storeDTO.street, storeDTO.number, geoZone)
        var anStore= Store(12, storeDTO.activity, anAddress, storeDTO.covDistance, storeDTO.name)

        return ResponseEntity.ok().body(storeService.save(anStore))

    }

    @PostMapping("/add")
    fun createStore(@RequestBody aStore: @Valid Store): ResponseEntity<Store> {
        return ResponseEntity.ok().body(storeService.save(aStore))
    }

    @PutMapping("/add")
    fun updateStore(@RequestParam("id") id: Long, @RequestBody aStore: @Valid Store):ResponseEntity<Store> {
        return ResponseEntity.ok().body(this.storeService.updateStore(aStore))
    }

    @DeleteMapping("/delete")
    fun deletestore(@RequestParam("id") id: Long):ResponseEntity<Store>{
        try {
            this.storeService.deleteStore(id)
            return ResponseEntity.noContent().build()
        }catch(ex:Exception) {
            return ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/store")
    fun getStoreProductsInsideRange(@RequestParam("latitude") latitude:Double,
                         @RequestParam("longitude") longitude:Double):ResponseEntity<Iterable<Product>>{
       var products:List<Product> = this.storeService.getProductsInsideRange(latitude, longitude).toList()

        if(products.toList().isNotEmpty()) {
            return ResponseEntity.ok().body(products)
        }else{
            return ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/stores")
    fun getStoresInsideRange(@RequestParam("latitude") latitude:Double,
                                    @RequestParam("longitude") longitude:Double):ResponseEntity<Iterable<Store>>{
        var stores:List<Store> = this.storeService.getStoresInsideRange(latitude, longitude).toList()

        if(stores.toList().isNotEmpty()) {
            return ResponseEntity.ok().body(stores)
        }else{
            return ResponseEntity.notFound().build()
        }
    }

}



