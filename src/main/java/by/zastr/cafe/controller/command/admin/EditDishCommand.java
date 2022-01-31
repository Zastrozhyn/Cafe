package by.zastr.cafe.controller.command.admin;

import static by.zastr.cafe.controller.command.RequestParameter.*;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.controller.command.UserMessage;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.service.impl.DishServiceImpl;
import by.zastr.cafe.util.MessageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class EditDishCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = new Router();
		router.setPagePath(PagePath.MENU);
		HttpSession session = request.getSession();
		String locale = (String) session.getAttribute(SESSION_LOCALE);
		MessageManager messageManager = MessageManager.defineLocale(locale);
		router.setPagePath(PagePath.MAIN_PAGE);
		router.setRedirect();
		int id = Integer.parseInt(request.getParameter(DISH_ID));
		String name = request.getParameter(NAME);
		String type = request.getParameter(DISH_TYPE);
		String description = request.getParameter(DESCRIPTION);
		String price = request.getParameter(DISH_PRICE);
		String weight = request.getParameter(DISH_WEIGHT);
		DishServiceImpl dishService = DishServiceImpl.getInstance();
		try {
			String result = dishService.update(id, name, weight, price, description, type, locale);
			request.setAttribute(AttributeName.MESSAGE, result);
			if (!result.equals(messageManager.getMessage(UserMessage.SUCCESSFUL))) {
				router.setPagePath(PagePath.EDIT_DISH);
				router.setForward();
			}
			
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Dish cannot be add:", e);
            throw new CommandException("User cannot be add:", e);
		}
		return router;
	}
	

}
