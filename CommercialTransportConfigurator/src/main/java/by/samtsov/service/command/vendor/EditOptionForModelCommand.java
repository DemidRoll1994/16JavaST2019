package by.samtsov.service.command.vendor;

import by.samtsov.bean.entity.Model;
import by.samtsov.bean.entity.OptionValue;
import by.samtsov.bean.type.EntityType;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ModelService;
import by.samtsov.service.ServiceException;
import by.samtsov.view.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class EditOptionForModelCommand extends VendorCommand {

    private static final EntityType MODEL_ENTITY_TYPE = EntityType.MODEL;
    private static Logger logger = LogManager.getLogger(
            EditOptionForModelCommand.class);

    @Override
    public ResponsePage execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException, ServiceException {

        ResponsePage responsePage = new ResponsePage("/vendor/editOptionForModel.jsp"
                , false);
        ModelService modelService = factory.createService(MODEL_ENTITY_TYPE);
        int modelId = -1;
        try {
            String id = request.getParameter("modelId");
            if (id != null) {
                modelId = Integer.parseInt(id);
            }
        } catch (Exception e) {
            throw new ServiceException("modelId is invalid", e);
        }


        int optionId = -1;
        try {
            String id = request.getParameter("optionId");
            if (id != null) {
                optionId = Integer.parseInt(id);
            }
        } catch (Exception e) {
            throw new ServiceException("optionId is invalid", e);
        }

        Model model = modelService.get(modelId);
        logger.trace("model {} is selected ", model == null ? null :
                model.getId());
        request.setAttribute("model", model);


        HashMap<OptionValue, Boolean> activeOptions
                = modelService.findActiveValuesForOption(modelId, optionId);
        if (activeOptions != null && activeOptions.size() > 0) {
            request.setAttribute("activeOptions", activeOptions);
        }

        return responsePage;
    }
}
