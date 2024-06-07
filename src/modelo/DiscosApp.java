/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Bia
 */

/**
 * La clase DiscosApp proporciona métodos para interactuar con la tabla 'Discos' y 'Empresa' en la base de datos.
 * Permite registrar, listar, eliminar, modificar y buscar discos.
 * También proporciona métodos para buscar y modificar datos de la empresa.
 */
public class DiscosApp {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    /**
     * Registra un nuevo disco en la base de datos.
     * @param dis El disco a registrar.
     * @return true si el disco se registra correctamente, false en caso contrario.
     */
    public boolean RegistrarDiscos(Discos dis){
        String sql = "INSERT INTO Discos(codigo, nombre, proveedor, stock, precio) VALUES(?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dis.getCodigo());
            ps.setString(2, dis.getNombre());
            ps.setString(3, dis.getProveedor());
            ps.setInt(4, dis.getStock());
            ps.setDouble(5, dis.getPrecio());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    /**
     * Consulta los nombres de los proveedores y los agrega a un JComboBox.
     * @param proveedor JComboBox para agregar los nombres de los proveedores.
     */
    public void ConsultarProveedor(JComboBox proveedor){
        String sql = "SELECT nombre FROM Proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                proveedor.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    /**
     * Obtiene una lista de todos los discos almacenados en la base de datos.
     * @return Una lista de objetos Discos.
     */
    public List ListarDiscos(){
        List<Discos> ListaDis = new ArrayList();
        String sql = "SELECT * FROM Discos";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Discos dis = new Discos();
                dis.setId(rs.getInt("id"));
                dis.setCodigo(rs.getString("codigo"));
                dis.setNombre(rs.getString("nombre"));
                dis.setProveedor(rs.getString("proveedor"));
                dis.setStock(rs.getInt("stock"));
                dis.setPrecio(rs.getDouble("precio"));
                ListaDis.add(dis);
            }
        } catch(SQLException e){
            System.out.println(e.toString());
        }
        return ListaDis;
    }
    
    /**
     * Elimina un disco de la base de datos según su ID.
     * @param id El ID del disco a eliminar.
     * @return true si el disco se elimina correctamente, false en caso contrario.
     */
    public boolean EliminarDiscos(int id){
        String sql = "DELETE FROM Discos WHERE id = ?";
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
     * Modifica la información de un disco en la base de datos.
     * @param dis El disco con la información actualizada.
     * @return true si la modificación se realiza correctamente, false en caso contrario.
     */
    public boolean ModificarDiscos(Discos dis){
        String sql = "UPDATE Discos SET codigo=?, nombre=?, proveedor=?, stock=?, precio=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, dis.getCodigo());
            ps.setString(2, dis.getNombre());
            ps.setString(3, dis.getProveedor());
            ps.setInt(4, dis.getStock());
            ps.setDouble(5, dis.getPrecio());
            ps.setInt(6, dis.getId());
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
     * Busca un disco en la base de datos según su código.
     * @param cod El código del disco a buscar.
     * @return Un objeto Discos con la información del disco encontrado.
     */
    public Discos BuscarDis(String cod){
        Discos disco = new Discos();
        String sql = "SELECT * FROM Discos WHERE codigo=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                disco.setNombre(rs.getString("nombre"));
                disco.setPrecio(rs.getDouble("precio"));
                disco.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return disco;
    }
    
    
    /**
     * Busca y obtiene los datos de la empresa almacenados en la base de datos.
     * @return Un objeto Datos con la información de la empresa.
     */
    public Datos BuscarDatos(){
        Datos datos = new Datos();
        String sql = "SELECT * FROM Empresa";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                datos.setId(rs.getInt("id"));
                datos.setRuc(rs.getString("ruc"));
                datos.setNombre(rs.getString("nombre"));
                datos.setTelefono(rs.getString("telefono"));
                datos.setDireccion(rs.getString("direccion"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return datos;
    }
    
    /**
     * Modifica los datos de la empresa en la base de datos.
     * @param dat Objeto Datos con la información actualizada de la empresa.
     * @return true si la modificación se realiza correctamente, false en caso contrario.
     */
    public boolean ModificarDatos(Datos dat){
        String sql = "UPDATE Empresa SET ruc=?, nombre=?, telefono=?, direccion=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, dat.getRuc());
            ps.setString(2, dat.getNombre());
            ps.setString(3, dat.getTelefono());
            ps.setString(4, dat.getDireccion());
            ps.setInt(5, dat.getId());
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

}
