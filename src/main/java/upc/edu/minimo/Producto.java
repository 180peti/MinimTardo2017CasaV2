package upc.edu.minimo;

public class Producto {

    String nombre;
    int precio,ventas;

    public Producto(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }


    //GETTERS Y SETTERS


    public int getPrecio() {
        return precio;
    }

    public int getVentas() {
        return ventas;
    }

    public String getNombre() {
        return nombre;
    }
}
