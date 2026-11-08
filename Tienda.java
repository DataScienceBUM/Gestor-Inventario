public class Tienda {
    private ArbolProductos inventario;
    private ColaClientes colaClientes;

    public Tienda() {
        this.inventario = new ArbolProductos();
        this.colaClientes = new ColaClientes();
    }

    public void agregarProductoAInventario(Producto nuevoProducto) {
        inventario.insertar(nuevoProducto);
        System.out.println("Producto: " + nuevoProducto.getNombre() + " agregado al inventario");
    }

    public void registrarClienteConCarrito(Cliente cliente) {
        if (cliente == null) {
            System.out.println("Error: El cliente no es válido.");
            return;
        }

        if (inventario.estaVacia()) {
            System.out.println("El inventario está vacío, no es posible añadir clientes con carritos.");
            return;
        }

        ListaProducto tempCarrito = cliente.getCarrito();
        if (tempCarrito == null || tempCarrito.estaVacia()) {
            System.out.println("El carrito del cliente está vacío. No se puede encolar.");
            return;
        }

        // Validar que todos los productos existan y tengan stock suficiente
        Producto tempProducto = tempCarrito.getPrimerProducto();
        while (tempProducto != null) {
            Producto tempProductoInventario = inventario.buscarProducto(tempProducto.getNombre());

            if (tempProductoInventario == null) {
                System.out.println("Error: El producto '" + tempProducto.getNombre() + "' no existe en el inventario.");
                return;
            }

            int stockDisponible = tempProductoInventario.getStock();
            int unidadesPedidas = tempProducto.getCantidad();

            if (stockDisponible < unidadesPedidas) {
                System.out.println("Error: Stock insuficiente para '" + tempProducto.getNombre() + "'."
                        + " Solicitados: " + unidadesPedidas
                        + " | Disponibles: " + stockDisponible);
                return;
            }

            tempProducto = tempProducto.getSiguienteProducto();
        }

        // Si pasó todas las validaciones de stock, se encola al cliente
        colaClientes.encolar(cliente);
        System.out
                .println("Cliente " + cliente.getNombre() + " registrado y agregado a la fila de atención con éxito.");
    }

    public void atenderSiguienteCliente() {
        if (colaClientes.estaVacia()) {
            System.out.println("No hay clientes en la fila para atender.");
            return;
        }

        Cliente clienteAtendido = colaClientes.desencolar();
        ListaProducto carrito = clienteAtendido.getCarrito();

        if (carrito == null || carrito.estaVacia()) {
            System.out.println("El cliente " + clienteAtendido.getNombre() + " no tiene productos en su carrito.");
            return;
        }

        // Descontar las unidades compradas del inventario
        Producto item = carrito.getPrimerProducto();
        while (item != null) {
            Producto itemInventario = inventario.buscarProducto(item.getNombre());
            if (itemInventario != null) {
                itemInventario.disminuirUnidades(item.getCantidad());
            }
            item = item.getSiguienteProducto();
        }

        // Imprimir factura formateada
        imprimirFactura(clienteAtendido);
    }

    private void imprimirFactura(Cliente cliente) {
        System.out.println("\n=======================================================");
        System.out.println("                 FACTURA DE COMPRA                     ");
        System.out.println("=======================================================");
        System.out.println("Cliente: " + cliente.getNombre() + " (ID: " + cliente.getIdentificacion() + ")");
        System.out.println(
                "Tipo de Cliente: " + cliente.getTipoCliente() + " (Prioridad " + cliente.getPrioridad() + ")");
        System.out.println("-------------------------------------------------------");
        System.out.println(String.format("%-20s %-8s %-12s %-12s", "Producto", "Cant.", "P. Unitario", "Subtotal"));
        System.out.println("-------------------------------------------------------");

        Producto item = cliente.getCarrito().getPrimerProducto();
        double total = cliente.getCarrito().calcularTotal();

        while (item != null) {
            double subtotal = item.getPrecio() * item.getCantidad();

            System.out.println(String.format("%-20s %-8d $%-11.2f $%-11.2f",
                    item.getNombre(),
                    item.getCantidad(),
                    item.getPrecio(),
                    subtotal));

            item = item.getSiguienteProducto();
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("TOTAL A PAGAR: $" + String.format("%.2f", total));
        System.out.println("=======================================================\n");
    }

    // Getters
    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaClientes() {
        return colaClientes;
    }
}
