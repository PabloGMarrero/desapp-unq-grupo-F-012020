package unq.tpi.desapp

import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import unq.tpi.desapp.Producto
import unq.tpi.desapp.builders.ProductoBuilder

@SpringBootTest
class ProductoTest {


    @Test
    fun testConstructorParametrosDefault() {
        var producto = ProductoBuilder.unProducto().build()
        assertEquals(producto.id, 0)
        assertEquals(producto.imagen, "")
        assertEquals(producto.marca, "")
        assertEquals(producto.nombre, "")
        assertEquals(producto.precio, 0.0)
    }

    @Test
    fun testProductoConId100(){
        var producto = ProductoBuilder.unProducto().conId(100).build()
        assertEquals(producto.id, 100)
    }

    @Test
    fun testProductoConImagenDistintoVacio(){
        var producto = ProductoBuilder.unProducto().conUrl("blabla").build()
        assertEquals(producto.imagen, "blabla")
    }

    @Test
    fun testProductoConMarcaDistintoVacio(){
        var producto = ProductoBuilder.unProducto().conMarca("Marolio").build()
        assertEquals(producto.marca, "Marolio")
    }

    @Test
    fun testProductoConNombreDistintoVacio(){
        var producto = ProductoBuilder.unProducto().conNombre("Aceite").build()
        assertEquals(producto.nombre, "Aceite")
    }

    @Test
    fun testProductoConPrecioDistintoVacio(){
        var producto = ProductoBuilder.unProducto().conPrecio(85.5).build()
        assertEquals(producto.precio, 85.5)
    }

    @Test
    fun testProductoConPrecio50YMarcaPepitos(){
        var producto = ProductoBuilder.unProducto().conPrecio(50.0).conMarca("Pepitos").build()
        assertEquals(producto.precio, 50.0)
        assertEquals(producto.marca, "Pepitos")
    }

}