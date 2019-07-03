INSERT INTO Users(name, lastname, login, password, role, age)
 values('Evgeny', 'Kabatov', 'kabatov.en@yandex.ru', '$2a$10$7OAbCHEehzR7.lU0oA7aL.bU43CBRpsPuJBekUOP9kmO1PzOZb/.O',
  'user', 25);

INSERT INTO Users(name, lastname, login, password, role, age)
 values('Sergey', 'Antonov', 'anton.s@yandex.ru', '$2a$10$7OAbCHEehzR7.lU0oA7aL.bU43CBRpsPuJBekUOP9kmO1PzOZb/.O',
  'user', 27);
INSERT INTO Users(name, lastname, login, password, role, age)
 values('Kirill', 'Alekseev', 'aleks@yandex.ru', '$2a$10$7OAbCHEehzR7.lU0oA7aL.bU43CBRpsPuJBekUOP9kmO1PzOZb/.O',
  'user', 18);
INSERT INTO users(name, lastname, login, password, role, age)
  values ('Admin', 'Admin', 'Admin', '$2a$10$7OAbCHEehzR7.lU0oA7aL.bU43CBRpsPuJBekUOP9kmO1PzOZb/.O', 'admin', 26);


INSERT INTO Items(name, price,count) values ('LG телевизор', 3000000, 5);
INSERT INTO Items(name, price,count) values ('iPhone XS смартфон', 8000000, 20);
INSERT INTO Items(name, price,count) values ('Bosch соковыжималка', 1000000, 7);
INSERT INTO Items(name, price,count) values ('Sennheiser HD200 наушники', 200000, 15);
INSERT INTO Items(name, price,count) values ('Genius клавиатура', 50000, 20);

INSERT INTO Orders(purchase_date, user_id) values ('2019-06-27 18:52:01.689', 1);
INSERT INTO Orders(purchase_date, user_id) values ('2019-06-28 17:52:01.689', 2);
INSERT INTO Orders(purchase_date, user_id) values ('2019-06-29 16:52:01.689', 3);

INSERT INTO Buy_items(item_id, order_id, count) values (1,1,1);
INSERT INTO Buy_items(item_id, order_id, count) values (2,2,1);
INSERT INTO Buy_items(item_id, order_id, count) values (3,3,1);
INSERT INTO Buy_items(item_id, order_id, count) values (4,1,1);
INSERT INTO Buy_items(item_id, order_id, count) values (5,1,1);
