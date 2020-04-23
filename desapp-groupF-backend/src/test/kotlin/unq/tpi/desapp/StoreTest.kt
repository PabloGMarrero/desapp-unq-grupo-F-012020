package unq.tpi.desapp

import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import unq.tpi.desapp.builders.StoreBuilder
import java.time.LocalDate

@SpringBootTest
class StoreTest {
    val aProduct = Product(1, "blabla","Pepitos",35.5, "Bagley")
    val aDiscount = DiscountByCategory(10.0, LocalDate.now(), LocalDate.MAX, "" )
    val openH = Store.OpenHours("Jueves","09:00","19:00")
    val payment = Store.PaymentType("Banco Galicia", "1000 0000 0000 0000")
    val aUser = User("Pelufo","123","pelufo@pelufo")
    val aPurchase = Purchase(aUser,"Envio a Domicilio")

    @Test
    fun testStoreDefault() {
        var store = StoreBuilder.aStore().build()
        assertEquals(store.activity, "")
        assertEquals(store.address, "")
        assertEquals(store.coverageDistance, 0.0)
    }

    @Test
    fun testStoreWithActivityKiosko(){
        var store = StoreBuilder.aStore().withActivity("Kiosko").build()
        assertEquals(store.activity, "Kiosko")
    }

    @Test
    fun testStoreAdress(){
        var store = StoreBuilder.aStore().withAdress("Av. Monroe 1010").build()
        assertEquals(store.address, "Av. Monroe 1010")
    }

    @Test
    fun testStoreCoverageDistance(){
        var store = StoreBuilder.aStore().withDistance(100.00).build()
        assertEquals(store.coverageDistance, 100.00)
    }

    @Test
    fun testStoreAddPaymentType(){
        var store = StoreBuilder.aStore().build()
        store.addPaymentType(payment)
        assert(store.paymentTypes.contains(payment))
    }

    @Test
    fun testStoreAddOpenHours(){
        var store = StoreBuilder.aStore().build()
        store.addOpenhours(openH)
        assert(store.openingHours.contains(openH))
    }

    @Test
    fun testStoreAddProduct(){
        var store = StoreBuilder.aStore().build()
        store.addProduct(aProduct)
        assert(store.productList.contains(aProduct))
    }

    @Test
    fun testStoreAddDiscount(){
        var store = StoreBuilder.aStore().build()
        store.addDiscount(aDiscount)
        assert(store.discounts.contains(aDiscount))
    }

    @Test
    fun testStoreAddPurchase(){
        var store = StoreBuilder.aStore().build()
        store.addPurchase(aPurchase)
        assert(store.purchasesReceived.contains(aPurchase))
    }


}