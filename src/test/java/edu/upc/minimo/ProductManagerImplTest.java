package edu.upc.minimo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductManagerImplTest {
    ProductManagerImpl test;
    @Before
    public void setUp() throws Exception {
        test=ProductManagerImpl.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        test.getInstance().borrar();
    }

    @Test
    public void pedir(){
        String[] cosas=new String[3];
        cosas[0]="Leche";
        cosas[1]="Huevos";
        cosas[2]="Bocadillo";
        int[] cantidad1=new int[2];
        int[] cantidad2=new int[3];
        cantidad1[0]=2;
        cantidad1[1]=3;
        cantidad2[0]=1;
        cantidad2[1]=2;
        cantidad2[2]=3;
        String resultado1=test.realizarPedido("Pepe",cosas,cantidad1);
        assertEquals(0,test.getPedidos().size());
        assertEquals(""+"Numero de productos distinto a numero de cantidades",resultado1);
        String resultado2=test.realizarPedido("Juan",cosas,cantidad2);
        assertEquals(0,test.getPedidos().size());
        assertEquals(""+"El usuario no esta autorizado a realizar pedidos",resultado2);
        String resultado3=test.realizarPedido("Ruben",cosas,cantidad2);
        assertEquals(1,test.getPedidos().size());
        assertEquals(2,test.getPedidos().get(0).getProductos().size());
    }

    @Test
    public void servir(){
        String[] cosas=new String[3];
        cosas[0]="Leche";
        cosas[1]="Huevos";
        cosas[2]="Bocadillo";
        int[] cantidad=new int[3];
        cantidad[0]=1;
        cantidad[1]=2;
        cantidad[2]=3;
        test.realizarPedido("Pepe",cosas,cantidad);

        cosas=new String[4];
        cosas[0]="Leche";
        cosas[1]="Huevos";
        cosas[2]="Jamon";
        cosas[3]="Pescado";
        cantidad=new int[4];
        cantidad[0]=1;
        cantidad[1]=2;
        cantidad[2]=3;
        cantidad[3]=4;
        test.realizarPedido("Ruben",cosas,cantidad);
        String s=test.servirPedido();
        assertEquals(1,test.getPedidos().size());
        assertEquals(1,test.pedidosUsuario("Pepe").size());
        String s2=test.servirPedido();
        assertEquals(0,test.getPedidos().size());
        assertEquals(1,test.pedidosUsuario("Ruben").size());

    }

}