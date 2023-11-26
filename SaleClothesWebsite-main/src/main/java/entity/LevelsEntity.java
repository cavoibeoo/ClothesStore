package entity;

import JPAConfig.JPAConfig;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.logging.Level;

@Entity
@Table(name = "levels", schema = "clothesstore", catalog = "")
public class LevelsEntity {
    private int levelId;
    private Double discount;
    private Double totalPayment;

    @Id
    @Column(name = "level_id")
    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    @Basic
    @Column(name = "discount")
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "total_payment")
    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelsEntity that = (LevelsEntity) o;
        return levelId == that.levelId && Objects.equals(discount, that.discount) && Objects.equals(totalPayment, that.totalPayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelId, discount, totalPayment);
    }
    
    public static LevelsEntity findByID(int id){
        return JPAConfig.getEntityManager().find(LevelsEntity.class, id);
    }
}
