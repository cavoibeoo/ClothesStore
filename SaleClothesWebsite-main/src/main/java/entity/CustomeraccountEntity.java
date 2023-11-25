package entity;

import JPAConfig.JPAConfig;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "customeraccount", schema = "clothesstore")
@NamedQuery(name = "CheckLogin", query = "select e from CustomeraccountEntity e where e.mail = ?1 and e.pwd = ?2")
@NamedQuery(name = "GetCusId", query = "select e.customerId from CustomeraccountEntity e where e.mail = ?1")
public class CustomeraccountEntity {
    private int customerId;
    private String mail;
    private String pwd;
    private BigDecimal totalPayment;
    private int levelId;
    
    @Id
    @Column(name = "customer_id", nullable = false)
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    @Basic
    @Column(name = "mail", nullable = false, length = 45)
    public String getMail() {
        return mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    @Basic
    @Column(name = "pwd", nullable = false, length = 45)
    public String getPwd() {
        return pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    @Basic
    @Column(name = "total_payment", nullable = false, precision = 2)
    public BigDecimal getTotalPayment() {
        return totalPayment;
    }
    
    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }
    
    @Basic
    @Column(name = "level_id", nullable = false)
    public int getLevelId() {
        return levelId;
    }
    
    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomeraccountEntity that = (CustomeraccountEntity) o;
        return customerId == that.customerId && levelId == that.levelId && Objects.equals(mail, that.mail) && Objects.equals(pwd, that.pwd) && Objects.equals(totalPayment, that.totalPayment);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(customerId, mail, pwd, totalPayment, levelId);
    }
    
    
    public boolean CheckAccount() {
        try {
            
            TypedQuery<CustomeraccountEntity> Result = JPAConfig.getEntityManager().createNamedQuery("CheckLogin", CustomeraccountEntity.class);
            Result.setParameter(1, this.mail);
            Result.setParameter(2, this.pwd);
            
            if (Result.getResultList().stream().count() != 0) {
                for (CustomeraccountEntity customer : Result.getResultList()) {
                    this.customerId = customer.getCustomerId();
                    this.totalPayment = customer.getTotalPayment();
                }
                return true;
            }
            return false;
        }catch (Exception exception) {
            return false;
        }
    }
    
    public String GetCusId() {
        try {
            TypedQuery<CustomeraccountEntity> Result = JPAConfig.getEntityManager().createNamedQuery("GetCusId", CustomeraccountEntity.class);
            Result.setParameter(1, this.mail);
            String customerid = null;
            for (CustomeraccountEntity customer : Result.getResultList()) {
                this.customerId = customer.getCustomerId();
                this.pwd = customer.getPwd();
                this.totalPayment = customer.getTotalPayment();
                customerid = customer.GetCusId();
            }
            return customerid;
        }catch (Exception exception) {
            return null;
        }
    }
}
