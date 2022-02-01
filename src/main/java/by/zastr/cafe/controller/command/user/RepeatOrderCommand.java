package by.zastr.cafe.controller.command.user;

import static by.zastr.cafe.controller.command.RequestParameter.ORDER_ID;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.CafeOrder;
import by.zastr.cafe.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class RepeatOrderCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = new Router();
		router.setPagePath(PagePath.ORDER);
		HttpSession session = request.getSession();
		int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
		OrderServiceImpl orderService = OrderServiceImpl.getInstance();
		try {
			CafeOrder order = orderService.findById(orderId).get();
			session.setAttribute(AttributeName.LIST_DISH, order.getOrderList());
			session.setAttribute(AttributeName.TOTAL_COST, order.getTotalCost());
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error in command repeat order", e);
            throw new CommandException( "Error in command repeat order", e);
		}
		return router;
	}
	

}
