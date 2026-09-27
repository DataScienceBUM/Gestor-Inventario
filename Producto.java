import java.util.ArrayList;

public class Producto {
    private String nombre, categoria, fechaVencimiento;
    private int precio, cantidad;
    private ArrayList<String> pathImagenes;
    private Producto siguienteProducto;

    public Producto(String nombre, String categoria, String fechaVencimiento, int precio,
            ArrayList<String> pathImagenes) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.precio = precio;
        this.cantidad = 0;
        this.pathImagenes = pathImagenes;
        this.siguienteProducto = null;
    }

    // Metodos
    public void agregarUnidades(int unidades) {
        if (unidades <= 0) {
            System.out.println("Error: la cantidad debe ser mayor a cero");
            return;
        }

        setCantidad(getCantidad() + unidades);
        System.out.println("Unidades agregadas correctamente");
    }

    public void disminuirUnidades(int unidades) {
        if (unidades <= 0) {
            System.out.println("Error: la cantidad debe ser mayor a cero");
            return;
        }

        if (getCantidad() < unidades) {
            System.out.println("Error: no hay stock suficiente para disminuir");
            return;
        }

        setCantidad(getCantidad() - unidades);
        System.out.println("Unidades disminuidas correctamente");
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<String> getPathImagenes() {
        return pathImagenes;
    }

    public void setPathImagenes(ArrayList<String> pathImagenes) {
        this.pathImagenes = pathImagenes;
    }

    public Producto getSiguienteProducto() {
        return siguienteProducto;
    }

    public void setSiguienteProducto(Producto siguienteProducto) {
        this.siguienteProducto = siguienteProducto;
    }

    @Override

    public String toString() {
        return "Nombre: " + nombre + "\nCategoria: " + categoria + "\nFecha de vencimiento: "
                + fechaVencimiento + "\nPrecio: " + precio + "\nCantidad: " + cantidad;
    }

}