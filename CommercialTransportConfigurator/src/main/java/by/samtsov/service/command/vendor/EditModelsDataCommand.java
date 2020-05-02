package by.samtsov.service.command.vendor;

import by.samtsov.bean.entity.Model;
import by.samtsov.bean.entity.Option;
import by.samtsov.bean.type.EntityType;
import by.samtsov.dao.PersistenceException;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ModelService;
import by.samtsov.service.OptionService;
import by.samtsov.service.ServiceException;
import by.samtsov.service.command.admin.AdminCommand;
import by.samtsov.view.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class EditModelsDataCommand extends AdminCommand {

    private static final EntityType MODEL_ENTITY_TYPE = EntityType.MODEL;
    private static final EntityType OPTION_ENTITY_TYPE = EntityType.OPTION;
    private static Logger logger = LogManager.getLogger(
            EditModelsDataCommand.class);

    @Override
    public ResponsePage execute(HttpServletRequest request, HttpServletResponse response) throws PersistenceException, InternalServerException, ServiceException {

        ResponsePage responsePage = new ResponsePage("/vendor/editModel.jsp"
                , false);
        ModelService modelService = factory.createService(MODEL_ENTITY_TYPE);
        OptionService optionService = factory.createService(OPTION_ENTITY_TYPE);
        int modelId = -1;
        try {
            String id = request.getParameter("modelId");
            if (id == null) {
                modelId = (Integer) request.getAttribute("modelId");
            } else {
                modelId = Integer.parseInt(request.getParameter("modelId"));
            }
        } catch (Exception e) {
            throw new ServiceException("modelId is invalid", e);
        }
        Model model = modelService.get(modelId);
        logger.trace("model {} is selected ", model == null ? null :
                model.getId());
        request.setAttribute("model", model);

        List<Option> fullOptions = new ArrayList<>();
        for (Option option: model.getAvailableOptions().values()) {
            fullOptions.add(optionService.get(option.getId()));
        }
        request.setAttribute("fullOptions",fullOptions);
        return responsePage;
    }
}
