package unq.tpi.desapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unq.tpi.desapp.model.Purchase
import unq.tpi.desapp.model.User
import unq.tpi.desapp.repository.UserRepository
import java.util.*

@Service
@Transactional
class UserService {

    @Autowired
    lateinit var repository: UserRepository

    fun findByID(id: Long): Optional<User> {
        return repository.findById(id)
    }
    fun getUserOrders(id: Long):List<Purchase>{
        var user = this.findByID(id)
        var purchases:List<Purchase> = mutableListOf()

        if (user.isPresent){
            purchases = user.get().historialPurchases
        }

        return purchases
    }
}