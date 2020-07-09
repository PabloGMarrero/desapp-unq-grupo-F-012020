package unq.tpi.desapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unq.tpi.desapp.builders.AddressBuilder
import unq.tpi.desapp.builders.GeographicMapBuilder
import unq.tpi.desapp.builders.StoreBuilder
import unq.tpi.desapp.dto.ProductListDto
import unq.tpi.desapp.dto.StoreDto
import unq.tpi.desapp.dto.UserDto
import unq.tpi.desapp.model.Product
import unq.tpi.desapp.model.Store
import unq.tpi.desapp.model.User
import unq.tpi.desapp.repository.StoreRepository
import java.util.*


@Service
@Transactional
class StoreService {

      @Autowired
      lateinit var repository: StoreRepository

      @Autowired
      var userService: UserService = UserService()

      fun findByID(id: Long): Optional<Store> {
            return repository.findById(id)
      }

      fun getAll() : MutableIterable<Store> {
            return repository.findAll()
      }

      fun save(aStore: Store): Store {
            return repository.save(aStore)
      }

      fun updateStore(aStore: Store): Store {
           return this.save(aStore)
      }

      fun deleteStore(id: Long){
            this.repository.deleteById(id)
      }

      fun getByName(storeName: String):Iterable<Store>{
            var listStores: Iterable<Store> = this.repository.findAll()
            listStores = listStores.filter { store -> store.storeName == storeName }
            return listStores
      }

      fun getAStorebyName(storeName: String): Store? {
            var listStores: Iterable<Store> = this.repository.findAll()
            var aStore = listStores.find { store -> store.storeName == storeName }

            return aStore

      }

      fun getProductsInsideRange(latitud:Double, longitude:Double):Iterable<ProductListDto>{
            var stores:Iterable<Store>  = this.repository.findAll()
            stores = stores.filter { store -> store.isInsideRange(latitud, longitude)}
            var products:MutableList<Product> = mutableListOf()
            var productList:MutableList<ProductListDto> = mutableListOf()

            stores.forEach { store ->
                  products = store.productList
                  products.forEach { prod ->
                        productList.add(
                                ProductListDto(
                                        product = prod,
                                        store = store)
                        )
                  }

            }


            return productList
          /*  var stores:Iterable<Store>  = this.repository.findAll()
            stores = stores.filter { store -> store.isInsideRange(latitud, longitude)}
            var products:MutableList<Product> = mutableListOf()
            stores.forEach { store -> products.addAll(store.productList) }
            /*for (store in stores){
                  products =
            }*/

            return products*/
      }

      fun getStoresInsideRange(latitud:Double, longitude:Double):Iterable<Store>{
            var stores:Iterable<Store>  = this.repository.findAll()
            stores = stores.filter { store -> store.isInsideRange(latitud, longitude)}

            return stores
      }

      fun create(aStore: Store): Store {
            var store: Store = this.repository.save(aStore)
            return store
      }

      fun addStore(userDto: UserDto, storeDTO: StoreDto): Store {
            var geoZone= GeographicMapBuilder.aGeographicMap()
                    .withLatitude(storeDTO.latitude).withLongitude(storeDTO.longitude).build()

            var anAddress= AddressBuilder.anAddress().withLocality(storeDTO.locality)
                    .withStreet(storeDTO.street).withNumber(storeDTO.number).withZone(geoZone).build()

            anAddress.validated()

            var aStore = StoreBuilder.aStore().withStoreName(storeDTO.name).
            withActivity(storeDTO.activity).withAdress(anAddress).withDistance(storeDTO.covDistance).build()

            aStore.validated()

            //save the store in the db
            aStore = this.create(aStore)

            //create the owner user to the store
            //var user:User = UserBuilder.anUser().withEmail(userDto.email).withPass(userDto.password).build()
            var user:User = userService.registerUser(userDto)

            //association between the user and the store
            user.addStore(aStore)

            return aStore
      }

}

