package by.samtsov.service.command.vendor;

import by.samtsov.bean.entity.Model;
import by.samtsov.bean.type.EntityType;
import by.samtsov.dao.PersistenceException;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ModelService;
import by.samtsov.service.ServiceException;
import by.samtsov.view.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowModelsCommand extends VendorCommand  {

    private static final EntityType MODEL_ENTITY_TYPE = EntityType.MODEL;
    private static Logger logger = LogManager.getLogger(
            ShowModelsCommand.class);

    @Override
    public ResponsePage execute(HttpServletRequest request, HttpServletResponse response) throws PersistenceException, InternalServerException, ServiceException {

        ResponsePage responsePage = new ResponsePage("/vendor/modelList.jsp"
                , false);
        ModelService service = factory.createService(MODEL_ENTITY_TYPE);

        List<Model> models = service.getAll();
        logger.trace("models array size {}", models == null ? null :
                models.size());
        request.setAttribute("models", models);
        return responsePage;
    }
}
