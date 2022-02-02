package by.zastr.cafe.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.CommandType;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.RequestParameter;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.exception.CommandException;


/**
 * class ControllerServlet.
 *
 * @author A.Zastrozhyn
 * Overrides doPost and doGet methods by calling
 * the own method processRequest(request, response).
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/controller"})
public class ControllerServlet extends HttpServlet {
	
	/** The Constant CURRENT_PAGE. */
	public static final String CURRENT_PAGE = "currentPage";
	
	/** The Constant logger. */
	protected static final Logger logger = LogManager.getLogger();

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
	
	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		processRequest(request, response);  
	}
	
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Router router;
        String commandName = request.getParameter(RequestParameter.COMMAND).toUpperCase();
        Command command = CommandType.defineCommand(commandName);
        try {
            router = command.execute(request);
            if (router.getPagePath() != null) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(router.getPagePath());
                switch (router.getType()) {
                    case FORWARD:
                        requestDispatcher.forward(request, response);
                        break;
                    case REDIRECT:
                        response.sendRedirect(request.getContextPath() + router.getPagePath());
                        break;
                    default:
                        String page =PagePath.ERROR_500_PAGE;
                        response.sendRedirect(request.getContextPath() + page + "");
                }
            } else {
                response.sendRedirect(request.getContextPath() + PagePath.MAIN_PAGE);
            }
        } catch (CommandException e) {
        	logger.log(Level.ERROR,"Internal error", e);
            response.sendRedirect(request.getContextPath() + PagePath.ERROR_500_PAGE);
        }
    }
}
