/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package modelo;

import modelo.Conexion;
import modelo.Proveedor;
import modelo.ProveedorApp;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Bia
 */
public class ProveedorAppTest {
    private ProveedorApp proveedorApp;
    private static Conexion conexion;
    
    public ProveedorAppTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        if (conexion != null) {
            conexion.close();
        }
    }
    
    @Before
    public void setUp() {
        conexion = new Conexion();
        proveedorApp = new ProveedorApp();
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of RegistrarProveedor method, of class ProveedorApp.
     */
    @Test
    public void testRegistrarProveedor() {
        System.out.println("RegistrarProveedor");
        Proveedor pr = new Proveedor(); //
        pr.setRuc("12345678901"); //
        pr.setNombre("Proveedor Test"); //
        pr.setTelefono("123456789"); //
        pr.setDireccion("Dirección Test"); //
         
        ProveedorApp instance = new ProveedorApp();
        boolean expResult = true;
        boolean result = instance.RegistrarProveedor(pr);
        assertEquals(expResult, result);
    }

    /**
     * Test of ListarProveedor method, of class ProveedorApp.
     */
    @Test
    public void testListarProveedor() {
        System.out.println("ListarProveedor");
        ProveedorApp instance = new ProveedorApp(); //
        List<Proveedor> result = instance.ListarProveedor(); //
        assertNotNull(result); //
        assertFalse(result.isEmpty()); //
    }

    /**
     * Test of EliminarProveedor method, of class ProveedorApp.
     */
    @Test
    public void testEliminarProveedor() {
        System.out.println("EliminarProveedor");
        int id = 1;
        boolean result = proveedorApp.EliminarProveedor(id);
        assertTrue(result);
    }

    /**
     * Test of ModificarProveedor method, of class ProveedorApp.
     */
    @Test
    public void testModificarProveedor() {
        System.out.println("ModificarProveedor");
        Proveedor pr = new Proveedor(); //
        pr.setId(1);
        pr.setRuc("12345678901");
        pr.setNombre("Proveedor Modificado");
        pr.setTelefono("987654321");
        pr.setDireccion("Nueva Dirección");
        
        ProveedorApp instance = new ProveedorApp();
        boolean result = proveedorApp.ModificarProveedor(pr);
        assertTrue(result);
    }
    
}
