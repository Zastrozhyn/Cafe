package by.zastr.cafe.controller.command;

/**
 * @author A.Zastrozhyn
 *
 * The names of session and request attributes.
 */
public class AttributeName {
	public static final String SESSION_USER = "user";
	public static final String REGISTRATION_RESULT = "registration_result";
	public static final String MENU = "menu";
	public static final String ADMIN = "admin";
	public static final String CLIENT = "client";
	public static final String USER_LIST = "users";
	public static final String MESSAGE = "message";
	public static final String ORDER_LIST = "orders";
	public static final String ORDER = "order";
	public static final String ORDER_HISTORY_LIST = "history_list_orders";
	public static final String ORDER_HISTORY = "order_history";
	public static final String DISH = "dish";
	public static final String TOTAL_COST = "total_cost";
    public static final String SESSION_LOCALE = "Locale";
    public static final String LIST_DISH = "list_dish";
    public static final String BEGIN_LIST = "begin";
    public static final String END_LIST = "end";
    public static final String PAGES = "pages";
    public static final String CURRENT_PAGE = "current_page";
	
    private AttributeName() {
    }

}
