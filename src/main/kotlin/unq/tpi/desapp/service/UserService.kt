package unq.tpi.desapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unq.tpi.desapp.builders.UserBuilder
import unq.tpi.desapp.dto.UserDto
import unq.tpi.desapp.exceptions.InvalidEmailOrPasswordException
import unq.tpi.desapp.exceptions.UserAlreadyExistsException
import unq.tpi.desapp.exceptions.UserDoesntExistException
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
        var users:List<User> = this.repository.findAll()

        users = users.filter { anUser -> anUser.email == email }
        var user: User? = null

        if (users.isNotEmpty()){
            user = users.first()
        }
        var anUser: Optional<User> = Optional.ofNullable(user)
        return anUser
    }

    fun getUserByEmailAndPass(email:String, pass:String): User{
        var user:Optional<User> = this.repository.findByEmailAndPassword(email, pass)
        if (! user.isPresent) {
            throw InvalidEmailOrPasswordException("Invalid email or password.")
        }
        return user.get()
    }

    fun create(anUser: User): User {
        var user: User = this.repository.save(anUser)
        return user
    }

    fun update(anUser: User):User{
//        var userToBeSaved = UserBuilder.anUser().build()//this.findByID(anUser.id).get()

        /*userToBeSaved.historialPurchases.addAll(anUser.historialPurchases)
        userToBeSaved.password = anUser.password
        userToBeSaved.name = anUser.name
        userToBeSaved.purchaseRange = anUser.purchaseRange
        userToBeSaved.categoryPreferences.addAll(anUser.categoryPreferences)*/

        var userToBeSaved = this.findByEmail(anUser.email)

        if(userToBeSaved.isPresent){
            var userSaved = userToBeSaved.get()
            userSaved.name = anUser.name
            userSaved.password = anUser.password
            userSaved.email = anUser.email

            userSaved = repository.save(userSaved)
            return userSaved
        }else{
            throw UserDoesntExistException("The user does not exist.")
        }
    }



    fun registerUser(userDTO: UserDto): User {
        var userExist = this.findByEmail(userDTO.email)
        if (userExist.isPresent) {
            throw UserAlreadyExistsException("The user with the email '${userDTO.email}' already exist.")
        }
        var anUser = UserBuilder.anUser().
            withEmail(userDTO.email).withName(userDTO.name).withPass(userDTO.password).build()
        return this.create(anUser)
    }
}