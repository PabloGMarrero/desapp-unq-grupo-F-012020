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

/**
 * Initial class to add fake objects to the application
 */
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
                .withPrice(80.0).withId(1).withUrl("https://costumbres.com.ar/wp-content/uploads/2020/01/pan-mignon.png").build()

        productRepository.save(kiloPan)

        panaderia.addProduct(kiloPan)

        productRepository.save(kiloPan)
        storeRepository.save(panaderia)




        var zoneKiosco = GeographicMapBuilder.aGeographicMap().withLongitude(-34.721732).withLatitude(-58.256736).build()
        var addressKiosco = AddressBuilder.anAddress().withLocality("Quilmes")
                .withNumber(130).withStreet("Rivadavia").withZone(zoneKiosco).build()
        var kiosco = StoreBuilder.aStore().withActivity("Kiosco").withDistance(0.5)
                .withAdress(addressKiosco).withStoreName("Open 25hs").build()
        storeRepository.save(kiosco)

        var cocacola = ProductBuilder.aProduct().withBrand("Coca-Cola").withName("Coca-Cola 2.25lts")
                .withPrice(210.0).withId(2).withUrl("https://www.pngkit.com/png/detail/76-764935_coca-cola-1-5-l-png.png").build()

        productRepository.save(cocacola)

        kiosco.addProduct(cocacola)

        productRepository.save(cocacola)
        storeRepository.save(kiosco)

        /*--------------------------*/
        var chocolate = ProductBuilder.aProduct().withBrand("Shot").withName("Chocolate Con Mani 200 gr")
                .withPrice(300.0).withId(3).withUrl("https://dulcilandia.com.ar/wp-content/uploads/2020/04/01101097-510x510.png").build()

        productRepository.save(chocolate)

        kiosco.addProduct(chocolate)

        productRepository.save(chocolate)
        storeRepository.save(kiosco)

        /*--------------------------*/
        var galletitas = ProductBuilder.aProduct().withBrand("Arcor").withName("Galletitas Merengadas 160 gr")
                .withPrice(50.0).withId(4).withUrl("https://latinamericadistributors.com/wp-content/uploads/2018/10/Sin-ti%CC%81tulo-10-19-600x600.png").build()

        productRepository.save(galletitas)

        kiosco.addProduct(galletitas)

        productRepository.save(galletitas)
        storeRepository.save(kiosco)

        /*--------------------------*/
        var sidra = ProductBuilder.aProduct().withBrand("Asturias").withName("Sidra Marques de Asturias 150 cc")
                .withPrice(1250.0).withId(5).withUrl("https://nietosdeasturias.com/wp-content/uploads/2016/08/Nietos-de-Asturias-Sidras-MA-Ambar3.png").build()

        productRepository.save(sidra)

        kiosco.addProduct(sidra)

        productRepository.save(sidra)
        storeRepository.save(kiosco)

        /*--------------------------*/
        var rosca = ProductBuilder.aProduct().withBrand("casero").withName("Rosca de Pascua")
                .withPrice(180.0).withId(6).withUrl("https://3.bp.blogspot.com/-Z1h0pcehtwY/XC60rEeoa9I/AAAAAAAAKb0/O12oBHrYn84e4NUu-QUCzIZHHY94r1ZSQCLcBGAs/s1600/DSCN9792.png").build()

        productRepository.save(rosca)

        panaderia.addProduct(rosca)

        productRepository.save(rosca)
        storeRepository.save(panaderia)

        /*--------------------------*/

        var polenta = ProductBuilder.aProduct().withBrand("PrestoPronta").withName("Polenta Presto Pronta 200gr")
                .withPrice(1250.0).withId(7).withUrl("https://cdn.shopify.com/s/files/1/2168/5959/products/polenta_360x.png").build()

        productRepository.save(polenta)
        kiosco.addProduct(polenta)

        productRepository.save(polenta)
        storeRepository.save(kiosco)





    }
}