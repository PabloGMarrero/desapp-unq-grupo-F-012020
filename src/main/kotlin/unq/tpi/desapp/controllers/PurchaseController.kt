package unq.tpi.desapp.controllers

import it.ozimov.springboot.mail.model.Email
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail
import it.ozimov.springboot.mail.service.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.aspects.ExceptionAspect
import unq.tpi.desapp.aspects.LoggingAspect
import unq.tpi.desapp.dto.PurchaseDto
import unq.tpi.desapp.model.Address
import unq.tpi.desapp.model.PaymentMethod
import unq.tpi.desapp.service.PurchaseService
import java.time.LocalDate
import java.time.LocalTime
import javax.mail.internet.InternetAddress


@RestController
@RequestMapping("/purchase")
@CrossOrigin(origins = ["http://localhost:3000","http://localhost:8080", "https://buyingfromhome.herokuapp.com/"])
class PurchaseController {

    @Autowired
    var purchaseService: PurchaseService = PurchaseService()

    @Autowired
    lateinit var emailService: EmailService

    @LoggingAspect
    @ExceptionAspect
    @PostMapping("/new")
    fun newPurchase(@RequestBody purchaseDto: PurchaseDto):ResponseEntity<String> {
        var purchaseSaved = this.purchaseService.newPurchase(purchaseDto)
        var orderNumber = this.purchaseService.getOrderNumber(
                purchaseDto.user.id, purchaseSaved.id, purchaseDto.deliveryType.dateOfTheDelivery() )

        this.sendConfirmationEmail(purchaseDto.user.name ,purchaseDto.user.email, purchaseSaved.getTotal(),
                purchaseSaved.paymentMethod, purchaseSaved.deliveryType.addressOfPickup(),
                purchaseSaved.deliveryType.dateOfTheDelivery(),
                purchaseSaved.deliveryType.hourOfTheDelivery())
        return ResponseEntity.ok().body(orderNumber)
    }

    private fun sendConfirmationEmail(name:String, email: String, total: Double, paymentMethod: PaymentMethod,
                                      addressOfPickup: Address, dateOfTheDelivery: LocalDate,
                                      hourOfTheDelivery: LocalTime) {
        val from = InternetAddress("buyfromhome.desapp@gmail.com", "Buy From Home")
        val to = InternetAddress(email, name)
        val toCollection = mutableListOf<InternetAddress>()
        toCollection.add(to)
        val body = "La compra se realizó exitosamente. " +
                "El total fue de ${total} con el método de pago ${paymentMethod}. La entrega será el ${dateOfTheDelivery} " +
                "a las ${hourOfTheDelivery} con dirección ${addressOfPickup.street}, ${addressOfPickup.number}, " +
                "${addressOfPickup.locality}. #QuedateEnCasa. "
        val email: Email = DefaultEmail.builder()
                .from(from)
                .to(toCollection)
                .subject("Compra exitosa")
                .body(body)
                .encoding("UTF-8").build()

        emailService.send(email)
    }
}