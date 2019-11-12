USE `vehiclessales`;
CREATE TABLE `users`
(
    `identity` INTEGER      NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(255) NOT NULL UNIQUE,
    `password` CHAR(256)     NOT NULL,
    `salt`      CHAR(32)    not null ,
    /*
     *
     */
     `status` tinyint .....https://metanit.com/sql/mysql/2.5.php.. ,
    /*
     * 0 - администратор (Role.ADMINISTRATOR)
     * 1 - архивариус (Role.REGISTRAR)
     * 2 - библиотекарь (Role.LIBRARIAN)
     */
    `role`     TINYINT      NOT NULL CHECK (`role` IN (0, 1, 2)),
    PRIMARY KEY (`identity`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;




