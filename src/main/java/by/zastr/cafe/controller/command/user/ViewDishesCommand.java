package by.zastr.cafe.controller.command.user;

import static by.zastr.cafe.controller.command.RequestParameter.ORDER_ID;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.RequestParameter;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.CafeOrder;
import by.zastr.cafe.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

/**
 * class ViewDishesCommand.
 *
 * @author A.Zastrozhyn
 */
public class ViewDishesCommand implements Command{

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
		boolean isAdminPage = Boolean.parseBoolean(request.getParameter(RequestParameter.ADMIN_PAGE));
		if(isAdminPage) {
			router.setPagePath(PagePath.ADMIN_ORDERS);
		}
		else {
			router.setPagePath(PagePath.PROFILE);
		}
		int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
		OrderServiceImpl orderService = OrderServiceImpl.getInstance();
		try {
			CafeOrder order = orderService.findById(orderId).get();
			request.setAttribute(AttributeName.ORDER_HISTORY_LIST, order.getOrderList());
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error in command Find order", e);
            throw new CommandException( "Error in command Find order", e);
		}
		return router;
	}

}
