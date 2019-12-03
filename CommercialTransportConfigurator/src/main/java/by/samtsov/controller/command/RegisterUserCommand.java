package by.samtsov.controller.command;

import by.samtsov.bean.User;
import by.samtsov.service.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.swing.text.Position.Bias.Forward;

public class RegisterUserCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Forward forward = new Forward("/user/edit.html");
        try {
            Validator<User> validator = ValidatorFactory.createValidator(User.class);
            User user = validator.validate(request);
            UserService service = factory.getService(UserService.class);
            service.save(user);
            forward.getAttributes().put("identity", user.getIdentity());
            forward.getAttributes().put("message", "Данные сотрудника успешно сохранены");
            logger.info(String.format("User \"%s\" saved user with identity %d", getAuthorizedUser().getLogin(), user.getIdentity()));
        } catch(IncorrectFormDataException e) {
            forward.getAttributes().put("message", "Были обнаружены некорректные данные");
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save user", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }


}
