package Order;


import Order.CartItem;
import Order.OrderInforDao;
import Order.OrderInfor;
import Dao.*;
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
@Path("/order")
@Stateless
public class OrderService {

    private OrderInforDao orderDao = OrderInforDao.getInstance();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    public List<OrderInfor> getAllListOrder() {
          return orderDao.getAll();
    }

    @GET
    @Path("/customer/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<OrderInfor> getListOrderbyCustomer(@PathParam("id") int id) {
        return orderDao.getOrderbyCustomerID(id) ;
    }
    
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderInfor getOrderInfor(@PathParam("id") int id) {
        return orderDao.getOrderbyOrderID(id) ;
    }
    
      
    @GET
    @Path("cart/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<CartItem> getListCartByOderID(@PathParam("id") int id) {
        return orderDao.getListCartItemByOrderID(id) ;
    }
    

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    public boolean inserOrder(OrderInfor order) throws SQLException {
        return orderDao.insert(order);

    }
    
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
