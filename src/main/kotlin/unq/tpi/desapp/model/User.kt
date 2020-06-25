package unq.tpi.desapp.model

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
    @Transient
    var historialPurchases: MutableList<Purchase> =  mutableListOf()
    @Transient
    var shoppingBag: MutableList<Item> = mutableListOf()

    class Categories(name: String) {
        var name: String = name
    }

    /**
     * Add a new item to the bag
     * @param aItem
     */
    fun addItemProduct(aItem: Item) {
        shoppingBag.add(aItem)
    }

    /**
     * Add the purchase to his own historial
     */
    fun addToHistorial(purchase: Purchase){
        historialPurchases.add(purchase)
    }

    /**
     * Changes the range of the purchase to find new stores in the map
     */
    fun changePurchaseRange(distancia: Double){
        this.purchaseRange = distancia
    }

    /**
     * Add a new category to his own preferences.
     */
    fun addCategory(aCategory: Categories){
        this.categoryPreferences.add(aCategory)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (name != other.name) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }

    /**
     * Convert User objecto to his DTO
     */
    fun toUserDTO()= UserDto(
            name = name,
            email = email,
            password =  "",
            id = id
    )


}

