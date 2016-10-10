package epam.homework.task5.command.impl;

import epam.homework.task5.bean.RegistrationRequest;
import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.command.Command;
import epam.homework.task5.command.exception.CommandException;
import epam.homework.task5.dao.exception.DAOException;
import epam.homework.task5.dao.factory.DAOFactory;

public class Registration implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		Response res = new Response();
		RegistrationRequest req = new RegistrationRequest();

		if (request instanceof RegistrationRequest) {
			req = (RegistrationRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		String login = req.getLogin();
		String pass = req.getPass();

		try {
			if (DAOFactory.getInstance().getUserDAO().registration(login, pass)) {
				res.setErrorStatus(false);
				res.setResultMessage("Everythings Great!");
				return res;
			} else {
				res.setErrorStatus(true);
				res.setErrorMessage("User is not register yet. Follow the registration and try again!");
				return res;
			}
		} catch (DAOException e) {
			System.out.println(e.getMessage());
			res.setErrorStatus(true);
			res.setErrorMessage("SOMETHING'S WHERY BAD");
			return res;
		}
		

	}

}
