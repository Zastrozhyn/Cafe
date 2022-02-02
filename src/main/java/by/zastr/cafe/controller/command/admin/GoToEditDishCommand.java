package by.zastr.cafe.controller.command.admin;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.RequestParameter;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.Dish;
import by.zastr.cafe.model.service.impl.DishServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

/**
 * class GoToEditDishCommand
 * @author A.Zastrozhyn
 *
 */
public class GoToEditDishCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = new Router();
		router.setPagePath(PagePath.EDIT_DISH);
		int dishId =Integer.parseInt(request.getParameter(RequestParameter.DISH_ID));
		DishServiceImpl dishService = DishServiceImpl.getInstance();
		try {
			Dish dish = dishService.findById(dishId).get();
			request.setAttribute(AttributeName.DISH, dish);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "cannot find dish:", e);
            throw new CommandException("cannot find dish:", e);
		}
		return router;
	}

}
