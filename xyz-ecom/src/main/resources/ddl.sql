CREATE TABLE Category (
     id integer PRIMARY KEY,
     name text NOT NULL,
last_update DATE,
cat_id integer
   );

   
CREATE TABLE Product (
    id integer PRIMARY KEY,
    name text NOT NULL,
description text NOT NULL,
brand_name text NOT NULL,
sku text NOT NULL,
color text NOT NULL,
size text NOT NULL,
stock integer NOT NULL,
category_id integer NOT NULL,
   price double NOT NULL,
    last_updated DATE,
     FOREIGN KEY (category_id)  REFERENCES Category(cat_id)
    );
