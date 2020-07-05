package unq.tpi.desapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unq.tpi.desapp.builders.ItemBuilder
import unq.tpi.desapp.builders.PurchaseBuilder
import unq.tpi.desapp.builders.StoreBuilder
import unq.tpi.desapp.dto.ItemDto
import unq.tpi.desapp.dto.PurchaseDto
import unq.tpi.desapp.exceptions.StoreDoesntExistException
import unq.tpi.desapp.exceptions.UserDoesntExistException
import unq.tpi.desapp.model.*
import unq.tpi.desapp.model.deliveryType.DeliveryType
import unq.tpi.desapp.model.deliveryType.HomeDelivery
import unq.tpi.desapp.repository.ProductRepository
import unq.tpi.desapp.repository.PurchaseRepository
import unq.tpi.desapp.repository.StoreRepository
import unq.tpi.desapp.repository.UserRepository
import java.util.*

@Service
@Transactional
class PurchaseService {

    @Autowired
    var userService: UserService = UserService()

    @Autowired
    var productService: ProductService = ProductService()

    @Autowired
    var storeService: StoreService = StoreService()

    @Autowired
    var itemService: ItemService = ItemService()

    @Autowired
    lateinit var purchaseRepository: PurchaseRepository

    @Throws(UserDoesntExistException::class)
    fun newPurchase(purchaseDto: PurchaseDto):Purchase{
        var anUser:Optional<User> = userService.findByID(purchaseDto.user.id)
        if (anUser.isPresent){
            var user = anUser.get()
            var deliveryType: HomeDelivery = purchaseDto.deliveryType
            var paymentMethod: PaymentMethod = purchaseDto.paymentMethod

            var purchase:Purchase = PurchaseBuilder.aPurchase().withUser(user).withDeliveryType(deliveryType).withPaymentMethod(paymentMethod).build()

            var purchasedSaved = purchaseRepository.save(purchase)
            addProductsToPurchase(user, purchasedSaved, purchaseDto.items)

            //guardarla al usuario con el repo
            user.addToHistorial(purchasedSaved)
            var userSaved = userService.update(user)

            return purchase

        }else{
            var id = purchaseDto.user.id
            throw UserDoesntExistException("The user with id '$id' does not exist.")
        }
    }

    /**
     *
     */
    @Throws(StoreDoesntExistException::class)
    private fun addProductsToPurchase(user: User, purchase: Purchase, items: MutableList<ItemDto>){
        for(anItem in items){
            var product = productService.findById(anItem.id).get()
            var store = StoreBuilder.aStore().build()
            var item: Item = ItemBuilder.anItem().withAStore(store).withProduct(product).withQuantity(anItem.quantity).build()
            var itemSaved = itemService.save(item)
            purchase.addItem(itemSaved)

//            var product = productService.findById(anItem.id).get()
//            var anStore: Optional<Store> = storeService.findByID(anItem.storeId)
//            if (anStore.isPresent){
//                var store = anStore.get()
//                var item: Item = ItemBuilder.anItem().withAStore(store).withProduct(product).withQuantity(anItem.quantity).build()
//                var itemSaved = itemService.save(item)
//                purchase.addItem(itemSaved)
//
//            }else{
//                var id = anItem.storeId
//                throw StoreDoesntExistException("the store with id '$id' doest not exist.")
//            }
        }


    }
}