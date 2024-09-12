package org.example.hellospring.order;

import java.math.BigDecimal;

public record OrderRequest(String orderNo, BigDecimal totalAmount) {

}
