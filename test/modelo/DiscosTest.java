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
public class DiscosTest {
    
    public DiscosTest() {
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
     * Test of getId method, of class Discos.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Discos instance = new Discos();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Discos.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = -15;
        Discos instance = new Discos();
        instance.setId(id);
    }

    /**
     * Test of getCodigo method, of class Discos.
     */
    @Test
    public void testGetCodigo() {
        System.out.println("getCodigo");
        Discos instance = new Discos();
        String expResult = "";
        String result = instance.getCodigo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCodigo method, of class Discos.
     */
    @Test
    public void testSetCodigo() {
        System.out.println("setCodigo");
        String codigo = null;
        Discos instance = new Discos();
        instance.setCodigo(codigo);
    }

    /**
     * Test of getNombre method, of class Discos.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Discos instance = new Discos();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNombre method, of class Discos.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = null;
        Discos instance = new Discos();
        instance.setNombre(nombre);
    }

    /**
     * Test of getProveedor method, of class Discos.
     */
    @Test
    public void testGetProveedor() {
        System.out.println("getProveedor");
        Discos instance = new Discos();
        String expResult = "";
        String result = instance.getProveedor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setProveedor method, of class Discos.
     */
    @Test
    public void testSetProveedor() {
        System.out.println("setProveedor");
        String proveedor = null;
        Discos instance = new Discos();
        instance.setProveedor(proveedor);
    }

    /**
     * Test of getStock method, of class Discos.
     */
    @Test
    public void testGetStock() {
        System.out.println("getStock");
        Discos instance = new Discos();
        int expResult = 0;
        int result = instance.getStock();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStock method, of class Discos.
     */
    @Test
    public void testSetStock() {
        System.out.println("setStock");
        int stock = -14;
        Discos instance = new Discos();
        instance.setStock(stock);
    }

    /**
     * Test of getPrecio method, of class Discos.
     */
    @Test
    public void testGetPrecio() {
        System.out.println("getPrecio");
        Discos instance = new Discos();
        double expResult = 0.0;
        double result = instance.getPrecio();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPrecio method, of class Discos.
     */
    @Test
    public void testSetPrecio() {
        System.out.println("setPrecio");
        double precio = -19.99;
        Discos instance = new Discos();
        instance.setPrecio(precio);
    }
    
}
