public class Main {

    public static void main(String[] args) {
        // Pruebas iniciales de funcionamiento de Producto y ListaProducto
        ListaProducto productos = new ListaProducto();

        productos.agregarProductoInicio(new Producto("Laptop", "Electronica", "", 1000, null));
        productos.agregarProductoFinal(new Producto("Mouse", "Electronica", "", 10, null));

        System.out.println(productos.toString());

        productos.buscarProducto("Mouse").setPrecio(20);

        productos.buscarProducto("Laptop").agregarUnidades(10);

        productos.buscarProducto("Mouse").disminuirUnidades(11);

        System.out.println(productos.toString());

        productos.eliminarProducto("Laptop");
        productos.buscarProducto("Laptop").disminuirUnidades(10);
        productos.eliminarProducto("Laptop");

        //Agrego datos para tener en el reporte
        productos.agregarProductoInicio(new Producto("Teclado", "Electronica", "", 45, null));
        productos.buscarProducto("Teclado").agregarUnidades(8);
        productos.buscarProducto("Mouse").agregarUnidades(15);

        System.out.println(productos.toString());

        // llamada de metodo de generar reporte.
        System.out.println("--- Probando reporte ---");
        productos.generarReporteCostos();
    }
}
