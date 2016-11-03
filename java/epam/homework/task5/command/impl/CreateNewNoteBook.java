package epam.homework.task5.command.impl;

import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.bean.entity.SQLUser;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.service.ServiceFactory;
import epam.homework.task5.service.exception.ServiceException;

public class CreateNewNoteBook implements Command {

	public Response execute(Request request) throws CommandException {

		Response response = new Response();

		int userID = SQLUser.getUserID();

		try {
			if (ServiceFactory.getInstance().getNoteBookService().createNewNoteBook(userID)) {
				response.setErrorStatus(false);
				response.setResultMessage("NoteBook created success!");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Houston We have a Problem! Can't to create a NoteBook");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}

		return response;
	}

}
