package by.zastr.cafe.controller.command.user;

import static by.zastr.cafe.controller.command.RequestParameter.*;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.controller.command.UserMessage;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.service.OrderService;
import by.zastr.cafe.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

/**
 * class AddCommentCommand.
 *
 * @author A.Zastrozhyn
 */
public class AddCommentCommand implements Command{

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
		router.setPagePath(PagePath.PROFILE);
		router.setRedirect();
		int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
		String comment = request.getParameter(COMMENT);
		OrderService orderService = OrderServiceImpl.getInstance();
		try {
			if(orderService.addComment(comment, orderId)) {
				request.setAttribute(AttributeName.MESSAGE, UserMessage.SUCCESSFUL);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "cannot add comment:", e);
            throw new CommandException("cannot add comment:", e);
		}
		return router;
	}

}
