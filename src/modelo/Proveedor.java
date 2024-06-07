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
 * La clase Proveedor representa un proveedor con sus atributos principales.
 */
public class Proveedor {

    private int id; // Identificador del proveedor
    private String ruc; // RUC del proveedor
    private String nombre; // Nombre del proveedor
    private String telefono; // Número de teléfono del proveedor
    private String direccion; // Dirección del proveedor

    /**
     * Constructor por defecto de la clase Proveedor.
     */
    public Proveedor() {

    }

    /**
     * Constructor con parámetros de la clase Proveedor.
     *
     * @param id El identificador del proveedor.
     * @param ruc El RUC del proveedor.
     * @param nombre El nombre del proveedor.
     * @param telefono El número de teléfono del proveedor.
     * @param direccion La dirección del proveedor.
     */
    public Proveedor(int id, String ruc, String nombre, String telefono, String direccion) {
        this.id = id;
        this.ruc = ruc;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    /**
     * Obtiene el identificador del proveedor.
     *
     * @return El identificador del proveedor.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del proveedor.
     *
     * @param id El identificador del proveedor.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el RUC del proveedor.
     *
     * @return El RUC del proveedor.
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * Establece el RUC del proveedor.
     *
     * @param ruc El RUC del proveedor.
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    /**
     * Obtiene el nombre del proveedor.
     *
     * @return El nombre del proveedor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del proveedor.
     *
     * @param nombre El nombre del proveedor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el número de teléfono del proveedor.
     *
     * @return El número de teléfono del proveedor.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del proveedor.
     *
     * @param telefono El número de teléfono del proveedor.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la dirección del proveedor.
     *
     * @return La dirección del proveedor.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del proveedor.
     *
     * @param direccion La dirección del proveedor.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
