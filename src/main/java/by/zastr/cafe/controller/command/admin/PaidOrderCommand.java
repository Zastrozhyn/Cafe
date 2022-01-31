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
import by.zastr.cafe.model.service.impl.OrderServiceImpl;
import by.zastr.cafe.util.MessageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


public class PaidOrderCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		Router router = new Router();
		String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
		MessageManager messageManager = MessageManager.defineLocale(locale);
		router.setPagePath(PagePath.ADMIN_ORDERS);
		int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
		OrderServiceImpl orderService = OrderServiceImpl.getInstance();
		try {
			orderService.paid(orderId);
			request.setAttribute(AttributeName.MESSAGE, messageManager.getMessage(UserMessage.SUCCESSFUL));
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Cannot paid order", e);
            throw new CommandException("Cannot paid order", e);
		}

		return router;
	}

}
