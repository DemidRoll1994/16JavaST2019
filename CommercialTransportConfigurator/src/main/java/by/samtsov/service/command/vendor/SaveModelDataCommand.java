package by.samtsov.service.command.vendor;

import by.samtsov.bean.entity.Model;
import by.samtsov.bean.type.EntityType;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ModelService;
import by.samtsov.service.ServiceException;
import by.samtsov.view.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveModelDataCommand extends VendorCommand {

    private static Logger logger = LogManager.getLogger(
            SaveModelDataCommand.class);
    private final EntityType MODEL_ENTITY_TYPE = EntityType.MODEL;

    public ResponsePage execute(HttpServletRequest request,
                                HttpServletResponse response) throws InternalServerException, ServiceException {
        ResponsePage responsePage = new ResponsePage("/models/editModel.action",
                true);
        int id;
        try {
            id = Integer.parseInt(request.getParameter("modelId"));
        } catch (NumberFormatException | NullPointerException e) {
            throw new ServiceException("modelId is not found", e);
        }
        String name = request.getParameter("name");
        String basicPrice = request.getParameter("basicPrice");
        //List<Option> options = request.getAttribute("options");
        ModelService service = factory.createService(MODEL_ENTITY_TYPE);
        Model model = service.get(id);
        model.setName(name);
        model.setPrice(Double.parseDouble(basicPrice));
        //todo model.setAvailableOptions(options);
        service.update(model);
        responsePage.getRedirectedAttributes().put("userId", id);
        logger.debug("model {} is updated with values: name {}, basicprice {}" ,
                model.getId(), name, basicPrice);
        return responsePage;
    }

}
