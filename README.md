# Proyecto de Gestión de Discos y Pedidos

Este proyecto es una aplicación para la gestión de discos, clientes, pedidos y usuarios en una tienda de música. La aplicación permite registrar, modificar, eliminar y consultar discos, clientes y pedidos, así como gestionar el inicio de sesión de los empleados.

## Clases Principales

### Discos
Representa un disco en el inventario.
- **Métodos**:
  - `int getId()`
  - `void setId(int id)`
  - `String getCodigo()`
  - `void setCodigo(String codigo)`
  - `String getNombre()`
  - `void setNombre(String nombre)`
  - `String getProveedor()`
  - `void setProveedor(String proveedor)`
  - `int getStock()`
  - `void setStock(int stock)`
  - `double getPrecio()`
  - `void setPrecio(double precio)`

### Cliente
Representa un cliente.
- **Métodos**:
  - `int getId()`
  - `void setId(int id)`
  - `String getDni()`
  - `void setDni(String dni)`
  - `String getNombre()`
  - `void setNombre(String nombre)`
  - `String getTelefono()`
  - `void setTelefono(String telefono)`
  - `String getDireccion()`
  - `void setDireccion(String direccion)`

### Pedidos
Representa un pedido realizado por un cliente.
- **Métodos**:
  - `int getId()`
  - `void setId(int id)`
  - `String getCliente()`
  - `void setCliente(String cliente)`
  - `String getVendedor()`
  - `void setVendedor(String vendedor)`
  - `double getTotal()`
  - `void setTotal(double total)`

## Ejemplos de Uso

### Crear un Disco
```java
Discos disco = new Discos();
disco.setId(1);
disco.setCodigo("D001");
disco.setNombre("Disco de Rock");
disco.setProveedor("Proveedor 1");
disco.setStock(100);
disco.setPrecio(9.99);

### Registrar un Cliente
```java
Cliente cliente = new Cliente();
cliente.setId(1);
cliente.setDni("12345678");
cliente.setNombre("Juan Perez");
cliente.setTelefono("555-1234");
cliente.setDireccion("Calle Falsa 123");

### Dependencias
JDK: Java Development Kit 8 o superior.
MySQL Connector: Biblioteca para conectarse a bases de datos MySQL.