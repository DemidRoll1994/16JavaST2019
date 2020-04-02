DROP DATABASE IF EXISTS `vehicles_sales`;
DROP USER vehicle_sales_user1;

CREATE DATABASE `vehicles_sales` DEFAULT CHARACTER SET utf8;
SET global time_zone = '+3:00';
CREATE USER vehicle_sales_user1 IDENTIFIED BY 'password';
GRANT INSERT, SELECT, DELETE, UPDATE ON `vehicles_sales`.* TO `vehicle_sales_user1`@`%`;
GRANT INSERT, SELECT, DELETE, UPDATE ON `vehicles_sales`.* TO `vehicle_sales_user1`@`localhost`;
