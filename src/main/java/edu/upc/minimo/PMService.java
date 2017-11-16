package edu.upc.minimo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("cafeteria")
public class PMService {

    ProductManagerImpl test=ProductManagerImpl.getInstance();

    @Path("/productos/precio")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String productosprecio(){
        String cosas="";
        ArrayList<Producto> ordenado=test.productosPrecio();
        for(Producto p:ordenado){
            cosas=cosas+"El producto "+p.getNombre()+" vale "+p.getPrecio()+" euros.\n";
        }
        return cosas;
    }

    @Path("/productos/ventas")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String productosventa(){
        String cosas="";
        ArrayList<Producto> ordenado=test.productosVentas();
        for(Producto p:ordenado){
            cosas=cosas+"El producto "+p.getNombre()+" se ha vendido "+p.getVentas()+" veces.\n";
        }
        return cosas;
    }


    @Path("/pedir/{usuario}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String pedido(@PathParam("usuario") String user/*,String[] cosas,int[] cantidad*/){

        String[] cosas=new String[3];
        cosas[0]="Leche";
        cosas[1]="Huevos";
        cosas[2]="Bocadillo";
        int[] cantidad=new int[3];
        cantidad[0]=1;
        cantidad[1]=2;
        cantidad[2]=3;
        /*
        if(!test.getUsuarios().containsKey(user)){
            Usuario usuario=new Usuario(user);
            test.setUsuario(usuario);
        }

       */
        if (test.realizarPedido(user,cosas,cantidad))
        {
            return"Pedido realizado con exito";
        }else{return "El pedido no se ha podido realizar";}


    }


    @Path("/servir")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String servir(){
        return test.servirPedido();
    }

    @Path("/{usuario}/realizados")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String pedidosRealizados(@PathParam("usuario") String user){
        String frase="";
        for(Pedido p:test.pedidosUsuario(user)){
            for(Producto pr:p.getProductos()){
                frase=frase+"Se le ha servido "+p.getCantidades().get(pr.getNombre())+" veces el producto "+pr.getNombre()+"\n";
            }
            frase=frase+"\n";
        }
        return frase;
    }
    /*
    @Path("/pedir")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response hacerPedido(String usuario,String[] cosas,int[] cantidades){
        int estado=501;
        if(test.getUsuarios().containsKey(usuario)) {
            int i = 0;
            Producto[] pedir = new Producto[cosas.length];
            int[] cantidad = new int[cantidades.length];
            for (String cosa : cosas) {
                for (Producto p : test.getProductos()) {
                    if (cosa == p.getNombre()) {
                        pedir[i] = p;
                        cantidad[i] = cantidades[i];
                        i++;
                        break;
                    }
                }
            }
            Producto[] tienen=new Producto[i];
            int[] cuantos = new int[i];
            i=0;
            for(Producto p:pedir){
                if (p != null) {
                    tienen[i]=p;
                    cuantos[i]=cantidad[i];
                    i++;
                }
            }
            test.realizarPedido(usuario,tienen,cuantos);
            estado=201;
        }

        return Response.status(estado).build();
    }*/

}

