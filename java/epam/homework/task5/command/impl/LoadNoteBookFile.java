package epam.homework.task5.command.impl;


import epam.homework.task5.bean.LoadFileRequest;
import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.service.NoteBookService;
import epam.homework.task5.service.ServiceFactory;
import epam.homework.task5.service.exception.ServiceException;

public class LoadNoteBookFile implements Command {

	public Response execute(Request request) throws CommandException {

		LoadFileRequest req = new LoadFileRequest();
		Response response = new Response();

		if (request instanceof LoadFileRequest) {
			req = (LoadFileRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}
		String fileName = req.getLoadFileName();

		ServiceFactory service = ServiceFactory.getInstance();
		NoteBookService nbService = service.getNoteBookService();

		try {
			nbService.loadNoteBookFromFile(fileName);
			response.setErrorStatus(false);
			response.setResultMessage(fileName + " is load to SQL-Base success!");
		} catch (ServiceException e1) {
			response.setErrorStatus(true);
			response.setErrorMessage("Ooops.. make sure that you have wrote a file name, and try do it again");
			return response;
		}

		return response;
	}

}
