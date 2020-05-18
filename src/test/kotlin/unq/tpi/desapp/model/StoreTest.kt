package unq.tpi.desapp.model

import org.joda.time.DateTime
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import unq.tpi.desapp.builders.ProductBuilder
import unq.tpi.desapp.builders.PurchaseBuilder
import unq.tpi.desapp.builders.StoreBuilder
import unq.tpi.desapp.builders.TurnBuilder
import unq.tpi.desapp.model.*
import java.time.LocalDate
import java.time.LocalTime

@SpringBootTest
class StoreTest {
    val aProduct = Product(1, "blabla", "Pepitos", 35.5, "Bagley")
    val aDiscount = DiscountByCategory(10.0, LocalDate.now(), LocalDate.MAX, "")
    val aPurchase = PurchaseBuilder.aPurchase().build() // Purchase(aUser, "Envio a Domicilio")

    @Test
    fun testStoreDefault() {
        var store = StoreBuilder.aStore().build()
        assertEquals(store.activity, "")
        assertEquals(store.address, "")
        assertEquals(store.coverageDistance, 0.0)
    }

    @Test
    fun testStoreWithActivityKiosko() {
        var store = StoreBuilder.aStore().withActivity("Kiosko").build()
        assertEquals(store.activity, "Kiosko")
    }

    @Test
    fun testStoreAdress() {
        var store = StoreBuilder.aStore().withAdress("Av. Monroe 1010").build()
        assertEquals(store.address, "Av. Monroe 1010")
    }

    @Test
    fun testStoreCoverageDistance() {
        var store = StoreBuilder.aStore().withDistance(100.00).build()
        assertEquals(store.coverageDistance, 100.00)
    }

    @Test
    fun testStoreAddPaymentType() {
        var store = StoreBuilder.aStore().build()
        var payment = PaymentMethod.CASH

        store.addPaymentMethod(payment)
        assert(store.paymentsMethods.contains(payment))
    }

    @Test
    fun testDeletePaymentType() {
        var store = StoreBuilder.aStore().build()
        var payment = PaymentMethod.DEBIT

        assertEquals(store.paymentsMethods.size, 1)
        assertTrue(store.paymentsMethods.contains(PaymentMethod.CASH))

        store.addPaymentMethod(payment)
        assertEquals(store.paymentsMethods.size, 2)
        assertTrue(store.paymentsMethods.contains(PaymentMethod.CASH))
        assertTrue(store.paymentsMethods.contains(payment))

        store.deletePaymentMethod(payment)
        assertTrue(store.paymentsMethods.contains(PaymentMethod.CASH))
        assertEquals(store.paymentsMethods.size, 1)
    }

    @Test
    fun testStoreAddOpenHours() {
        var store = StoreBuilder.aStore().build()
        var openH = OpenHours("Jueves", DateTime().withHourOfDay(9).withMinuteOfHour(30),
                DateTime().withHourOfDay(13).withMinuteOfHour(30))
        store.addOpenhours(openH)
        assert(store.openingHours.contains(openH))
    }

    @Test
    fun testStoreAddProduct() {
        var store = StoreBuilder.aStore().build()
        store.addProduct(aProduct)
        assert(store.productList.contains(aProduct))
    }

    @Test
    fun testStoreAddDiscount() {
        var store = StoreBuilder.aStore().build()
        store.addDiscount(aDiscount)
        assert(store.discounts.contains(aDiscount))
    }

    @Test
    fun testStoreAddPurchase() {
        var store = StoreBuilder.aStore().build()
        store.addPurchase(aPurchase)
        assert(store.purchasesReceived.contains(aPurchase))
    }

    @Test
    fun testChangeCoverageDistanceFrom100to200() {
        var store = StoreBuilder.aStore().withDistance(100.0).build()
        assertEquals(store.coverageDistance, 100.00)

        store.changeCoverageDistance(200.0)
        assertEquals(store.coverageDistance, 200.0)
    }

    @Test
    fun testVerifyIfStoreHasProductAsStock() {
        var store = StoreBuilder.aStore().withDistance(100.0).build()
        var product = ProductBuilder.aProduct().build()

        assertFalse(store.hasProductAsStock(product))
        store.addProduct(product)

        assertTrue(store.hasProductAsStock(product))
    }

    @Test
    fun testEqualsAndHashCodeMethods() {
        var store = StoreBuilder.aStore().build()

        assertEquals(store, store)
        assertEquals(store.hashCode(), store.hashCode())
    }

    @Test
    fun testStoreWithDifferentAddress() {
        var store = StoreBuilder.aStore().build()
        var anotherStore = StoreBuilder.aStore().withAdress("test").build()

        assertNotEquals(store, anotherStore)
    }

    @Test
    fun testStoreRecentlyCreatedListOfTurnIsempty() {
        var store = StoreBuilder.aStore().build()

        assertTrue(store.listOfTurns.isEmpty())
    }

    @Test
    fun testStoreRecentlyCreatedAddATurnAndNowSizeIs1() {
        var store = StoreBuilder.aStore().build()
        var turn = TurnBuilder.aTurn().build()

        assertTrue(store.listOfTurns.isEmpty())
        store.addTurn(turn)
        assertEquals(store.listOfTurns.size, 1)
        assertTrue(store.listOfTurns.contains(turn))
    }

    @Test
    fun testStoreRecentlyCreatedAddATurnAndThenDeleteItSizeIs0() {
        var store = StoreBuilder.aStore().build()
        var turn = TurnBuilder.aTurn().build()

        assertTrue(store.listOfTurns.isEmpty())
        store.addTurn(turn)
        assertEquals(store.listOfTurns.size, 1)
        store.deleteTurn(turn)
        assertEquals(store.listOfTurns.size, 0)
    }
}