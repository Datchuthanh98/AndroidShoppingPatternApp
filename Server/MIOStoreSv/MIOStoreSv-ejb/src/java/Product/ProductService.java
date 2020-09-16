package Product;


import User.User;
import User.CustomerDao;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
@Path("/product")
@Stateless
public class ProductService {

    private ProductInterface productDao = ProductDao.getInstance();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    public List<Product> GetAllProducts() {
          return productDao.getAll();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam("id") int id) {
        return productDao.getbyID(id);
     
    }
    
      @GET
    @Path("/category/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Product> getlistProductbyCategoryID(@PathParam("id") int id) {
        return productDao.getbyCategoryID(id);
    }
    
       @GET
    @Path("/name/{search}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Product> getlistProductbySearchName(@PathParam("search") String search) throws SQLException {
        return productDao.getbySearchName(search.toString().trim());
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    public boolean insert(Product product) throws SQLException {
        return  productDao.insert(product);
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
     public boolean deleteProduct(@PathParam("id") int id) throws SQLException {
         System.out.println("service"+id);
        return  productDao.delete(id);
    }


}
