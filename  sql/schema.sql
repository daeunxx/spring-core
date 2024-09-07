create table orders (OrderId bigint not null, OrderNo varchar(255), totalAmount numeric(38,2), primary key (OrderId));
alter table if exists orders drop constraint if exists UK2hc3dhurlthaqif4a7gqefnaw;
alter table if exists orders add constraint UK2hc3dhurlthaqif4a7gqefnaw unique (OrderNo);
create sequence orders_SEQ start with 1 increment by 50;