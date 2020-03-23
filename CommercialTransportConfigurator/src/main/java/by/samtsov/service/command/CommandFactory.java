package by.samtsov.service.command;

import by.samtsov.service.command.admin.EditUsersDataCommand;
import by.samtsov.service.command.admin.SaveUserDataCommand;
import by.samtsov.service.command.admin.ShowUsersCommand;
import by.samtsov.service.command.authorizeduser.EditPersonalDataCommand;
import by.samtsov.service.command.authorizeduser.LogoutCommand;
import by.samtsov.service.command.authorizeduser.SavePersonalDataCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {
    private static Logger logger = LogManager.getLogger(CommandFactory.class);

    public static Command createCommand(AvailableCommand availableCommands) {
        Command command = null;
        if (availableCommands != null) {
            switch (availableCommands) {
                case INDEX:
                    command = new IndexCommand();
                    break;
                case LOGIN:
                    command = new LoginCommand();
                    break;
                case REGISTER:
                    command = new RegisterUserCommand();
                    break;
                case LOGOUT:
                    command = new LogoutCommand();
                    break;
                case PROFILE_EDIT:
                    command = new EditPersonalDataCommand();
                    break;
                case PROFILE_SAVE:
                    command = new SavePersonalDataCommand();
                    break;
                case EDIT_USERS_DATA:
                    command = new EditUsersDataCommand();
                    break;
                case SAVE_USER_DATA:
                    command =new SaveUserDataCommand();
                    break;
                case SHOW_USERS_DATA:
                    command = new ShowUsersCommand();
                    break;
                default:
                    logger.error("Can't create command {}. The corresponding " +
                            "case is not written", availableCommands);
            }
        }
        return command;
    }
}
