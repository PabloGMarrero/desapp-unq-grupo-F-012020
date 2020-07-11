package unq.tpi.desapp.model

import unq.tpi.desapp.dto.ProductDto
import unq.tpi.desapp.exceptions.InvalidBrandProductException
import unq.tpi.desapp.exceptions.InvalidNameProductException
import unq.tpi.desapp.exceptions.InvalidProductPriceException
import java.io.Serializable
import javax.persistence.*

/**
 * Product represents the item of a purchase.
 *
 * @param id represents the unique ID of each product in the system.
 * @param imageUrl represents the url online of the product.
 * @param productName represents the name of the product
 * @param price represents the price of the product for each store
 * @param brand represents the name of the brand.
 */

@Entity
@Table(name="product")
class Product :Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column
    var imageUrl: String= ""

    @Column
    var productName: String = ""

    @Column
    var price: Double = 0.0

    @Column
    var brand: String = ""

    @Column
    var stock: Long= 0

    constructor()
    constructor(id:Long, imageUrl:String, productName:String, price:Double, brand:String){
        this.id = id
        this.imageUrl = imageUrl
        this.productName = productName
        this.price = price
        this.brand= brand
        this.stock= 0
    }

    /**
     *
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false

        return true
    }

    /**
     *
     */
    override fun hashCode(): Int {
        return id.hashCode()
    }

    /**
     *  Validate product and returns an exception if some validation fails
     *  @exception InvalidProductPriceException
     *  @exception InvalidBrandProductException
     *  @exception InvalidNameProductException
     */
    @Throws (InvalidBrandProductException::class, InvalidNameProductException::class, InvalidProductPriceException::class)
    fun validated() {
        if (this.brand.equals("")){
            throw InvalidBrandProductException("The brand can not be empty")
        }
        if(this.productName.equals("")){
            throw InvalidNameProductException("The product name can not be empty.")
        }
        if(this.price <= 0){
            throw InvalidProductPriceException("The product can be only possitive numbers")
        }
    }

    /**
     * Convert pojo to DTO
     */
    fun toProductDto()= ProductDto(
            name = productName,
            brand = brand,
            price = price,
            id = id,
            imageUrl = imageUrl,
            store = ""
    )

}

