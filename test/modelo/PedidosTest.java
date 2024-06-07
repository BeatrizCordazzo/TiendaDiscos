/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package modelo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bia
 */
public class PedidosTest {
    
    public PedidosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Pedidos.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Pedidos instance = new Pedidos();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Pedidos.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = -2;
        Pedidos instance = new Pedidos();
        instance.setId(id);
    }

    /**
     * Test of getCliente method, of class Pedidos.
     */
    @Test
    public void testGetCliente() {
        System.out.println("getCliente");
        Pedidos instance = new Pedidos();
        String expResult = null;
        String result = instance.getCliente();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCliente method, of class Pedidos.
     */
    @Test
    public void testSetCliente() {
        System.out.println("setCliente");
        String cliente = null;
        Pedidos instance = new Pedidos();
        instance.setCliente(cliente);
    }

    /**
     * Test of getVendedor method, of class Pedidos.
     */
    @Test
    public void testGetVendedor() {
        System.out.println("getVendedor");
        Pedidos instance = new Pedidos();
        String expResult = null;
        String result = instance.getVendedor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVendedor method, of class Pedidos.
     */
    @Test
    public void testSetVendedor() {
        System.out.println("setVendedor");
        String vendedor = "";
        Pedidos instance = new Pedidos();
        instance.setVendedor(vendedor);
    }

    /**
     * Test of getTotal method, of class Pedidos.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");
        Pedidos instance = new Pedidos();
        double expResult = 0.0;
        double result = instance.getTotal();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTotal method, of class Pedidos.
     */
    @Test
    public void testSetTotal() {
        System.out.println("setTotal");
        double total = -5.67;
        Pedidos instance = new Pedidos();
        instance.setTotal(total);
    }
    
}
