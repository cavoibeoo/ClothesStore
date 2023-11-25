package entity;

import JPAConfig.JPAConfig;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "color_product", schema = "clothesstore", catalog = "")
@IdClass(ColorProductEntityPK.class)
public class ColorProductEntity {
    private int productId;
    private int colorId;
    
    @Id
    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
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
        ColorProductEntity that = (ColorProductEntity) o;
        return productId == that.productId && colorId == that.colorId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(productId, colorId);
    }
    
    public static ColorEntity findByID (int id){
        return JPAConfig.getEntityManager().find(ColorEntity.class,id);
    }
}
