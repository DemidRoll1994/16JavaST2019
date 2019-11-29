package by.samtsov.webxml.controller.servlet;

import by.samtsov.webxml.beans.Tariff;
import by.samtsov.webxml.service.exception.BuilderException;
import by.samtsov.webxml.service.exception.ServiceException;
import by.samtsov.webxml.service.exception.ValidateException;
import by.samtsov.webxml.service.validator.TariffXMLParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@MultipartConfig

@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(
            ControllerServlet.class);
    private static final String PARSING_ERROR = "XML-parsing Error";
    private static final String VALIDATION_ERROR = "File is invalid";
    private static final String BUILDING_ERROR = "File building error";

    private static final String UPLOAD_DIRECTORY = "uploaded files";

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp) {
        try {
            RequestDispatcher dispatcher
                    = req.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error("request for the GET could not be handled", e);
        } catch (IOException e) {
            LOGGER.error("I/O error is detected when the servlet handles"
                    + " the GET request", e);
        }
    }


    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition")
                .split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2,
                        content.length() - 1);
        }
        return "dexfaultxml.xml";
    }


    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp) {
        try {
            processPostRequest(req, resp);
        } catch (ServletException e) {
            LOGGER.error("request for the POST could not be handled", e);
        } catch (IOException e) {
            LOGGER.error("I/O error is detected when the servlet handles"
                    + " the POST request", e);
        }
    }

    private void processPostRequest(final HttpServletRequest req,
                                    final HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String uploadPath = getServletContext().getRealPath("") +
                    File.separator + UPLOAD_DIRECTORY;
            String filePath = "";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            for (Part part : req.getParts()) {
                filePath = uploadPath + File.separator + getFileName(part);
                part.write(filePath);
                LOGGER.debug(filePath);
            }
            LOGGER.debug(filePath);
            TariffXMLParser tariffXMLParser = new TariffXMLParser();
            List<Tariff> tariffs = tariffXMLParser.parseXML(
                    filePath, req.getParameter("parserName"));

            LOGGER.debug("tariffs list size is " + tariffs.size());
            req.setAttribute("tariffs", tariffs);
        } catch (ValidateException e) {
            e.printStackTrace();
            LOGGER.error(VALIDATION_ERROR + e.getMessage());
            req.setAttribute("errorMessage", VALIDATION_ERROR);
        } catch (BuilderException e) {
            e.printStackTrace();
            LOGGER.error(BUILDING_ERROR + e.getMessage());
            req.setAttribute("errorMessage", BUILDING_ERROR);
        } catch (ServiceException e) {
            e.printStackTrace();
            LOGGER.error(PARSING_ERROR + e.getMessage());
            req.setAttribute("errorMessage", PARSING_ERROR);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }

}
