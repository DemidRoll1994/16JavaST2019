package by.samtsov.controller.command;

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
            /*case LOGOUT:
                command = new LogoutCommand();
                break;
            */
                default:
                    logger.error("Can't create command {}. The corresponding " +
                            "case is not written", availableCommands);
            }
        }
        return command;
    }
}
