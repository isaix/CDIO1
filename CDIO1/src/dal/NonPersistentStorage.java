package dal;

import java.util.*;
import java.util.stream.Collectors;

import dto.UserDTO;

public class NonPersistentStorage implements IUserDAO {
	
	ArrayList<UserDTO> users = new ArrayList<UserDTO>();
	public NonPersistentStorage() {
		users.add(new UserDTO(11, "Mads J�rgensen", new ArrayList<String>(Arrays.asList("test")), "root", "080197-0761") );
		users.add(new UserDTO(12, "Frederik Husted", new ArrayList<String>(Arrays.asList("test", "Bassist")), "root", "123456-7890"));
		users.add(new UserDTO(12, "Frederik Husted", new ArrayList<String>(Arrays.asList("test", "Bassist")), "root", "123456-7890"));

	}

	@Override
	public UserDTO getUser(int userId) throws DALException {
		
		// ######## Har vi ikke lært endnu ########
		UserDTO user = users.stream().filter(u->u.getUserId() == userId).collect(Collectors.toList()).get(0);
	
		return user;
		
		
//		####### almindeligt for loop ######
/**		
* UserDTO user = new UserDTO();
*		for (UserDTO u : users) {
*			if (u.getUserId() == userId) {
*				user = u;
*			}
*		}
**/
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		return users;
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		users.add(user);
		
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		for (int i = 0; i<=users.size();i++) {
			if (users.get(i).getUserId() == user.getUserId()) {
				users.set(i, user);
			}
		}	
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		for (int i = 0; i<=users.size();i++) {
			if (users.get(i).getUserId() == userId) {
				users.remove(i);
			}
		}
	}
	
}