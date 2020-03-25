package by.samtsov.service.command.vendor;

import by.samtsov.bean.type.Role;
import by.samtsov.service.command.Command;

import java.util.Arrays;

public abstract class VendorCommand extends Command {
    public VendorCommand() {
        getAllowedRoles().addAll(Arrays.asList(Role.ADMIN));
    }
}
