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

        System.out.println(productos.toString());
    }
}
