package by.zastr.cafe.controller.command;

import static by.zastr.cafe.controller.command.PagePath.*;

/**
 * class Router.
 *
 * @author A.Zastrozhyn
 * that are used with by controller to find out where and how
 * request and response should be processed after the controller.
 */
public class Router {
    private String pagePath = INDEX;
    private RouterType type;
    
    /**
     * The Enum RouterType.
     */
    public enum RouterType {
        
        /** The forward. */
        FORWARD,
        
        /** The redirect. */
        REDIRECT;
    }
    
	/**
	 * Instantiates a new router.
	 */
	public Router() {
		type = RouterType.FORWARD;
		pagePath = MAIN_PAGE;
	}

	/**
	 * Gets the page path.
	 *
	 * @return the page path
	 */
	public String getPagePath() {
		return pagePath;
	}

	/**
	 * Sets the page path.
	 *
	 * @param page the new page path
	 */
	public void setPagePath(String page) {
		this.pagePath = page;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public RouterType getType() {
		return type;
	}
    
	/**
	 * Sets the redirect.
	 */
	public void setRedirect() {
        this.type = Router.RouterType.REDIRECT;
    }
	
	/**
	 * Sets the forward.
	 */
	public void setForward() {
        this.type = Router.RouterType.FORWARD;
    }
    

}
