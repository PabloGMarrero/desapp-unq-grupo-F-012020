package unq.tpi.desapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import unq.tpi.desapp.model.User
import java.util.*

/**
 * Class to manage the users
 */
@Repository
interface UserRepository: JpaRepository<User, Long> {
    /**
     * Query to find the user by email
     * @param email to find the user
     * @return an Optional<User> if the user does not exist
     */
    fun findByEmail(email:String): Optional<User>

}