package epam.homework.task5.command.impl;

import epam.homework.task5.bean.AuthentificationRequest;
import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.dao.exception.DAOException;
import epam.homework.task5.dao.factory.DAOFactory;

public class Authentification implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		Response res = new Response();
		AuthentificationRequest req = new AuthentificationRequest();

		if (request instanceof AuthentificationRequest) {
			req = (AuthentificationRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		String login = req.getLogin();
		String pass = req.getPass();
		try {
			DAOFactory.getInstance().getUserDAO().logination(login, pass);
			res.setErrorStatus(false);
			res.setResultMessage("Everythings Great!");
			return res;
		} catch (DAOException e) {
			System.out.println(e.getMessage());
			res.setErrorStatus(true);
			res.setErrorMessage("SOMETHING'S WHERY BAD");
			return res;
		}
	}

}
