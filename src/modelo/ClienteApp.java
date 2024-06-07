/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.tree.ExpandVetoException;

/**
 *
 * @author Bia
 */

/**
 * La clase ClienteApp proporciona métodos para interactuar con la tabla 'Clientes' en la base de datos.
 * Permite registrar, listar, eliminar, modificar y buscar clientes.
 */
public class ClienteApp {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    /**
     * Registra un nuevo cliente en la base de datos.
     * @param cl El objeto Cliente que representa el cliente a registrar.
     * @return true si el cliente se registra correctamente, false en caso contrario.
     */
    public boolean RegistarCliente(Cliente cl){
        String sql = "INSERT INTO Clientes (dni, nombre, telefono, direccion) VALUES (?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.execute();
            return true;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try{
                con.close();
            } catch (SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    
    /**
     * Obtiene una lista de todos los clientes almacenados en la base de datos.
     * @return Una lista de objetos Cliente.
     */
    public List ListarCliente(){
        List<Cliente> ListaCl = new ArrayList();
        String sql = "SELECT * FROM Clientes";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cl = new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setDni(rs.getString("dni"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                ListaCl.add(cl);
            }
        } catch(Exception e){
            System.out.println(e.toString());
        }
        return ListaCl;
    }
    
    /**
     * Elimina un cliente de la base de datos según su ID.
     * @param id El ID del cliente a eliminar.
     * @return true si el cliente se elimina correctamente, false en caso contrario.
     */
    public boolean EliminarCliente(int id){
        String sql = "DELETE FROM Clientes WHERE id = ?";
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
    
    /**
     * Modifica los datos de un cliente en la base de datos.
     * @param cl El objeto Cliente que representa el cliente con los datos modificados.
     * @return true si los datos del cliente se modifican correctamente, false en caso contrario.
     */
    public boolean ModificarCliente(Cliente cl){
        String sql = "UPDATE Clientes SET dni=?, nombre=?, telefono=?, direccion=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setInt(5, cl.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    /**
     * Busca un cliente en la base de datos según su DNI.
     * @param dni El DNI del cliente a buscar.
     * @return El objeto Cliente encontrado, o un objeto Cliente vacío si no se encuentra ningún cliente con ese DNI.
     */
    public Cliente BuscarCliente(int dni){
        Cliente cl = new Cliente();
        String sql = "SELECT * FROM Clientes WHERE dni=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cl;
    }
}
