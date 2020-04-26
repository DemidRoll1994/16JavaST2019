package by.samtsov.service.command;

import by.samtsov.service.IncorrectDataException;
import by.samtsov.view.ResponsePage;
import by.samtsov.service.InternalServerException;
import by.samtsov.dao.PersistenceException;
import by.samtsov.service.ServiceException;
import by.samtsov.service.sql.SQLServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandManagerImpl implements CommandManager {

    private static Logger logger = LogManager.getLogger(CommandManagerImpl.class);

    private SQLServiceFactory serviceFactory;

    public CommandManagerImpl(SQLServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public ResponsePage execute(Command command, HttpServletRequest request, HttpServletResponse response) {
        command.setServiceFactory(serviceFactory);

        try {
            return command.execute(request, response);
        } catch (IncorrectDataException e) {
            logger.debug("incorrect data : " + command.getName() + ". Error: ", e);
            request.setAttribute("error",
                    "Неверно введены данные. <br>" + e.getErrorMessage());
            return new ResponsePage("/error.jsp", false); //todo сделать
            // нормальную проброску на страницу через json
        } catch (PersistenceException e) { // todo объединить
            logger.error("It is impossible to process command: " + command.getName() + ". Error: \n", e);
            request.setAttribute("error", "Ошибка обработки данных. " +
                    "Обратитесь к администратору или попробуйте позднее");
            return new ResponsePage("/error.jsp", false);
        } catch (InternalServerException | ServiceException e) {
            logger.error("It is impossible to process command: " + command.getName() + ". Error: \n", e);
            request.setAttribute("error", "Ошибка обработки данных. " +
                    "Обратитесь к администратору или попробуйте позднее");
            return new ResponsePage("/error.jsp", false);
        }
    }

    @Override
    public void close() throws PersistenceException {
        serviceFactory.close();
    }
}
