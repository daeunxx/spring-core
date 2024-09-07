package org.example.hellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.hellospring.order.Order;
import org.example.hellospring.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public void save(Order order) {
    em.persist(order);
  }
}
