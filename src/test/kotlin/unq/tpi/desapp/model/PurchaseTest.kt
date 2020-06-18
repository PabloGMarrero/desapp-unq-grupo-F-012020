package unq.tpi.desapp.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import unq.tpi.desapp.PaymentMethod
import unq.tpi.desapp.builders.*
import java.time.LocalDate

@SpringBootTest
class PurchaseTest {

    @Test
    fun testCreationPurchase(){

        var anUser = UserBuilder.anUser().build()
        var purchase = PurchaseBuilder.aPurchase().build()

        assertEquals(purchase.getItems().size, 0)
        assertEquals(purchase.getTotal(), 0.0)
        assertEquals(purchase.user, anUser)
        assertEquals(purchase.purchaseDate, LocalDate.now())

    }

    @Test
    fun testPurchaseAddItem(){
        var anItem = ItemBuilder.anItem().build()
        var purchase = PurchaseBuilder.aPurchase().build()
        purchase.addItem(anItem)
        assert(purchase.getItems().contains(anItem))
    }

    @Test
    fun testPurchaseGetTotalWithAProductWhichCost35_5AndQuantity1(){
        var aProduct = ProductBuilder.aProduct().withPrice(35.5).build()
        var anItem = ItemBuilder.anItem().withProduct(aProduct).withQuantity(1.0).build()
        var purchase = PurchaseBuilder.aPurchase().build()

        purchase.addItem(anItem)
        assertEquals(purchase.getTotal(), 35.5)
    }

    @Test
    fun testPurchaseGetTotalWithAProductWhichCost35_5AndQuantity1AndOtherProductCost50AndQuantity2(){
        var aProduct = ProductBuilder.aProduct().withPrice(35.5).build()
        var anotherProduct = ProductBuilder.aProduct().withPrice(50.0).build()
        var anItem = ItemBuilder.anItem().withProduct(aProduct).withQuantity(1.0).build()
        var anotherItem = ItemBuilder.anItem().withProduct(anotherProduct).withQuantity(2.0).build()
        var purchase = PurchaseBuilder.aPurchase().build()

        purchase.addItem(anItem)
        purchase.addItem(anotherItem)
        assertEquals(purchase.getTotal(), 135.5)
    }

    @Test
    fun testChangeDeliveryType(){
        var aDeliveryType = PickupInStoreBuilder.aPickupInStore().build()
        var purchase = PurchaseBuilder.aPurchase().withDeliveryType(aDeliveryType).build()
        var anotherDeliveryType = HomeDeliveryBuilder.aHomeDelivery().build()

        assertEquals(purchase.deliveryType, aDeliveryType)

        purchase.changeDeliveryType(anotherDeliveryType)

        assertEquals(purchase.deliveryType, anotherDeliveryType)
    }

    @Test
    fun testAddPaymentMethod(){
        var purchase = PurchaseBuilder.aPurchase().build()

        assertEquals(purchase.paymentMethod, PaymentMethod.CASH)

        purchase.changePaymentMethod(PaymentMethod.DEBIT)
        assertEquals(purchase.paymentMethod, PaymentMethod.DEBIT)
    }
}