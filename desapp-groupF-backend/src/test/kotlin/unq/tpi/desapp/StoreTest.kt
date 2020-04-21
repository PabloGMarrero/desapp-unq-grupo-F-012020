package unq.tpi.desapp

import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import unq.tpi.desapp.builders.ProductoBuilder
import unq.tpi.desapp.builders.StoreBuilder

@SpringBootTest
class StoreTest {

    @Test
    fun testDefault() {
        var store = StoreBuilder.aStore().build()
        assertEquals(store.activity, "")
        assertEquals(store.adress, "")
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
        assertEquals(store.adress, "Av. Monroe 1010")
    }

    @Test
    fun testStoreCoverageDistance(){
        var store = StoreBuilder.aStore().withDistance(100.00).build()
        assertEquals(store.coverageDistance, 100.00)
    }
}