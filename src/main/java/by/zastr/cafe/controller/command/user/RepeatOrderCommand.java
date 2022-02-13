package by.zastr.cafe.controller.command.user;

import static by.zastr.cafe.controller.command.RequestParameter.ORDER_ID;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.CafeOrder;
import by.zastr.cafe.model.entity.Dish;
import by.zastr.cafe.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class RepeatOrderCommand.
 *
 * @author A.Zastrozhyn
 */
public class RepeatOrderCommand implements Command{

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
		router.setPagePath(PagePath.ORDER);
		HttpSession session = request.getSession();
		int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
		OrderServiceImpl orderService = OrderServiceImpl.getInstance();
		try {
			CafeOrder order = orderService.findById(orderId).get();
			List<Dish> listDish = new ArrayList<Dish>(order.getOrderList());
			session.setAttribute(AttributeName.LIST_DISH, listDish);
			session.setAttribute(AttributeName.TOTAL_COST, order.getTotalCost());
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error in command repeat order", e);
            throw new CommandException( "Error in command repeat order", e);
		}
		return router;
	}
	

}
