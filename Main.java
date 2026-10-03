import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //crea una lista donde se guardaran los productos

        ListaProducto productos = new ListaProducto();
        //Inicia el menu principal
        menu(productos);
    }

    public static void menu(ListaProducto productos) {
        //Se utiliza scaner para recibir lo datos del usuario
        Scanner scanner = new Scanner(System.in);
        double opcion;

        //Se utiliza un do while para que el menu se repita hasta que el usuario decida salir

        do {

            System.out.println("\n===== GESTOR DE INVENTARIO =====");
            System.out.println("1. Agregar producto al inicio");
            System.out.println("2. Agregar producto al final");
            System.out.println("3. Modificar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Mostrar productos");
            System.out.println("6. Generar reporte de costos");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");

            //Se valida para que el usuario ingrese un numero correcto
            opcion = leerEntero(scanner);
            
            //Se utiliza un switch para que el usuario pueda elegir la opcion que desea realizar
            switch ((int) opcion) {

                //Acá se crea el nuevo producto y se agrega al inicio de la lista
                case 1:

                    Producto productoInicio = crearProducto(scanner);

                    productos.agregarProductoInicio(productoInicio);

                    break;

                //Aqui se crea un nuevo producto para agregarlo al final
                case 2:

                    Producto productoFinal = crearProducto(scanner);

                    //Se valida si la lista esta vacia, si es asi se agrega al inicio, si no se agrega al final
                    if (productos.estaVacia()) {

                        productos.agregarProductoInicio(productoFinal);

                    } else {

                        productos.agregarProductoFinal(productoFinal);
                    }

                    break;

                //Acá nos permite buscar y modificar un producto existente
                case 3:

                    modificarProducto(productos, scanner);

                    break;

                //Aca nos permite eliminar un producto existente
                case 4:

                    System.out.print(
                            "Nombre del producto a eliminar: "
                    );

                    String nombreEliminar =
                            scanner.nextLine();

                    productos.eliminarProducto(
                            nombreEliminar
                    );

                    break;

                //Aca nos permite mostrar todos los productos existentes
                case 5:

                    System.out.println(
                            productos.toString()
                    );

                    break;

                //Aqui genera un reporte de costos de los productos
                case 6:

                    productos.generarReporteCostos();

                    break;

                //Aca nos permite salir del programa
                case 7:

                    System.out.println(
                            "Saliendo del programa..."
                    );

                    break;


                default:

                    System.out.println(
                            "Opcion invalida. Intente nuevamente."
                    );
            }

        } while (opcion != 7);

        scanner.close();
    }


    //Crear producto

    private static Producto crearProducto(
            Scanner scanner) {

        System.out.println(
                "\n=== REGISTRAR PRODUCTO ==="
        );


        System.out.print("Nombre: ");

        String nombre =
                scanner.nextLine();


        System.out.print("Categoria: ");

        String categoria =
                scanner.nextLine();


        System.out.print(
                "Fecha de vencimiento " +
                "(dejar vacio si no aplica): "
        );

        String fechaVencimiento =
                scanner.nextLine();


        System.out.print("Precio: ");

        double  precio =
                leerEnteroNoNegativo(scanner);


        System.out.print("Cantidad: ");

        double cantidad =
                leerEnteroNoNegativo(scanner);

        //Aqui se crea un nuevo producto con los datos ingresados por el usuario y se le asigna una lista vacia de imagenes
        Producto nuevoProducto =
                new Producto(
                        nombre,
                        categoria,
                        fechaVencimiento,
                        precio,
                        new ArrayList<>()
                );


        //Aqui se valida si la cantidad es mayor a 0, si es asi se agregan las unidades al producto
        if (cantidad > 0) {

            nuevoProducto.agregarUnidades(
                    cantidad
            );
        }


        return nuevoProducto;
    }


    
    // MODIFICAR PRODUCTO
    private static void modificarProducto(
            ListaProducto productos,
            Scanner scanner) {
        //Aca se busca el producto que se desea modificar, si no se encuentra se retorna al menu principal
        System.out.print(
                "\nNombre del producto a modificar: "
        );

        String nombre =
                scanner.nextLine();


        Producto producto =
                productos.buscarProducto(nombre);


        //Si el producto no se encuentra, se retorna al menu principal
        if (producto == null) {

            return;
        }


        double opcion;

        //Acá se utiliza un do while para que el menu de modificacion se repita hasta que el usuario decida volver al menu principal
        do {

            System.out.println(
                    "\n===== MODIFICAR PRODUCTO ====="
            );

            System.out.println(
                    "1. Cambiar nombre"
            );

            System.out.println(
                    "2. Cambiar categoria"
            );

            System.out.println(
                    "3. Cambiar fecha de vencimiento"
            );

            System.out.println(
                    "4. Cambiar precio"
            );

            System.out.println(
                    "5. Agregar unidades"
            );

            System.out.println(
                    "6. Disminuir unidades"
            );

            System.out.println(
                    "7. Agregar imagen"
            );

            System.out.println(
                    "8. Eliminar imagen"
            );

            System.out.println(
                    "9. Mostrar imagenes"
            );

            System.out.println(
                    "0. Volver al menu principal"
            );

            System.out.print(
                    "Seleccione una opcion: "
            );


            opcion = leerEntero(scanner);


            switch ((int) opcion) {

                case 1:

                    System.out.print(
                            "Nuevo nombre: "
                    );

                    producto.setNombre(
                            scanner.nextLine()
                    );

                    System.out.println(
                            "Nombre modificado correctamente"
                    );

                    break;


                case 2:

                    System.out.print(
                            "Nueva categoria: "
                    );

                    producto.setCategoria(
                            scanner.nextLine()
                    );

                    System.out.println(
                            "Categoria modificada correctamente"
                    );

                    break;


                case 3:

                    System.out.print(
                            "Nueva fecha de vencimiento " +
                            "(dejar vacio si no aplica): "
                    );

                    producto.setFechaVencimiento(
                            scanner.nextLine()
                    );

                    System.out.println(
                            "Fecha modificada correctamente"
                    );

                    break;


                case 4:

                    System.out.print(
                            "Nuevo precio: "
                    );

                    producto.setPrecio(
                            leerEnteroNoNegativo(scanner)
                    );

                    System.out.println(
                            "Precio modificado correctamente"
                    );

                    break;

                //aqui agregamos unidades a la cantidad actual del producto
                case 5:

                    System.out.print(
                            "Cantidad de unidades a agregar: "
                    );

                    producto.agregarUnidades(
                            leerEntero(scanner)
                    );

                    break;

                //aqui disminuimos unidades a la cantidad actual del producto
                case 6:

                    System.out.print(
                            "Cantidad de unidades a disminuir: "
                    );

                    producto.disminuirUnidades(
                            leerEntero(scanner)
                    );

                    break;

                //aqui agregamos una imagen a la lista de imagenes del producto
                case 7:

                    System.out.print(
                            "Ruta de la imagen: "
                    );

                    producto.agregarImagen(
                            scanner.nextLine()
                    );

                    break;

                //acá eliminamos una imagen registrada
                case 8:

                    System.out.print(
                            "Ruta de la imagen a eliminar: "
                    );

                    producto.eliminarImagen(
                            scanner.nextLine()
                    );

                    break;

                //aqui se muestran las imagenes asociadas al producto
                case 9:

                    producto.mostrarImagenes();

                    break;


                case 0:

                    System.out.println(
                            "Volviendo al menu principal..."
                    );

                    break;


                default:

                    System.out.println(
                            "Opcion invalida. Intente nuevamente."
                    );
            }


        } while (opcion != 0);
    }


   
    // VALIDAR NUMEROS
    

    private static double leerEntero(
            Scanner scanner) {

        while (true) {

            try {

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.print(
                        "Ingrese un numero valido: "
                );
            }
        }
    }


    
    // VALIDAR NUMEROS NO NEGATIVOS
    private static double leerEnteroNoNegativo(
            Scanner scanner) {

        while (true) {

            double numero =
                    leerEntero(scanner);


            if (numero >= 0) {

                return numero;
            }


            System.out.print(
                    "Ingrese un numero mayor o igual a 0: "
            );
        }
    }
}