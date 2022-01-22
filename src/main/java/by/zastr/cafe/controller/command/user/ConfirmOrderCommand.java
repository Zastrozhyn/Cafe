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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ConfirmOrderCommand implements Command{
	private static final String DEFAULT_COMMENT = "";

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = new Router();
		router.setRedirect();
		HttpSession session = request.getSession();
		List<Dish> orderList = (List<Dish>) session.getAttribute(AttributeName.ORDER_LIST);
		int userId = Integer.parseInt(request.getParameter(USER_ID));
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.parse(request.getParameter(TIME));
		String description = request.getParameter(DESCRIPTION);
		String payment = request.getParameter(PAYMENT_TYPE).toUpperCase();
		BigDecimal totalCost = (BigDecimal) session.getAttribute(AttributeName.TOTAL_COST);
		OrderServiceImpl orderService = OrderServiceImpl.getInstance();
		try {
			String result = orderService.confirmOrder(userId, orderList, description, DEFAULT_COMMENT, date, time, payment, totalCost);
			request.setAttribute(AttributeName.MESSAGE, result);
			if (!result.equals(UserMessage.CONFIRM_ORDER_SUCCESSFUL)) {
				router.setPagePath(PagePath.ORDER);
				router.setForward();
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "cannot confirm order:", e);
            throw new CommandException("cannot confirm order:", e);
		}
		return router;
	}

}
