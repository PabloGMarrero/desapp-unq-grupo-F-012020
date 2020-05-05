package unq.tpi.desapp.model

class Sistema {

    var usuarios = mutableListOf<User>()
    var comercios = mutableListOf<Store>()



    fun listarProductos(user: User) {
        val catalogos: MutableList<Product>
            for(comercio in comercios) {
                //catalogo.addAll(comercio.listaDeProductos)
                // aca estoy intentando unir la lista de productos de cada comercio en una sola para mostrarlos
        }
        return

        //luego retorna esa lista de productos
    }

    fun registrarUsuario(user: User){
        this.usuarios.add(user)
    }

    fun crearComercio(comercio: Store){
        this.comercios.add(comercio)
    }

    fun agregarItemAlCarrito(user: User, product: Product, cantidad: Double, store: Store){

        if (this.comercioTieneStock(product)) {
      //      user.agregarItemProducto(product, cantidad, store)
        }
    }

    private fun comercioTieneStock(product: Product): Boolean {
        for (comercio in comercios) {
     //       if (comercio.tieneStock(product)) {
     //           return true
     //       }

            // Ya se que esta feo.. despues lo arreglamos
        }
        return false
    }

    fun hacerCompra(user: User){
        // este metodo es el que toma el carrito del usuario
        // y tiene que ver cada producto ahi si es de cada comercio
        // y luego mandar la compra al comercio
    }
}