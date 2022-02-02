package by.zastr.cafe.controller.command;

import java.util.Optional;

import by.zastr.cafe.controller.command.admin.AddDishCommand;
import by.zastr.cafe.controller.command.admin.AddMoneyCommand;
import by.zastr.cafe.controller.command.admin.BlockUserCommand;
import by.zastr.cafe.controller.command.admin.ChangeRoleCommand;
import by.zastr.cafe.controller.command.admin.DeleteOrderCommand;
import by.zastr.cafe.controller.command.admin.DeleteRestoreDishCommand;
import by.zastr.cafe.controller.command.admin.EditDishCommand;
import by.zastr.cafe.controller.command.admin.FindOrderCommand;
import by.zastr.cafe.controller.command.admin.FindUserCommand;
import by.zastr.cafe.controller.command.admin.GoToEditDishCommand;
import by.zastr.cafe.controller.command.admin.PaidOrderCommand;
import by.zastr.cafe.controller.command.admin.ViewDeletedUser;
import by.zastr.cafe.controller.command.admin.ViewOrderCommand;
import by.zastr.cafe.controller.command.admin.ViewTodayOrderCommand;
import by.zastr.cafe.controller.command.admin.ViewUnpaidOrderCommand;
import by.zastr.cafe.controller.command.admin.ViewUserCommand;
import by.zastr.cafe.controller.command.menu.DisplayMenuCommand;
import by.zastr.cafe.controller.command.menu.PaginationCommand;
import by.zastr.cafe.controller.command.user.AddCommentCommand;
import by.zastr.cafe.controller.command.user.AddToOrderCommand;
import by.zastr.cafe.controller.command.user.ChangeLocaleCommand;
import by.zastr.cafe.controller.command.user.ChangePasswordCommand;
import by.zastr.cafe.controller.command.user.ConfirmOrderCommand;
import by.zastr.cafe.controller.command.user.DeleteFromOrderCommand;
import by.zastr.cafe.controller.command.user.DeleteUserCommand;
import by.zastr.cafe.controller.command.user.EditProfileCommand;
import by.zastr.cafe.controller.command.user.FindOrderHistoryCommand;
import by.zastr.cafe.controller.command.user.LoginCommand;
import by.zastr.cafe.controller.command.user.LogoutCommand;
import by.zastr.cafe.controller.command.user.RegistrationCommand;
import by.zastr.cafe.controller.command.user.RepeatOrderCommand;
import by.zastr.cafe.controller.command.user.ViewDishesCommand;
import by.zastr.cafe.model.entity.User;

/**
 * The enum Command type.
 */
public enum CommandType {	
	
    /** The registration. */
    REGISTRATION(new RegistrationCommand()),
    
    /** The logout. */
    LOGOUT(new LogoutCommand()),
	
	/** The login. */
	LOGIN(new LoginCommand()),
	
	/** The delete order. */
	DELETE_ORDER(new DeleteOrderCommand()),	
	
	/** The add to order. */
	ADD_TO_ORDER(new AddToOrderCommand()),
	
	/** The confirm order. */
	CONFIRM_ORDER(new ConfirmOrderCommand()),	
	
	/** The delete from order. */
	DELETE_FROM_ORDER(new DeleteFromOrderCommand()),
	
	/** The edit profile. */
	EDIT_PROFILE(new EditProfileCommand()),	
	
	/** The delete user. */
	DELETE_USER(new DeleteUserCommand()),	
	
	/** The change password. */
	CHANGE_PASSWORD(new ChangePasswordCommand()),	
	
	/** The find order history. */
	FIND_ORDER_HISTORY(new FindOrderHistoryCommand()),
	
	/** The add comment. */
	ADD_COMMENT(new AddCommentCommand()),
	
	/** The repeat order. */
	REPEAT_ORDER(new RepeatOrderCommand()),
	
	/** The add dish. */
	ADD_DISH(new AddDishCommand()),
	
	/** The add money. */
	ADD_MONEY(new AddMoneyCommand()),
	
	/** The view user. */
	VIEW_USER(new ViewUserCommand()),
	
	/** The paid order. */
	PAID_ORDER(new PaidOrderCommand()),
	
	/** The view order. */
	VIEW_ORDER(new ViewOrderCommand()),
	
	/** The view dishes. */
	VIEW_DISHES(new ViewDishesCommand()),
	
	/** The unpaid order. */
	UNPAID_ORDER(new ViewUnpaidOrderCommand()),
	
	/** The find order. */
	FIND_ORDER(new FindOrderCommand()),
	
	/** The today order. */
	TODAY_ORDER(new ViewTodayOrderCommand()),
	
	/** The go to edit dish. */
	GO_TO_EDIT_DISH(new GoToEditDishCommand()), 
	
	/** The edit dish. */
	EDIT_DISH(new EditDishCommand()),
	
	/** The change role. */
	CHANGE_ROLE(new ChangeRoleCommand()),
	
	/** The block user. */
	BLOCK_USER(new BlockUserCommand()),
	
	/** The find user. */
	FIND_USER(new FindUserCommand()),
	
	/** The deleted user. */
	DELETED_USER(new ViewDeletedUser()),
	
	/** The delete dish. */
	DELETE_DISH(new DeleteRestoreDishCommand()),
	
	/** The menu. */
	MENU(new DisplayMenuCommand()),
	
	/** The pagination. */
	PAGINATION(new PaginationCommand()),
	
	/** The change locale. */
	CHANGE_LOCALE(new ChangeLocaleCommand());
	
    private final Command command;
    
    /**
     * Instantiates a new command type.
     *
     * @param command the command
     */
    CommandType(Command command) {
        this.command = command;
    }

    /**
     * Instantiates a new command type.
     *
     * @param command the command
     * @param role the role
     */
    CommandType(Command command, User.Role role) {
        this.command = command;
    }
    
    /**
     * Gets the command.
     *
     * @return the command
     */
    public Command getCommand() {
        return this.command;
    }
    
    /**
     * Define command.
     *
     * @param commandName the command name
     * @return the command
     */
    public static Command defineCommand(String commandName) {
    	Command command = new DefaultCommand();
        Optional<String> action = Optional.ofNullable(commandName);
        if (action.isPresent()) {
            CommandType currentType = CommandType.valueOf(action.get());
            command = currentType.getCommand();
        }
        return command;
    }
}
