package tui;

import java.util.ArrayList;
import java.util.Scanner;

import dal.IUserDAO.DALException;
import dto.UserDTO;
import ful.UserFunction;

public class TUI {

	Scanner in = new Scanner(System.in);
	UserFunction function;

	public TUI(UserFunction function) {
		this.function = function;
	}

	public void showMenu() {
		Scanner in = new Scanner(System.in);
		System.out.println(
				"--Menu--\n" + 
				"1. Opret Ny Bruger\n"+ 
				"2. List brugere\n" + 
				"3. Ret Bruger\n" + 
				"4. Slet Bruger\n" + 
				"5. Afslut Program\n");

		switch(in.nextInt()) {
		case 1: createUser();
		break;
		case 2: listUsers();
		break;
		case 3: editUser();
		break;
		case 4: deleteUser();
		break;
		}
	}

	private void createUser() {


		System.out.println("Skriv brugerens id (11-99)");
		int id = in.nextInt();

		System.out.println("Skriv brugerens navn");
		String userName = in.next();
		ArrayList<String> roles = new ArrayList<String>();

		System.out.println("Skriv brugerens roller. Skriv ok n�r du er f�rdig");

		while(in.next().equals("ok")) {
			roles.add(in.next());
		}

		System.out.println("Skriv brugerens password");
		String password = in.next();

		System.out.println("Skriv brugerens cpr");
		String cpr = in.next();

		try {
			function.addUser(id, userName, roles, password, cpr);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		in.close();
	}

	private void editUser() {

		System.out.println("V�lg bruger");

		UserDTO currentUser = function.findUser(in.nextInt());

		System.out.println(
				"--Du har valgt " + currentUser.getUserName() + ", hvad vil du �ndre?\n" +
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
		case 3: System.out.println("Indtast nye roller, tryk ok n�r du er f�rdig");
		ArrayList<String> roles = new ArrayList<String>();
		while(!in.next().equals("ok")) {
			roles.add(in.next());
		}
		for(int i = 0; i < roles.size(); i++) {
			currentUser.addRole(roles.get(i));
		}
		break;
		}
	}

	private void listUsers() {
		try {
			for(int i = 0; i<function.getUserList().size(); i++) {
				System.out.println(function.getUserList().get(i).toString());
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteUser() {

		System.out.println("Indtast ID p� den bruger du vul slette");

		try {
			function.deleteUser(in.nextInt());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
