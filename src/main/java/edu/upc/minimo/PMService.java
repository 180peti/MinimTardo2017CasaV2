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
    //Para probar el resto de funcionalidades.
    @Path("/pedir/{username}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String pedido(@PathParam("username") String user){
        String[] cosas=new String[3];
        cosas[0]="Leche";
        cosas[1]="Huevos";
        cosas[2]="Bocadillo";
        int[] cantidad=new int[3];
        cantidad[0]=1;
        cantidad[1]=2;
        cantidad[2]=3;
        if(test.realizarPedido(user,cosas,cantidad)){return"pedido estandar realizado";}
        else{return "Error al hacer el pedido";}
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


}

