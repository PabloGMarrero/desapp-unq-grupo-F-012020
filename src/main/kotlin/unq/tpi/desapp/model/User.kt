package unq.tpi.desapp.model

import unq.tpi.desapp.builders.StoreBuilder
import unq.tpi.desapp.dto.UserDto
import javax.persistence.*
import kotlin.jvm.Transient

/**
 * Purchase represents the purchase of each user
 *
 * @param aName represents the name of the User.
 * @param aPassword represents the password
 * @param aMail represents the email of the user.
 */

@Entity
data class User(
        var name: String,
        var email: String,
        var password: String){
    constructor():this("", "", "")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0
    var purchaseRange: Double = 0.0

    @Transient
    var categoryPreferences: MutableList<Categories> =  mutableListOf()

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    var historialPurchases: MutableList<Purchase> =  mutableListOf()

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    var store:Store = StoreBuilder.aStore().build()

    class Categories(name: String) {
        var name: String = name
    }


    /**
     * Add the purchase to his own historial
     * @param purchase
     */
    fun addToHistorial(purchase: Purchase){
        historialPurchases.add(purchase)
    }

    /**
     * Changes the range of the purchase to find new stores in the map
     * @param distance
     */
    fun changePurchaseRange(distance: Double){
        this.purchaseRange = distance
    }

    /**
     * Add a new category to his own preferences.
     * @param aCategory
     */
    fun addCategory(aCategory: Categories){
        this.categoryPreferences.add(aCategory)
    }

    /**
     * 
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (name != other.name) return false
        if (email != other.email) return false

        return true
    }

    /**
     *
     */

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }

    /**
     *  Returns true is the user is admin
     */
    fun isAdmin():Boolean{
        return ! this.store.storeName.equals("")
    }

    /**
     * Convert User object to his DTO
     */
    fun toUserDTO()= UserDto(
            name = name,
            email = email,
            password =  "",
            id = id,
            isAdmin = isAdmin(),
            idStore = store.id
    )

    /**
     * Add the store to the admin
     */
    fun addStore(aStore: Store) {
        this.store = aStore
    }

}

