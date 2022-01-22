package by.zastr.cafe.controller.command;

import java.util.Optional;

import by.zastr.cafe.controller.command.admin.AddDishCommand;
import by.zastr.cafe.controller.command.admin.EditDishCommand;
import by.zastr.cafe.controller.command.admin.GoToEditDishCommand;
import by.zastr.cafe.controller.command.admin.ViewUserCommand;
import by.zastr.cafe.controller.command.menu.DisplayMenuCommand;
import by.zastr.cafe.controller.command.user.AddToOrderCommand;
import by.zastr.cafe.controller.command.user.ConfirmOrderCommand;
import by.zastr.cafe.controller.command.user.DeleteFromOrderCommand;
import by.zastr.cafe.controller.command.user.LoginCommand;
import by.zastr.cafe.controller.command.user.LogoutCommand;
import by.zastr.cafe.controller.command.user.RegistrationCommand;
import by.zastr.cafe.model.entity.User;


public enum CommandType {	
    REGISTRATION(new RegistrationCommand()),
    LOGOUT(new LogoutCommand()),
	LOGIN(new LoginCommand()),
	VIEWUSER(new ViewUserCommand()),
	ADD_DISH(new AddDishCommand()),
	ADD_TO_ORDER(new AddToOrderCommand()),
	GO_TO_EDIT_DISH(new GoToEditDishCommand()), 
	EDIT_DISH(new EditDishCommand()),
	CONFIRM_ORDER(new ConfirmOrderCommand()),
	DELETE_FROM_ORDER(new DeleteFromOrderCommand()), 
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
