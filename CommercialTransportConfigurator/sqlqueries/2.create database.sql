create database `vehiclesSales`;

create user vehiclesSalesUser1 identified BY 'vehiclesSalesUser1Password';

GRANT SELECT,INSERT,UPDATE,DELETE
    ON `vehiclesSales`.*
    TO vehiclesSalesUser1@localhost
IDENTIFIED BY 'vehiclesSalesUser1Password';

GRANT SELECT,INSERT,UPDATE,DELETE
    ON `vehiclesSales`.*
    TO vehiclesSalesUser1@'%'
IDENTIFIED BY 'vehiclesSalesUser1Password';