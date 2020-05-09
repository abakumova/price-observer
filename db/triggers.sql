DELIMITER $$

-- USER TABLE

-- WISH_PRODUCT

-- PRODUCT TABLE

-- PRODUCT_PRICE TABLE

-- STORE TABLE

-- PRODUCT_TYPE TABLE

-- PRODUCT_PROPERTIES TABLE

-- USER_ROLE TABLE

/* Prevent inserting more than 3 user roles (user, admin and god) into the user_role table. */
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

-- MANUFACTURER TABLE

DELIMITER ;