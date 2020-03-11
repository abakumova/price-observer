CREATE TABLE `product` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	`description` varchar(255),
	`current_price` FLOAT NOT NULL,
	`properties_id` INT NOT NULL,
	`model` VARCHAR(255) NOT NULL,
	`manufacturer_id` INT NOT NULL,
	`year` INT,
	`image` VARCHAR(255),
	`product_type_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `product_properties` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`properties` VARCHAR(255),
	PRIMARY KEY (`id`)
);

CREATE TABLE `manufacturer` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	`country` varchar(70),
	PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`first_name` varchar(50) NOT NULL,
	`last_name` varchar(50) NOT NULL,
	`email` varchar(70) NOT NULL,
	`birth` varchar(35) NOT NULL,
	`password` varchar(50) NOT NULL,
	`role_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user_role` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `wish_list` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`user_id` INT NOT NULL,
	`product_id` INT NOT NULL,
	`is_deleted` BOOLEAN NOT NULL DEFAULT FALSE,
	`date_added` DATE NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `product_type` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `store` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `product_price` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`product_id` INT NOT NULL,
	`store_id` INT NOT NULL,
	`price` FLOAT NOT NULL,
	`date` DATE NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `product` ADD CONSTRAINT `product_fk0` FOREIGN KEY (`properties_id`) REFERENCES `product_properties`(`id`);

ALTER TABLE `product` ADD CONSTRAINT `product_fk1` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer`(`id`);

ALTER TABLE `product` ADD CONSTRAINT `product_fk2` FOREIGN KEY (`product_type_id`) REFERENCES `product_type`(`id`);

ALTER TABLE `user` ADD CONSTRAINT `user_fk0` FOREIGN KEY (`role_id`) REFERENCES `user_role`(`id`);

ALTER TABLE `wish_list` ADD CONSTRAINT `wish_list_fk0` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);

ALTER TABLE `wish_list` ADD CONSTRAINT `wish_list_fk1` FOREIGN KEY (`product_id`) REFERENCES `product`(`id`);

ALTER TABLE `product_price` ADD CONSTRAINT `product_price_fk0` FOREIGN KEY (`product_id`) REFERENCES `product`(`id`);

ALTER TABLE `product_price` ADD CONSTRAINT `product_price_fk1` FOREIGN KEY (`store_id`) REFERENCES `store`(`id`);

