package by.zastr.cafe.controller.command.admin;

import static by.zastr.cafe.controller.command.RequestParameter.*;

import java.math.BigDecimal;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.RequestParameter;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.controller.command.UserMessage;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.Account;
import by.zastr.cafe.model.entity.User;
import by.zastr.cafe.model.service.AccountService;
import by.zastr.cafe.model.service.impl.AccountServiceImpl;
import by.zastr.cafe.model.service.impl.UserServiceImpl;
import by.zastr.cafe.util.MessageManager;
import by.zastr.cafe.util.impl.InputValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class AddMoneyCommand.
 *
 * @author A.Zastrozhyn
 */
public class AddMoneyCommand implements Command{

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
		router.setPagePath(PagePath.ADMIN_USERS);
		HttpSession session = request.getSession();
		String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
		MessageManager messageManager = MessageManager.defineLocale(locale);
		int userId = Integer.parseInt(request.getParameter(USER_ID));
		String moneyString = request.getParameter(RequestParameter.MONEY);
		InputValidatorImpl validator = InputValidatorImpl.getInstance();
		if(!validator.isCorrectPrice(moneyString)) {
			request.setAttribute(AttributeName.MESSAGE, messageManager.getMessage(UserMessage.SUCCESSFUL));
			return router;
		}
		long money = Long.parseLong(moneyString);
		BigDecimal addMoney = BigDecimal.valueOf(money);
		UserServiceImpl userService = UserServiceImpl.getInstance();
		AccountService accountService = AccountServiceImpl.getInstance();
		try {
			User user = userService.findById(userId).get();
			Account account = user.getAccount();
			account.setBalance(account.getBalance().add(addMoney));
			accountService.update(account);
			request.setAttribute(AttributeName.MESSAGE, messageManager.getMessage(UserMessage.SUCCESSFUL));
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "cannot add money:", e);
            throw new CommandException("cannot add money:", e);
		}	
		return router;
	}

}
