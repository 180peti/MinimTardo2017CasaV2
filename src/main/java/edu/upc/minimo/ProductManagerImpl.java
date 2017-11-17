package edu.upc.minimo;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductManagerImpl implements ProductManager {

    ArrayList<Producto> productos;
    HashMap<String,Usuario> usuarios;
    ArrayList<Pedido> pedidos,realizados;
    private static ProductManagerImpl instance;
    final Logger log= Logger.getLogger(ProductManagerImpl.class);

    public ProductManagerImpl() {
        org.apache.log4j.BasicConfigurator.configure();

        this.productos = new ArrayList<Producto>();
        this.usuarios = new HashMap<String, Usuario>();
        this.pedidos = new ArrayList<Pedido>();
        this.realizados=new ArrayList<Pedido>();

        Producto a=new Producto("Leche",5);
        productos.add(a);
        a=new Producto("Pan",2);
        productos.add(a);
        a=new Producto("Huevos",10);
        productos.add(a);
        a=new Producto("Jamon",7);
        productos.add(a);
        a=new Producto("Pizza",15);
        productos.add(a);
        a=new Producto("Pescado",13);
        productos.add(a);

        Usuario u=new Usuario("Pepe");
        usuarios.put(u.getNombre(),u);
        u=new Usuario("Pol");
        usuarios.put(u.getNombre(),u);
        u=new Usuario("Ruben");
        usuarios.put(u.getNombre(),u);
    }

    public static ProductManagerImpl getInstance(){
        if(instance==null) instance=new ProductManagerImpl();
        return instance;
    }

    public static ProductManagerImpl borrar(){
        instance=null;
        return instance;
    }

    public void ordenarP(ArrayList<Producto> cosas){

        this.productos=new ArrayList<Producto>(cosas.size());
        Producto buf;
        for(int i=0;i<cosas.size();i++){
            for(int j=0;j<cosas.size();j++){
                if(i!=j){
                    if(cosas.get(i).getPrecio()>cosas.get(j).getPrecio()&&i<j){
                        buf=cosas.remove(i);
                        cosas.add(i,cosas.remove(j-1));
                        cosas.add(j,buf);
                    }
                }
            }
        }
        this.productos=cosas;

    }

    public ArrayList<Producto> productosPrecio() {
        log.info(productos);
        ordenarP(productos);
        log.info(productos);
        return productos;
    }


    public boolean realizarPedido(String user, String[] cosas, int[] cantidad) {
        boolean existe,hecho=false;
        String frase="";
        log.info(pedidos);
        if(usuarios.containsKey(user))
            if(cosas.length==cantidad.length) {
                Pedido p=new Pedido(user);
                for(int i=0;i<cosas.length;i++)
                {
                    existe=false;
                    for(Producto pr:productos){
                        if(cosas[i].equals(pr.getNombre()))
                        {
                            p.añadirProducto(pr,cantidad[i]);
                            existe=true;
                            frase=frase+"Añadido el producto "+pr.getNombre()+" al pedido con "+cantidad[i]+" unidades.\n";
                            break;
                        }
                    }
                    if(!existe){frase=frase+"No tenemos el producto "+cosas[i]+".\n";}
                }
                pedidos.add(p);
                usuarios.get(user).setPedido(p);
                hecho=true;
            }else{frase=frase+"Numero de productos distinto a numero de cantidades";}
        else{frase="El usuario no esta autorizado a realizar pedidos";}

        //log.info(pedidos);
        log.info(frase);
        return hecho;
    }

    public String servirPedido() {
        log.info(pedidos);
        String frase="";
        int precio=0;
        int cantidad;
        Pedido siguiente=pedidos.remove(0);
        for(Producto pr:siguiente.getProductos())
        {
            cantidad=siguiente.getCantidades().get(pr.getNombre());
            frase=frase+"Aqui tiene "+cantidad+" unidades del producto "+pr.getNombre()+": "+cantidad*pr.getPrecio()+"\n";
            precio=precio+cantidad*pr.getPrecio();
            pr.setVentas(cantidad);
        }
        log.info(pedidos);
        usuarios.get(siguiente.getUsuario()).pedidoRealizado();
        realizados.add(siguiente);
        frase=frase+"TOTAL: "+precio;
        return frase;
    }

    public ArrayList<Pedido> pedidosUsuario(String user) {
        ArrayList<Pedido> realizados=new ArrayList<Pedido>();
        if(usuarios.containsKey(user))
        {
            realizados=usuarios.get(user).getRealizados();
        }
        log.info(realizados);
        return realizados;
    }

    private void ordenarV(ArrayList<Producto> cosas){
        this.productos=new ArrayList<Producto>(cosas.size());
        Producto buf;
        for(int i=0;i<cosas.size();i++){
            for(int j=0;j<cosas.size();j++){
                if(i!=j){
                    if(cosas.get(i).getVentas()<cosas.get(j).getVentas()&&i<j){
                        buf=cosas.remove(i);
                        cosas.add(i,cosas.remove(j-1));
                        cosas.add(j,buf);
                    }
                }
            }
        }
        this.productos=cosas;
    }

    public ArrayList<Producto> productosVentas() {
        log.info(productos);
        ordenarV(productos);
        log.info(productos);
        return productos;
    }


    //GETTERS Y SETTERS
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setUsuario(Usuario user){
        usuarios.put(user.getNombre(),user);
    }
}
