package epam.homework.task5.command.impl;

import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.bean.SaveNotesRequest;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.service.ServiceFactory;
import epam.homework.task5.service.exception.ServiceException;

public class SaveAddsToNoteBook implements Command {

	public Response execute(Request request) throws CommandException {

		SaveNotesRequest req = new SaveNotesRequest();

		if (request instanceof SaveNotesRequest) {
			req = (SaveNotesRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		Response response = new Response();
		int userID = req.getUserID();

		try {
			if (ServiceFactory.getInstance().getNoteBookService().saveNoteBook(userID)) {
				response.setErrorStatus(false);
				response.setResultMessage("Notes is saved to file: 'NoteBookUser" + userID + "'.txt");
				return response;
			} else {
				response.setErrorMessage("Oooops Something was wrong!");
				response.setErrorStatus(true);
				return response;
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}

	}

}
