package unq.tpi.desapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unq.tpi.desapp.builders.ItemBuilder
import unq.tpi.desapp.builders.PurchaseBuilder
import unq.tpi.desapp.builders.StoreBuilder
import unq.tpi.desapp.dto.PurchaseDto
import unq.tpi.desapp.model.*
import unq.tpi.desapp.model.deliveryType.DeliveryType
import unq.tpi.desapp.repository.ProductRepository
import unq.tpi.desapp.repository.UserRepository
import java.util.*

@Service
@Transactional
class PurchaseService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    fun newPurchase(purchaseDto: PurchaseDto):Purchase{
        var anUser:Optional<User> = userRepository.findById(purchaseDto.user.id)
        var deliveryType: DeliveryType = purchaseDto.deliveryType
        var paymentMethod: PaymentMethod = purchaseDto.paymentMethod

        var purchase:Purchase = PurchaseBuilder.aPurchase().withUser(anUser.get()).withDeliveryType(deliveryType).withPaymentMethod(paymentMethod).build()
        addProductsToPurchase(purchase, purchaseDto.products)
        return purchase
    }

    private fun addProductsToPurchase(purchase: Purchase, products: MutableList<Product>){
        for(product in products){
            var store: Store = StoreBuilder.aStore().build()
            var item: Item = ItemBuilder.anItem().withAStore(store).withProduct(product).withQuantity(1.0).build()
            purchase.addItem(item)
        }

    }
}