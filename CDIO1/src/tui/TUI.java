package tui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import dal.IUserDAO.DALException;
import dto.UserDTO;
import ful.UserFunction;

public class TUI {

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

		System.out.println("Tryk venligst et tal mellem 1 og 5");
		int choice = 0;
		try {
			choice = in.nextInt();
		}catch (InputMismatchException e) {
		}

		switch(choice) {
		case 1: createUser();
		break;
		case 2: listUsers();
		break;
		case 3: editUser();
		break;
		case 4: deleteUser();
		break;
		case 5: System.exit(0);
		break;
		default:
			System.out.println("FEJL: Tryk venligst et tal mellem 1 og 5");
			choice = 0;
			break;
		}
	}

	private void createUser() {
		Scanner in = new Scanner(System.in);

		System.out.println("Skriv brugerens id (11-99)");
		int id = 0;

		while(true) {
			try {
				id = in.nextInt();
				if(id < 11 || id > 99) {
					System.out.println("Id skal være mellem 11 og 99. Prøv igen");
				} else if(function.asserIfIdExists(id)){
					System.out.println("Id findes allerede. Prøv igen");
				} else break;
			} catch(InputMismatchException e) {
				System.out.println("FEJL: Skriv venligst et tal mellem 11 og 99");
				in.next();
			} 
		}

		System.out.println("Skriv brugerens navn");
		String userName = in.next();

		ArrayList<String> roles = new ArrayList<String>();
		System.out.println("Skriv brugerens roller. Brug enter efter hver rolle. Skriv ok når du er færdig");

		String newRole;
		while(true) {
			newRole = in.next();
			if(newRole.equals("ok")) {
				break;
			}
			roles.add(newRole);
		}

		System.out.println("Skriv brugerens password");
		String password = in.next();
		
		while(!function.assertPasswordQuality(password)) {
			System.out.println("Adgangskoden skal indeholde mindst 6 tegn af mindst tre af de følgende fire kategorier: \nsmå bogstaver (’a’ til ’z’), store bogstaver (’A’ til ’Z’), \ncifre (’0’ til ’9’) og specialtegn ('.', '-', '_', '+', '!', '?', '=').");
			password = in.next();
		}

		System.out.println("Skriv brugerens cpr");

		String cpr = in.next();

		try {
			function.addUser(id, userName, roles, password, cpr);
		} catch (DALException e) {
			System.out.println("Brugeren kunne ikke tilføjes. Afslutter til menu");
		}

	}

	private void editUser() {
		Scanner in = new Scanner(System.in);

		System.out.println("Vælg id på den bruger du vil ændre på.");

		int id = 0;

		while(true) {
			try {
				id = in.nextInt();
				if(id < 11 || id > 99) {
					System.out.println("Id skal være mellem 11 og 99. Prøv igen");
				} else if(!function.asserIfIdExists(id)){
					System.out.println("Id findes ikke. Prøv igen");
				} else break;
			} catch(InputMismatchException e) {
				System.out.println("Skriv venligst et tal mellem 11 og 99.");
				in.next();
			}
		}
		UserDTO currentUser = function.findUser(id);

		System.out.println(
				"--Du har valgt " + currentUser.getUserName() + ", hvad vil du ændre?\n" +
						"1. Brugernavn\n" + 
						"2. Password\n" + 
				"3. Roller");
		switch(in.nextInt()) {
		case 1: System.out.println("Indtast nyt brugernavn");
		function.editName(currentUser.getUserId(), in.next());
		break;
		case 2: System.out.println("Indtast nyt password");
		function.editPassword(currentUser.getUserId(), in.next());
		break;
		case 3: System.out.println("Indtast nye roller, tryk ok når du er færdig");
		ArrayList<String> roles = new ArrayList<String>();
		String newRole;
		while(true) {
			newRole = in.next();
			if(newRole.equals("ok")) {
				break;
			}
			roles.add(newRole);
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
			System.out.println("Der er sket en fejl. Afslutter til hovedmenu");
		}
	}

	private void deleteUser() {
		Scanner in = new Scanner(System.in);

		System.out.println("Indtast ID på den bruger du vil slette");

		try {
			function.deleteUser(in.nextInt());
		} catch (InputMismatchException e) {
			System.out.println("Skriv venligst et tal mellem 11 og 99. Afslutter til hovedmenu");
		}

	}

}
