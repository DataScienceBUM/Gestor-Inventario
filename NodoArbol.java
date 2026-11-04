public class NodoArbol {

    // Atributos
    private Producto producto;
    private NodoArbol izq;
    private NodoArbol der;

    // Constructor
    public NodoArbol(Producto producto) {
        this.producto = producto;
        this.izq = null;
        this.der = null;
    }

    // Metodo de conveniencia para obtener la llave del nodo (el nombre del producto)
    public String getClave() {
        return producto.getNombre();
    }

    // Getters & Setters
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public NodoArbol getIzq() {
        return izq;
    }

    public void setIzq(NodoArbol izq) {
        this.izq = izq;
    }

    public NodoArbol getDer() {
        return der;
    }

    public void setDer(NodoArbol der) {
        this.der = der;
    }

    @Override
    public String toString() {
        return "NodoArbol{" + "producto=" + producto + '}';
    }
}