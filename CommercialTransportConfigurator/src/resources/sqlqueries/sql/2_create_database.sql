CREATE DATABASE `library_db` DEFAULT CHARACTER SET utf8;

GRANT SELECT,INSERT,UPDATE,DELETE
ON `library_db`.*
TO library_user@localhost
IDENTIFIED BY 'library_password';

GRANT SELECT,INSERT,UPDATE,DELETE
ON `library_db`.*
TO library_user@'%'
IDENTIFIED BY 'library_password';