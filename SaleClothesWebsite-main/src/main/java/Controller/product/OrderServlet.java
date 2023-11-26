package Controller.product;

import JPAConfig.JPAConfig;
import entity.*;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getOrders(req, resp);
        
        String action = req.getParameter("action");
        if (action != null){
            // If current user confirm the oder then create the order
            if (action.equalsIgnoreCase("isConfirmed")){
                action = "create";
            }
            if (action.equalsIgnoreCase("checkOrder")){
                checkOrder(req,resp);
                return;
            }
            else if (action.equalsIgnoreCase("create")){
                createOrder(req, resp);
            }
            else if (action.equalsIgnoreCase("cancel")){
                cancelOrder(req, resp);
            }
        }
        displayOrders(req, resp);
    }
    
    public static void getOrders(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        
        CustomeraccountEntity customer = (CustomeraccountEntity) session.getAttribute("user");
        TypedQuery<OrdersEntity> query = JPAConfig.getEntityManager().createNamedQuery("getCustomerOrders", OrdersEntity.class);
        query.setParameter(1,customer.getCustomerId());
        List<OrdersEntity> customerOrders = query.getResultList();
        session.setAttribute("customerOrders", customerOrders);
    }
    
    protected void displayOrders(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        getOrders(req,resp);
        List<OrdersEntity> customerOrders = (List<OrdersEntity>) session.getAttribute("customerOrders");
        
        String url = "/CustomerAccount.jsp";
        req.getRequestDispatcher(url).forward(req, resp);
    }
    
    protected void checkOrder(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        String[] checkedProducts = req.getParameterValues("checkedProduct");
        CustomeraccountEntity user = (CustomeraccountEntity) session.getAttribute("user");
        CustomerProductEntity[] products = new CustomerProductEntity[checkedProducts.length];
        for (int i=0; i<checkedProducts.length; i++){
            String[] productAttribute = (checkedProducts[i].split(" "));
            CustomerProductEntityPK pk = new CustomerProductEntityPK();
            pk.setCustomerId(user.getCustomerId());
            pk.setProductId(Integer.parseInt(productAttribute[0]));
            pk.setSizeId(Integer.parseInt(productAttribute[1]));
            pk.setColorId(Integer.parseInt(productAttribute[2]));
        
            CustomerProductEntity product = entityManager.find(CustomerProductEntity.class, pk);
            if (product != null){
                products[i] = product;
            }
        }
        
        float tempPrice = 0;
        
        for (CustomerProductEntity product : products) {
            tempPrice += product.assignUnitPrice() * product.getQuantity();
        }
        session.setAttribute("tempOrderPrice",tempPrice);
        session.setAttribute("tempOrder", products);
        
        String url = "/orderdetails.jsp";
        req.getRequestDispatcher(url).forward(req, resp);
    }
    
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        CustomeraccountEntity user = (CustomeraccountEntity) session.getAttribute("user");
        CustomerProductEntity[] products = (CustomerProductEntity[]) session.getAttribute("tempOrder");
        
        for (CustomerProductEntity product : products){
            if (product != null){
                byte stat = 1;
                product.setStatus(stat);
                transaction.begin();
                entityManager.merge(product);
                transaction.commit();
            }
        }
        
        transaction.begin();
        Query query = entityManager.createNativeQuery("CALL PROC_DeleteBoughtInCart(:param1)");
        query.setParameter("param1", user.getCustomerId());
        query.executeUpdate();
        transaction.commit();
        CartServlet.doPost_GetCart(req,resp);
    }
    
    protected void cancelOrder(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        OrdersEntity orders = OrdersEntity.findByID(Integer.parseInt(req.getParameter("orderId")));
        
        if (orders != null){
            Byte tmp = 1;
            orders.setIsCancel(tmp);
            transaction.begin();
            entityManager.merge(orders);
            transaction.commit();
        }
        
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
