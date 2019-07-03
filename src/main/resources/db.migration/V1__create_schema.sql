CREATE TABLE Users (
	id serial NOT NULL,
	name varchar(255) NOT NULL,
	lastname varchar(255) NOT NULL,
	age integer NOT NULL,
	login varchar(255) NOT NULL UNIQUE ,
	password varchar(255) NOT NULL,
	role varchar(255) NOT NULL,
	enabled BOOLEAN DEFAULT TRUE,
	CONSTRAINT Users_pk PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Items (
	id serial NOT NULL,
	name varchar(255) NOT NULL UNIQUE,
	price integer NOT NULL,
	count integer NOT NULL,
	CONSTRAINT Items_pk PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Orders (
	id serial NOT NULL,
	purchase_date TIMESTAMP ,
	user_id integer NOT NULL,
	CONSTRAINT Orders_pk PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Buy_items (
	id serial NOT NULL,
	item_id integer NOT NULL,
	order_id integer NOT NULL,
	count integer NOT NULL,
	CONSTRAINT Buy_items_pk PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);



ALTER TABLE Orders ADD CONSTRAINT Orders_fk0 FOREIGN KEY (user_id) REFERENCES Users(id);

ALTER TABLE Buy_items ADD CONSTRAINT Buy_items_fk0 FOREIGN KEY (item_id) REFERENCES Items(id);
ALTER TABLE Buy_items ADD CONSTRAINT Buy_items_fk1 FOREIGN KEY (order_id) REFERENCES Orders(id);

GRANT ALL ON ALL TABLES IN SCHEMA public TO evgenyk;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO evgenyk;
