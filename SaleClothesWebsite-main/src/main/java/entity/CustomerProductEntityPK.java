package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CustomerProductEntityPK implements Serializable {
    private int customerId;
    private int productId;
    private int sizeId;
    private int colorId;

    @Column(name = "customer_id", nullable = false)
    @Id
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
        CustomerProductEntityPK that = (CustomerProductEntityPK) o;
        return customerId == that.customerId && productId == that.productId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(customerId, productId);
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
