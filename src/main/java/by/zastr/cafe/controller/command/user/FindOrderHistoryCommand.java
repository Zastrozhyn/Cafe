package by.zastr.cafe.controller.command.user;

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
import by.zastr.cafe.model.entity.CafeOrder;
import by.zastr.cafe.model.entity.User;
import by.zastr.cafe.model.service.impl.OrderServiceImpl;
import by.zastr.cafe.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class FindOrderHistoryCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = new Router();
		HttpSession session = request.getSession();
		router.setPagePath(PagePath.PROFILE);
		String login = request.getParameter(RequestParameter.NAME);
		List<CafeOrder> orders = new ArrayList<CafeOrder>();
		OrderServiceImpl orderService = OrderServiceImpl.getInstance();
		UserServiceImpl userService = UserServiceImpl.getInstance();
 		try {
 			User user = userService.findByLogin(login).get();
 			orders.addAll(orderService.findByLogin(user.getLogin()));	
 			session.setAttribute(AttributeName.ORDER_HISTORY, orders);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error in command Find all order", e);
            throw new CommandException( "Error in command Find all order", e);
		}

		return router;
	}

}
