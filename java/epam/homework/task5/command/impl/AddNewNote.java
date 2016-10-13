package epam.homework.task5.command.impl;


import epam.homework.task5.bean.AddNoteRequest;
import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.bean.entity.Note;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.dao.factory.DAOFactory;

public class AddNewNote implements Command {

	public Response execute(Request request) throws CommandException {

		AddNoteRequest req = new AddNoteRequest();
		Response response = new Response();

		if (request instanceof AddNoteRequest) {
			req = (AddNoteRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}
		
		String note = req.getNote();
		int userID = req.getUserID();

		if (note == null || "".equals(note)) {
			response.setErrorStatus(true);
			response.setErrorMessage("Don't try to add an empty note");
			return response;
		}

		
		DAOFactory.getInstance().getNoteBookDAO().addNote(new Note(note), userID);
		response.setErrorStatus(false);
		response.setResultMessage("You already add a note!");


		return response;
	}

}
