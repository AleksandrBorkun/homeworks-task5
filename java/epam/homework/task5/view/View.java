package epam.homework.task5.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import epam.homework.task5.bean.AddNoteRequest;
import epam.homework.task5.bean.AuthentificationRequest;
import epam.homework.task5.bean.CreateNewNoteBookRequest;
import epam.homework.task5.bean.CurrentFileRequest;
import epam.homework.task5.bean.FindNotesByDateRequest;
import epam.homework.task5.bean.FindNotesRequest;
import epam.homework.task5.bean.LoadFileRequest;
import epam.homework.task5.bean.RegistrationRequest;
import epam.homework.task5.bean.Request;
import epam.homework.task5.bean.Response;
import epam.homework.task5.bean.SaveNotesRequest;
import epam.homework.task5.bean.entity.SQLUser;
import epam.homework.task5.controller.Controller;

public class View {

	private static boolean exit = true;
	static Scanner in = new Scanner(System.in);
	private static String help = "Take a list of command:\n0 - EXIT\n\'help\' - HELP\n1 - CREATE NEW NOTEBOOK\n2 - ADD NEW NOTE\n3 - LOAD FILE\n4 - FIND NOTES BY CONTENT\n5 - SAVE\n6 - FIND NOTES BY DATE\n7 - SHOW NOTES TO THE SREEN\n8 - Registration New User\n9 - Authentification";

	public static void main(String[] args) {
		Controller controller = new Controller();
		CreateNewNoteBookRequest createNoteBook = new CreateNewNoteBookRequest();
		LoadFileRequest loadFile = new LoadFileRequest();

		System.out.println("WELCOME TO NOTEBOOK SYSTEM! MAKE YOUR COMMAND OR PRINT \'help\' TO CALL HELP AND FIND IT");
		while (exit) {
			System.out.println("Enter the command");
			String choise = in.nextLine();

			// CLOSE PROGRAM
			if (choise.equals("0")) {
				System.out.println("SYSTEM IS CLOSING...\nBEST REGARDS!");
				break;
			}
			switch (choise) {

			// CALL HELP
			case "help":
				System.out.println(help);
				break;

			// CREATE NEW NOTEBOOK
			case "1":
				if (SQLUser.getUserID() == 0) {
					System.out.println("Authentification ERROR! Logon and try AGAIN!");
					break;
				}
				System.out.println("Create new NOTEBOOK.\nEnter the name of your file.");
				createNoteBook.setCommandName("CREATE_NEW_FILE");
				Response createNoteBookResponse = controller.doRequest(createNoteBook);
				if (createNoteBookResponse.isErrorStatus() == true) {
					System.out.println(createNoteBookResponse.getErrorMessage());
				} else {
					System.out.println(createNoteBookResponse.getResultMessage());
				}
				break;
			// ADD NOTE
			case "2":
				if (SQLUser.getUserID() == 0) {
					System.out.println("Authentification ERROR! Logon and try AGAIN!");
					break;
				}
				AddNoteRequest addNoteRequest = new AddNoteRequest();
				addNoteRequest.setCommandName("ADD_NEW_NOTE");
				System.out.println("Write you note here: ");
				String note = in.nextLine();
				addNoteRequest.setNote(note);

				Response addNoteResponse = controller.doRequest(addNoteRequest);
				if (addNoteResponse.isErrorStatus() == true) {
					System.out.println(addNoteResponse.getErrorMessage());
				} else {
					System.out.println(addNoteResponse.getResultMessage());
				}
				break;

			// LOAD FILE
			case "3":
				if (SQLUser.getUserID() == 0) {
					System.out.println("Authentification ERROR! Logon and try AGAIN!");
					break;
				}
				System.out.println("Please wrire the name of file would you like to load, and go on to work with it: ");
				loadFile.setCommandName("LOAD_FILE");
				String loadFileName = in.nextLine() + ".txt";
				loadFile.setLoadFileName(loadFileName);
				Response loadResponse = controller.doRequest(loadFile);
				if (loadResponse.isErrorStatus() == true) {
					System.out.println(loadResponse.getErrorMessage());
				} else {
					System.out.println(loadResponse.getResultMessage());
					CurrentFileRequest.setCurrentFileName(loadFileName);
				}
				break;

			// FIND NOTES BY CONTENT
			case "4":
				if (SQLUser.getUserID() == 0) {
					System.out.println("Authentification ERROR! Logon and try AGAIN!");
					break;
				}
				List<String> findNoteByContent = new ArrayList<>();
				FindNotesRequest find = new FindNotesRequest();
				find.setCommandName("FIND_NOTES");
				System.out.println("To Find Notes By Content, pls write your key word for search: ");
				String keyWords = in.nextLine();
				find.setKeyWords(keyWords);
				Response findContent = controller.doRequest(find);
				if (findContent.isErrorStatus() == true) {
					System.out.println(findContent.getErrorMessage());
				} else {

					findNoteByContent = findContent.getFindNoteByContent();
					for (String line : findNoteByContent) {
						System.out.println(line);
					}

					System.out.println(findContent.getResultMessage());
				}
				break;

			// SAVE ALL TO NOTEBOOK
			case "5":
				if (SQLUser.getUserID() == 0) {
					System.out.println("Authentification ERROR! Logon and try AGAIN!");
					break;
				}
				SaveNotesRequest save = new SaveNotesRequest();
				save.setCommandName("SAVE");
				Response saveResponse = controller.doRequest(save);
				if (saveResponse.isErrorStatus() == true) {
					System.out.println(saveResponse.getErrorMessage());
				} else {
					System.out.println(saveResponse.getResultMessage());
				}
				break;

			// FIND NOTES BY DATE
			case "6":
				if (SQLUser.getUserID() == 0) {
					System.out.println("Authentification ERROR! Logon and try AGAIN!");
					break;
				}
				List<String> findNotesByDateLsit = new ArrayList<>();
				FindNotesByDateRequest findByDateRequest = new FindNotesByDateRequest();
				findByDateRequest.setCommandName("FIND_NOTES_BY_DATE");
				System.out
						.println("For searching by date, pls write it in a format yyyy-MM-dd (for example 2000-01-30)");
				String searchDate = in.nextLine();
				findByDateRequest.setSearchDate(searchDate);
				Response findByDate = controller.doRequest(findByDateRequest);
				if (findByDate.isErrorStatus() == true) {
					System.out.println(findByDate.getErrorMessage());

				} else {
					findNotesByDateLsit = findByDate.getFindNotesByDateLsit();
					for (String line : findNotesByDateLsit) {
						System.out.println(line);
					}
					System.out.println(findByDate.getResultMessage());
				}
				break;
			// SHOW NOTES TO THE SREEN
			case "7":
				if (SQLUser.getUserID() == 0) {
					System.out.println("Authentification ERROR! Logon and try AGAIN!");
					break;
				}
				List<String> showNotesList = new ArrayList<>();
				Request request = new Request();
				request.setCommandName("SHOW_NOTES_TO_SCREEN");
				Response showResponse = controller.doRequest(request);
				if (showResponse.isErrorStatus() == true) {
					System.out.println(showResponse.getErrorMessage());
				} else {

					showNotesList = showResponse.getShowNotesList();
					for (String line : showNotesList) {
						System.out.println(line);
					}

					System.out.println(showResponse.getResultMessage());
				}
				break;

			case "8":
				RegistrationRequest registration = new RegistrationRequest();
				registration.setCommandName("REGISTRATION");
				System.out.println("Enter Your New Login");
				String newLogin = in.nextLine();
				System.out.println("Enter you password");
				String newPass = in.nextLine();
				registration.setLogin(newLogin);
				registration.setPass(newPass);
				Response regResponse = controller.doRequest(registration);
				if (regResponse.isErrorStatus() == true) {
					System.out.println(regResponse.getErrorMessage());
				} else {
					System.out.println(regResponse.getResultMessage());
				}
				break;

			case "9":

				AuthentificationRequest authentification = new AuthentificationRequest();
				authentification.setCommandName("AUTHENTIFICATION");
				System.out.println("Enter Your Login");
				String login = in.nextLine();
				System.out.println("Enter your password");
				String pass = in.nextLine();
				authentification.setLogin(login);
				authentification.setPass(pass);
				Response autResponse = controller.doRequest(authentification);
				if (autResponse.isErrorStatus() == true) {
					System.out.println(autResponse.getErrorMessage());
				} else {
					System.out.println(autResponse.getResultMessage());
				}
				break;

			default:
				System.out
						.println("You write an unknown command. Pls try again or call \'help\' to find your command..");

				break;
			}

		}
	}
}