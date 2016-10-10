package epam.homework.task5.bean;

import epam.homework.task5.bean.entity.SQLUser;

public class Request {
	private String commandName;
	private int userID;

	public int getUserID() {
		userID = SQLUser.getUserID();
		return userID;
	}


	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	
	

}
