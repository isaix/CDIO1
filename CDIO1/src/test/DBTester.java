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
	PersistentStorage storage = new PersistentStorage("testFile");
	UserFunction function = new UserFunction(storage);
	TUI ui = new TUI(function);
	public static void main(String[] args) {
//		DBTester test = new DBTester();
//		test.run();
//		DatabaseStorage db = new DatabaseStorage();
//		db.testConnection();
		
<<<<<<< HEAD
        DBTester test = new DBTester();
        test.run();
=======
		
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
			
		
		DBTester test = new DBTester();
//		test.run();
		try {
			test.storage.loadUsers();
			printUsers(test.storage);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			UserDTO user = new UserDTO(0, null, null, null, null);
			//user.addRole;
			user.setUserName("testName");
			user.setUserId(1);
			test.storage.createUser(user);
			test.storage.saveTofile();;
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			test.storage.loadUsers();
			printUsers(test.storage);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
>>>>>>> branch 'master' of https://github.com/isaix/CDIO1.git
	}
	
	public void run() {
		while(true) {
			ui.showMenu();
		}
	}
	private static void printUsers(IUserDAO iDAO) {
		try {
			System.out.println("Printing users...");
			List<UserDTO> userList = iDAO.getUserList();
			for (UserDTO userDTO : userList) {
				System.out.println(userDTO);
			}

		} catch (DALException e) {
			e.printStackTrace();
		}
	}
}
