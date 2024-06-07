/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bia
 */

/**
 * La clase ProveedorApp proporciona métodos para interactuar con la tabla 'Proveedor' en la base de datos.
 * Permite registrar, listar, eliminar y modificar proveedores.
 * Requiere una conexión a la base de datos para funcionar correctamente.
 */
public class ProveedorApp {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    /**
     * Registra un nuevo proveedor en la base de datos.
     * @param pr El proveedor a registrar.
     * @return true si el proveedor se registra correctamente, false en caso contrario.
     */
    public boolean RegistrarProveedor(Proveedor pr){
        String sql = "INSERT INTO Proveedor(ruc, nombre, telefono, direccion) VALUES(?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            closeResources();
        }
    }
    
    /**
     * Obtiene una lista de todos los proveedores almacenados en la base de datos.
     * @return Una lista de objetos Proveedor.
     */
    public List ListarProveedor(){
        List<Proveedor> Listapr = new ArrayList();
        String sql = "SELECT * FROM Proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Proveedor pr = new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setRuc(rs.getString("ruc"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getString("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                Listapr.add(pr);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
            closeResources();
        }
        return Listapr;
    }
    
    /**
     * Elimina un proveedor de la base de datos según su ID.
     * @param id El ID del proveedor a eliminar.
     * @return true si el proveedor se elimina correctamente, false en caso contrario.
     */
    public boolean EliminarProveedor(int id){
        String sql = "DELETE FROM Proveedor WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            closeResources();
        }
    }
    
    /**
     * Modifica la información de un proveedor en la base de datos.
     * @param pr El proveedor con la información actualizada.
     * @return true si la modificación se realiza correctamente, false en caso contrario.
     */
    public boolean ModificarProveedor(Proveedor pr){
        String sql = "UPDATE Proveedor SET ruc=?, nombre=?, telefono=?, direccion=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setInt(5, pr.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            closeResources();
        }
    }
    
    /**
     * Cierra todos los recursos utilizados por la conexión a la base de datos.
     */
    private void closeResources() {
    try {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (con != null) con.close();
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
}
}

