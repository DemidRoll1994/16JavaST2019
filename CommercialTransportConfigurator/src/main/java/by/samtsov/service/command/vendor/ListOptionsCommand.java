package by.samtsov.service.command.vendor;

import by.samtsov.bean.entity.Option;
import by.samtsov.bean.type.EntityType;
import by.samtsov.dao.PersistenceException;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.OptionService;
import by.samtsov.service.ServiceException;
import by.samtsov.service.command.admin.EditUsersDataCommand;
import by.samtsov.view.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListOptionsCommand extends VendorCommand {

    private static final EntityType OPTION_ENTITY_TYPE = EntityType.OPTION;
    private static Logger logger = LogManager.getLogger(
            EditUsersDataCommand.class);

    @Override
    public ResponsePage execute(HttpServletRequest request, HttpServletResponse response) throws PersistenceException, InternalServerException, ServiceException {

        ResponsePage responsePage = new ResponsePage("/vendor/optionsList.jsp"
                , false);
        OptionService service = factory.createService(OPTION_ENTITY_TYPE);

        List<Option> options = service.getAll();
        logger.trace("options array size {}", options == null ? null :
                options.size());
        request.setAttribute("options", options);
        return responsePage;

    }
}
