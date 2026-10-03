import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private ListaProducto productos;
    private Scanner scanner;

    public Menu() {
        this.productos = new ListaProducto();
        this.scanner = new Scanner(System.in);
    }

    public void menuPrincipal() {
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n=== Menu principal ===\n");
            System.out.println("1. Crear producto");
            System.out.println("2. Modificar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Mostrar productos");
            System.out.println("5. Generar reporte de costos");
            System.out.println("6. Salir");
            System.out.println("Ingrese una opcion: ");

            // Se hace de esta forma para evitar el error que ocurre al pedir primero un
            // numero y luego un string
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Error: La opcion debe ser un numero");
                continue;
            }
            switch (opcion) {
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
                    mostrarProductos();
                    break;
                /*
                 * case 5:
                 * generarReporteCostos();
                 * break;
                 * Falta validar el formateo de los atributos, brinca un error al intentar usar
                 * el reporte
                 */
                case 6:
                    System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }

    public boolean crearProducto() {
        boolean created = false;

        System.out.println("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();

        if (nombre.equals("")) {
            System.out.println("Error: El nombre no puede estar vacio");
            return created;
        }

        System.out.println("Ingrese la categoria del producto: ");
        String categoria = scanner.nextLine();

        if (categoria.equals("")) {
            System.out.println("Error: La categoria no puede estar vacia");
            return created;
        }

        System.out.println("Ingrese la fecha de vencimiento del producto:\nSi no aplica dejar vacia.");
        String fechaVencimiento = scanner.nextLine();
        double precio = 0;
        System.out.println("Ingrese el precio del producto: ");
        try {
            precio = Double.parseDouble(scanner.nextLine());
            if (precio <= 0) {
                System.out.println("Error: El precio debe ser mayor a 0");
                return created;
            }
        } catch (Exception e) {
            System.out.println("Error: El precio debe ser un numero");
            return created;
        }
        // Creamos el producto y lo añadimos a la lista y retornamos true
        productos.agregarProductoInicio(new Producto(nombre, categoria, fechaVencimiento, precio));
        created = true;
        return created;
    }

    public boolean modificarProducto() {
        boolean updated = false;

        System.out.println("Ingrese el nombre del producto a modificar: ");
        String nombre = scanner.nextLine();
        if (nombre.equals("")) {
            System.out.println("Error: El nombre no puede estar vacio");
            return updated;
        }

        Producto producto = productos.buscarProducto(nombre);
        if (producto == null) {
            System.out.println("Error: El producto no se encuentra en la lista");
            return updated;
        }

        System.out.println("Detalle del producto:\n" + producto.toString());
        System.out.println("En caso de no querer modificar un campo, deje el campo vacio.");

        System.out.println("Ingrese el nuevo nombre del producto:\n");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.equals("")) {
            producto.setNombre(nuevoNombre);
        }

        System.out.println("Ingrese la nueva categoria del producto:\n");
        String nuevaCategoria = scanner.nextLine();
        if (!nuevaCategoria.equals("")) {
            producto.setCategoria(nuevaCategoria);
        }

        System.out.println("Ingrese la nueva fecha de vencimiento del producto:\n");
        String nuevaFechaVencimiento = scanner.nextLine();
        if (!nuevaFechaVencimiento.equals("")) {
            producto.setFechaVencimiento(nuevaFechaVencimiento);
        }

        System.out.println("Ingrese el nuevo precio del producto:\n");
        String nuevoPrecioTemp = scanner.nextLine();
        double nuevoPrecio = 0;
        if (!nuevoPrecioTemp.equals("")) {
            try {
                nuevoPrecio = Double.parseDouble(nuevoPrecioTemp);
                if (nuevoPrecio <= 0) {
                    System.out.println("Error: El precio debe ser mayor a 0");

                    return updated;
                }
                producto.setPrecio(nuevoPrecio);
            } catch (NumberFormatException e) {
                System.out.println("Error: El precio debe ser un numero");
                return updated;
            }
        }

        System.out.println(
                "Modificar existencias:\n1. Añadir existencias\n2. Eliminar existencias\nIngrese una opcion: ");
        String opcionTemp = scanner.nextLine();
        if (!opcionTemp.equals("")) {
            try {
                int opcion = Integer.parseInt(opcionTemp);
                if (opcion == 1) {
                    System.out.println("Ingrese la cantidad de existencias a anadir: ");
                    double cantidad = Double.parseDouble(scanner.nextLine());
                    if (cantidad <= 0) {
                        System.out.println("Error: La cantidad debe ser mayor a 0");
                        return updated;
                    }
                    producto.agregarUnidades(cantidad);
                } else if (opcion == 2) {
                    System.out.println("Ingrese la cantidad de existencias a eliminar: ");
                    double cantidad = Double.parseDouble(scanner.nextLine());
                    if (cantidad <= 0) {
                        System.out.println("Error: La cantidad debe ser mayor a 0");
                        return updated;
                    }
                    if (cantidad > producto.getCantidad()) {
                        System.out.println("Error: La cantidad a eliminar es mayor a la cantidad disponible");
                        return updated;
                    }
                    producto.disminuirUnidades(cantidad);
                } else {
                    System.out.println("Error: Opcion invalida");
                    return updated;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: La cantidad debe ser un numero");
                return updated;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return updated;
            }
        }

        updated = true;
        System.out.println("Producto modificado correctamente");
        return updated;

    }

    public boolean eliminarProducto() {
        boolean deleted = false;

        System.out.println("Ingrese el nombre del producto a eliminar:\n");
        String nombre = scanner.nextLine();
        if (nombre.equals("")) {
            System.out.println("Error: El nombre no puede estar vacio");
            return deleted;
        }

        if (productos.eliminarProducto(nombre) != null) {
            deleted = true;
        }
        return deleted;
    }

    public void mostrarProductos() {
        System.out.println("\n=== Reporte de productos ===\n" + productos.toString());
    }

}