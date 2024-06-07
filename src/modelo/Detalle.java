/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Bia
 */
public class Detalle {
    private int id;
    private String cod_disco;
    private int cantidad;
    private double precio;
    private int id_pedido;
    
    public Detalle(){
        
    }

    public Detalle(int id, String cod_disco, int cantidad, double precio, int id_pedido) {
        this.id = id;
        this.cod_disco = cod_disco;
        this.cantidad = cantidad;
        this.precio = precio;
        this.id_pedido = id_pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCod_disco() {
        return cod_disco;
    }

    public void setCod_disco(String cod_disco) {
        this.cod_disco = cod_disco;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    
}
