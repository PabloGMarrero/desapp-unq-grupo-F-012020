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

    private fun initializePanaderia(){
        var zone = GeographicMapBuilder.aGeographicMap().withLongitude(-58.258655).withLatitude(-34.721533).build()
        var addressPanaderia = AddressBuilder.anAddress().withLocality("Quilmes")
                .withNumber(160).withStreet("Alsina").withZone(zone).build()
        var panaderia = StoreBuilder.aStore().withActivity("Panaderia").withDistance(2.0)
                .withAdress(addressPanaderia).withStoreName("Hausbrot").build()

        //storeRepository.save(panaderia)

        var kiloPan = ProductBuilder.aProduct().withBrand("casero").withName("Mingnon por kilo")
                .withPrice(80.0).withUrl("https://costumbres.com.ar/wp-content/uploads/2020/01/pan-mignon.png").build()

        kiloPan = productRepository.save(kiloPan)

        panaderia.addProduct(kiloPan)

        //productRepository.save(kiloPan)
        //storeRepository.save(panaderia)

        var rosca = ProductBuilder.aProduct().withBrand("casero").withName("Rosca de Pascua")
                .withPrice(180.0).withUrl("https://3.bp.blogspot.com/-Z1h0pcehtwY/XC60rEeoa9I/AAAAAAAAKb0/O12oBHrYn84e4NUu-QUCzIZHHY94r1ZSQCLcBGAs/s1600/DSCN9792.png").build()

        rosca = productRepository.save(rosca)

        panaderia.addProduct(rosca)

        //productRepository.save(rosca)
        storeRepository.save(panaderia)

        /*--------------------------*/
    }

    private fun initializeKiosco(){
        var zoneKiosco = GeographicMapBuilder.aGeographicMap().withLongitude(-58.256736).withLatitude(-34.721732).build()
        var addressKiosco = AddressBuilder.anAddress().withLocality("Quilmes")
                .withNumber(130).withStreet("Rivadavia").withZone(zoneKiosco).build()
        var kiosco = StoreBuilder.aStore().withActivity("Kiosco").withDistance(0.5)
                .withAdress(addressKiosco).withStoreName("Open 25hs").build()
        //storeRepository.save(kiosco)

        var cocacola = ProductBuilder.aProduct().withBrand("Coca-Cola").withName("Coca-Cola 2.25lts")
                .withPrice(210.0).withUrl("https://www.pngkit.com/png/detail/76-764935_coca-cola-1-5-l-png.png").build()

        cocacola = productRepository.save(cocacola)

        kiosco.addProduct(cocacola)

        //productRepository.save(cocacola)
        //storeRepository.save(kiosco)

        /*--------------------------*/
        var chocolate = ProductBuilder.aProduct().withBrand("Shot").withName("Chocolate Con Mani 200 gr")
                .withPrice(300.0).withUrl("https://dulcilandia.com.ar/wp-content/uploads/2020/04/01101097-510x510.png").build()

        chocolate = productRepository.save(chocolate)

        kiosco.addProduct(chocolate)

        //productRepository.save(chocolate)
        //storeRepository.save(kiosco)

        /*--------------------------*/
        var galletitas = ProductBuilder.aProduct().withBrand("Arcor").withName("Galletitas Merengadas 160 gr")
                .withPrice(50.0).withUrl("https://latinamericadistributors.com/wp-content/uploads/2018/10/Sin-ti%CC%81tulo-10-19-600x600.png").build()

        galletitas = productRepository.save(galletitas)

        kiosco.addProduct(galletitas)

        //productRepository.save(galletitas)
        //storeRepository.save(kiosco)

        /*--------------------------*/
        var sidra = ProductBuilder.aProduct().withBrand("Asturias").withName("Sidra Marques de Asturias 150 cc")
                .withPrice(1250.0).withUrl("https://nietosdeasturias.com/wp-content/uploads/2016/08/Nietos-de-Asturias-Sidras-MA-Ambar3.png").build()

        sidra = productRepository.save(sidra)

        kiosco.addProduct(sidra)

        //productRepository.save(sidra)
        //storeRepository.save(kiosco)

        /*--------------------------*/

        var polenta = ProductBuilder.aProduct().withBrand("PrestoPronta").withName("Polenta Presto Pronta 200gr")
                .withPrice(45.0).withUrl("https://cdn.shopify.com/s/files/1/2168/5959/products/polenta_360x.png").build()

        polenta = productRepository.save(polenta)
        kiosco.addProduct(polenta)

        //productRepository.save(polenta)
       // storeRepository.save(kiosco)

        /*--------------------------*/

        var tita = ProductBuilder.aProduct().withBrand("Terrabusi").withName("Tita")
                .withPrice(20.0).withUrl("https://d26lpennugtm8s.cloudfront.net/stores/001/082/289/products/tita-terrabusi-18g_172829690_779008451-67655f88aa5223505915866192292263-480-0.jpg").build()

        tita = productRepository.save(tita)
        kiosco.addProduct(tita)

        //productRepository.save(tita)

        /*saves all the store one at time*/
        storeRepository.save(kiosco)

    }

    private fun initializeCarniceria() {
        var zoneCarniceria = GeographicMapBuilder.aGeographicMap().withLongitude(-58.256736).withLatitude(-34.721732).build()
        var addressCarniceria = AddressBuilder.anAddress().withLocality("Quilmes")
                .withNumber(130).withStreet("Garibaldi").withZone(zoneCarniceria).build()
        var carniceria = StoreBuilder.aStore().withActivity("Kiosco").withDistance(1.0)
                .withAdress(addressCarniceria).withStoreName("Pobre vaca Carnicería").build()

        /*------*/
        var cuadrada = ProductBuilder.aProduct().withBrand("Carne").withName("Cuadrada por kilo")
                .withPrice(350.0).withUrl("https://bucket2.glanacion.com/anexos/fotos/33/2924133w380.jpg").build()

        cuadrada = productRepository.save(cuadrada)
        carniceria.addProduct(cuadrada)

        /*------*/
        var nalga = ProductBuilder.aProduct().withBrand("Carne").withName("Nalga por kilo")
                .withPrice(395.0).withUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe9X5sDLAQItPY8ep6NLcl0q2pPU4p78aVuV-Nke8Ani0nAxY&s").build()

        nalga = productRepository.save(nalga)
        carniceria.addProduct(nalga)

        /*------*/
        var peceto = ProductBuilder.aProduct().withBrand("Carne").withName("Peceto por kilo")
                .withPrice(420.75).withUrl("https://www.carneangus.com.uy/wp-content/uploads/2017/04/peceto.png").build()

        peceto = productRepository.save(peceto)
        carniceria.addProduct(peceto)

        /*------*/
        var huevos = ProductBuilder.aProduct().withBrand("Carne").withName("Seis huevos blancos")
                .withPrice(60.0).withUrl("https://delamanana.files.wordpress.com/2019/09/6-huevos-blancos-grandes.jpg").build()

        huevos = productRepository.save(huevos)
        carniceria.addProduct(huevos)

        storeRepository.save(carniceria)
    }

    override fun run(vararg args: String?) {
        this.initializePanaderia()
        this.initializeKiosco()
        this.initializeCarniceria()
    }

}