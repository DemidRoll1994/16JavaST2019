USE `vehiclessales`;
CREATE TABLE `users`
(
    `id`            INTEGER      NOT NULL AUTO_INCREMENT,
    `login`         VARCHAR(255) NOT NULL UNIQUE,
    `password_Hash` text(512)    NOT NULL,
    `salt`          text(512)     NOT NULL,

    /*
     * 0 - не активирован
     * 1 - активирован 
     * 2 - заблокирован 
     */
    `status`        TINYINT      NOT NULL CHECK (`role` IN (0, 1, 2)),
    /*
     * 0 - администратор
     * 1 - покупатель
     * 2 - продавец
     */
    `role`          TINYINT      NOT NULL CHECK (`role` IN (0, 1, 2)),
    `company`       VARCHAR(255),
    `PHONE_NUMBER`  BIGINT,
    `ADDRESS`       VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `orders`
(
    id      INTEGER      NOT NULL AUTO_INCREMENT,
    user_id INTEGER      NOT NULL,

    /*
     * 0 - ФОРМИРУЕТСЯ
     * 1 - СФОРМИРОВАН 
     * 2 - ПОДТВЕРЖЕН 
     * 3 - ВЫПОЛНЯЕТСЯ 
     * 4 - ВЫПОЛНЕН 
     */

    STATUS  TINYINT DEFAULT 0 NOT NULL CHECK (`STATUS` IN (0, 1, 2, 3, 4)) ,

    PRIMARY KEY (`id`),
    Foreign KEY (user_id) REFERENCES users (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `CONFIGURATIONS`
(
    `id`           INTEGER      NOT NULL AUTO_INCREMENT,
    `ORDER_ID`     INTEGER       NOT NULL,
    `vehicle_name` VARCHAR(255) NOT NULL,
    `basic_config` tinyint(1) default 0,
    PRIMARY KEY (`id`),
    FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;


CREATE TABLE `CONFIGURATION_OPTIONS`
(
    `CONFIGURATION_ID` INTEGER NOT NULL,
    `OPTIONS_ID`       INTEGER NOT NULL,
    PRIMARY KEY (`CONFIGURATION_ID`, `OPTIONS_ID`),
        FOREIGN KEY  (`CONFIGURATION_ID`) REFERENCES `CONFIGURATIONS` (`ID`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
        FOREIGN KEY (`OPTIONS_ID`) REFERENCES `OPTIONS` (`ID`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT

) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `OPTIONS`
(
    `id`   INTEGER      NOT NULL AUTO_INCREMENT,
    `NAME` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `OPTIONS_VALUES`
(
    `id`         INTEGER      NOT NULL AUTO_INCREMENT,
    `VALUE`      VARCHAR(255) NOT NULL,
    `OPTIONS_ID` INTEGER      NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (OPTIONS_ID) REFERENCES OPTIONS (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;


