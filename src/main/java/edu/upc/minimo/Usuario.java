package edu.upc.minimo;

import java.util.ArrayList;

public class Usuario {
    String nombre;
    ArrayList<Pedido> pendientes,realizados;

    public Usuario(String nombre) {
        this.pendientes=new ArrayList<Pedido>();
        this.realizados=new ArrayList<Pedido>();
        this.nombre = nombre;
    }

    public void pedidoRealizado(){
        this.realizados.add(pendientes.remove(0));
    }

    //GETTERS Y SETTERS


    public void setPedido(Pedido p) {
        this.pendientes.add(p);
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Pedido> getRealizados() {
        return realizados;
    }
}
