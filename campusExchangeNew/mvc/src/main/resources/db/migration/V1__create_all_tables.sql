CREATE TABLE customers (
	id		  				BIGSERIAL NOT NULL,
	name	      			VARCHAR(30) NOT NULL UNIQUE,
	password         		VARCHAR(15),
	email 			  		VARCHAR(50),
	phoneNumber      		VARCHAR(10)
);
ALTER TABLE customers ADD CONSTRAINT customers_pk PRIMARY KEY (id);
CREATE TABLE furnitures (
 	id     					BIGSERIAL,
	name 					VARCHAR(100),
	category     			VARCHAR(30),
	description				VARCHAR(150),
	customers_id 		    BIGSERIAL NOT NULL,
	price        			NUMERIC(10, 2)
);
ALTER TABLE furnitures ADD CONSTRAINT furnitures_pk PRIMARY KEY (id);
CREATE TABLE textbooks(
	id 						BIGSERIAL,
	name					VARCHAR(100),
	category     			VARCHAR(30),
	isbn					VARCHAR(13),
	description 			VARCHAR(150),
	customers_id			BIGSERIAL NOT NULL,
	price        			NUMERIC(10, 2)
);
ALTER TABLE textbooks ADD CONSTRAINT textbooks_pk PRIMARY KEY (id);

ALTER TABLE furnitures
    ADD CONSTRAINT furnitures_customers_fk FOREIGN KEY ( id )
        REFERENCES customers(id );
ALTER TABLE textbooks
    ADD CONSTRAINT textbooks_customers_fk FOREIGN KEY ( id )
        REFERENCES customers(id );