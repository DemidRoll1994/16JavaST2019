USE `vehiclessales`;
INSERT INTO `users` (`id`,
                     `email`,
                     `password_Hash`,
                     `salt`,
                     `name`,
                     `surname`,

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
        "admin@admin.com",
        /* password hash for "Admin_2020" */
        "6a1t7bLOcKIYNqJ/FA6AOwoT0RBc4pJ+nPyNrBalQ7g3dVab1B5UI3mtQweIyWTunHTWE3e+vCQEcspavL5wEw==",
        /*salt for password "Admin_2020"*/
        "RC38nMA2111pEqpHEdGxhEVSO00gK48luElziSf3dJxSBzrSY99GIWj3ori9uEep0KnUziBvyMbbbKCBIiA0eQOhAe4JfJk5geuxQEKX1iYctubbv9CuYOt59xateosdHxcYzHBNNnGhzR2Z1iswuOuTJs7KundoASmJRX7djBEaWJKk5nS1Xnp2MlJ3oK9nyW94WCmEJPX4MUwGp4gLy2Yev8bXAauXCT0rbVgpfFKLEmbaqPDCsJVw63aLfCB", /*todo salt and hash*/
        "admin",
        "adminov",
        1,
        0);

