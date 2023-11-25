package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer_product", schema = "clothesstore", catalog = "")
@NamedQuery(name = "getCustomerCart", query = "select e from CustomerProductEntity e where e.customerId = ?1")
@NamedQuery(name = "getProductIn4", query = "select e from ProductsEntity e where e.productId = ?1")
@NamedQuery(name = "deletePurchased", query = "delete from CustomerProductEntity e where e.status = 1")
@IdClass(CustomerProductEntityPK.class)
public class CustomerProductEntity {
    private int customerId;
    private int productId;
    private int quantity;
    private byte status;
    private int sizeId;
    private int colorId;
    
    @Id
    @Column(name = "customer_id", nullable = false)
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Basic
    @Column(name = "status", nullable = false)
    public byte getStatus() {
        return status;
    }
    
    public void setStatus(byte status) {
        this.status = status;
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
        CustomerProductEntity that = (CustomerProductEntity) o;
        return customerId == that.customerId && productId == that.productId && quantity == that.quantity && status == that.status && sizeId == that.sizeId && colorId == that.colorId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(customerId, productId, quantity, status, sizeId, colorId);
    }
    
    public int assignUnitPrice(){
        return ProductsEntity.findByID(this.productId).getUnitPrice();
    }
    
    public String assignName(){
        return ProductsEntity.findByID(this.productId).getProductName();
    }
}
