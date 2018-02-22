package ful;

import java.util.ArrayList;
import java.util.List;

import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dal.PersistentStorage;
import dto.UserDTO;

public class UserFunction {

	IUserDAO storage;
	public UserFunction(IUserDAO storage) {
		this.storage = storage;
	}

	public void addUser(int userId, String userName, List<String> roles, String password, String cpr) throws DALException {
		storage.createUser(new UserDTO(userId, userName, roles, password, cpr));
	}

	public void editName(int userId, String newName) {
		try {
			for(int i = 0; i < storage.getUserList().size(); i++) {
				if (storage.getUserList().get(i).getUserId() == userId) {
					storage.getUserList().get(i).setUserName(newName);
					storage.updateUser(storage.getUserList().get(i));
				}
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editPassword(int userId, String newPassword) {
		try {
			for(int i = 0; i < storage.getUserList().size(); i++) {
				if (storage.getUserList().get(i).getUserId() == userId) {
					storage.getUserList().get(i).setPassword(newPassword);
					storage.updateUser(storage.getUserList().get(i));
				}
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editRoles(int userId, ArrayList<String> newRoles) {
		UserDTO currentUser = null;
		try {
			for(int i = 0; i < storage.getUserList().size()-1; i++) {
				if (storage.getUserList().get(i).getUserId() == userId) {
					currentUser = storage.getUserList().get(i);
				}
			}
			for(int i = 0; i < newRoles.size(); i++) {
				currentUser.getRoles().add(newRoles.get(i));
			}

		}
		catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UserDTO findUser(int userId) {
		try {
			return storage.getUser(userId);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<UserDTO> getUserList() throws DALException{
		return storage.getUserList();
	}

	public void deleteUser(int userId) {
		try {
			storage.deleteUser(userId);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean asserIfIdExists(int userId) {
		try {
			for(int i = 0; i < storage.getUserList().size(); i++) {

				if(storage.getUserList().get(i).getUserId() == userId) {
					return true;
				}

			} } catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			} return false;
	}

	public boolean assertPasswordQuality(String password) {
		if(password.length() < 6) {
			return false;
		}
		int smallLetterCount = 0;
		for(int i = 0; i < password.length(); i++) {
			if(Character.isLowerCase(password.charAt(i))) {
				smallLetterCount++;
			}
		} int bigLetterCount = 0;
		for(int i = 0; i < password.length(); i++) {
			if(Character.isUpperCase(password.charAt(i))) {
				bigLetterCount++;
			}
		}  int numberCount = 0;
		for(int i = 0; i < password.length(); i++) {
			if(Character.isDigit(password.charAt(i))){
				numberCount++;
			}
		} int specialCount = 0;
		for(int i = 0; i < password.length(); i++) {
			switch(password.charAt(i)) {
			case '.' : specialCount++;
			break;
			case '-' : specialCount++;
			break;
			case '_' : specialCount++;
			break;
			case '+' : specialCount++;
			break;
			case '!' : specialCount++;
			break;
			case '?' : specialCount++;
			break;
			case '=' : specialCount++;
			break;
			}
		}
		int count = 0;
		if(smallLetterCount != 0) {
			count++;
		} if(bigLetterCount != 0) {
			count++;
		} if(numberCount != 0) {
			count++;
		} if(specialCount != 0) {
			count ++;
		}

		if(count < 3) {
			return false;
		}
		if(password.length() != smallLetterCount + bigLetterCount + numberCount + specialCount) {
			return false;
		}

		return true;
	}
}
