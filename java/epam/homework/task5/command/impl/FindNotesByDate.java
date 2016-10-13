package epam.homework.task5.command.impl;

import epam.homework.task5.bean.FindNotesByDateRequest;
import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.service.NoteBookService;
import epam.homework.task5.service.ServiceFactory;
import epam.homework.task5.service.exception.ServiceException;

public class FindNotesByDate implements Command {

	public Response execute(Request request) throws CommandException {

		FindNotesByDateRequest req = null;
		Response response = new Response();

		if (request instanceof FindNotesByDateRequest) {
			req = (FindNotesByDateRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		String dateKey = req.getSearchDate();
		ServiceFactory service = ServiceFactory.getInstance();
		NoteBookService nbService = service.getNoteBookService();

	
			try {
				nbService.findNotesByDate(dateKey, req.getUserID());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		response.setErrorStatus(false);
		response.setResultMessage("\nSearch by date complete success\n");
		return response;
	}

}
