package epam.homework.task5.source;

import epam.homework.task5.bean.entity.NoteBook;

public class NoteBookProvider {
	
	private static final NoteBookProvider instance = new NoteBookProvider();
	
	private NoteBook noteBook;
	
	private NoteBookProvider(){
		noteBook = new NoteBook();
	}
	
	
	public static NoteBookProvider getInstance(){
		return instance;
	}
	
	
	public NoteBook getNoteBook(){
		return noteBook;
	}

}
