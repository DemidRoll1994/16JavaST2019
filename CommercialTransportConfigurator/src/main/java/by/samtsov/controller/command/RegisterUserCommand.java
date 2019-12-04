package by.samtsov.controller.command;

import by.samtsov.bean.User;
import by.samtsov.bean.enums.EntityType;
import by.samtsov.bean.exceptions.IncorrectDataException;
import by.samtsov.bean.exceptions.InternalServerException;
import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.service.ServiceFactory;
import by.samtsov.service.UserService;
import by.samtsov.service.parser.ParserFactory;
import by.samtsov.service.parser.UserParser;
import by.samtsov.service.validator.UserValidator;
import by.samtsov.service.validator.ValidatorFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUserCommand extends Command {

    private final EntityType USER_ENTITY_TYPE = EntityType.USER;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Forward forward = new Forward("/user/edit.html");
        try {
            UserParser userParser = ParserFactory.createParser(USER_ENTITY_TYPE);
            User user = userParser.parse(request);
            UserValidator validator = ValidatorFactory.createValidator(USER_ENTITY_TYPE);
            UserService service = ServiceFactory.createService(USER_ENTITY_TYPE);
            if (validator.isValid(user))
            {
                if (user.getId() !=service.save(user)){

                }
                forward.getAttributes().put("identity", user.getIdentity());
                forward.getAttributes().put("message", "Данные сотрудника успешно сохранены");
                logger.info(String.format("User \"%s\" saved user with identity %d", getAuthorizedUser().getLogin(), user.getIdentity()));
            }
        } catch (IncorrectDataException e) {
            forward.getAttributes().put("message", "Были обнаружены некорректные данные");
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save user", getAuthorizedUser().getLogin()), e);
        } catch (PersistentException e) {
            forward.getAttributes().put("message", "Были обнаружены некорректные данные");
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save user", getAuthorizedUser().getLogin()), e);
        } catch (InternalServerException e) {
            forward.getAttributes().put("message", "внутрення ошибка сервера, запрос не может быть обработан");
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save user", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }


}
