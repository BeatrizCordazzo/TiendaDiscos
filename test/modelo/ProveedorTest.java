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
public class ProveedorTest {
    
    public ProveedorTest() {
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
     * Test of getId method, of class Proveedor.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Proveedor instance = new Proveedor();
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Proveedor.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 1; //
        Proveedor instance = new Proveedor();
        instance.setId(id);
    }

    /**
     * Test of getRuc method, of class Proveedor.
     */
    @Test
    public void testGetRuc() {
        System.out.println("getRuc");
        Proveedor instance = new Proveedor();
        String expResult = "1234";
        String result = instance.getRuc();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRuc method, of class Proveedor.
     */
    @Test
    public void testSetRuc() {
        System.out.println("setRuc");
        String ruc = "1234";
        Proveedor instance = new Proveedor();
        instance.setRuc(ruc);
    }

    /**
     * Test of getNombre method, of class Proveedor.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Proveedor instance = new Proveedor();
        String expResult = "Proveedor";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNombre method, of class Proveedor.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "Proveedor1";
        Proveedor instance = new Proveedor();
        instance.setNombre(nombre);
        
    }

    /**
     * Test of getTelefono method, of class Proveedor.
     */
    @Test
    public void testGetTelefono() {
        System.out.println("getTelefono");
        Proveedor instance = new Proveedor();
        String expResult = null;
        String result = instance.getTelefono();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setTelefono method, of class Proveedor.
     */
    @Test
    public void testSetTelefono() {
        System.out.println("setTelefono");
        String telefono = null;
        Proveedor instance = new Proveedor();
        instance.setTelefono(telefono);
        
    }

    /**
     * Test of getDireccion method, of class Proveedor.
     */
    @Test
    public void testGetDireccion() {
        System.out.println("getDireccion");
        Proveedor instance = new Proveedor();
        String expResult = null;
        String result = instance.getDireccion();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setDireccion method, of class Proveedor.
     */
    @Test
    public void testSetDireccion() {
        System.out.println("setDireccion");
        String direccion = "";
        Proveedor instance = new Proveedor();
        instance.setDireccion(direccion);
        
    }
    
}
