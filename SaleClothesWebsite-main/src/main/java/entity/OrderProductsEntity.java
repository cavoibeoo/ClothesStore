package entity;

import JPAConfig.JPAConfig;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.lang.Double;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order_products", schema = "clothesstore", catalog = "")
@IdClass(OrderProductsEntityPK.class)
public class OrderProductsEntity {
    private int orderId;
    private int productId;
    private Double unitPrice;
    private int quantity;
    private Double total;
    private int sizeId;
    private int colorId;
    
    @Id
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    @Id
    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    @Basic
    @Column(name = "unit_price", nullable = false, precision = 2)
    public Double getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Basic
    @Column(name = "total", nullable = false, precision = 2)
    public Double getTotal() {
        return total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
    
    @Id
    @Column(name = "size_id", nullable = false)
    public int getSizeId() {
        return sizeId;
    }
    
    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }
    
    @Id
    @Column(name = "color_id", nullable = false)
    public int getColorId() {
        return colorId;
    }
    
    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductsEntity that = (OrderProductsEntity) o;
        return orderId == that.orderId && productId == that.productId && quantity == that.quantity && sizeId == that.sizeId && colorId == that.colorId && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(total, that.total);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, unitPrice, quantity, total, sizeId, colorId);
    }
    
    public static List<OrderProductsEntity>  getOrderProductById(int id){
        EntityManager entityManager = JPAConfig.getEntityManager();
    
        // Create a query to retrieve OrderProductsEntity by ID
        TypedQuery<OrderProductsEntity> query = entityManager.createQuery(
                "SELECT op FROM OrderProductsEntity op WHERE op.orderId = :id", OrderProductsEntity.class);
        query.setParameter("id", id);
    
        List<OrderProductsEntity> products = query.getResultList();
    
        return products;
    }
}
