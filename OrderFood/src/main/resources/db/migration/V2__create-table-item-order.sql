CREATE TABLE order_item (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  amount int(11) NOT NULL,
  description varchar(255) DEFAULT NULL,
  order_food_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (order_food_id) REFERENCES order_food(id)
);