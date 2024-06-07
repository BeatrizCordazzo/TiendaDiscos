/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bia
 */

/**
 * La clase LoginApp proporciona métodos para autenticar usuarios y registrar nuevos usuarios en la base de datos.
 * Permite realizar operaciones relacionadas con la tabla 'Empleados'.
 */
public class LoginApp {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    /**
     * Autentica a un usuario en el sistema.
     * @param correo El correo electrónico del usuario.
     * @param contrasena La contraseña del usuario.
     * @return Un objeto de tipo login si la autenticación es exitosa, o un objeto login vacío si no se encuentra el usuario o la contraseña es incorrecta.
     */
    public login log(String correo, String contrasena) {
        login l = new login();
        String sql = "SELECT * FROM Empleados WHERE correo = ? AND contrasena = ?";
        try {
            con = (Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            String encriptedContrasena = Encriptar.hashPassword(contrasena);
            ps.setString(2, contrasena);
            System.out.println("Contraseña encriptada al hacer login: " + encriptedContrasena);
            rs = ps.executeQuery();
            if (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setContrasena(rs.getString("contrasena"));
                l.setCargo(rs.getString("cargo"));
                
                String storedHash = rs.getString("contrasena");
                System.out.println("\nHash almacenado: " + storedHash);
                System.out.println("Hash ingresado: " + encriptedContrasena);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param reg El objeto de tipo login que contiene los datos del usuario a registrar.
     * @return true si el registro se realiza correctamente, false en caso contrario.
     */
    public boolean Registro(login reg) {
        String sql = "INSERT INTO empleados (nombre, cargo, correo, contrasena) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getCargo());
            ps.setString(3, reg.getCorreo());
            String encriptedContrasena = Encriptar.hashPassword(reg.getContrasena());
            ps.setString(4, reg.getContrasena());
            System.out.println("Contraseña encriptada al registrar: " + encriptedContrasena);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
