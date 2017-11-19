CREATE DATABASE IF NOT EXISTS jd2_test;
USE jd2_test;

DROP TABLE IF EXISTS ORDERS_PRODUCTS;
DROP TABLE IF EXISTS ORDERS;
DROP TABLE IF EXISTS PRODUCTS;
DROP TABLE IF EXISTS USERS;


CREATE DATABASE IF NOT EXISTS jd2_project;
USE jd2_project;

CREATE TABLE IF NOT EXISTS ORDERS (
  ORDER_ID        INTEGER     NOT NULL AUTO_INCREMENT,
  ORDER_CONFIRMED DATETIME,
  ORDER_RECEIVED  DATETIME,
  ORDER_DELIVERED DATETIME,
  STATUS          VARCHAR(10) NOT NULL,
  USER_ID         INTEGER,
  PRIMARY KEY (ORDER_ID)
);

CREATE TABLE IF NOT EXISTS ORDERS_PRODUCTS (
  ORDER_ID            INTEGER NOT NULL,
  products_PRODUCT_ID INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS PRODUCTS (
  PRODUCT_ID INTEGER NOT NULL AUTO_INCREMENT,
  DETAILS    VARCHAR(255),
  NAME       VARCHAR(255),
  PRICE      DOUBLE PRECISION,
  PRIMARY KEY (PRODUCT_ID)
);

CREATE TABLE IF NOT EXISTS USERS (
  USER_ID  INTEGER      NOT NULL AUTO_INCREMENT,
  ADDRESS  VARCHAR(255),
  EMAIL    VARCHAR(30)  NOT NULL,
  NAME     VARCHAR(255),
  PASSWORD VARCHAR(255) NOT NULL,
  PHONE    VARCHAR(255),
  SURNAME  VARCHAR(255),
  TYPE     VARCHAR(10)  NOT NULL,
  PRIMARY KEY (USER_ID)
);

ALTER TABLE USERS
  ADD CONSTRAINT UK_81nqioeq3njjrwqaltk2mcobj UNIQUE (EMAIL);
ALTER TABLE ORDERS
  ADD CONSTRAINT FK6jhqv9srg8s7x7ycrce7oxuur FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);
ALTER TABLE ORDERS_PRODUCTS
  ADD CONSTRAINT FKrcu1mupg0bl45tmu89luoly FOREIGN KEY (products_PRODUCT_ID) REFERENCES PRODUCTS (PRODUCT_ID);
ALTER TABLE ORDERS_PRODUCTS
  ADD CONSTRAINT FKbg6c53px6dp5ec8oma36dm4ei FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ORDER_ID);

INSERT INTO USERS ( NAME, SURNAME, EMAIL, PASSWORD, ADDRESS, PHONE, TYPE)
VALUES ("Yauheni", "Krasko", "krasko@mail.ru", "krasko", "Korobka#1", "375256541298", "ADMIN");
INSERT INTO USERS (NAME, SURNAME, EMAIL, PASSWORD, ADDRESS, PHONE, TYPE)
VALUES ("Simple", "User", "simple@mail.ru", "simple", "Korobka#2", "375256521998", "USER");
INSERT INTO USERS (NAME, SURNAME, EMAIL, PASSWORD, ADDRESS, PHONE, TYPE)
VALUES ("Grand", "User", "grand@mail.ru", "grandu", "Korobka#3", "375449518742", "GRAND_USER");
INSERT INTO USERS (NAME, SURNAME, EMAIL, PASSWORD, ADDRESS, PHONE, TYPE)
VALUES ("Deleted", "User", "deleted@mail.ru", "deleted", "Korobka#4", "3752741593245", "USER");

INSERT INTO PRODUCTS (NAME, PRICE, DETAILS)
VALUES ("Ролл Бонито", "14.20", "лосось, авокадо, сыр, стружка тунца, рис, нори");
INSERT INTO PRODUCTS (NAME, PRICE, DETAILS)
VALUES ("Ролл с копченным лососем и сливочным сыром", "7.30", "копченый лосось, сыр, авокадо, рис, нори");
INSERT INTO PRODUCTS (NAME, PRICE, DETAILS)
VALUES ("Ролл Праздничный", "13.20", "копченый лосось, огурец, сыр, икра лосося, рис, нори");
INSERT INTO PRODUCTS (NAME, PRICE, DETAILS) VALUES ("Ролл с лососем и сыром", "6.84", "лосось, сыр, нори, рис");
INSERT INTO PRODUCTS (NAME, PRICE, DETAILS) VALUES ("Ролл с тунцом, морским гребешком и клубникой", "14.60",
                                                    "тунец, морской гребешок, авокадо, клубника, сыр, кунжут, рис, нори");
INSERT INTO PRODUCTS (NAME, PRICE, DETAILS) VALUES ("Ролл Рак-н-Ролл", "12.70",
                                                    "креветка, авокадо, икра летучей рыбы тобико, кунжут, рис, нори, рак, перец Чили, лист нори");
INSERT INTO PRODUCTS (NAME, PRICE, DETAILS)
VALUES ("Ролл с угрем, авокадо и сливочным сыром", "10.10", "копченый угорь, сыр, авокадо, рис, нори");

INSERT INTO ORDERS (USER_ID, ORDER_RECEIVED, STATUS) VALUES (2, TIMESTAMP(NOW()), "Received");
INSERT INTO ORDERS (USER_ID, ORDER_RECEIVED, STATUS) VALUES (4, TIMESTAMP(NOW()), "Received");

INSERT INTO ORDERS_PRODUCTS (ORDER_ID, products_PRODUCT_ID) VALUES (1, 2);
INSERT INTO ORDERS_PRODUCTS (ORDER_ID, products_PRODUCT_ID) VALUES (1, 4);
INSERT INTO ORDERS_PRODUCTS (ORDER_ID, products_PRODUCT_ID) VALUES (1, 7);
INSERT INTO ORDERS_PRODUCTS (ORDER_ID, products_PRODUCT_ID) VALUES (2, 1);
INSERT INTO ORDERS_PRODUCTS (ORDER_ID, products_PRODUCT_ID) VALUES (2, 3);