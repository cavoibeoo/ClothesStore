import JPAConfig.JPAConfig;
import entity.*;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = JPAConfig.getEntityManager();
    
        // Create a query to retrieve OrderProductsEntity by ID
        TypedQuery<OrderProductsEntity> query = entityManager.createQuery(
                "SELECT op FROM OrderProductsEntity op WHERE op.orderId = :id", OrderProductsEntity.class);
        query.setParameter("id", 40);
    
        List<OrderProductsEntity> products = query.getResultList();
        
        for (OrderProductsEntity productsEntity : products){
            System.out.println(productsEntity.getProductId());
        }
    }
}