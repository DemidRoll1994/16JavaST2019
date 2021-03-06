USE `vehicles_sales`;
CREATE TABLE `users`
(
    `id`            INTEGER             NOT NULL AUTO_INCREMENT,
    `email`         VARCHAR(255) unique not null,
    `password_Hash` text(512)           NOT NULL,
    `salt`          varchar(255)        NOT NULL,
    `name`          VARCHAR(255),
    `surname`       VARCHAR(255),

    /*
     * 0 - not activated
     * 1 - activated
     * 2 - blocked
     */
    `status`        TINYINT             NOT NULL CHECK (`status` IN (0, 1, 2)),
    /*
     * 0 - admin
     * 1 - buyer
     * 2 - vendor
     */
    `role`          TINYINT             NOT NULL CHECK (`role` IN (0, 1, 2)),
    `company`       VARCHAR(255),
    `PHONE_NUMBER`  BIGINT,
    `ADDRESS`       VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `orders`
(
    `id`      INTEGER           NOT NULL AUTO_INCREMENT,
    `user_id` INTEGER           NOT NULL,
    /*
     * 0 - ФОРМИРУЕТСЯ
     * 1 - СФОРМИРОВАН 
     * 2 - ПОДТВЕРЖЕН 
     * 3 - ВЫПОЛНЯЕТСЯ 
     * 4 - ВЫПОЛНЕН 
     */
    `STATUS`  TINYINT DEFAULT 0 NOT NULL CHECK (`STATUS` IN (0, 1, 2, 3, 4)),
    PRIMARY KEY (`id`),
    Foreign KEY (user_id) REFERENCES users (id)
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;



CREATE TABLE `OPTIONS`
(
    `id`          INTEGER      NOT NULL AUTO_INCREMENT,
    `NAME`        VARCHAR(255) NOT NULL,
    /*now is unavailable
    0-string
      1-integer
      2-double
      3-boolean*/
    `option_type` tinyint      NOT NULL CHECK (`option_type` IN (0, 1, 2, 3)),
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;


CREATE TABLE `OPTION_VALUES`
(
    `id`          INTEGER      NOT NULL AUTO_INCREMENT,
    `value`       VARCHAR(255) NOT NULL,
    `description` text         NOT NULL,
    `price`       double       NOT NULL,
    `OPTION_ID`   INTEGER      NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (OPTION_ID) REFERENCES OPTIONS (id)
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;


CREATE TABLE `Models`
(
    `id`          INTEGER      NOT NULL AUTO_INCREMENT,
    `model_name`  VARCHAR(255) NOT NULL,
    `basic_price` double       NOT NULL,
    `img_path`    VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;


CREATE TABLE `available_model_OPTION_Values`
(
    `model_ID`        INTEGER NOT NULL,
    `OPTION_value_ID` INTEGER NOT NULL,
    PRIMARY KEY (`model_ID`, `OPTION_value_ID`),
    FOREIGN KEY (`model_ID`) REFERENCES `Models` (`ID`)
        ON DELETE RESTRICT,
    FOREIGN KEY (`OPTION_value_ID`) REFERENCES `OPTION_VALUES` (`ID`)
        ON DELETE RESTRICT

) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;



CREATE TABLE `CONFIGURATIONS`
(
    `id`       INTEGER      NOT NULL AUTO_INCREMENT,
    `name`     varchar(255) not null,
    `ORDER_ID` INTEGER,
    `MODEL_ID` INTEGER,
    `OWNER_ID` INTEGER,
    `img_path` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`id`),
    FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (id)
        ON DELETE RESTRICT,
    FOREIGN KEY (MODEL_ID) REFERENCES MODELS (id)
        ON DELETE RESTRICT,
    Foreign KEY (OWNER_ID) REFERENCES USERS (id)
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;


CREATE TABLE `selected_config_OPTION_Values`
(
    `config_ID`       INTEGER NOT NULL,
    `OPTION_value_ID` INTEGER NOT NULL,
    PRIMARY KEY (`config_ID`, `OPTION_value_ID`),
    FOREIGN KEY (`config_ID`) REFERENCES `CONFIGURATIONS` (`ID`)
        ON DELETE RESTRICT,
    FOREIGN KEY (`OPTION_value_ID`) REFERENCES `OPTION_VALUES` (`ID`)
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;