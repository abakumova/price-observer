CREATE DATABASE IF NOT EXISTS price_observer;
USE price_observer;

CREATE TABLE IF NOT EXISTS `product`
(
    `id`              INT          NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(50)  NOT NULL,
    `description`     VARCHAR(255),
    `properties_id`   INT          NOT NULL,
    `model`           VARCHAR(255) NOT NULL,
    `manufacturer_id` INT          NOT NULL,
    `year`            INT,
    `image`           VARCHAR(255),
    `product_type_id` INT          NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `product_properties`
(
    `id`         INT NOT NULL AUTO_INCREMENT,
    `properties` VARCHAR(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `manufacturer`
(
    `id`      INT         NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(50) NOT NULL,
    `country` VARCHAR(70),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `user`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name`  VARCHAR(50) NOT NULL,
    `email`      VARCHAR(70) NOT NULL,
    `birth`      DATE        NOT NULL,
    `password`   VARCHAR(50) NOT NULL,
    `role_id`    INT         NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `user_role`
(
    `id`   TINYINT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `wish_list`
(
    `id`         INT     NOT NULL AUTO_INCREMENT,
    `user_id`    INT     NOT NULL,
    `product_id` INT     NOT NULL,
    `is_deleted` BOOLEAN NOT NULL DEFAULT FALSE,
    `date_added` DATE    NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `product_type`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `store`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `product_price`
(
    `id`         INT   NOT NULL AUTO_INCREMENT,
    `product_id` INT   NOT NULL,
    `store_id`   INT   NOT NULL,
    `price`      FLOAT NOT NULL,
    `date`       DATE  NOT NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE `product`
    ADD CONSTRAINT `product_fk0` FOREIGN KEY (`properties_id`) REFERENCES `product_properties` (`id`);

ALTER TABLE `product`
    ADD CONSTRAINT `product_fk1` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer` (`id`);

ALTER TABLE `product`
    ADD CONSTRAINT `product_fk2` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`id`);

ALTER TABLE `user`
    ADD CONSTRAINT `user_fk0` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`);

ALTER TABLE `wish_list`
    ADD CONSTRAINT `wish_list_fk0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `wish_list`
    ADD CONSTRAINT `wish_list_fk1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `product_price`
    ADD CONSTRAINT `product_price_fk0` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `product_price`
    ADD CONSTRAINT `product_price_fk1` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`);

