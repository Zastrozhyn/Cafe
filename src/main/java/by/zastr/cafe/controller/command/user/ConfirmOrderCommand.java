package by.zastr.cafe.controller.command.user;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;

import static by.zastr.cafe.controller.command.RequestParameter.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.controller.command.UserMessage;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.Dish;
import by.zastr.cafe.model.service.impl.OrderServiceImpl;
import by.zastr.cafe.util.MessageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class ConfirmOrderCommand.
 *
 * @author A.Zastrozhyn
 */
public class ConfirmOrderCommand implements Command{
	private static final String DEFAULT_COMMENT = "";

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
		HttpSession session = request.getSession();
		String locale = (String) session.getAttribute(SESSION_LOCALE);
		MessageManager messageManager = MessageManager.defineLocale(locale);
		router.setRedirect();
		List<Dish> orderList = (List<Dish>) session.getAttribute(AttributeName.LIST_DISH);
		String userLogin = request.getParameter(USER_ID);
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.parse(request.getParameter(TIME));
		String description = request.getParameter(DESCRIPTION);
		String payment = request.getParameter(PAYMENT_TYPE).toUpperCase();
		BigDecimal totalCost = (BigDecimal) session.getAttribute(AttributeName.TOTAL_COST);
		OrderServiceImpl orderService = OrderServiceImpl.getInstance();
		try {
			String result = orderService.confirmOrder(userLogin, orderList, description, DEFAULT_COMMENT, date, time, payment, totalCost, locale);
			request.setAttribute(AttributeName.MESSAGE, result);
			if (!result.equals(messageManager.getMessage(UserMessage.SUCCESSFUL))) {
				router.setPagePath(PagePath.ORDER);
				router.setForward();
			}
			if (result.equals(messageManager.getMessage(UserMessage.SUCCESSFUL))) {
				session.removeAttribute(AttributeName.LIST_DISH);
				session.removeAttribute(AttributeName.TOTAL_COST);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "cannot confirm order:", e);
            throw new CommandException("cannot confirm order:", e);
		}
		return router;
	}

}
