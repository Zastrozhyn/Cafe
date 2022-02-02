package by.zastr.cafe.controller.command.user;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.RequestParameter;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.util.LocaleValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class ChangeLocaleCommand
 * @author A.Zastrozhyn
 *
 */
public class ChangeLocaleCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String newLocale = request.getParameter(RequestParameter.SESSION_LOCALE);
        if (LocaleValidator.isLocaleExist(newLocale)) {
            session.setAttribute(AttributeName.SESSION_LOCALE, newLocale);
        } else {
            logger.log(Level.WARN, "Wrong locale parameter: " + newLocale);
        }
        return router;
	}
}
