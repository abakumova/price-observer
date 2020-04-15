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
    `year`            YEAR,
    `image`           TEXT,
    `product_type_id` INT          NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `product_properties`
(
    `id`         INT NOT NULL AUTO_INCREMENT,
    `properties` TEXT, # A json presentation of product properties
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
    `id`            INT         NOT NULL AUTO_INCREMENT,
    `first_name`    VARCHAR(50) NOT NULL,
    `last_name`     VARCHAR(50) NOT NULL,
    `email`         VARCHAR(70) NOT NULL,
    `birth`         DATE        NOT NULL,
    `password`      VARCHAR(64) NOT NULL,
    `role_id`       TINYINT     NOT NULL,
    `profile_image` TEXT, #A link to the profile image (local storage).
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `user_role`
(
    `id`   TINYINT     NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `wish_product`
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
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50)  NOT NULL,
    `url`  VARCHAR(100) NOT NULL,
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

ALTER TABLE `wish_product`
    ADD CONSTRAINT `wish_product_fk0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `wish_product`
    ADD CONSTRAINT `wish_product_fk1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `product_price`
    ADD CONSTRAINT `product_price_fk0` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `product_price`
    ADD CONSTRAINT `product_price_fk1` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`);

/* Prevent inserting more than 3 user roles (user, admin and god) into the user_role table. */

DELIMITER $$
CREATE TRIGGER before_insert_trigger
    BEFORE INSERT
    ON user_role
    FOR EACH ROW

BEGIN

    DECLARE id_check tinyint;
    SELECT COUNT(`user_role`.`id`) FROM `price_observer`.`user_role` INTO id_check;

    IF id_check + 1 > 3 THEN
        signal sqlstate '45000' set message_text = 'You can not insert more than 3 rows';
    END IF;
END;
$$

DELIMITER ;