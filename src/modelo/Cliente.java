/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Bia
 */

/**
 * La clase Cliente representa un cliente con sus atributos principales.
 */
public class Cliente {
    private int id;
    private String dni;
    private String nombre;
    private String telefono;
    private String direccion;
    
    /**
     * Constructor por defecto de la clase Cliente.
     */
    public Cliente(){
    }

    /**
     * Constructor con parámetros de la clase Cliente.
     * @param id El identificador del cliente.
     * @param dni El DNI del cliente.
     * @param nombre El nombre del cliente.
     * @param telefono El teléfono del cliente.
     * @param direccion La dirección del cliente.
     */
    public Cliente(int id, String dni, String nombre, String telefono, String direccion) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    /**
     * Obtiene el identificador del cliente.
     * @return El identificador del cliente.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del cliente.
     * @param id El identificador del cliente.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el DNI del cliente.
     * @return El DNI del cliente.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del cliente.
     * @param dni El DNI del cliente.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     * @param nombre El nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el teléfono del cliente.
     * @return El teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del cliente.
     * @param telefono El teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la dirección del cliente.
     * @return La dirección del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del cliente.
     * @param direccion La dirección del cliente.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}