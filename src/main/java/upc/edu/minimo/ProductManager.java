package upc.edu.minimo;


import java.util.ArrayList;

public interface ProductManager {

    ArrayList<Producto> productosPrecio();

    String realizarPedido(String user, String[] cosas,int[] cantidad);

    String servirPedido();

    ArrayList<Pedido> pedidosUsuario(String user);

    ArrayList<Producto> productosVentas();


}
