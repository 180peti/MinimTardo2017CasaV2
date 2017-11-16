package edu.upc.minimo;


import java.util.ArrayList;

public interface ProductManager {

    ArrayList<Producto> productosPrecio();

    boolean realizarPedido(String user, String[] cosas,int[] cantidad);

    String servirPedido();

    ArrayList<Pedido> pedidosUsuario(String user);

    ArrayList<Producto> productosVentas();


}
