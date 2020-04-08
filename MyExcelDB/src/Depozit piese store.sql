 -- create database storage;
 
 -- drop table storage.product;

CREATE TABLE storage.product (
    product_id INT(11) NOT NULL AUTO_INCREMENT,
    product_cod VARCHAR(50) NULL,
    category_name VARCHAR(50) NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    quantity DOUBLE NULL,
    UM VARCHAR(5) NOT NULL,
    notes TEXT NULL,
    PRIMARY KEY (product_id));

   -- drop table storage.store;
CREATE TABLE storage.store (
    store_id INT(6) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    store_cod VARCHAR(4) NOT NULL,
    store_name VARCHAR(12) NOT NULL,
    product_id INT NOT NULL
);
  
   -- drop table storage.category;  
   
CREATE TABLE storage.category (
    category_id INT(6) NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(10) NOT NULL,
    PRIMARY KEY (category_id),
    FOREIGN KEY (category_id)
        REFERENCES storage.product (product_id)
);

CREATE TABLE IF NOT EXISTS storage.store_x_category (
    store_id INT(6) NOT NULL,
    category_id INT(6) NOT NULL,
    PRIMARY KEY (store_id , category_id),
    FOREIGN KEY (store_id)
        REFERENCES storage.store (store_id),
    FOREIGN KEY (category_id)
        REFERENCES storage.category (category_id)
);
	
-- ---------  insert data into tables  ------------------------////////
    
  insert  into storage.product
(product_cod, category_name,product_name, quantity, UM, notes)
values
('A3','electric', 'sursa in comutatie 24V/3A', 7 ,'buc', 'nu sunt'),
('A2','electric', 'Placa de baza',14,'buc', 'not');
  
  insert  into storage.product
(product_cod, category_name,product_name, quantity, UM, notes)
values
('B1','mecanic', 'piesa de alunecare', 5 ,'buc', 'nu sunt');

insert into storage.category(category_name) values ('electric');
insert into storage.category (category_name) values ('mecanic');
insert into storage.category (category_name) values ('optic');
insert into storage.category (category_name) values ('diverse');


/*
describe storage.category;
describe storage.store;
select * from storage.product;
select * from storage.category;
select * from storage.store;
select * from storage.store_x_category;
DELETE FROM storage.category WHERE category_name = 'optic';
delete from storage.category;
DELETE  FROM storage.product;  --*/