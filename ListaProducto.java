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
            return;
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
    //Metodo para generar el reporte de costos - Yen Lee
    public void generarReporteCostos () {
        if  (estaVacia()) {
            System.out.println("No se puede generar el reporte: La lista de inventario está vacía. ");
            return;
        }
        System.out.println("\n===Reporte de costos de inventario===\n");
        System.out.println(String.format("%-20s %-15s %-10s %-15s", "Producto", "Precio Unitario", "Cantidad", "Costo Total"));
        System.out.println("======================================");

        Producto tempProducto = getPrimerProducto();
        double costoTotalAcumulado = 0.0;

        //Recorrido de la lista
        while (tempProducto != null) {
            //Calculamos el subtotal: precio * cantidad
            double subtotalProducto = tempProducto.getPrecio() * tempProducto.getCantidad();
            costoTotalAcumulado += subtotalProducto;

            //Imprimir la fila del producto (formateada para que se vea como una tabla)
            System.out.println(String.format("%-20s %-14s %-10d %-14.2f",
            tempProducto.getNombre(),
            tempProducto.getPrecio(),
            tempProducto.getCantidad(),
            subtotalProducto));
            //Avanzamos con el siguiente nodo
            tempProducto = tempProducto.getSiguienteProducto();
        }

        System.out.println("=======================================");
        System.out.println("Costo Total Acumulado del inventario: " + String.format("%.2f", costoTotalAcumulado));
        System.out.println("=======================================\n");
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
