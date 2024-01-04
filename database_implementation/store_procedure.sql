DELIMITER //

CREATE PROCEDURE GetProductInventory(IN productId INT)
BEGIN
    DECLARE productStock INT;

    SELECT pinv INTO productStock
    FROM product
    WHERE pno = productId;

    IF productStock IS NOT NULL THEN
        SELECT CONCAT('Product ID ', productId, ' has ', productStock, ' units in stock.') AS Inventory;
    ELSE
        SELECT 'Product not found' AS Inventory;
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE GetRawInventory(IN rawId INT)
BEGIN
    DECLARE rawStock INT;

    SELECT rinv INTO rawStock
    FROM raw
    WHERE rno = rawId;

    IF rawStock IS NOT NULL THEN
        SELECT CONCAT('raw ID ', rawId, ' has ', rawStock, ' units in stock.') AS Inventory;
    ELSE
        SELECT 'Raw not found' AS Inventory;
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE GetCapacity(IN warehouseNumber INT)
BEGIN
    DECLARE capacity INT;
    DECLARE total INT;
    DECLARE percentage INT;

    SELECT rcap INTO capacity
    FROM warehouse
    WHERE wno = warehouseNumber;
    SELECT cap INTO total
    FROM warehouse
    WHERE wno = warehouseNumber;

    SET percentage = (capacity/total)*100;

    IF capacity IS NOT NULL THEN
        SELECT CONCAT('It', ' has ', percentage, ' % remaining.') AS Inventory;
    ELSE
        SELECT 'Warehouse not found' AS Inventory;
    END IF;
END //

DELIMITER ;

