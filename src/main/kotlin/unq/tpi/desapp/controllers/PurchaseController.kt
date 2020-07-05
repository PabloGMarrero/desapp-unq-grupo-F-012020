package unq.tpi.desapp.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.aspects.ExceptionAspect
import unq.tpi.desapp.aspects.LoggingAspect
import unq.tpi.desapp.dto.PurchaseDto
import unq.tpi.desapp.model.Purchase
import unq.tpi.desapp.service.PurchaseService

@RestController
@RequestMapping("/purchase")
@CrossOrigin(origins = ["http://localhost:3000", "https://buyingfromhome.herokuapp.com/"])
class PurchaseController {

    @Autowired
    var purchaseService: PurchaseService = PurchaseService()

    @LoggingAspect
    @ExceptionAspect
    @PostMapping("/new")
    fun newPurchase(@RequestBody purchaseDto: PurchaseDto):ResponseEntity<Purchase> {
        var purchaseSaved = this.purchaseService.newPurchase(purchaseDto)
        return ResponseEntity.ok().body(purchaseSaved)
    }
}