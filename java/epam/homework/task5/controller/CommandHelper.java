package epam.homework.task5.controller;

import java.util.HashMap;
import java.util.Map;

import epam.homework.task5.command.Command;
import epam.homework.task5.command.impl.AddNewNote;
import epam.homework.task5.command.impl.Authentification;
import epam.homework.task5.command.impl.CreateNewNoteBook;
import epam.homework.task5.command.impl.FindNotes;
import epam.homework.task5.command.impl.FindNotesByDate;
import epam.homework.task5.command.impl.LoadNoteBookFile;
import epam.homework.task5.command.impl.Registration;
import epam.homework.task5.command.impl.SaveAddsToNoteBook;
import epam.homework.task5.command.impl.ShowNotesToScreen;

public class CommandHelper {

	private Map<String, Command> commands = new HashMap<String, Command>();

	public CommandHelper() {

		commands.put("ADD_NEW_NOTE", new AddNewNote());
		commands.put("FIND_NOTES", new FindNotes());
		commands.put("CREATE_NEW_FILE", new CreateNewNoteBook());
		commands.put("LOAD_FILE", new LoadNoteBookFile());
		commands.put("SAVE", new SaveAddsToNoteBook());
		commands.put("FIND_NOTES_BY_DATE", new FindNotesByDate());
		commands.put("SHOW_NOTES_TO_SCREEN", new ShowNotesToScreen());
		commands.put("REGISTRATION", new Registration());
		commands.put("AUTHENTIFICATION", new Authentification());

	}

	public Command getCommand(String commandName) {
		Command command;

		command = commands.get(commandName);

		return command;

	}

}
