package unq.tpi.desapp.controllers


import it.ozimov.springboot.mail.service.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.aspects.ExceptionAspect
import unq.tpi.desapp.aspects.LoggingAspect
import unq.tpi.desapp.dto.PurchaseDto
import unq.tpi.desapp.service.PurchaseService

@RestController
@RequestMapping("/purchase")
//@CrossOrigin(origins = ["http://localhost:3000","http://localhost:8080", "https://buyingfromhome.herokuapp.com/"])

class PurchaseController {

    @Autowired
    private var purchaseService: PurchaseService = PurchaseService()

    @LoggingAspect
    @ExceptionAspect
    @PostMapping("/new")
    @CrossOrigin(origins = ["*"])
    fun newPurchase(@RequestBody purchaseDto: PurchaseDto):ResponseEntity<String> {
        var purchaseSaved = this.purchaseService.newPurchase(purchaseDto)
        var orderNumber = this.purchaseService.getOrderNumber(
                purchaseDto.user.id, purchaseSaved.id, purchaseDto.deliveryType.dateOfTheDelivery() )

        return ResponseEntity.ok().body(orderNumber)
    }

}