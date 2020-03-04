package by.samtsov.controller.command;

public class CommandFactory {
    public static Command createCommand(AvailableCommands availableCommands) {
        Command command = null;
        switch (availableCommands) {
            case INDEX:
                command = new IndexCommand();
                break;
            case LOGIN:
                command = new LoginCommand();
                break;
            /*case LOGOUT:
                command = new LogoutCommand();
                break;
            case REGISTER:
                command = new RegisterUserCommand();
                break;*/
        }

        //command = new IndexCommand();

        return command;
    }
}
