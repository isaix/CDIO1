package tui;

import java.util.ArrayList;
import java.util.Scanner;

import dal.IUserDAO.DALException;
import dto.UserDTO;
import ful.UserFunction;

public class TUI {
	
	UserFunction function;

	public TUI(UserFunction function) {
		function = function;
	}
	
	private void showMenu() {
		System.out.println(
				"--Menu--" + 
				"1. Opret Ny Bruger\n "+ 
				"2. List brugere\n" + 
				"3. Ret Bruger\n" + 
				"4. Slet Bruger\n" + 
				"5. Afllut Program\n");
	}
	
	private void createUser() throws DALException {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Skriv brugerens id (11-99)");
		int id = in.nextInt();
		
		System.out.println("Skriv brugerens navn");
		String userName = in.next();
		ArrayList<String> roles = new ArrayList<String>();
		
		System.out.println("Skriv brugerens roller. Skriv ok når du er færdig");
		
		while(in.next().equals("ok")) {
			roles.add(in.next());
		}
		
		System.out.println("Skriv brugerens password");
		String password = in.next();
		
		System.out.println("Skriv brugerens cpr");
		String cpr = in.next();
				
		function.addUser(id, userName, roles, password, cpr);
		
		in.close();
	}
	
	private void editUser() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Vælg bruger");
		
		UserDTO currentUser = function.findUser(in.nextInt());
		
		System.out.println(
				"--Du har valgt " + currentUser.getUserName() + ", hvad vil du ændre?\n" +
				"1. Brugernavn\n" + 
				"2. Password" + 
				"3. Roller");
		switch(in.nextInt()) {
		case 1: System.out.println("Indtast nyt brugernavn");
		function.editName(currentUser.getUserId(), in.next());
		break;
		case 2: System.out.println("Indtast nyt password");
		function.editPassword(currentUser.getUserId(), in.next());
		break;
		case 3: System.out.println("Indtast nye roller, tryk ok når du er færdig");
		ArrayList<String> roles = null;
		while(!in.next().equals("ok")) {
			roles.add(in.next());
		}
		break;
		}
		
	}
	

}
