CREATE TABLE IF NOT EXISTS `client` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `status` enum('ACTIVE','INACTIVE') NOT NULL,
                            `name` varchar(100) NOT NULL,
                            `cpf` varchar(14) NOT NULL,
                            `email` varchar(100) NOT NULL,
                            `phone` varchar(20) NOT NULL,
                            `cep` varchar(9) NOT NULL,
                            `address` varchar(255) NOT NULL,
                            PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `supplier` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `status` enum('ACTIVE','INACTIVE') NOT NULL,
                            `name` varchar(100) NOT NULL,
                            `cnpj` varchar(18) NOT NULL,
                            `email` varchar(100) NOT NULL,
                            `phone` varchar(20) NOT NULL,
                            `cep` varchar(9) NOT NULL,
                            `address` varchar(255) NOT NULL,
                            PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `employee` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `status` enum('ACTIVE','INACTIVE') NOT NULL,
                            `username` varchar(20) NOT NULL,
                            `password` varchar(100) NOT NULL,
                            `role` enum('ADMIN','OWNER','USER') NOT NULL,
                            `name` varchar(100) NOT NULL,
                            `cpf` varchar(14) NOT NULL,
                            `email` varchar(100) NOT NULL,
                            `phone` varchar(20) NOT NULL,
                            `cep` varchar(9) NOT NULL,
                            `address` varchar(255) NOT NULL,
                            PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `product` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `status` enum('ACTIVE','INACTIVE') NOT NULL,
                           `barcode` varchar(10) NOT NULL,
                            `name` varchar(100) NOT NULL,
                            `size` varchar(255) NOT NULL,
                            `type` varchar(100) NOT NULL,
                           `buy_value` double NOT NULL,
                            `sell_value` double NOT NULL,
                           `quantity` int NOT NULL,
                           PRIMARY KEY (`id`)
);