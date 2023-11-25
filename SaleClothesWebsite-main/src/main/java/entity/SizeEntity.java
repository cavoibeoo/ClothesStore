package entity;

import JPAConfig.JPAConfig;
import org.hibernate.engine.jdbc.Size;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "size", schema = "clothesstore", catalog = "")
public class SizeEntity {
    private int sizeId;
    private String sizeName;
    
    @Id
    @Column(name = "size_id", nullable = false)
    public int getSizeId() {
        return sizeId;
    }
    
    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }
    
    @Basic
    @Column(name = "size_name", nullable = true, length = 45)
    public String getSizeName() {
        return sizeName;
    }
    
    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeEntity that = (SizeEntity) o;
        return sizeId == that.sizeId && Objects.equals(sizeName, that.sizeName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(sizeId, sizeName);
    }
    
    public static SizeEntity findByID(int id){
        return JPAConfig.getEntityManager().find(SizeEntity.class, id);
    }
}