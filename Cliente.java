public class Cliente {

    // definir la prioridad del cliente
    public static final int PRIORIDAD_BASICO = 1;
    public static final int PRIORIDAD_AFILIADO = 2;
    public static final int PRIORIDAD_PREMIUM = 3;

    // informacion del cliente
    private String identificacion;
    private String nombre;
    private int prioridad;
    private ListaProducto carrito;

    // se crea un cliente con un carrito vacio
    public Cliente(String identificacion, String nombre, int prioridad) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        setPrioridad(prioridad);
        this.carrito = new ListaProducto();
    }

    // se crea un cliente con carrito que ya existe
    public Cliente(String identificacion, String nombre, int prioridad, ListaProducto carrito) {
        this(identificacion, nombre, prioridad);
        if (carrito != null) {
            this.carrito = carrito;
        }
    }

    // getters y setters
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    // se asigna la prioridad del clinte
    public void setPrioridad(int prioridad) {
        if (!esPrioridadValida(prioridad)) {
            throw new IllegalArgumentException("La prioridad debe ser 1 (Básico), 2 (Afiliado) o 3 (Premium).");
        }
        this.prioridad = prioridad;
    }

    // retorna el carrito del cliente
    public ListaProducto getCarrito() {
        return carrito;
    }

    // guarda el carrito de compras del cliente
    public void setCarrito(ListaProducto carrito) {
        this.carrito = carrito;
    }

    public static boolean esPrioridadValida(int prioridad) {
        return prioridad >= PRIORIDAD_BASICO && prioridad <= PRIORIDAD_PREMIUM;
    }

    // retorna el tipo de cliente
    public String getTipoCliente() {
        switch (prioridad) {
            case PRIORIDAD_PREMIUM:
                return "Premium";
            case PRIORIDAD_AFILIADO:
                return "Afiliado";
            default:
                return "Básico";
        }
    }

    @Override
    public String toString() {
        return nombre + " (ID: " + identificacion + ") - Prioridad "
                + prioridad + " [" + getTipoCliente() + "]";
    }
}