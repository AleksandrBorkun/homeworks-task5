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

		Response response = new Response();
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
				response.setErrorStatus(false);
				response.setResultMessage("Everythings Great!");
				return response;
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("User is not register yet. Follow the registration and try again!");
				return response;
			}
		} catch (DAOException e) {
			System.out.println(e.getMessage());
			response.setErrorStatus(true);
			response.setErrorMessage("SOMETHING'S WHERY BAD");
			return response;
		}
		

	}

}
