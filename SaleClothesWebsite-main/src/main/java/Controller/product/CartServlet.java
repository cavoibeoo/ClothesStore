package Controller.product;

import JPAConfig.JPAConfig;
import entity.CustomerProductEntity;
import entity.CustomerProductEntityPK;
import entity.CustomeraccountEntity;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if (session.getAttribute("user")==null) {
            String url = "/login";
            request.getRequestDispatcher(url).forward(request, response);
        }
        else {
            String action = request.getParameter("action");
            if (action != null) {
                if (action.equalsIgnoreCase("buy") || action.equalsIgnoreCase("add")) {
                    doPost_AddCart(request, response);
                } else if (action.equalsIgnoreCase("remove")) {
                    doPost_RemoveItem(request, response, null);
                } else if (action.equalsIgnoreCase("getCart")){
                    doPost_GetCart(request, response);
                }
            }
            doPost_DisplayCart(request, response);
        }
    }
    
    public static void doPost_GetCart(HttpServletRequest request, HttpServletResponse response){
        //      Get user from Session
        HttpSession session = request.getSession();
        CustomeraccountEntity customer = (CustomeraccountEntity) session.getAttribute("user");
        
        //      Get customer cart
        TypedQuery<CustomerProductEntity> getCustomerCartQuery =
                JPAConfig.getEntityManager().createNamedQuery("getCustomerCart", CustomerProductEntity.class);
        getCustomerCartQuery.setParameter(1, customer.getCustomerId());
        List<CustomerProductEntity> customerCart = getCustomerCartQuery.getResultList();
        
        session.setAttribute("cart", customerCart);
    }
    
    protected void doPost_DisplayCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        HttpSession session = request.getSession();
        doPost_GetCart(request,response);
        List<CustomerProductEntity> customerCart = (List<CustomerProductEntity>) session.getAttribute("cart");
        
        request.setAttribute("customerCart", customerCart);
        String url = "/shoping-cart.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }
    
    protected void doPost_AddCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        // Get product id and quantity from request
        String id2 = request.getParameter("id");
        
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int size = Integer.parseInt(request.getParameter("size"));
        int color = Integer.parseInt(request.getParameter("color"));
        
        // Get user from session
        HttpSession session = request.getSession();
        CustomeraccountEntity user = (CustomeraccountEntity)session.getAttribute("user");
        
        // Create the pk for the cart items to find item
        CustomerProductEntityPK pk = new CustomerProductEntityPK();
        pk.setProductId(id);
        pk.setColorId(color);
        pk.setSizeId(size);
        pk.setCustomerId(user.getCustomerId());
        
        // Find items if exist then increase quantity else add new item
        CustomerProductEntity modifiedProduct = entityManager.find(CustomerProductEntity.class, pk);
        if (modifiedProduct !=null && modifiedProduct.getSizeId()==size
                    && modifiedProduct.getColorId()==color){
            modifiedProduct.setQuantity(modifiedProduct.getQuantity() + quantity);
            //If after-changed product has quantity <=0 then remove it
            if (modifiedProduct.getQuantity() <=0){
                doPost_RemoveItem(request,response, modifiedProduct);
                return;
            }
        }
        else {
            modifiedProduct = new CustomerProductEntity();
            modifiedProduct.setProductId(pk.getProductId());
            modifiedProduct.setCustomerId(pk.getCustomerId());
            modifiedProduct.setQuantity(quantity);
            modifiedProduct.setSizeId(size);
            modifiedProduct.setColorId(color);
        }
        
        transaction.begin();
        entityManager.merge(modifiedProduct);
        transaction.commit();
    }
    
    protected void doPost_RemoveItem(HttpServletRequest request, HttpServletResponse response,
                                     CustomerProductEntity modifiedProduct)
            throws ServletException, IOException{
        
        if (modifiedProduct == null){
            modifiedProduct = new CustomerProductEntity();
            HttpSession session = request.getSession();
            CustomeraccountEntity user = (CustomeraccountEntity)session.getAttribute("user");
            // Get product id and quantity from request
            modifiedProduct.setProductId(Integer.parseInt(request.getParameter("id")));
            modifiedProduct.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            modifiedProduct.setColorId(Integer.parseInt(request.getParameter("color")));
            modifiedProduct.setSizeId(Integer.parseInt(request.getParameter("size")));
            modifiedProduct.setCustomerId(user.getCustomerId());
        }
        
        transaction.begin();
        entityManager.remove(modifiedProduct);
        transaction.commit();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
}
