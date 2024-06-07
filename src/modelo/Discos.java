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
 * La clase Discos representa un disco con sus atributos principales.
 */
public class Discos {

    private int id; // Identificador del disco
    private String codigo; // Código del disco
    private String nombre; // Nombre del disco
    private String proveedor; // Proveedor del disco
    private int stock; // Stock disponible del disco
    private double precio; // Precio del disco

    /**
     * Constructor por defecto de la clase Discos.
     */
    public Discos() {

    }

    /**
     * Constructor con parámetros de la clase Discos.
     *
     * @param id El identificador del disco.
     * @param codigo El código del disco.
     * @param nombre El nombre del disco.
     * @param proveedor El proveedor del disco.
     * @param stock El stock disponible del disco.
     * @param precio El precio del disco.
     */
    public Discos(int id, String codigo, String nombre, String proveedor, int stock, double precio) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.stock = stock;
        this.precio = precio;
    }

    /**
     * Obtiene el identificador del disco.
     * @return El identificador del disco.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del disco.
     * @param id El identificador del disco.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el código del disco.
     * @return El código del disco.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código del disco.
     * @param codigo El código del disco.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre del disco.
     * @return El nombre del disco.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del disco.
     * @param nombre El nombre del disco.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el proveedor del disco.
     * @return El proveedor del disco.
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * Establece el proveedor del disco.
     * @param proveedor El proveedor del disco.
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Obtiene el stock disponible del disco.
     * @return El stock disponible del disco.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece el stock disponible del disco.
     * @param stock El stock disponible del disco.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Obtiene el precio del disco.
     * @return El precio del disco.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del disco.
     * @param precio El precio del disco.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
