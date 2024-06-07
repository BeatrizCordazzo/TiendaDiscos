/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package modelo;

import com.mysql.jdbc.Connection;
import java.util.List;
import javax.swing.JComboBox;
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
public class DiscosAppTest {
    private DiscosApp discosApp;
    private Conexion conexion;
    private Connection con;
    
    public DiscosAppTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        discosApp = new DiscosApp();
        conexion = new Conexion();
        con = (Connection) conexion.getConnection();
        assertNotNull("Conexión a la base de datos fallida", con);
    }
    
    @After
    public void tearDown() {
        if (conexion != null) {
            conexion.close();
        }
    }

    /**
     * Test of RegistrarDiscos method, of class DiscosApp.
     */
    @Test
    public void testRegistrarDiscos() {
        Discos disco = new Discos();
        disco.setCodigo("12345");
        disco.setNombre("Disco Test");
        disco.setPrecio(9.99);

        boolean result = discosApp.RegistrarDiscos(disco);
        assertTrue(result);
    }

    /**
     * Test of ConsultarProveedor method, of class DiscosApp.
     */
    @Test
    public void testConsultarProveedor() {
        System.out.println("ConsultarProveedor");
        JComboBox proveedor = null;
        DiscosApp instance = new DiscosApp();
        instance.ConsultarProveedor(proveedor);
    }

    /**
     * Test of ListarDiscos method, of class DiscosApp.
     */
    @Test
    public void testListarDiscos() {
        System.out.println("ListarDiscos");
        DiscosApp instance = new DiscosApp();
        List expResult = null;
        List result = instance.ListarDiscos();
        assertEquals(expResult, result);
    }

    /**
     * Test of EliminarDiscos method, of class DiscosApp.
     */
    @Test
    public void testEliminarDiscos() {
        System.out.println("EliminarDiscos");
        int id = 0;
        DiscosApp instance = new DiscosApp();
        boolean expResult = false;
        boolean result = instance.EliminarDiscos(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of ModificarDiscos method, of class DiscosApp.
     */
    @Test
    public void testModificarDiscos() {
        System.out.println("ModificarDiscos");
        Discos dis = null;
        DiscosApp instance = new DiscosApp();
        boolean expResult = false;
        boolean result = instance.ModificarDiscos(dis);
        assertEquals(expResult, result);
    }

    /**
     * Test of BuscarDis method, of class DiscosApp.
     */
    @Test
    public void testBuscarDis() {
        System.out.println("BuscarDis");
        String cod = ""; 
        DiscosApp instance = new DiscosApp();
        Discos expResult = null;
        Discos result = instance.BuscarDis(cod);
        assertEquals(expResult, result);
    }

    /**
     * Test of BuscarDatos method, of class DiscosApp.
     */
    @Test
    public void testBuscarDatos() {
        System.out.println("BuscarDatos");
        DiscosApp instance = new DiscosApp();
        Datos expResult = null;
        Datos result = instance.BuscarDatos();
        assertEquals(expResult, result);
    }

    /**
     * Test of ModificarDatos method, of class DiscosApp.
     */
    @Test
    public void testModificarDatos() {
        Discos disco = new Discos();
        disco.setCodigo("12345");
        disco.setNombre("Disco Modificado");
        disco.setPrecio(19.99);
    }
    
}
