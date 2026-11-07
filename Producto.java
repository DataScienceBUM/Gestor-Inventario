import java.io.File;
import java.util.ArrayList;

    // Atributos
public class Producto {
    private String nombre, categoria, fechaVencimiento;
    private double precio;
    private int stock; // Se agrega este atributo para poder meter y sacar del stock.
    private int cantidad;
    private ArrayList<String> pathImagenes;
    private Producto siguienteProducto;
    //Constructor
    public Producto(String nombre, String categoria, String fechaVencimiento, double precio, int stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.precio = precio;
        this.cantidad = 0;
        this.pathImagenes = null;
        this.siguienteProducto = null;
        this.stock = stock;
    }

    // Metodos
    //Agrega unidades al stock del producto
    public void agregarUnidades(int unidades) {
        if (unidades <= 0) {
            System.out.println("Error: la cantidad debe ser mayor a cero");
            return;
        }
        setStock(getStock() + unidades);
        System.out.println("Unidades agregadas correctamente al stock");
    }

    // Disminuye unidades del stock del producto
    public void disminuirUnidades(int unidades) {
        if (unidades <= 0) {
            System.out.println("Error: la cantidad debe ser mayor a cero");
            return;
        }
        if (getStock() < unidades) {
            System.out.println("Error: no hay stock suficiente para disminuir");
            return;
        }
        setStock(getStock() - unidades);
        System.out.println("Unidades disminuidas correctamente del stock");
    }

    // IMAGENES
    public void agregarImagen(String ruta) {
        if (pathImagenes == null) {
            pathImagenes = new ArrayList<>();
        }
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            System.out.println("Error:No se encontro la imagen en " + ruta);
            return;
        }
        if (pathImagenes.contains(ruta)) {
            System.out.println("Esa imagen ya esta registrada");
            return;
        }
        pathImagenes.add(ruta);
        System.out.println("Imagen agregada correctamente");
    }

    public void eliminarImagen(String ruta) {
        if (pathImagenes == null) {
            pathImagenes = new ArrayList<>();
        }
        if (pathImagenes.remove(ruta)) {
            System.out.println("Imagen eliminada correctamente");
        } else {
            System.out.println("Esa imagen no esta registrada en el producto");
        }
    }

    public void mostrarImagenes() {
        if (pathImagenes == null) {
            pathImagenes = new ArrayList<>();
        }
        if (pathImagenes.isEmpty()) {
            System.out.println("El producto no tiene imagenes");
            return;
        }
        for (int i = 0; i < pathImagenes.size(); i++) {
            System.out.println((i + 1) + ". " + pathImagenes.get(i));
        }
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getStock() {return stock;}

    public void setStock(int stock) {this.stock = stock;}

    public ArrayList<String> getPathImagenes() {
        return pathImagenes;
    }

    public void setPathImagenes(ArrayList<String> pathImagenes) {
        this.pathImagenes = (pathImagenes != null) ? pathImagenes : new ArrayList<>();
    }

    public Producto getSiguienteProducto() {
        return siguienteProducto;
    }

    public void setSiguienteProducto(Producto siguienteProducto) {
        this.siguienteProducto = siguienteProducto;
    }

    @Override
    public String toString() {
        if (pathImagenes == null) {
            pathImagenes = new ArrayList<>();
        }
        String imagenes = pathImagenes.isEmpty() ? "Sin imagenes" : String.join(", ", pathImagenes);
        return "Nombre: " + nombre + "\nCategoria: " + categoria + "\nFecha de vencimiento: "
                + fechaVencimiento + "\nPrecio: " + precio + "\nCantidad: " + cantidad
                + "\nImagenes: " + imagenes + "\nStock: " + stock;
    }
}
