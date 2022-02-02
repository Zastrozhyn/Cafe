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

/**
 * class DisplayMenuCommand
 * @author A.Zastrozhyn
 *
 */
public class DisplayMenuCommand implements Command{
	private static final String DELETED_TYPE = "deleted";
	public static final int DEFAULT_BEGIN = 1;
	public static final int DEFAULT_END = 7;
	public static final int DEFAULT_CURRENT_PAGE = 1;

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String type = request.getParameter(RequestParameter.DISH_TYPE);
        DishServiceImpl dishService = DishServiceImpl.getInstance();
        try {
        	List<Dish> menu = dishService.findByType(type);
        	
        	if(type.isEmpty()) {
     			menu = dishService.findAll();
        	}
        	if(type.equals(DELETED_TYPE)) {
        		menu = dishService.findDeleted();
        	}
        	int pages = (int) 1+ menu.size()/DEFAULT_END;
 			session.setAttribute(MENU, menu);
 			session.setAttribute(PAGES, pages);
 			session.setAttribute(CURRENT_PAGE, DEFAULT_CURRENT_PAGE);

 			session.setAttribute(BEGIN_LIST, DEFAULT_BEGIN);
 			session.setAttribute(END_LIST, DEFAULT_END);
 			router.setPagePath(PagePath.MENU);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "cannot display menu:", e);
            throw new CommandException("cannot display menu:", e);
		}
        
		return router;
	}

}
