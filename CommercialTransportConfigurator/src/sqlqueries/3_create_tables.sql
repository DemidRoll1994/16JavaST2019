USE `vehiclessales`;
CREATE TABLE `users`
(
    `id` 			INTEGER			NOT NULL AUTO_INCREMENT,
    `login`    		VARCHAR(255)	NOT NULL UNIQUE,
    `password_Hash`	CHAR(256)		NOT NULL,
    `salt`			CHAR(32)		NOT NULL,
	
    /*
     * 0 - не активирован
     * 1 - активирован 
     * 2 - заблокирован 
     */
     `status` 		TINYINT  		NOT NULL CHECK (`role` IN (0, 1, 2)),
    /*
     * 0 - администратор
     * 1 - покупатель
     * 2 - продавец
     */
    `role`     		TINYINT      	NOT NULL CHECK (`role` IN (0, 1, 2)),
	`company`  		VARCHAR(255),
	`PHONE_NUMBER` 	BIGINT,
	`ADDRESS` 		VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;
  
  CREATE TABLE `orders`
(
    id				INTEGER			NOT NULL AUTO_INCREMENT,
    user_id	    	VARCHAR(255)	NOT NULL ,
	
    /*
     * 0 - ФОРМИРУЕТСЯ
     * 1 - СФОРМИРОВАН 
     * 2 - ПОДТВЕРЖЕН 
     * 3 - ВЫПОЛНЯЕТСЯ 
     * 4 - ВЫПОЛНЕН 
     */
	
	STATUS 		TINYINT NOT NULL DEFAULT '0',
	
    PRIMARY KEY (`id`)
	FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

 CREATE TABLE `CONFIGURATIONS`
(
    `id` 			INTEGER			NOT NULL AUTO_INCREMENT,
    `ORDER_ID`    	VARCHAR(255)	NOT NULL ,
	`vehicle_name`	VARCHAR(255)	NOT NULL,
	`basic_config`	tinyint(1)		default 0,
    PRIMARY KEY (`id`)
	FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(id)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;
  
  
 CREATE TABLE `CONFIGURATION_OPTIONS`
(
    `CONFIGURATION_ID` 		INTEGER			NOT NULL ,
    `OPTIONS_ID`    		INTEGER			NOT NULL ,
    PRIMARY KEY (`CONFIGURATION_ID`, `OPTIONS_ID`),
    CONSTRAINT `CONFIGURATION_CONFOPTTABLE_fk`
        FOREIGN KEY `CONFIGURATION_fk` (`CONFIGURATION_ID`) REFERENCES `CONFIGURATIONS` (`ID`)
    CONSTRAINT `OPTIONS_CONFOPTTABLE_fk`
        FOREIGN KEY `OPTIONS_fk` (`OPTIONS_ID`) REFERENCES `OPTIONS_ID` (`ID`)
        
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;
  
 CREATE TABLE `OPTIONS`
(
    `id` 			INTEGER			NOT NULL AUTO_INCREMENT,
    `NAME`    		VARCHAR(255)	NOT NULL ,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;
  
 CREATE TABLE `OPTIONS_VALUES`
(
    `id` 			INTEGER			NOT NULL AUTO_INCREMENT,
    `VALUE`    		VARCHAR(255)	NOT NULL ,
    `OPTIONS_ID`    INTEGER			NOT NULL ,
    PRIMARY KEY (`id`)
	FOREIGN KEY (OPTIONS_ID) REFERENCES OPTIONS(id)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;


