package epam.homework.task5.command.impl;

import java.util.List;

import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.bean.entity.Note;
import epam.homework.task5.bean.entity.SQLUser;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.dao.factory.DAOFactory;
import epam.homework.task5.source.NoteBookProvider;

public class ShowNotesToScreen implements Command {

	public Response execute(Request request) throws CommandException {

		Response response = new Response();

		DAOFactory.getInstance().getNoteBookDAO().showAllNotes(SQLUser.getUserID());
		
	/*	List<Note> notes = NoteBookProvider.getInstance().getNoteBook().getNotes();
		if (notes.isEmpty() != true) {
		
			for (Note added : notes) {
				System.out.println(added.getNote());
			}
		} else {
			response.setErrorStatus(true);
			response.setErrorMessage("You didn\'t wrote any notes yet");
		}
*/
		response.setErrorStatus(false);
		response.setResultMessage("The notes is shown great!");
		return response;
	}

}
