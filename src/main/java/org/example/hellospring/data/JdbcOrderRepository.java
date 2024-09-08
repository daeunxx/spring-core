package org.example.hellospring.data;

import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import org.example.hellospring.order.Order;
import org.example.hellospring.order.OrderRepository;
import org.springframework.jdbc.core.simple.JdbcClient;

public class JdbcOrderRepository implements OrderRepository {

  private final JdbcClient jdbcClient;

  public JdbcOrderRepository(DataSource dataSource) {
    this.jdbcClient = JdbcClient.create(dataSource);
  }

  @PostConstruct
  void initDb() {
    jdbcClient.sql("""
        create table orders (OrderId bigint not null, OrderNo varchar(255), totalAmount numeric(38,2), primary key (OrderId));
        alter table if exists orders drop constraint if exists UK2hc3dhurlthaqif4a7gqefnaw;
        alter table if exists orders add constraint UK2hc3dhurlthaqif4a7gqefnaw unique (OrderNo);
        create sequence orders_SEQ start with 1 increment by 50;
    """).update();
  }

  @Override
  public void save(Order order) {
    Long orderId = jdbcClient.sql("select next value for orders_SEQ;").query(Long.class).single();
    order.setOrderId(orderId);

    jdbcClient.sql("insert into orders (OrderNo,totalAmount,OrderId) values (?,?,?);")
        .params(order.getOrderNo(), order.getTotalAmount(), order.getOrderId())
        .update();
  }
}
