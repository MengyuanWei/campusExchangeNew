ALTER TABLE furnitures
    DROP CONSTRAINT furnitures_customers_fk;

ALTER TABLE textbooks
    DROP CONSTRAINT textbooks_customers_fk;

ALTER TABLE furnitures
    ADD CONSTRAINT furnitures_customers_fk FOREIGN KEY ( customers_id )
        REFERENCES customers(id);
ALTER TABLE textbooks
    ADD CONSTRAINT textbooks_customers_fk FOREIGN KEY ( customers_id )
        REFERENCES customers(id);