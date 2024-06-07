/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bia
 */

/**
 * La clase PedidosApp proporciona métodos para interactuar con la tabla 'Pedidos' y 'Detalle_pedidos' en la base de datos.
 * Permite registrar, listar y eliminar pedidos, así como actualizar el stock de discos y obtener el ID máximo de un pedido.
 */
public class PedidosApp {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    
    /**
     * Obtiene el ID máximo de los pedidos almacenados en la base de datos.
     * @return El ID máximo de los pedidos.
     */
    public int IdPedido(){
        int id = 0;
        String sql = "SELECT MAX(id) FROM pedidos";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }
    
    /**
     * Registra un nuevo pedido en la base de datos.
     * @param p El objeto Pedidos que representa el pedido a registrar.
     * @return El resultado de la operación de registro.
     */
    public int RegistrarPedidos(Pedidos p){
        String sql = "INSERT INTO Pedidos (cliente, vendedor, total) VALUES (?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCliente());
            ps.setString(2, p.getVendedor());
            ps.setDouble(3, p.getTotal());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return r;
    }
    
    /**
     * Registra el detalle de un pedido en la base de datos.
     * @param Dv El objeto Detalle que representa el detalle del pedido a registrar.
     * @return El resultado de la operación de registro.
     */
    public int RegistrarDetalle(Detalle Dv){
        String sql = "INSERT INTO Detalle_pedidos (cod_disco, cantidad, precio, id_pedido) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Dv.getCod_disco());
            ps.setInt(2, Dv.getCantidad());
            ps.setDouble(3, Dv.getPrecio());
            ps.setInt(4, Dv.getId());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());    
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }
    
    /**
     * Actualiza el stock de un disco en la base de datos.
     * @param cant La nueva cantidad en stock del disco.
     * @param cod El código del disco cuyo stock se actualizará.
     * @return true si la actualización se realiza correctamente, false en caso contrario.
     */
    public boolean ActualizarStock(int cant, String cod){
        String sql = "UPDATE Discos SET stock=? WHERE codigo=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setString(2, cod);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    /**
     * Obtiene una lista de todos los pedidos almacenados en la base de datos.
     * @return Una lista de objetos Pedidos.
     */
    public List ListarPedidos(){
        List<Pedidos> ListaPedidos = new ArrayList();
        String sql = "SELECT * FROM Pedidos";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Pedidos ped = new Pedidos();
                ped.setId(rs.getInt("id"));
                ped.setCliente(rs.getString("cliente"));
                ped.setVendedor(rs.getString("vendedor"));
                ped.setTotal(rs.getDouble("total"));
                ListaPedidos.add(ped);
            }
        } catch(SQLException e){
            System.out.println(e.toString());
        }
        return ListaPedidos;
    }
    
    /**
     * Elimina un pedido de la base de datos según su ID.
     * @param id El ID del pedido a eliminar.
     * @return true si el pedido se elimina correctamente, false en caso contrario.
     */
    public boolean EliminarPedidos(int id){
        String sql = "DELETE FROM Pedidos WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
