public class ArbolProductos {
    //Se arma de acuerdo a lo que vimos en clase con el profe.
    // Atributos
    private NodoArbol raiz;

    // Constructor
    public ArbolProductos() {
        raiz = null;
    }

    // Getter & Setter
    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    // Operaciones auxiliares
    public boolean estaVacia() {
        return raiz == null;
    }

    // Inserta un nuevo Producto en el inventario utilizando el algoritmo iterativo de clase.
    // Si el producto ya existe (mismo nombre), actualiza el stock disponible.

    public void insertar(Producto producto) {
        if (producto == null) {
            return;
        }

        NodoArbol nuevo = new NodoArbol(producto);

        // Si el árbol está vacío, se inserta el nodo nuevo en la raíz y se retorna prematuramente
        if (estaVacia()) {
            raiz = nuevo;
            return;
        }

        // Se declaran dos variables temp y padreTemp y se inicializan ambas en la raíz
        NodoArbol temp = raiz;
        NodoArbol padreTemp = raiz;

        // Con un ciclo que continúa siempre que temp != null:
        while (temp != null) {
            padreTemp = temp;

            int comparacion = producto.getNombre().compareToIgnoreCase(temp.getClave());

            // Si el nombre del nuevo producto es alfabéticamente mayor, va a la derecha
            if (comparacion > 0) {
                temp = temp.getDer();
            }
            // Si el nombre del nuevo producto es alfabéticamente menor, va a la izquierda
            else if (comparacion < 0) {
                temp = temp.getIzq();
            }
            // Si son iguales, se actualiza el stock y precio del producto existente
            else {
                System.out.println("El producto '" + producto.getNombre() + "' ya existe. Actualizando stock...");
                temp.getProducto().setStock(temp.getProducto().getStock() + producto.getStock());
                temp.getProducto().setPrecio(producto.getPrecio());
                return;
            }
        }

        // Se enlaza el nuevo nodo al padre correspondiente
        int compFinal = producto.getNombre().compareToIgnoreCase(padreTemp.getClave());
        if (compFinal > 0) {
            padreTemp.setDer(nuevo);
        } else {
            padreTemp.setIzq(nuevo);
        }
    }

    // Búsqueda iterativa por nombre que retorna el NodoArbol correspondiente.
    public NodoArbol buscar(String nombre) {
        if (estaVacia()) {
            System.out.println("El inventario se encuentra vacío.");
            return null;
        }

        NodoArbol temp = raiz;

        while (temp != null) {
            int comparacion = nombre.compareToIgnoreCase(temp.getClave());

            if (comparacion > 0) {
                temp = temp.getDer();
            } else if (comparacion < 0) {
                temp = temp.getIzq();
            } else {
                return temp; // Encontrado
            }
        }

        System.out.println("El producto '" + nombre + "' no se encuentra en el inventario.");
        return null;
    }

    // Metodo de conveniencia para Steven y la Tienda:
     //Retorna directamente el objeto Producto a partir del nombre buscado.
    public Producto buscarProducto(String nombre) {
        NodoArbol nodo = buscar(nombre);
        return (nodo != null) ? nodo.getProducto() : null;
    }

    // Encontrar el padre de un nodo según la clave
    private NodoArbol getPadre(String clave) {
        if (raiz == null || raiz.getClave().equalsIgnoreCase(clave)) {
            return null;
        }

        NodoArbol temp = raiz;
        NodoArbol padreTemp = null;

        while (temp != null && !temp.getClave().equalsIgnoreCase(clave)) {
            padreTemp = temp;
            int comparacion = clave.compareToIgnoreCase(temp.getClave());
            if (comparacion > 0) {
                temp = temp.getDer();
            } else {
                temp = temp.getIzq();
            }
        }

        return (temp != null) ? padreTemp : null;
    }

    // Encontrar el sucesor en caso de eliminación de un nodo con dos hijos
    private NodoArbol getSucesor(NodoArbol nodoBorrar) {
        NodoArbol padreSucesor = nodoBorrar;
        NodoArbol sucesor = nodoBorrar;
        NodoArbol nodoActual = nodoBorrar.getDer();

        while (nodoActual != null) {
            padreSucesor = sucesor;
            sucesor = nodoActual;
            nodoActual = nodoActual.getIzq();
        }

        if (sucesor != nodoBorrar.getDer()) {
            padreSucesor.setIzq(sucesor.getDer());
            sucesor.setDer(nodoBorrar.getDer());
        }

        return sucesor;
    }

    // Elimina un producto del árbol según su nombre.

    public NodoArbol eliminar(String nombre) {
        if (estaVacia()) {
            System.out.println("El inventario se encuentra vacío.");
            return null;
        }

        NodoArbol nodoBorrar = buscar(nombre);
        if (nodoBorrar == null) {
            return null;
        }

        // Si el nodo a borrar es la raíz
        if (nodoBorrar == raiz) {
            if (nodoBorrar.getIzq() == null && nodoBorrar.getDer() == null) {
                raiz = null;
            } else if (nodoBorrar.getDer() == null) {
                raiz = nodoBorrar.getIzq();
            } else if (nodoBorrar.getIzq() == null) {
                raiz = nodoBorrar.getDer();
            } else {
                NodoArbol sucesor = getSucesor(nodoBorrar);
                sucesor.setIzq(nodoBorrar.getIzq());
                raiz = sucesor;
            }
            return nodoBorrar;
        }

        NodoArbol padre = getPadre(nombre);
        boolean esHijoIzq = (padre.getIzq() == nodoBorrar);

        // Caso 1: No tiene hijos (nodo hoja)
        if (nodoBorrar.getIzq() == null && nodoBorrar.getDer() == null) {
            if (esHijoIzq) {
                padre.setIzq(null);
            } else {
                padre.setDer(null);
            }
        }
        // Caso 2: Solo tiene hijo izquierdo
        else if (nodoBorrar.getDer() == null) {
            if (esHijoIzq) {
                padre.setIzq(nodoBorrar.getIzq());
            } else {
                padre.setDer(nodoBorrar.getIzq());
            }
        }
        // Caso 2: Solo tiene hijo derecho
        else if (nodoBorrar.getIzq() == null) {
            if (esHijoIzq) {
                padre.setIzq(nodoBorrar.getDer());
            } else {
                padre.setDer(nodoBorrar.getDer());
            }
        }
        // Caso 3: Tiene dos hijos
        else {
            NodoArbol sucesor = getSucesor(nodoBorrar);
            sucesor.setIzq(nodoBorrar.getIzq());

            if (esHijoIzq) {
                padre.setIzq(sucesor);
            } else {
                padre.setDer(sucesor);
            }
        }

        return nodoBorrar;
    }

    // Recorrido En-Orden (Izquierda, Raíz, Derecha) - Imprime el catálogo en orden alfabético
    private void enOrdenRec(NodoArbol subraiz) {
        if (subraiz != null) {
            enOrdenRec(subraiz.getIzq());
            System.out.println(subraiz.getProducto());
            enOrdenRec(subraiz.getDer());
        }
    }

    public void enOrden() {
        if (estaVacia()) {
            System.out.println("Inventario vacío.");
            return;
        }
        enOrdenRec(raiz);
        System.out.println();
    }

    // Recorrido Pre-Orden (Raíz, Izquierda, Derecha)
    private void preOrdenRec(NodoArbol subraiz) {
        if (subraiz != null) {
            System.out.println(subraiz.getProducto());
            preOrdenRec(subraiz.getIzq());
            preOrdenRec(subraiz.getDer());
        }
    }

    public void preOrden() {
        if (estaVacia()) {
            System.out.println("Inventario vacío.");
            return;
        }
        preOrdenRec(raiz);
        System.out.println();
    }

    // Recorrido Post-Orden (Izquierda, Derecha, Raíz)
    private void postOrdenRec(NodoArbol subraiz) {
        if (subraiz != null) {
            postOrdenRec(subraiz.getIzq());
            postOrdenRec(subraiz.getDer());
            System.out.println(subraiz.getProducto());
        }
    }

    public void postOrden() {
        if (estaVacia()) {
            System.out.println("Inventario vacío.");
            return;
        }
        postOrdenRec(raiz);
        System.out.println();
    }
}