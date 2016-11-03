package epam.homework.task5.command.impl;

import epam.homework.task5.bean.AddNoteRequest;
import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.bean.entity.Note;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.service.ServiceFactory;
import epam.homework.task5.service.exception.ServiceException;

public class AddNewNote implements Command {

	public Response execute(Request request) throws CommandException {

		AddNoteRequest req = new AddNoteRequest();
		Response response = new Response();

		if (request instanceof AddNoteRequest) {
			req = (AddNoteRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		Note note = req.getNote();
		int userID = req.getUserID();

		// NoteBookProvider.getInstance().getNoteBook().add(note);
		if (note == null || "".equals(note)) {
			response.setErrorStatus(true);
			response.setErrorMessage("Don't try to add an empty note");
			return response;
		}

		try {
			if (ServiceFactory.getInstance().getNoteBookService().addNote(note, userID)) {
				response.setErrorStatus(false);
				response.setResultMessage("You already add a note!");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage(
						"We can't save your note now. Our command working to fix it. Please try again with another note");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}

		return response;
	}

}
