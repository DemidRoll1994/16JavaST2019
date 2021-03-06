package by.samtsov.service.command.admin;

import by.samtsov.bean.type.Role;
import by.samtsov.service.command.Command;

public abstract class AdminCommand extends Command {

    public AdminCommand() {
        getAllowedRoles().add(Role.ADMIN);
    }
}
