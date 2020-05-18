package unq.tpi.desapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unq.tpi.desapp.model.Store
import unq.tpi.desapp.repositories.StoreRepository


@Service
@Transactional
class StoreService {

      @Autowired
      var repository: StoreRepository? = null

      fun findByID(id: String?): Store? {
            return repository!!.findById(id)
      }

      fun getAll() : MutableIterable<Store?> {
            return repository!!.findAll()
      }

      fun save(aStore: Store): Store? {
            return repository!!.save(aStore)
      }
}

