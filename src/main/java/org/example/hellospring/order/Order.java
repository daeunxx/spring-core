package org.example.hellospring.order;

import java.math.BigDecimal;

public class Order {

  private Long OrderId;
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
