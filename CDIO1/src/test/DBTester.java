package test;


import java.util.List;

import dal.IUserDAO;
import dal.PersistentStorage;
import dal.IUserDAO.DALException;
import dal.NonPersistentStorage;
import dal.UserDAODiscImpl;
import dto.UserDTO;
import ful.UserFunction;
import tui.TUI;

public class DBTester {
	IUserDAO storage = new NonPersistentStorage();
	UserFunction function = new UserFunction(storage);
	TUI ui = new TUI(function);
	public static void main(String[] args) {
		DBTester test = new DBTester();
		test.run();
	}
	
	public void run() {
		while(true) {
			ui.showMenu();
		}
	}
}
