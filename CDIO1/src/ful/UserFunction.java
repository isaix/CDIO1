package ful;

import java.util.List;

import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dal.PersistentStorage;
import dto.UserDTO;

public class UserFunction {
	
	IUserDAO storage;
	private UserFunction(IUserDAO storage) {
		this.storage = storage;
	}

	public void addUser(int userId, String userName, List<String> roles, String password, String cpr) throws DALException {
		storage.createUser(new UserDTO(userId, userName, roles, password, cpr));
	}
	
	public UserDTO findUserIdFromList(int userId) throws DALException {
		UserDTO user = storage.getUserList().get(userId);
		return user;
		
	}
}
