package unq.tpi.desapp.model

import org.joda.time.DateTime
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import unq.tpi.desapp.DiscountByCategory
import unq.tpi.desapp.OpenHours
import unq.tpi.desapp.PaymentMethod
import unq.tpi.desapp.Product
import unq.tpi.desapp.builders.*
import java.time.LocalDate

@SpringBootTest
class StoreTest {
    val aProduct = Product(1, "blabla", "Pepitos", 35.5, "Bagley")
    val aDiscount = DiscountByCategory(10.0, LocalDate.now(), LocalDate.MAX, "")
    val aPurchase = PurchaseBuilder.aPurchase().build() // Purchase(aUser, "Envio a Domicilio")

    @Test
    fun testStoreDefault() {
        var store = StoreBuilder.aStore().build()
        var address = AddressBuilder.anAddress().build()
        assertEquals(store.activity, "")
        assertEquals(store.address, address)
        assertEquals(store.coverageDistance, 0.0)
        assertEquals(store.storeName, "")
    }

    @Test
    fun testStoreWithActivityKiosko() {
        var store = StoreBuilder.aStore().withActivity("Kiosko").build()
        assertEquals(store.activity, "Kiosko")
    }

    @Test
    fun testStoreAdress() {
        var address = AddressBuilder.anAddress().withStreet("Av. Monroe").withNumber(1010).build()
        var store = StoreBuilder.aStore().withAdress(address).build()
        assertEquals(store.getAddressStreet(), "Av. Monroe")
        assertEquals(store.getAddressNumber(), 1010)
    }

    @Test
    fun testStoreCoverageDistance() {
        var store = StoreBuilder.aStore().withDistance(100.00).build()
        assertEquals(store.coverageDistance, 100.00)
    }

    @Test
    fun testStoreNameStore() {
        var store = StoreBuilder.aStore().withStoreName("Kiosco").build()
        assertEquals(store.storeName, "Kiosco")
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
        var address = AddressBuilder.anAddress().withStreet("test").build()
        var anotherStore = StoreBuilder.aStore().withAdress(address).build()

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

    @Test
    fun testStoreWihtCoverageDistance1IsInsideOfTheRangeOfUNQ(){

        var marianoMoreno = GeographicMapBuilder.aGeographicMap().withLatitude(-34.710895).withLongitude(-58.283421).build()

        var unq = GeographicMapBuilder.aGeographicMap().withLatitude(-34.706272).withLongitude(-58.278519).build()
        var storeAddress = AddressBuilder.anAddress().withZone(unq).build()
        var store = StoreBuilder.aStore().withDistance(1.0).withAdress(storeAddress).build()

        assertTrue(store.isInsideRange(marianoMoreno.latitude, marianoMoreno.longitude))
    }

    @Test
    fun testStoreWihtCoverageDistance0_5IsNotInsideOfTheRangeOfUNQ(){

        var marianoMoreno = GeographicMapBuilder.aGeographicMap().withLatitude(-34.710895).withLongitude(-58.283421).build()

        var unq = GeographicMapBuilder.aGeographicMap().withLatitude(-34.706272).withLongitude(-58.278519).build()
        var storeAddress = AddressBuilder.anAddress().withZone(unq).build()
        var store = StoreBuilder.aStore().withDistance(0.5).withAdress(storeAddress).build()

        assertFalse(store.isInsideRange(marianoMoreno.latitude, marianoMoreno.longitude))
    }
}