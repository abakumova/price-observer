INSERT INTO `price_observer`.`user_role` (`id`, `name`)
VALUES (1, 'admin'),
       (2, 'user'),
       (3, 'god');

INSERT INTO `price_observer`.`user` (`id`, `first_name`, `last_name`, `email`, `birth`, `password`, `role_id`)
VALUES (1, 'admin', 'admin', 'admin@gmail.com', '2020-01-01', 'admin', 3),
       (2, 'Evgeniy', 'Kiprenko', 'zhenyakiprenko@gmail.com', '2000-02-15', 'admin', 1),
       (3, 'Viktoriia', 'Abakumova', 'abakumovaviktory@gmail.com', '1999-07-08', 'admin', 1),
       (4, 'Test', 'Test', 'test@gmail.com', '2020-02-02', 'test', 2),
       (5, 'User', 'User', 'user@gmail.com', '2020-03-03', 'test', 2);

INSERT INTO `price_observer`.`product_type` (`id`, `name`)
VALUES (1, 'smartphone'),
       (2, 'laptop'),
       (3, 'tablet'),
       (4, 'smartwatch'),
       (5, 'earphones'),
       (6, 'TV'),
       (7, 'all-in-one');

INSERT INTO `price_observer`.`manufacturer` (`id`, `name`, `country`)
VALUES (1, 'Apple', 'USA'),
       (2, 'Samsung', 'South Korea'),
       (3, 'Xiaomi', 'China'),
       (4, 'Huawei', 'China'),
       (5, 'Meizu', 'China'),
       (6, 'OnePlus', 'China'),
       (7, 'Philips', 'Netherlands'),
       (8, 'Bosch', 'Germany'),
       (9, 'Asus', 'Taiwan'),
       (10, 'Acer', 'Taiwan'),
       (11, 'LG', 'South Korea'),
       (12, 'Lenovo', 'Hong Kong'),
       (13, 'HP', 'USA'),
       (14, 'Dell', 'USA'),
       (15, 'Microsoft', 'USA'),
       (16, 'JBL', 'USA'),
       (17, 'Sony', 'Japan');

INSERT INTO `price_observer`.`store` (`id`, `name`, `url`)
VALUES (1, 'Avic', 'https://avic.ua/'),
       (2, 'Allo', 'https://allo.ua/'),
       (3, 'Rozetka', 'https://rozetka.com.ua/'),
       (4, 'Citrus', 'https://www.citrus.ua/'),
       (5, 'Cactus', 'https://www.c.ua/'),
       (6, 'Moyo', 'https://www.moyo.ua/'),
       (7, 'Foxtrot', 'https://www.foxtrot.com.ua/'),
       (8, 'Comfy', 'https://comfy.ua/'),
       (9, 'Eldorado', 'https://eldorado.ua/');