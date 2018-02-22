package tui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import dal.IUserDAO.DALException;
import dto.UserDTO;
import ful.UserFunction;
import dal.PasswordGenerator;

public class TUI {

	UserFunction function;
	PasswordGenerator passGen = new PasswordGenerator();
	Scanner in = new Scanner(System.in);
	
	public TUI(UserFunction function) {
		this.function = function;
	}

	public void showMenu() {

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

		System.out.println("Skriv brugerens id (11-99)");
		int id = 0;

		while(true) {
			try {
				id = in.nextInt();
				if(id < 11 || id > 99) {
					System.out.println("Id skal v�re mellem 11 og 99. Pr�v igen");
				} else if(function.asserIfIdExists(id)){
					System.out.println("Id findes allerede. Pr�v igen");
				} else break;
			} catch(InputMismatchException e) {
				System.out.println("FEJL: Skriv venligst et tal mellem 11 og 99");
				in.next();
			} 
		}

		System.out.println("Skriv brugerens navn");
		in.nextLine();
		String userName = in.nextLine();

		ArrayList<String> roles = new ArrayList<String>();
		System.out.println("Skriv brugerens roller. Brug enter efter hver rolle. Skriv ok n�r du er f�rdig");

		String newRole;
		while(true) {
			newRole = in.next();
			if(newRole.equals("ok")) {
				break;
			}
			roles.add(newRole);
		}
		
		
		String password = PasswordGenerator.generatePassword(10, PasswordGenerator.ALPHA_CAPS + PasswordGenerator.ALPHA +PasswordGenerator.NUMERIC);
		System.out.println("Brugerens password er: " + password);

		System.out.println("Skriv brugerens cpr");

		String cpr = in.next();

		try {
			function.addUser(id, userName, roles, password, cpr);
		} catch (DALException e) {
			System.out.println("Brugeren kunne ikke tilf�jes. Afslutter til menu");
		}

	}

	private void editUser() {
		System.out.println("V�lg id p� den bruger du vil �ndre p�.");

		int id = 0;

		while(true) {
			try {
				id = in.nextInt();
				if(id < 11 || id > 99) {
					System.out.println("Id skal v�re mellem 11 og 99. Pr�v igen");
				} else if(!function.asserIfIdExists(id)){
					System.out.println("Id findes ikke. Pr�v igen");
				} else break;
			} catch(InputMismatchException e) {
				System.out.println("Skriv venligst et tal mellem 11 og 99.");
				in.next();
			}
		}
		UserDTO currentUser = function.findUser(id);

		System.out.println(
				"--Du har valgt " + currentUser.getUserName() + ", hvad vil du �ndre?\n" +
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
		case 3: System.out.println("Indtast nye roller, tryk ok n�r du er f�rdig");
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
			currentUser.setRoles(roles);
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

		System.out.println("Indtast ID p� den bruger du vil slette");

		try {
			function.deleteUser(in.nextInt());
		} catch (InputMismatchException e) {
			System.out.println("Skriv venligst et tal mellem 11 og 99. Afslutter til hovedmenu");
		}

	}

}
