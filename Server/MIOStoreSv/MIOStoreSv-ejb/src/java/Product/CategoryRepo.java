package Product;


import User.Customer;
import Dao.*;
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
@Path("/category")
@Stateless
public class CategoryRepo {

    private CategoryDao categoryDao = CategoryDao.getInstance();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    public List<Categorie> getAll() {
          return categoryDao.getAll();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Categorie get(@PathParam("id") int id) {
        return categoryDao.getCategorybyID(id);
         
    }

//    @POST
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
//    public User getCustomer(User customer) {
//        customer = customerDao.insert(customer);
//        return customer;
//    }
//
//    @PUT
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
//    public boolean updateCustomer(User customer) {
//        return customerDao.update(customer);
//    }
//
//    @DELETE
//    @Path("/{id}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
//    public boolean deleteCustomer(@PathParam("id") int id) {
//        return customerDao.delete(id);
//    }

}
