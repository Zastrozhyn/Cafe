package by.zastr.cafe.controller.command;

import static by.zastr.cafe.controller.command.PagePath.*;

/**
 * class Router
 * @author A.Zastrozhyn
 * that are used with by controller to find out where and how
 * request and response should be processed after the controller.
 */
public class Router {
    private String pagePath = INDEX;
    private RouterType type;
    
    public enum RouterType {
        FORWARD,
        REDIRECT;
    }
    
	public Router() {
		type = RouterType.FORWARD;
		pagePath = MAIN_PAGE;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String page) {
		this.pagePath = page;
	}

	public RouterType getType() {
		return type;
	}

	public void set(RouterType type) {
		this.type = type;
	}
    
	public void setRedirect() {
        this.type = Router.RouterType.REDIRECT;
    }
	
	public void setForward() {
        this.type = Router.RouterType.FORWARD;
    }
    

}
