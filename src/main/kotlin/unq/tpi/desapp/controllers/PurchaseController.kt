package unq.tpi.desapp.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.aspects.ExceptionAspect
import unq.tpi.desapp.aspects.LoggingAspect
import unq.tpi.desapp.dto.PurchaseDto
import unq.tpi.desapp.service.PurchaseService

@RestController
@RequestMapping("/purchase")

class PurchaseController {

    @Autowired
    private var purchaseService: PurchaseService = PurchaseService()

    @LoggingAspect
    @ExceptionAspect
    @PostMapping("/new")
    @CrossOrigin(origins = ["*"])
    fun newPurchase(@RequestBody purchaseDto: PurchaseDto):ResponseEntity<String> {


        var orderNumber = this.purchaseService.generatePurchase(purchaseDto)

        return ResponseEntity.ok().body(orderNumber)
    }

}