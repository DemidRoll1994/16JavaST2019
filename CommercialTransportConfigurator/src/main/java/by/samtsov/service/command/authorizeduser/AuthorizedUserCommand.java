package by.samtsov.service.command.authorizeduser;

import by.samtsov.bean.type.Role;
import by.samtsov.service.command.Command;

import java.util.Arrays;

public abstract class AuthorizedUserCommand extends Command {
    public AuthorizedUserCommand() {
        getAllowedRoles().addAll(Arrays.asList(Role.values()));
    }
}
