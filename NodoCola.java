public class NodoCola {
    private Cliente cliente;
    private NodoCola siguiente;

 // cliente almacenado
    public NodoCola(Cliente cliente) {
        this.cliente = cliente;
        this.siguiente = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public NodoCola getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCola siguiente) {
        this.siguiente = siguiente;
    }
}