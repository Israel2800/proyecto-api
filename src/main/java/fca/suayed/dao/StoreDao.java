package fca.suayed.dao;


import fca.suayed.dto.ClientDto;
import fca.suayed.dto.ProductDto;
import jakarta.ws.rs.client.Client;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface StoreDao {
    // GET Productos
    @RegisterBeanMapper(ProductDto.class)
    @SqlQuery("SELECT * FROM productos")
    List<ProductDto> getProducts();

    // POST Producto
    @SqlUpdate("INSERT INTO productos (nombre, descripcion, precio, cantidad, sku) VALUES(:p.name, :p.description, :p.price, :p.quantity, :p.sku)")
    void addProduct(@BindBean("p") ProductDto productDto);

    // DELETE Producto
    @SqlUpdate("DELETE FROM productos WHERE id = :id")
    boolean deleteProduct(@Bind("id") int id);
    
    // GET Clientes
    @RegisterBeanMapper(ClientDto.class)
    @SqlQuery("SELECT * FROM clientes")
    List<ClientDto> getClients();

    // POST Cliente
    @SqlUpdate("INSERT INTO clientes (nombre, apellido_paterno, apellido_materno, rfc) VALUES(:p.name, :p.lastName1, :p.lastName2, :p.rfc)")
    void addClient(@BindBean("p") ClientDto clientDto);

    // DELETE Cliente
    @SqlUpdate("DELETE FROM clientes WHERE id = :id")
    boolean deleteClient(@Bind("id") int id);
}
