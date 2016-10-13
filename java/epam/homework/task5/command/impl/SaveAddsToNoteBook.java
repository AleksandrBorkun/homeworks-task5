package epam.homework.task5.command.impl;

import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.bean.SaveNotesRequest;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.dao.factory.DAOFactory;

public class SaveAddsToNoteBook implements Command {

	public Response execute(Request request) throws CommandException {

		SaveNotesRequest req = new SaveNotesRequest();
		Response response = new Response();
		if (request instanceof SaveNotesRequest) {
			req = (SaveNotesRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		int userId = req.getUserID();
		
		DAOFactory.getInstance().getNoteBookDAO().saveNoteBook(userId);

		
		response.setErrorStatus(false);
		response.setResultMessage("Notes is saved to file: 'NoteBookUser"+ userId+"'.txt");
		
		return response;
	}

}
