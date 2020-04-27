USE `vehicles_sales`;
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
     * 0 - BUYER,
     * 1 - ADMIN,
     * 2 - VENDOR;
     */
                     `role`)
VALUES (1,
        "admin@admin.com",
        /* password hash for "Admin_2020" */
        "c/QM75wrF0Gtt9olWiBva6uNyeZFTWdAE5EqRqgnASnyhIEaUmsCmi4RfAaDyjJMVwJc4qQK3AkOkcYpOfDz0Q==",
        /*salt for password "Admin_2020"*/
        "U2FoHtngTOPHEdDMwbtcUfaJ4F748N5vNdEWgiGeP5ncsdbWyf6cRUvCmrwhhLd6ILF2B6B9FfVKYOEtBnWyYCRTqTJgHmfMMrGzVSkbd3EHNQjX59KESTkW7lOOP1czfoIe1F8jLRKoeaFctHWaghpyEdUfwSZoqf5jA7xQ9Yimp30KxUhYccyZvnr9FVfZ5XouX3Ho9BTNfnbYdvSXpmZCYcflKXWBscJhaOhAkwxCc9J5jqaJpfebBJVMmX0", /*todo salt and hash*/
        "admin",
        "adminov",
        1,
        1);

