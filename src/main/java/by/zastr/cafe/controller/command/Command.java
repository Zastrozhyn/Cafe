package by.zastr.cafe.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zastr.cafe.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
	Logger logger = LogManager.getLogger();
	
    Router execute(HttpServletRequest request) throws CommandException;

}
