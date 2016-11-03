package epam.homework.task5.command.impl;

import java.util.ArrayList;
import java.util.List;

import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.bean.entity.SQLUser;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.dao.factory.DAOFactory;
import epam.homework.task5.service.NoteBookService;
import epam.homework.task5.service.ServiceFactory;
import epam.homework.task5.service.exception.ServiceException;

public class ShowNotesToScreen implements Command {

	public Response execute(Request request) throws CommandException {

		Response response = new Response();

		List<String> showNotesList = new ArrayList<>();
		int userID = request.getUserID();

		try {
			
			showNotesList = ServiceFactory.getInstance().getNoteBookService().showAllNotes(userID);
			
			response.setShowNotesList(showNotesList);
			response.setErrorStatus(false);
			response.setResultMessage("The notes is shown great!");
			
			return response;
	
		} catch (ServiceException e) {
			response.setErrorMessage(e.getMessage());
			response.setErrorStatus(true);
			return response;
		}

	}

}
