package unq.tpi.desapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unq.tpi.desapp.model.Product
import unq.tpi.desapp.model.Store
import unq.tpi.desapp.repository.StoreRepository
import java.util.*


@Service
@Transactional
class StoreService {

      @Autowired
      lateinit var repository: StoreRepository

      fun findByID(id: Long): Optional<Store> {
            return repository.findById(id)
      }

      fun getAll() : MutableIterable<Store> {
            return repository.findAll()
      }

      fun save(aStore: Store): Store {
            return repository.save(aStore)
      }

      fun updateStore(aStore: Store):Store{
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

      fun getProductsInsideRange(latitud:Double, longitude:Double):Iterable<Product>{
            var stores:Iterable<Store>  = this.repository.findAll()
            stores = stores.filter { store -> store.isInsideRange(latitud, longitude)}
            var products:MutableList<Product> = mutableListOf()
            stores.forEach { store -> products.addAll(store.productList) }
            /*for (store in stores){
                  products =
            }*/

            return products
      }

      fun getStoresInsideRange(latitud:Double, longitude:Double):Iterable<Store>{
            var stores:Iterable<Store>  = this.repository.findAll()
            stores = stores.filter { store -> store.isInsideRange(latitud, longitude)}

            return stores
      }

}

