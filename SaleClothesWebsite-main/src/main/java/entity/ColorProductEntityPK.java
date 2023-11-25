package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ColorProductEntityPK implements Serializable {
    private int productId;
    private int colorId;
    
    @Column(name = "product_id", nullable = false)
    @Id
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    @Column(name = "color_id", nullable = false)
    @Id
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
        ColorProductEntityPK that = (ColorProductEntityPK) o;
        return productId == that.productId && colorId == that.colorId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(productId, colorId);
    }
}
