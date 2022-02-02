package by.zastr.cafe.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zastr.cafe.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * interface Command.
 *
 * @author A.Zastrozhyn
 */
public interface Command {
	
	/** The logger. */
	Logger logger = LogManager.getLogger();
	
	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return Router
	 * @throws CommandException the command exception
	 */
    Router execute(HttpServletRequest request) throws CommandException;

}
