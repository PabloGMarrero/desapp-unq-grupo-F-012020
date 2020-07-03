package unq.tpi.desapp.model

import unq.tpi.desapp.builders.ProductBuilder
import unq.tpi.desapp.builders.StoreBuilder
import javax.persistence.*
import kotlin.jvm.Transient


/**
 * Item represents the item of a purchase.
 *
 * @param product represents the product of the purchase.
 * @param quantity represents the amount of products.
 * @param store represents from what store is the product
 */

@Entity
@Table(name = "item")
class Item {
    constructor()
    constructor(product: Product, quantity: Long, store: Store){
        this.product = product
        this.quantity = quantity
//        this.store = store
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0

    @OneToOne(cascade = [CascadeType.ALL])
    var product: Product = ProductBuilder.aProduct().build()
    @Column
    var quantity: Long = 0

//    @ManyToMany(cascade = [CascadeType.ALL])
//    var store: Store = StoreBuilder.aStore().build()

}
