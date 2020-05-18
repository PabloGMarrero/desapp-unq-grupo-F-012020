package unq.tpi.desapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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
}

