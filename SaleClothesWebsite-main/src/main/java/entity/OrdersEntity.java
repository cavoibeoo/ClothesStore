package entity;

import JPAConfig.JPAConfig;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "clothesstore", catalog = "")
@NamedQuery(name = "getCustomerOrders", query = "select e from OrdersEntity e where e.customerId = ?1")
public class OrdersEntity {
    private int orderId;
    private int customerId;
    private Double totalAmount;
    private Timestamp date;
    private byte status;
    private Double discount;
    private byte isCancel;
    private Timestamp dateComplete;
    private Byte isConfirmed;
    
    @Id
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    @Basic
    @Column(name = "customer_id", nullable = false)
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    @Basic
    @Column(name = "total_amount", nullable = false)
    public Double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }
    
    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    @Basic
    @Column(name = "status", nullable = false)
    public byte getStatus() {
        return status;
    }
    
    public void setStatus(byte status) {
        this.status = status;
    }
    
    @Basic
    @Column(name = "discount", nullable = false, precision = 2)
    public Double getDiscount() {
        return discount;
    }
    
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    
    @Basic
    @Column(name = "isCancel", nullable = false)
    public byte getIsCancel() {
        return isCancel;
    }
    
    public void setIsCancel(byte isCancel) {
        this.isCancel = isCancel;
    }
    
    @Basic
    @Column(name = "dateComplete", nullable = true)
    public Timestamp getDateComplete() {
        return dateComplete;
    }
    
    public void setDateComplete(Timestamp dateComplete) {
        this.dateComplete = dateComplete;
    }
    
    @Basic
    @Column(name = "isConfirmed", nullable = true)
    public Byte getIsConfirmed() {
        return isConfirmed;
    }
    
    public void setIsConfirmed(Byte isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return orderId == that.orderId && customerId == that.customerId && totalAmount == that.totalAmount && status == that.status && isCancel == that.isCancel && Objects.equals(date, that.date) && Objects.equals(discount, that.discount) && Objects.equals(dateComplete, that.dateComplete) && Objects.equals(isConfirmed, that.isConfirmed);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, totalAmount, date, status, discount, isCancel, dateComplete, isConfirmed);
    }
    
    public static OrdersEntity findByID(int id){
        return JPAConfig.getEntityManager().find(OrdersEntity.class, id);
    }
}
