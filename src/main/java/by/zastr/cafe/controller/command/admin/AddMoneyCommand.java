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
import by.zastr.cafe.model.service.impl.AccountServiceImpl;
import by.zastr.cafe.model.service.impl.UserServiceImpl;
import by.zastr.cafe.util.impl.InputValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

public class AddMoneyCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = new Router();
		router.setPagePath(PagePath.USERS);
		int userId = Integer.parseInt(request.getParameter(USER_ID));
		String moneyString = request.getParameter(RequestParameter.MONEY);
		InputValidatorImpl validator = InputValidatorImpl.getInstance();
		if(!validator.isCorrectPrice(moneyString)) {
			return router;
		}
		long money = Long.parseLong(moneyString);
		BigDecimal addMoney = BigDecimal.valueOf(money);
		
		UserServiceImpl userService = UserServiceImpl.getInstance();
		AccountServiceImpl accountService = AccountServiceImpl.getInstance();
		try {
			User user = userService.findById(userId).get();
			Account account = user.getAccount();
			account.setBalance(account.getBalance().add(addMoney));
			accountService.update(account);
			request.setAttribute(AttributeName.MESSAGE, UserMessage.SUCCESSFUL);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "cannot add money:", e);
            throw new CommandException("cannot add money:", e);
		}	
		return router;
	}

}
