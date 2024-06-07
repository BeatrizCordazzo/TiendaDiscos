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
 * La clase Pedidos representa un pedido con sus atributos principales.
 */
public class Pedidos {
    private int id;
    private String cliente;
    private String vendedor;
    private double total;
    
    
    /**
     * Constructor por defecto de la clase Pedidos.
     */
    public Pedidos(){
        
    }

    /**
     * Constructor con parámetros de la clase Pedidos.
     * @param id El identificador del pedido.
     * @param cliente El nombre del cliente que realizó el pedido.
     * @param vendedor El nombre del vendedor que procesó el pedido.
     * @param total El total del pedido.
     */
    public Pedidos(int id, String cliente, String vendedor, double total) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.total = total;
    }

    /**
     * Obtiene el identificador del pedido.
     * @return El identificador del pedido.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del pedido.
     * @param id El identificador del pedido.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente que realizó el pedido.
     * @return El nombre del cliente.
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Establece el nombre del cliente que realizó el pedido.
     * @param cliente El nombre del cliente.
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtiene el nombre del vendedor que procesó el pedido.
     * @return El nombre del vendedor.
     */
    public String getVendedor() {
        return vendedor;
    }

    /**
     * Establece el nombre del vendedor que procesó el pedido.
     * @param vendedor El nombre del vendedor.
     */
    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * Obtiene el total del pedido.
     * @return El total del pedido.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Establece el total del pedido.
     * @param total El total del pedido.
     */
    public void setTotal(double total) {
        this.total = total;
    }
    
    
}