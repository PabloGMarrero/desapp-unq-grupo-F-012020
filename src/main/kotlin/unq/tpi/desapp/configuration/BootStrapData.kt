package unq.tpi.desapp.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import unq.tpi.desapp.builders.AddressBuilder
import unq.tpi.desapp.builders.GeographicMapBuilder
import unq.tpi.desapp.builders.ProductBuilder
import unq.tpi.desapp.builders.StoreBuilder
import unq.tpi.desapp.repository.ProductRepository
import unq.tpi.desapp.repository.StoreRepository

@Component
class BootStrapData(storeRepository: StoreRepository, productRepository: ProductRepository) : CommandLineRunner {
    @Autowired
    val storeRepository = storeRepository

    @Autowired
    val productRepository = productRepository

    override fun run(vararg args: String?) {
        var zone = GeographicMapBuilder.aGeographicMap().withLongitude(-34.721533).withLatitude(-58.258655).build()
        var addressPanaderia = AddressBuilder.anAddress().withLocality("Quilmes")
                .withNumber(160).withStreet("Alsina").withZone(zone).build()
        var panaderia = StoreBuilder.aStore().withActivity("Panaderia").withDistance(2.0)
                .withAdress(addressPanaderia).withStoreName("Hausbrot").build()

        storeRepository.save(panaderia)

        var kiloPan = ProductBuilder.aProduct().withBrand("casero").withName("Mingnon por kilo")
                .withPrice(80.0).withId(1).withUrl("").build()

        productRepository.save(kiloPan)

        panaderia.addProduct(kiloPan)

        productRepository.save(kiloPan)
        storeRepository.save(panaderia)

        /*--------------------------*/
        var zoneKiosco = GeographicMapBuilder.aGeographicMap().withLongitude(-34.721732).withLatitude(-58.256736).build()
        var addressKiosco = AddressBuilder.anAddress().withLocality("Quilmes")
                .withNumber(130).withStreet("Rivadavia").withZone(zoneKiosco).build()
        var kiosco = StoreBuilder.aStore().withActivity("Kiosco").withDistance(0.5)
                .withAdress(addressKiosco).withStoreName("Open 25hs").build()
        storeRepository.save(kiosco)

        var cocacola = ProductBuilder.aProduct().withBrand("Coca-Cola").withName("Coca-Cola 2.25lts")
                .withPrice(210.0).withId(2).withUrl("").build()

        productRepository.save(cocacola)

        kiosco.addProduct(cocacola)

        productRepository.save(cocacola)
        storeRepository.save(kiosco)


    }
}