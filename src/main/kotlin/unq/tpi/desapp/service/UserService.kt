package unq.tpi.desapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unq.tpi.desapp.builders.UserBuilder
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

    fun getAll() : MutableIterable<User> {
        return repository.findAll()
    }

    fun getUserOrders(id: Long):List<Purchase>{
        var user = this.findByID(id)
        var purchases:List<Purchase> = mutableListOf()

        if (user.isPresent){
            purchases = user.get().historialPurchases
        }

        return purchases
    }

    fun findByEmail(email: String):Optional<User>{
        //return this.repository.findByEmail(email)
        var users:List<User> = this.repository.findAll()

        users = users.filter { anUser -> anUser.email == email }
        var user:User? = null

        if (users.isNotEmpty()){
            user = users.first()
        }
        var anUser: Optional<User> = Optional.ofNullable(user)
        return anUser
    }

    fun getUserByEmailAndPass(email:String, pass:String): Optional<User>{
        var user:Optional<User> = this.repository.findByEmail(email)
        return user
    }

    fun create(anUser: User):User{
        var user:User = this.repository.save(anUser)
        return user
    }
}