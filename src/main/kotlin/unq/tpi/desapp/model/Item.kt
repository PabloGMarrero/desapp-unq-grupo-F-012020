package unq.tpi.desapp.model


/**
 * Item represents the item of a purchase.
 *
 * @param product represents the product of the purchase.
 * @param quantity represents the amount of products.
 * @param store represents from what store is the product
 */

class Item(product: Product, quantity: Double, store: Store) {
    var product: Product = product
    var quantity: Double = quantity
    val store: Store = store
}
