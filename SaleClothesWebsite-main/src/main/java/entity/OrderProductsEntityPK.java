package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrderProductsEntityPK implements Serializable {
    private int orderId;
    private int productId;
    private int sizeId;
    private int colorId;

    @Column(name = "order_id", nullable = false)
    @Id
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(name = "product_id", nullable = false)
    @Id
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductsEntityPK that = (OrderProductsEntityPK) o;
        return orderId == that.orderId && productId == that.productId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }
    
    @Column(name = "size_id", nullable = false)
    @Id
    public int getSizeId() {
        return sizeId;
    }
    
    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }
    
    @Column(name = "color_id", nullable = false)
    @Id
    public int getColorId() {
        return colorId;
    }
    
    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}
