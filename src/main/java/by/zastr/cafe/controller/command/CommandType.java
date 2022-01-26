package by.zastr.cafe.controller.command;

import java.util.Optional;

import by.zastr.cafe.controller.command.admin.AddDishCommand;
import by.zastr.cafe.controller.command.admin.AddMoneyCommand;
import by.zastr.cafe.controller.command.admin.BlockUserCommand;
import by.zastr.cafe.controller.command.admin.ChangeRoleCommand;
import by.zastr.cafe.controller.command.admin.DeleteOrderCommand;
import by.zastr.cafe.controller.command.admin.EditDishCommand;
import by.zastr.cafe.controller.command.admin.FindOrderCommand;
import by.zastr.cafe.controller.command.admin.FindUserCommand;
import by.zastr.cafe.controller.command.admin.GoToEditDishCommand;
import by.zastr.cafe.controller.command.admin.PaidOrderCommand;
import by.zastr.cafe.controller.command.admin.ViewOrderCommand;
import by.zastr.cafe.controller.command.admin.ViewTodayOrderCommand;
import by.zastr.cafe.controller.command.admin.ViewUnpaidOrderCommand;
import by.zastr.cafe.controller.command.admin.ViewUserCommand;
import by.zastr.cafe.controller.command.menu.DisplayMenuCommand;
import by.zastr.cafe.controller.command.user.AddCommentCommand;
import by.zastr.cafe.controller.command.user.AddToOrderCommand;
import by.zastr.cafe.controller.command.user.ChangePasswordCommand;
import by.zastr.cafe.controller.command.user.ConfirmOrderCommand;
import by.zastr.cafe.controller.command.user.DeleteFromOrderCommand;
import by.zastr.cafe.controller.command.user.DeleteUserCommand;
import by.zastr.cafe.controller.command.user.EditProfileCommand;
import by.zastr.cafe.controller.command.user.FindOrderHistoryCommand;
import by.zastr.cafe.controller.command.user.LoginCommand;
import by.zastr.cafe.controller.command.user.LogoutCommand;
import by.zastr.cafe.controller.command.user.RegistrationCommand;
import by.zastr.cafe.model.entity.User;


public enum CommandType {	
	
    REGISTRATION(new RegistrationCommand()),
    LOGOUT(new LogoutCommand()),
	LOGIN(new LoginCommand()),
	DELETE_ORDER(new DeleteOrderCommand()),	
	ADD_TO_ORDER(new AddToOrderCommand()),
	CONFIRM_ORDER(new ConfirmOrderCommand()),	
	DELETE_FROM_ORDER(new DeleteFromOrderCommand()),
	EDIT_PROFILE(new EditProfileCommand()),	
	DELETE_USER(new DeleteUserCommand()),	
	CHANGE_PASSWORD(new ChangePasswordCommand()),	
	FIND_ORDER_HISTORY(new FindOrderHistoryCommand()),
	ADD_COMMENT(new AddCommentCommand()),
	
	ADD_DISH(new AddDishCommand()),
	ADD_MONEY(new AddMoneyCommand()),
	VIEW_USER(new ViewUserCommand()),
	PAID_ORDER(new PaidOrderCommand()),
	VIEW_ORDER(new ViewOrderCommand()),
	UNPAID_ORDER(new ViewUnpaidOrderCommand()),
	FIND_ORDER(new FindOrderCommand()),
	TODAY_ORDER(new ViewTodayOrderCommand()),
	GO_TO_EDIT_DISH(new GoToEditDishCommand()), 
	EDIT_DISH(new EditDishCommand()),
	CHANGE_ROLE(new ChangeRoleCommand()),
	BLOCK_USER(new BlockUserCommand()),
	FIND_USER(new FindUserCommand()),
	
	MENU(new DisplayMenuCommand());
	
    private final Command command;
    
    CommandType(Command command) {
        this.command = command;
    }

    CommandType(Command command, User.Role role) {
        this.command = command;
    }
    
    public Command getCommand() {
        return this.command;
    }
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
