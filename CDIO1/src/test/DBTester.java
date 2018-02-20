package test;


import java.util.List;

import dal.DatabaseStorage;
import dal.IUserDAO;
import dal.PersistentStorage;
import dal.IUserDAO.DALException;
import dal.NonPersistentStorage;
import dal.UserDAODiscImpl;
import dto.UserDTO;
import ful.UserFunction;
import tui.TUI;
import dal.DatabaseStorage;

public class DBTester {
	IUserDAO storage = new NonPersistentStorage();
	UserFunction function = new UserFunction(storage);
	TUI ui = new TUI(function);
	public static void main(String[] args) {
//		DBTester test = new DBTester();
//		test.run();
//		DatabaseStorage db = new DatabaseStorage();
//		db.testConnection();
		
		DatabaseStorage db = new DatabaseStorage();
        try {
        		//db.testConnection();
			db.readDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			ui.showMenu();
		}
	}
}
