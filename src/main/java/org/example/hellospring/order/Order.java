package org.example.hellospring.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue
  private Long OrderId;

  @Column(unique = true)
  private String OrderNo;

  private BigDecimal totalAmount;

  public Order() {
  }

  public Order(String orderNo, BigDecimal totalAmount) {
    OrderNo = orderNo;
    this.totalAmount = totalAmount;
  }

  public Long getOrderId() {
    return OrderId;
  }

  public String getOrderNo() {
    return OrderNo;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  @Override
  public String toString() {
    return "Order{" +
        "OrderId=" + OrderId +
        ", OrderNo='" + OrderNo + '\'' +
        ", totalAmount=" + totalAmount +
        '}';
  }
}
