package unq.tpi.desapp

class Sistema {

    var usuarios = mutableListOf<Usuario>()
    var comercios = mutableListOf<Comercio>()



    fun listarProductos() {
        val catalogo: MutableList<Producto>
            for(comercio in comercios) {
                //catalogo.addAll(comercio.listaDeProductos)
                // aca estoy intentando unir la lista de productos de cada comercio en una sola para mostrarlos
        }
        return

        //luego retorna esa lista de productos
    }

    fun registrarUsuario(user: Usuario){
        this.usuarios.add(user)
    }

    fun crearComercio(comercio: Comercio){
        this.comercios.add(comercio)
    }

    fun agregarItemAlCarrito(user: Usuario, producto: Producto, cantidad: Double){

        if (this.comercioTieneStock(producto)) {
            user.agregarItemProducto(producto, cantidad)
        }
    }

    private fun comercioTieneStock(producto: Producto): Boolean {
        for (comercio in comercios) {
            if (comercio.tieneStock(producto)) {
                return true
            }

            // Ya se que esta feo.. despues lo arreglamos
        }
        return false
    }

    fun hacerCompra(user: Usuario){
        // este metodo es el que toma el carrito del usuario
        // y tiene que ver cada producto ahi si es de cada comercio
        // y luego mandar la compra al comercio
    }
}