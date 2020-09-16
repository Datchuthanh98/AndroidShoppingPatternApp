
import Shipment.ShippingService;
import Payment.PaymentService;
import Order.OrderService;
import Product.ProductService;
import Product.CategoryRepo;
import User.CustomerService;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
@ApplicationPath("/rest")
public class MyApplication extends Application {

    private Set<Object> singletons = new HashSet();

    public MyApplication() {
        singletons.add(new CustomerService());
        singletons.add(new ProductService());
        singletons.add(new OrderService());
        singletons.add(new ShippingService());
        singletons.add(new PaymentService());
         singletons.add(new CategoryRepo());
        

    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}
