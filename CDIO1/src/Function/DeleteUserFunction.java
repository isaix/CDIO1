package Function;
 

import dal.IUserDAO;
import dal.IUserDAO.DALException;


public class DeleteUserFunction {
	IUserDAO storage;
	
	private DeleteUserFunction(IUserDAO storage) {
		this.storage = storage;
	}

	public void deleteUser(int userId) throws DALException {
		storage.deleteUser(userId);
	}

}
