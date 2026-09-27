public class ListaProducto {
    private Producto primerProducto;

    public ListaProducto() {
        this.primerProducto = null;
    }

    // Metodos

    public boolean estaVacia() {
        return primerProducto == null;
    }

    public Producto buscarProducto(String nombre) {
        if (estaVacia()) {
            System.out.println("No hay productos en la lista");
            return null;
        }

        Producto tempProducto = getPrimerProducto();

        while (tempProducto != null) {

            if (tempProducto.getNombre().equals(nombre)) {
                System.out.println("Se encontro el producto " + nombre);
                return tempProducto;
            }
            tempProducto = tempProducto.getSiguienteProducto();
        }

        System.out.println("No se encontro el producto " + nombre);
        return null;

    }

    public void agregarProductoInicio(Producto nuevoProducto) {
        if (estaVacia()) {
            setPrimerProducto(nuevoProducto);
            System.out.println("Producto agregado correctamente");
            return;
        }

        nuevoProducto.setSiguienteProducto(getPrimerProducto());
        setPrimerProducto(nuevoProducto);
        System.out.println("Producto agregado correctamente");
    }

    public void agregarProductoFinal(Producto nuevoProducto) {
        if (estaVacia()) {
            setPrimerProducto(nuevoProducto);
        }

        Producto tempProducto = getPrimerProducto();

        while (tempProducto.getSiguienteProducto() != null) {
            tempProducto = tempProducto.getSiguienteProducto();
        }

        tempProducto.setSiguienteProducto(nuevoProducto);
        System.out.println("Producto agregado correctamente");
    }

    public Producto eliminarProducto(String nombre) {
        if (estaVacia()) {
            System.out.println("No hay productos en la lista");
            return null;
        }

        Producto tempProducto = getPrimerProducto();
        Producto anteriorProducto = tempProducto;
        boolean encontrado = false;

        while (tempProducto != null) {

            if (tempProducto.getNombre().equals(nombre)) {
                encontrado = true;
                break;
            }

            anteriorProducto = tempProducto;
            tempProducto = tempProducto.getSiguienteProducto();
        }

        if (encontrado) {
            if (tempProducto.getCantidad() > 0) {
                System.out.println("No se puede eliminar el producto porque tiene unidades");
                return null;
            }

            if (getPrimerProducto() == tempProducto) {
                setPrimerProducto(tempProducto.getSiguienteProducto());
            } else if (tempProducto.getSiguienteProducto() == null) {
                anteriorProducto.setSiguienteProducto(null);
            } else {
                anteriorProducto.setSiguienteProducto(tempProducto.getSiguienteProducto());
            }

            System.out.println("Producto eliminado correctamente");
            return tempProducto;
        }

        System.out.println("No se encontro el producto");
        return null;

    }

    @Override
    public String toString() {
        if (estaVacia()) {
            return "No hay productos en la lista";
        }

        String informacionProductos = "\nInformacion de los productos:\n=============================\n";

        Producto tempProducto = getPrimerProducto();

        while (tempProducto != null) {
            informacionProductos += tempProducto.toString() + "\n=============================\n";
            tempProducto = tempProducto.getSiguienteProducto();
        }
        return informacionProductos;
    }

    // Getters y Setters

    public Producto getPrimerProducto() {
        return primerProducto;
    }

    public void setPrimerProducto(Producto primerProducto) {
        this.primerProducto = primerProducto;
    }
}
