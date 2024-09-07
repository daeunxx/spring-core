package org.example.hellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.hellospring.order.Order;

public class OrderRepository {

  @PersistenceContext
  private EntityManager em;

  public void save(Order order) {
    em.persist(order);
  }
}
