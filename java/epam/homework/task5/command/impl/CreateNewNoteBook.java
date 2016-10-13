package epam.homework.task5.command.impl;

import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.bean.entity.SQLUser;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.dao.factory.DAOFactory;

public class CreateNewNoteBook implements Command {

	public Response execute(Request request) throws CommandException {

		Response response = new Response();

		int userID = SQLUser.getUserID();
		DAOFactory.getInstance().getNoteBookDAO().createNewNoteBook(userID);

		response.setErrorStatus(false);
		response.setResultMessage("NoteBook created success!");

		return response;
	}

}
