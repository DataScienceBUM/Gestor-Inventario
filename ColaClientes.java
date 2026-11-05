public class ColaClientes {

    //Atributos
    private NodoCola frente;
    private NodoCola fin;
    private int tamanio;

    //Constructor: se crea una cola vacía.
    public ColaClientes() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    // indica si la cola esta vacia.(sin clientes)
    public boolean estaVacia() {
        return frente == null;
    }

    //retorna la cantidad de clientes en la cola
    public int getTamanio() {
        return tamanio;
    }

     // agrega un cliente a una posicion segun el orden y prioridad//
    public void encolar(Cliente c) {
        if (c == null) {
            throw new IllegalArgumentException("Error: El cliente no existe  No se puede agregar a la fila");
        }

        NodoCola nuevo = new NodoCola(c);

        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        }
        else if (c.getPrioridad() > frente.getCliente().getPrioridad()) {
            nuevo.setSiguiente(frente);
            frente = nuevo;
        }
        else if (c.getPrioridad() <= fin.getCliente().getPrioridad()) {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        else {
            NodoCola actual = frente;
            while (actual.getSiguiente() != null
                    && actual.getSiguiente().getCliente().getPrioridad() >= c.getPrioridad()) {
                actual = actual.getSiguiente();
            }
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }

        tamanio++;
    }

    // saca y retorna al clinte que esta delante
    public Cliente desencolar() {
        if (estaVacia()) {
            return null;
        }

        Cliente atendido = frente.getCliente();
        frente = frente.getSiguiente();

        if (frente == null) {
            fin = null;
        }

        tamanio--;
        return atendido;
    }

    public Cliente verFrente() {
        return estaVacia() ? null : frente.getCliente();
    }
// muestra el orden de los clientes proximos a atender
    public void mostrarCola() {
        if (estaVacia()) {
            System.out.println("La cola de clientes está vacía.");
            return;
        }

        System.out.println("===== Cola de atención (" + tamanio + " cliente(s)) ======");
        NodoCola actual = frente;
        int posicion = 1;
        while (actual != null) {
            System.out.println(posicion + ". " + actual.getCliente());
            actual = actual.getSiguiente();
            posicion++;
        }
        System.out.println("=====================================");
    }
}


