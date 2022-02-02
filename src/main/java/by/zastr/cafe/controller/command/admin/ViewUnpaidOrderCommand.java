package by.zastr.cafe.controller.command.admin;

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
import by.zastr.cafe.model.service.OrderService;
import by.zastr.cafe.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

/**
 * class ViewUnpaidOrderCommand.
 *
 * @author A.Zastrozhyn
 */
public class ViewUnpaidOrderCommand implements Command{

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		OrderService orderService = OrderServiceImpl.getInstance();
        Router router = new Router();
        try {
        	List<CafeOrder> orderList = new ArrayList<CafeOrder>();
 			orderList = orderService.findUnpaid();
 			request.setAttribute(AttributeName.ORDER_LIST, orderList);
 			router.setPagePath(PagePath.ADMIN_ORDERS);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Cannot find all order", e);
            throw new CommandException("Cannot find all order", e);
		}
        
		return router;
	}
}
