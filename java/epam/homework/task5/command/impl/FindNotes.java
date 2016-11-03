package epam.homework.task5.command.impl;

import java.util.ArrayList;
import java.util.List;

import epam.homework.task5.bean.FindNotesRequest;
import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
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
		int userID = req.getUserID();
		List<String> findNoteByContent = new ArrayList<>();

		try {
			findNoteByContent = ServiceFactory.getInstance().getNoteBookService().findNoteByContent(keyWord, userID);
			if (findNoteByContent != null) {
				response.setFindNoteByContent(findNoteByContent);
				response.setErrorStatus(false);
				response.setResultMessage("\nSearch completed success\n");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Houston We have a Problem! Save not completed!!!");
			}

		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}

		return response;
	}

}
