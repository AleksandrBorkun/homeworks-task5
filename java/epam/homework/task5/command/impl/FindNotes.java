package epam.homework.task5.command.impl;

import epam.homework.task5.bean.FindNotesRequest;
import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.bean.entity.Note;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.dao.factory.DAOFactory;
import epam.homework.task5.service.NoteBookService;
import epam.homework.task5.service.ServiceFactory;
import epam.homework.task5.service.exception.ServiceException;

public class FindNotes implements Command {

	public Response execute(Request request) throws CommandException {

		Response response = new Response();
		FindNotesRequest req = new FindNotesRequest();
		if (request instanceof FindNotesRequest) {
			req = (FindNotesRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		String keyWord = req.getKeyWords();
		DAOFactory.getInstance().getNoteBookDAO().findNoteByContent(keyWord, req.getUserID());

		response.setErrorStatus(false);
		response.setResultMessage("\nSearch completed success\n");

		return response;
	}

}
