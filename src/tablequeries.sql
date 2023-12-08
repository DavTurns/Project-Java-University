CREATE TABLE `products` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(45) NOT NULL,
                            `price` float NOT NULL,
                            `size` int NOT NULL,
                            `unit` varchar(45) NOT NULL,
                            PRIMARY KEY (`id`)
);

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
