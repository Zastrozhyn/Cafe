package by.zastr.cafe.controller.command.user;

import static by.zastr.cafe.controller.command.AttributeName.*;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.RequestParameter;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.controller.command.UserMessage;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.service.impl.UserServiceImpl;
import by.zastr.cafe.util.MessageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class EditProfileCommand.
 *
 * @author A.Zastrozhyn
 */
public class EditProfileCommand implements Command{

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		Router router = new Router();
		String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
		MessageManager messageManager = MessageManager.defineLocale(locale);
		int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
		String name = request.getParameter(NAME);
		String lastName = request.getParameter(LAST_NAME);
		String email = request.getParameter(EMAIL);
		String phone = request.getParameter(PHONE);
		UserServiceImpl userService = UserServiceImpl.getInstance();
		try {
			String result = userService.edit(userId, name, lastName, phone, email, locale);
			if (result.equals(messageManager.getMessage(UserMessage.SUCCESSFUL))) {
				router.setPagePath(PagePath.PROFILE);
				session.removeAttribute(AttributeName.SESSION_USER);
				session.setAttribute(AttributeName.SESSION_USER, userService.findById(userId).get());
			}
			else {
				router.setPagePath(PagePath.EDIT_PROFILE);
			}
			request.setAttribute(MESSAGE, result);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "User cannot be registered:", e);
            throw new CommandException("User cannot be registered:", e);
		}
		return router;
	}
}
