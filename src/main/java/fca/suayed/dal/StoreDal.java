package fca.suayed.dal;

import fca.suayed.dao.StoreDao;
import fca.suayed.dto.ClientDto;
import fca.suayed.dto.ProductDto;
import fca.suayed.dto.ResponseDto;
import fca.suayed.services.JdbiService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import org.jdbi.v3.core.Jdbi;

import java.util.List;


@ApplicationScoped
public class StoreDal {

    private static final Logger LOGGER = Logger.getLogger(StoreDal.class);

    @Inject
    JdbiService jdbiService;
    // GET Productos
    public ResponseDto<List<ProductDto>> getProducts() {

        ResponseDto responseDto = new ResponseDto<List<ProductDto>>();
        responseDto.setSuccess(true);
        Jdbi jdbi = jdbiService.getInstance();
        var products = jdbi.withExtension(StoreDao.class, dao -> dao.getProducts());
        responseDto.setData(products);
        return responseDto;
    }

    // POST Producto
    public ResponseDto<String> addProduct(final ProductDto productDto) {

        ResponseDto responseDto = new ResponseDto<String>();
        responseDto.setSuccess(false);

        Jdbi jdbi = jdbiService.getInstance();
        jdbi.useExtension(StoreDao.class, dao -> {
            dao.addProduct(productDto);
            responseDto.setSuccess(true);
            responseDto.setData("ok");
        });

        return responseDto;
    }

    // DELETE Producto
    public ResponseDto<String> deleteProduct(final int id) {
        ResponseDto<String> responseDto = new ResponseDto<>();
        Jdbi jdbi = jdbiService.getInstance();
        boolean isDeleted = jdbi.withExtension(StoreDao.class, dao -> dao.deleteProduct(id));
        if (isDeleted) {
            responseDto.setSuccess(true);
            responseDto.setData("Product successfully deleted");
        } else {
            responseDto.setSuccess(false);
            responseDto.setData("Product not found");
        }
        return responseDto;
    }

    // GET Clientes
    public ResponseDto<List<ClientDto>> getClients(){
        ResponseDto responseDto = new ResponseDto<List<ClientDto>>();
        responseDto.setSuccess(true);
        Jdbi jdbi = jdbiService.getInstance();
        var clients = jdbi.withExtension(StoreDao.class, dao -> dao.getClients());
        responseDto.setData(clients);
        return responseDto;
    }

    // POST Cliente
    public ResponseDto<String> addClient(final ClientDto clientDto){
        ResponseDto responseDto = new ResponseDto<String>();
        responseDto.setSuccess(false);
        Jdbi jdbi = jdbiService.getInstance();
        jdbi.useExtension(StoreDao.class, dao ->{
            dao.addClient(clientDto);
            responseDto.setSuccess(true);
            responseDto.setData("ok");
        });
        return responseDto;
    }

    // DELETE Cliente
    public ResponseDto<String> deleteClient(final int id) {
        ResponseDto<String> responseDto = new ResponseDto<>();
        Jdbi jdbi = jdbiService.getInstance();
        boolean isDeleted = jdbi.withExtension(StoreDao.class, dao -> dao.deleteClient(id));
        if (isDeleted) {
            responseDto.setSuccess(true);
            responseDto.setData("Client successfully deleted");
        } else {
            responseDto.setSuccess(false);
            responseDto.setData("Client not found");
        }
        return responseDto;
    }

}
