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
}