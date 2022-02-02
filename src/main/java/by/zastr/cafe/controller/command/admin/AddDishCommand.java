package by.zastr.cafe.controller.command.admin;

import static by.zastr.cafe.controller.command.RequestParameter.*;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.service.DishService;
import by.zastr.cafe.model.service.impl.DishServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class AddDishCommand.
 *
 * @author A.Zastrozhyn
 */
public class AddDishCommand implements Command{

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = new Router();
		router.setPagePath(PagePath.NEW_DISH);
		HttpSession session = request.getSession();
		String locale = (String) session.getAttribute(SESSION_LOCALE);
		String name = request.getParameter(NAME);
		String type = request.getParameter(DISH_TYPE);
		String description = request.getParameter(DESCRIPTION);
		String price = request.getParameter(DISH_PRICE);
		String weight = request.getParameter(DISH_WEIGHT);
		DishService dishService = DishServiceImpl.getInstance();
		try {
			String result = dishService.create(name, weight, price, description, type, locale);
			request.setAttribute(AttributeName.MESSAGE, result);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Dish cannot be add:", e);
            throw new CommandException("User cannot be add:", e);
		}
		return router;
	}

}
