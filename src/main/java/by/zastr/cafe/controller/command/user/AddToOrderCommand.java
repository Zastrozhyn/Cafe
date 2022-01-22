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
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.Dish;
import by.zastr.cafe.model.service.impl.DishServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AddToOrderCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		List<Dish> orderList;
		BigDecimal totalCost = BigDecimal.ZERO;
		if (session.getAttribute(AttributeName.ORDER_LIST) == null) {
			orderList = new ArrayList<Dish>();
		}
		else {
			orderList = (List<Dish>) session.getAttribute(AttributeName.ORDER_LIST);
			totalCost = (BigDecimal) session.getAttribute(AttributeName.TOTAL_COST);
			
		}
		Router router = new Router();
		router.setPagePath(PagePath.MENU);
		int dishId =Integer.parseInt(request.getParameter(RequestParameter.DISH_ID));
		DishServiceImpl dishService = DishServiceImpl.getInstance();
		try {
			Dish dish = dishService.findById(dishId).get();
			orderList.add(dish);
			totalCost = totalCost.add(dish.getPrice());
			session.setAttribute(AttributeName.ORDER_LIST, orderList);
			session.setAttribute(AttributeName.TOTAL_COST, totalCost);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "cannot find dish:", e);
            throw new CommandException("cannot find dish:", e);
		}
		
		return router;
	}

}
