CREATE TABLE `products` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(20) DEFAULT NULL,
                            `price` float DEFAULT NULL,
                            `size` int DEFAULT NULL,
                            `unit` varchar(10) DEFAULT NULL,
                            PRIMARY KEY (`id`)
)

CREATE TABLE coffeeshop.location (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     name VARCHAR(64),
                                     address VARCHAR(128),
                                     active BIT,
                                     PRIMARY KEY (`id`)
);

Drop TABLE coffeeshop.Location;

CREATE TABLE coffeeshop.Employee (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     first_name VARCHAR(64),
                                     last_name VARCHAR(64),
                                     address VARCHAR(128),

                                     salary FLOAT,
                                     title VARCHAR(64),
                                     location_id INT,
                                     FOREIGN KEY (location_id) REFERENCES coffeeshop.location(id),
                                     PRIMARY KEY (`id`)
);

DROP TABLE coffeeshop.Employee;

CREATE TABLE coffeeshop.Manager (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    first_name VARCHAR(64),
                                    last_name VARCHAR(64),
                                    address VARCHAR(128),
                                    salary FLOAT,
                                    location_id INT,
                                    FOREIGN KEY (location_id) REFERENCES coffeeshop.location(id),
                                    PRIMARY KEY (`id`)
);

CREATE TABLE coffeeshop.Event (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  name VARCHAR(64),
                                  host VARCHAR(64),
                                  profit FLOAT,
                                  location_id INT,
                                  FOREIGN KEY (location_id) REFERENCES coffeeshop.location(id),
                                  PRIMARY KEY (`id`)
);

CREATE TABLE coffeeshop.orders(
                                  id INT auto_increment PRIMARY KEY,
                                  date_time VARCHAR(20),
                                  location int,
                                  customer int,
                                  order_type varchar(30),
                                  delivery_address varchar(20),
                                  delivery_man varchar(20),
                                  foreign key (location) references coffeeshop.location(id),
                                  foreign key (customer) references coffeeshop.customer(id)
);

CREATE TABLE `customer` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `first_name` varchar(20) DEFAULT NULL,
                            `last_name` varchar(20) DEFAULT NULL,
                            `address` varchar(20) DEFAULT NULL,
                            PRIMARY KEY (`id`)
)
