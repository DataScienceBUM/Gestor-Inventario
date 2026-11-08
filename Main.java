import java.util.Scanner;

public class Main {

        private static Tienda tienda = new Tienda();
        private static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
                precargarDatos();
                menuPrincipal();
        }

        /**
         * Carga inicial de datos (Seed Data)
         */
        public static void precargarDatos() {
                System.out.println("=== Cargando datos iniciales de prueba (Seed Data)... ===");

                // 1. Precarga de productos en el inventario (Arbol BST)
                tienda.agregarProductoAInventario(new Producto("Laptop HP", "Computadoras", "N/A", 450000.0, 15));
                tienda.agregarProductoAInventario(new Producto("Mouse Inalámbrico", "Periféricos", "N/A", 12000.0, 40));
                tienda.agregarProductoAInventario(new Producto("Teclado Mecánico", "Periféricos", "N/A", 35000.0, 25));
                tienda.agregarProductoAInventario(new Producto("Monitor 24 Pulg", "Pantallas", "N/A", 85000.0, 20));
                tienda.agregarProductoAInventario(new Producto("Audífonos Gamer", "Audio", "N/A", 28000.0, 30));

                // 2. Precarga de clientes en la cola con carritos listos
                Cliente cliente1 = new Cliente("301230456", "Carlos Rodriguez", Cliente.PRIORIDAD_BASICO);
                Producto compra1 = new Producto("Mouse Inalámbrico", "Periféricos", "N/A", 12000.0, 40);
                Producto compra2 = new Producto("Teclado Mecánico", "Periféricos", "N/A", 35000.0, 25);
                cliente1.getCarrito().agregarProducto(compra1, 2);
                cliente1.getCarrito().agregarProducto(compra2, 1);
                tienda.registrarClienteConCarrito(cliente1);

                Cliente cliente2 = new Cliente("109870654", "Mariana Gomez", Cliente.PRIORIDAD_PREMIUM);
                Producto compra3 = new Producto("Laptop HP", "Computadoras", "N/A", 450000.0, 15);
                Producto compra4 = new Producto("Monitor 24 Pulg", "Pantallas", "N/A", 85000.0, 20);
                cliente2.getCarrito().agregarProducto(compra3, 1);
                cliente2.getCarrito().agregarProducto(compra4, 2);
                tienda.registrarClienteConCarrito(cliente2);

                System.out.println("=== Datos iniciales cargados con éxito ===\n");
        }

        /**
         * Menú principal de consola.
         */
        public static void menuPrincipal() {
                int opcion = 0;

                while (opcion != 5) {
                        System.out.println("\n=======================================================");
                        System.out.println("       SISTEMA GESTOR DE TIENDA E INVENTARIO           ");
                        System.out.println("=======================================================");
                        System.out.println("1. Gestión de Inventario (Agregar / Modificar / Eliminar)");
                        System.out.println("2. Registrar cliente en la cola (Llenar carrito)");
                        System.out.println("3. Atender siguiente cliente (Factura y cobro)");
                        System.out.println("4. Visualizar estado (Inventario actual y Cola de clientes)");
                        System.out.println("5. Salir del sistema");
                        System.out.println("=======================================================");

                        opcion = leerEntero("Seleccione una opción (1-5): ");

                        switch (opcion) {
                                case 1:
                                        menuGestionInventario();
                                        break;
                                case 2:
                                        registrarClienteEnCola();
                                        break;
                                case 3:
                                        tienda.atenderSiguienteCliente();
                                        break;
                                case 4:
                                        menuVisualizacion();
                                        break;
                                case 5:
                                        System.out.println("\nGracias por utilizar el sistema. ¡Hasta pronto!");
                                        break;
                                default:
                                        System.out.println("Opción inválida. Ingrese un valor entre 1 y 5.");
                        }
                }
        }

        /**
         * Submenú de gestión de inventario sobre el árbol.
         */
        public static void menuGestionInventario() {
                int subOpcion = 0;
                while (subOpcion != 4) {
                        System.out.println("\n--- GESTIÓN DE INVENTARIO ---");
                        System.out.println("1. Agregar nuevo producto al inventario");
                        System.out.println("2. Modificar producto existente");
                        System.out.println("3. Eliminar producto del inventario");
                        System.out.println("4. Regresar al menú principal");

                        subOpcion = leerEntero("Seleccione una opción: ");

                        switch (subOpcion) {
                                case 1:
                                        crearProducto();
                                        break;
                                case 2:
                                        modificarProducto();
                                        break;
                                case 3:
                                        eliminarProducto();
                                        break;
                                case 4:
                                        break;
                                default:
                                        System.out.println("Opción no válida.");
                        }
                }
        }

        /**
         * Opción 1: Crear nuevo producto en el árbol de inventario.
         */
        public static void crearProducto() {
                System.out.println("\n--- AGREGAR PRODUCTO AL INVENTARIO ---");
                System.out.print("Ingrese el nombre del producto: ");
                String nombre = scanner.nextLine().trim();
                if (nombre.isEmpty()) {
                        System.out.println("Error: El nombre no puede estar vacío.");
                        return;
                }

                System.out.print("Ingrese la categoría del producto: ");
                String categoria = scanner.nextLine().trim();
                if (categoria.isEmpty()) {
                        System.out.println("Error: La categoría no puede estar vacía.");
                        return;
                }

                System.out.print("Ingrese la fecha de vencimiento / garantía (o Enter para omitir): ");
                String fechaVencimiento = scanner.nextLine().trim();
                if (fechaVencimiento.isEmpty()) {
                        fechaVencimiento = "N/A";
                }

                double precio = leerDoublePositivo("Ingrese el precio del producto: ");
                int stock = leerEnteroPositivo("Ingrese la cantidad inicial de stock: ");

                Producto nuevo = new Producto(nombre, categoria, fechaVencimiento, precio, stock);
                tienda.agregarProductoAInventario(nuevo);
        }

        /**
         * Modificar producto existente en el inventario.
         */
        public static void modificarProducto() {
                System.out.println("\n--- MODIFICAR PRODUCTO ---");
                System.out.print("Ingrese el nombre del producto a modificar: ");
                String nombreAntiguo = scanner.nextLine().trim();
                if (nombreAntiguo.isEmpty()) {
                        System.out.println("Error: El nombre no puede estar vacío.");
                        return;
                }

                Producto productoOriginal = tienda.getInventario().buscarProducto(nombreAntiguo);
                if (productoOriginal == null) {
                        return; // buscarProducto ya imprime mensaje de error
                }

                System.out.println("\nInformación actual del producto:\n" + productoOriginal);
                System.out.println("(Presione Enter sin escribir nada para conservar el valor actual)");

                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine().trim();

                System.out.print("Nueva categoría: ");
                String nuevaCat = scanner.nextLine().trim();

                System.out.print("Nueva fecha de vencimiento / garantía: ");
                String nuevaFecha = scanner.nextLine().trim();

                System.out.print("Nuevo precio: ");
                String precioStr = scanner.nextLine().trim();
                double nuevoPrecio = productoOriginal.getPrecio();
                if (!precioStr.isEmpty()) {
                        try {
                                double tempPrecio = Double.parseDouble(precioStr);
                                if (tempPrecio > 0) {
                                        nuevoPrecio = tempPrecio;
                                } else {
                                        System.out.println("Precio inválido, no modificado.");
                                }
                        } catch (NumberFormatException e) {
                                System.out.println("Formato de número inválido. Precio no modificado.");
                        }
                }

                System.out.println("\nModificar stock:");
                System.out.println("1. Agregar stock");
                System.out.println("2. Disminuir stock");
                System.out.println("3. Conservar stock actual");
                int opStock = leerEntero("Opción: ");

                int stockActualizado = productoOriginal.getStock();
                if (opStock == 1) {
                        int agregar = leerEnteroPositivo("Cantidad a ingresar: ");
                        stockActualizado += agregar;
                } else if (opStock == 2) {
                        int restar = leerEnteroPositivo("Cantidad a retirar: ");
                        if (restar <= stockActualizado) {
                                stockActualizado -= restar;
                        } else {
                                System.out.println("Error: No se puede retirar más stock del existente.");
                        }
                }

                // Preservación del árbol BST: Si el nombre cambió, se elimina el nodo anterior y se inserta el nuevo
                if (!nuevoNombre.isEmpty() && !nuevoNombre.equalsIgnoreCase(nombreAntiguo)) {
                        tienda.getInventario().eliminar(nombreAntiguo);
                        String cat = nuevaCat.isEmpty() ? productoOriginal.getCategoria() : nuevaCat;
                        String fecha = nuevaFecha.isEmpty() ? productoOriginal.getFechaVencimiento() : nuevaFecha;
                        Producto productoActualizado = new Producto(nuevoNombre, cat, fecha, nuevoPrecio, stockActualizado);
                        tienda.agregarProductoAInventario(productoActualizado);
                } else {
                        if (!nuevaCat.isEmpty()) {
                                productoOriginal.setCategoria(nuevaCat);
                        }
                        if (!nuevaFecha.isEmpty()) {
                                productoOriginal.setFechaVencimiento(nuevaFecha);
                        }
                        productoOriginal.setPrecio(nuevoPrecio);
                        productoOriginal.setStock(stockActualizado);
                }

                System.out.println("Producto actualizado correctamente.");
        }

        /**
         * Eliminar producto del inventario.
         */
        public static void eliminarProducto() {
                System.out.println("\n--- ELIMINAR PRODUCTO ---");
                System.out.print("Ingrese el nombre del producto a eliminar: ");
                String nombre = scanner.nextLine().trim();
                if (nombre.isEmpty()) {
                        System.out.println("Error: El nombre no puede estar vacío.");
                        return;
                }

                NodoArbol eliminado = tienda.getInventario().eliminar(nombre);
                if (eliminado != null) {
                        System.out.println("Producto '" + nombre + "' eliminado correctamente del inventario.");
                }
        }

        /**
         * Opción 2: Registro de clientes en la cola (guiando al usuario para
         * seleccionar productos y llenar el carrito).
         */
        public static void registrarClienteEnCola() {
                System.out.println("\n=======================================================");
                System.out.println("             REGISTRO DE CLIENTE Y CARRITO             ");
                System.out.println("=======================================================");

                if (tienda.getInventario().estaVacia()) {
                        System.out.println("El inventario está vacío. Agregue productos antes de registrar clientes.");
                        return;
                }

                System.out.print("Ingrese la cédula / identificación del cliente: ");
                String id = scanner.nextLine().trim();
                if (id.isEmpty()) {
                        System.out.println("Error: La identificación no puede estar vacía.");
                        return;
                }

                System.out.print("Ingrese el nombre del cliente: ");
                String nombre = scanner.nextLine().trim();
                if (nombre.isEmpty()) {
                        System.out.println("Error: El nombre no puede estar vacío.");
                        return;
                }

                System.out.println("\nSeleccione el tipo / prioridad del cliente:");
                System.out.println("1. Básico (Prioridad 1)");
                System.out.println("2. Afiliado (Prioridad 2)");
                System.out.println("3. Premium (Prioridad 3)");
                int prioridad = 0;
                while (prioridad < 1 || prioridad > 3) {
                        prioridad = leerEntero("Ingrese la prioridad (1-3): ");
                        if (prioridad < 1 || prioridad > 3) {
                                System.out.println("Opción inválida. Debe ser 1, 2 o 3.");
                        }
                }

                Cliente cliente = new Cliente(id, nombre, prioridad);

                // Guía interactiva para llenar el carrito
                System.out.println("\n--- Catálogo de productos disponibles en inventario ---");
                tienda.getInventario().enOrden();

                boolean agregando = true;
                while (agregando) {
                        System.out.print(
                                "\nIngrese el nombre del producto que desea agregar al carrito (o 'fin' para terminar): ");
                        String prodNombre = scanner.nextLine().trim();

                        if (prodNombre.equalsIgnoreCase("fin")) {
                                if (cliente.getCarrito().estaVacia()) {
                                        System.out.println(
                                                "El carrito no puede estar vacío. Debe agregar al menos un producto.");
                                        continue;
                                }
                                agregando = false;
                                break;
                        }

                        Producto prodInventario = tienda.getInventario().buscarProducto(prodNombre);
                        if (prodInventario == null) {
                                System.out.println("Producto no encontrado en inventario. Intente nuevamente.");
                                continue;
                        }

                        System.out.println("Stock disponible de " + prodInventario.getNombre() + ": "
                                + prodInventario.getStock());
                        if (prodInventario.getStock() <= 0) {
                                System.out.println("No hay existencias disponibles de este producto.");
                                continue;
                        }

                        int cantidad = leerEnteroPositivo("Ingrese la cantidad a comprar: ");
                        if (cantidad > prodInventario.getStock()) {
                                System.out.println("Error: La cantidad solicitada (" + cantidad
                                        + ") supera el stock disponible (" + prodInventario.getStock() + ").");
                                continue;
                        }

                        // Crear el producto para el carrito
                        Producto itemCarrito = new Producto(
                                prodInventario.getNombre(),
                                prodInventario.getCategoria(),
                                prodInventario.getFechaVencimiento(),
                                prodInventario.getPrecio(),
                                prodInventario.getStock());

                        // Se utiliza agregarProducto para acumular cantidades de productos repetidos
                        cliente.getCarrito().agregarProducto(itemCarrito, cantidad);
                        System.out.println(">> " + cantidad + " unidad(es) de '" + prodInventario.getNombre()
                                + "' agregada(s) al carrito.");

                        System.out.print("¿Desea agregar otro producto? (s/n): ");
                        String continuar = scanner.nextLine().trim();
                        if (!continuar.equalsIgnoreCase("s")) {
                                agregando = false;
                        }
                }

                // Registrar y encolar al cliente en la tienda
                tienda.registrarClienteConCarrito(cliente);
        }

        /**
         * Opción 4: Visualización del estado actual (Inventario y Cola).
         */
        public static void menuVisualizacion() {
                int op = 0;
                while (op != 3) {
                        System.out.println("\n--- VISUALIZACIÓN DEL SISTEMA ---");
                        System.out.println("1. Mostrar inventario actual (Orden alfabético En-Orden)");
                        System.out.println("2. Visualizar estado de la cola de clientes");
                        System.out.println("3. Regresar al menú principal");

                        op = leerEntero("Seleccione una opción: ");

                        switch (op) {
                                case 1:
                                        System.out.println("\n=== INVENTARIO ACTUAL ===");
                                        tienda.getInventario().enOrden();
                                        break;
                                case 2:
                                        System.out.println("\n=== ESTADO DE LA COLA DE ATENCIÓN ===");
                                        tienda.getColaClientes().mostrarCola();
                                        break;
                                case 3:
                                        break;
                                default:
                                        System.out.println("Opción inválida.");
                        }
                }
        }

        // MÉTODOS DE VALIDACIÓN Y CONTROL DE ERRORES

        public static int leerEntero(String mensaje) {
                while (true) {
                        System.out.print(mensaje);
                        try {
                                return Integer.parseInt(scanner.nextLine().trim());
                        } catch (NumberFormatException e) {
                                System.out.println("Error: Debe ingresar un número entero válido.");
                        }
                }
        }

        public static int leerEnteroPositivo(String mensaje) {
                while (true) {
                        int valor = leerEntero(mensaje);
                        if (valor > 0) {
                                return valor;
                        }
                        System.out.println("Error: El número debe ser mayor que cero.");
                }
        }

        public static double leerDoublePositivo(String mensaje) {
                while (true) {
                        System.out.print(mensaje);
                        try {
                                double valor = Double.parseDouble(scanner.nextLine().trim());
                                if (valor > 0) {
                                        return valor;
                                }
                                System.out.println("Error: El valor debe ser mayor a 0.");
                        } catch (NumberFormatException e) {
                                System.out.println("Error: Debe ingresar un valor numérico válido.");
                        }
                }
        }
}