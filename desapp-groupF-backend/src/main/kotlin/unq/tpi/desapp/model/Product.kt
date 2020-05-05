package unq.tpi.desapp.model

/**
 * Product represents the item of a purchase.
 *
 * @param id represents the unique ID of each product in the system.
 * @param imageUrl represents the url online of the product.
 * @param productName represents the name of the product
 * @param price represents the price of the product for each store
 * @param brand represents the name of the brand.
 */

class Product(id:Long, imageUrl:String, productName:String, price:Double, brand:String) {
    var id: Long = id
    var imageUrl:String = imageUrl
    var productName: String = productName
    var price: Double = price
    var brand: String = brand



}
