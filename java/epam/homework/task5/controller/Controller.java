package epam.homework.task5.controller;

import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;

public class Controller {
	
	private CommandHelper helper = new CommandHelper();
	
	
	public Controller(){}
	
	public Response doRequest(Request request){
		
		String commandName = request.getCommandName();
		
		Command command = helper.getCommand(commandName);
		
		Response response;
		try {
			response = command.execute(request);
		} catch (CommandException e) {
			// logging
			response = new Response();
			response.setErrorStatus(true);
			response.setErrorMessage("ERROR!");
		}
		
		return response;
		
	}

}
