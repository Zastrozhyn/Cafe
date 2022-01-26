package by.zastr.cafe.controller.command.menu;

import java.util.List;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.RequestParameter;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.Dish;
import by.zastr.cafe.model.service.impl.DishServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import static by.zastr.cafe.controller.command.AttributeName.*;

public class DisplayMenuCommand implements Command{
	private static final DishServiceImpl dishService = DishServiceImpl.getInstance();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String type = request.getParameter(RequestParameter.DISH_TYPE);
        try {
        	List<Dish> menu;
        	if(type.isEmpty()) {
     			menu = dishService.findAll();
        	}
        	else {
        		menu = dishService.findByType(type);
        	}
 			session.setAttribute(MENU, menu);
 			router.setPagePath(PagePath.MENU);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "cannot display menu:", e);
            throw new CommandException("cannot display menu:", e);
		}
        
		return router;
	}

}
