package epam.homework.task5.command.impl;

import epam.homework.task5.bean.CreateNewNoteBookRequest;
import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.service.NoteBookService;
import epam.homework.task5.service.ServiceFactory;
import epam.homework.task5.service.exception.ServiceException;

public class CreateNewNoteBook implements Command {

	public Response execute(Request request) throws CommandException {

		Response response = new Response();
		CreateNewNoteBookRequest req;
		
		if (request instanceof CreateNewNoteBookRequest) {
			req = (CreateNewNoteBookRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		ServiceFactory service = ServiceFactory.getInstance();
		NoteBookService nbService = service.getNoteBookService();
		
		nbService.createNewNoteBook(req.getUserID());
	
		//nbService.addNote("NoteBook is created", req.getUserID());
	
		response.setErrorStatus(false);
		response.setResultMessage("NoteBook created success!");
		
		return response;
	}

}
