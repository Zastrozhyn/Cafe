package by.zastr.cafe.controller.command.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.RequestParameter;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.controller.command.UserMessage;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.Dish;
import by.zastr.cafe.model.service.impl.DishServiceImpl;
import by.zastr.cafe.util.MessageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AddToOrderCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
		MessageManager messageManager = MessageManager.defineLocale(locale);
		List<Dish> listDish;
		BigDecimal totalCost = BigDecimal.ZERO;
		if (session.getAttribute(AttributeName.LIST_DISH) == null) {
			listDish = new ArrayList<Dish>();
		}
		else {
			listDish = (List<Dish>) session.getAttribute(AttributeName.LIST_DISH);
			totalCost = (BigDecimal) session.getAttribute(AttributeName.TOTAL_COST);
			
		}
		Router router = new Router();
		router.setPagePath(PagePath.MENU);
		int dishId =Integer.parseInt(request.getParameter(RequestParameter.DISH_ID));
		DishServiceImpl dishService = DishServiceImpl.getInstance();
		try {
			Dish dish = dishService.findById(dishId).get();
			listDish.add(dish);
			totalCost = totalCost.add(dish.getPrice());
			session.setAttribute(AttributeName.LIST_DISH, listDish);
			session.setAttribute(AttributeName.TOTAL_COST, totalCost);
			String message = messageManager.getMessage(UserMessage.ADDED_TO_ORDER) + dish.getName();
			request.setAttribute(AttributeName.MESSAGE, message);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "cannot find dish:", e);
            throw new CommandException("cannot find dish:", e);
		}
		
		return router;
	}

}
