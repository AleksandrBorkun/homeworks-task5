package epam.homework.task5.command;

import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.command.exception.CommandException;

public interface Command {
	
	Response execute(Request request) throws CommandException;
	
	
}
