USE `vehiclessales`;
INSERT INTO `users` (`id`,
                     `login`,
                     `password_Hash`,
                     `salt`,

    /*
     * 0 - не активирован
     * 1 - активирован
     * 2 - заблокирован
     */
                     `status`,
    /*
     * 0 - администратор
     * 1 - покупатель
     * 2 - продавец
     */
                     `role`)
VALUES (1,
        "admin",
        "21232F297A57A5A743894A0E4A801FC3", /* MD5 хэш пароля "admin" */
        "21232F297A57A5A743894A0E4A801FC3", /*todo salt and hash*/
        1,
        0);

