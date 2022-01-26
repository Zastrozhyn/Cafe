package by.zastr.cafe.controller.command.admin;

import static by.zastr.cafe.controller.command.RequestParameter.USER_ID;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.controller.command.UserMessage;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.User;
import by.zastr.cafe.model.service.impl.AccountServiceImpl;
import by.zastr.cafe.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class BlockUserCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = new Router();
		router.setPagePath(PagePath.USERS);
		int userId = Integer.parseInt(request.getParameter(USER_ID));
		UserServiceImpl userService = UserServiceImpl.getInstance();
		AccountServiceImpl accountService = AccountServiceImpl.getInstance();
		try {
			User user = userService.findById(userId).get();
			if (user.getAccount().isActive()){
				user.getAccount().setActive(false);
				accountService.update(user.getAccount());
				return router;
			}
			if (!user.getAccount().isActive()){
				user.getAccount().setActive(true);
				accountService.update(user.getAccount());
				return router;
			}
			request.setAttribute(AttributeName.MESSAGE, UserMessage.SUCCESSFUL);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "User cannot be blocked:", e);
            throw new CommandException("User cannot be blocked:", e);
		}
		
		return router;
	}

}
