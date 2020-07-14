package unq.tpi.desapp.service

import it.ozimov.springboot.mail.model.Email
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail
import it.ozimov.springboot.mail.service.EmailService
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
import java.time.LocalDate
import java.util.*
import javax.mail.internet.InternetAddress

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

    @Autowired
    private lateinit var emailService: EmailService

    @Throws(UserDoesntExistException::class)
    fun newPurchase(purchaseDto: PurchaseDto):Purchase{
        var anUser:Optional<User> = userService.findByID(purchaseDto.user.id)
        if (anUser.isPresent){
            var user = anUser.get()
            var deliveryType: DeliveryType = purchaseDto.deliveryType
            var paymentMethod: PaymentMethod = purchaseDto.paymentMethod

            var purchase:Purchase = PurchaseBuilder.aPurchase().withUser(user).withDeliveryType(deliveryType).withPaymentMethod(paymentMethod).build()

            var purchasedSaved = purchaseRepository.save(purchase)
            addProductsToPurchase(user, purchasedSaved, purchaseDto.items)

            //guardarla al usuario con el repo
            user.addToHistorial(purchasedSaved)
            var userSaved = userService.update(user)


            this.sendConfirmationEmail(purchaseDto.user.name ,purchaseDto.user.email, purchase.getTotal(),
                    purchase.paymentMethod,  purchase.deliveryType)

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
        }


    }

    fun getOrderNumber(userId: Long, purchaseId: Long, dateOfTheDelivery: LocalDate): String {
        return "#" + userId.toString() + purchaseId.toString() + dateOfTheDelivery.dayOfWeek.value.toString() + dateOfTheDelivery.dayOfYear.toString()
    }

    private fun sendConfirmationEmail(name:String, email: String, total: Double, paymentMethod: PaymentMethod,
                                      deliveryType: DeliveryType) {
        val from = InternetAddress("buyfromhome.desapp@gmail.com", "Buy From Home")
        val to = InternetAddress(email, name)
        val toCollection = mutableListOf<InternetAddress>()
        toCollection.add(to)
        val body = "La compra se realizó exitosamente. El total fue de ${total} con el método de pago ${paymentMethod}. "+
                this.getDeliveryTypeText(deliveryType)

        val email: Email = DefaultEmail.builder()
                .from(from)
                .to(toCollection)
                .subject("Compra exitosa")
                .body(body)
                .encoding("UTF-8").build()

        emailService.send(email)
    }

    private fun getDeliveryTypeText(deliveryType: DeliveryType):String{
        var text = ""
        if (deliveryType.javaClass == HomeDelivery::class){
            text = "El comercio se estará contactando para coordinar la entrega el."
        }else{
            text = "Tu pedido será enviado el."
        }

        text = text +" ${deliveryType.dateOfTheDelivery()} " +
                "a las ${deliveryType.hourOfTheDelivery()} con dirección ${deliveryType.addressOfPickup().street}, " +
                "${deliveryType.addressOfPickup().number}, ${deliveryType.addressOfPickup().locality}."

        return text
    }
}