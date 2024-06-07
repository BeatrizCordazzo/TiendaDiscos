/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package modelo;

import com.mysql.jdbc.Connection;
import java.util.List;
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
public class PedidosAppTest {
    private PedidosApp instance;
    private Conexion conexion;
    private Connection con;
    
    public PedidosAppTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new PedidosApp();
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
     * Test of IdPedido method, of class PedidosApp.
     */
    @Test
    public void testIdPedido() {
        System.out.println("IdPedido");
        PedidosApp instance = new PedidosApp();
        int expResult = 0;
        int result = instance.IdPedido();
        assertEquals(expResult, result);
    }

    /**
     * Test of RegistrarPedidos method, of class PedidosApp.
     */
    @Test
    public void testRegistrarPedidos() {
        System.out.println("RegistrarPedidos");
        Pedidos p = new Pedidos();
        p.setId(1);
        p.setCliente("Cliente Test");
        int result = instance.RegistrarPedidos(p);
        assertTrue("El resultado de la inserción debe ser mayor que 0", result > 0);
    }

    /**
     * Test of RegistrarDetalle method, of class PedidosApp.
     */
    @Test
    public void testRegistrarDetalle() {
        System.out.println("RegistrarDetalle");
        Detalle Dv = new Detalle();
        Dv.setId(1);
        Dv.setCantidad(1);
        Dv.setId_pedido(1);
        Dv.setCantidad(2);
        int result = instance.RegistrarDetalle(Dv);
        assertTrue("El resultado de la inserción debe ser mayor que 0", result > 0);
    }

    /**
     * Test of ActualizarStock method, of class PedidosApp.
     */
    @Test
    public void testActualizarStock() {
        System.out.println("ActualizarStock");
        int cant = 5;
        String cod = "12345";
        boolean result = instance.ActualizarStock(cant, cod);
        assertTrue("El stock debe actualizarse correctamente", result);
    }

    /**
     * Test of ListarPedidos method, of class PedidosApp.
     */
    @Test
    public void testListarPedidos() {
        System.out.println("ListarPedidos");
        List<Pedidos> result = instance.ListarPedidos();
        assertNotNull("La lista de pedidos no debe ser null", result);
        assertFalse("La lista de pedidos no debe estar vacía", result.isEmpty());
    }

    /**
     * Test of EliminarPedidos method, of class PedidosApp.
     */
    @Test
    public void testEliminarPedidos() {
        System.out.println("EliminarPedidos");
        int id = 1;
        boolean result = instance.EliminarPedidos(id);
        assertTrue("El pedido debe eliminarse correctamente", result);
    }
    
}
