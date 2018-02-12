package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dal.UserStore;
import dto.UserDTO;

public class PersistentStorage implements IUserDAO {
	
	ArrayList<UserDTO> users = new ArrayList<UserDTO>();
	
	String fileName;
	
	public PersistentStorage(String fileName) {
		this.fileName = fileName;
	}
	
	public UserStore loadUsers() throws DALException {
		UserStore userStore = new UserStore();
		ObjectInputStream oIS = null;
		try {
			FileInputStream fIS = new FileInputStream(fileName);
			oIS = new ObjectInputStream(fIS);
			Object inObj = oIS.readObject();
			if (inObj instanceof UserStore){
				userStore = (UserStore) inObj;
				users = new ArrayList<UserDTO>(userStore.getUsers().values());
			} else {
				throw new DALException("Wrong object in file");
			}
		} catch (FileNotFoundException e) {
			//No problem - just returning empty userstore
		} catch (IOException e) {
			throw new DALException("Error while reading disk!", e);
		} catch (ClassNotFoundException e) {
			throw new DALException("Error while reading file - Class not found!", e);
		} finally {
			if (oIS!=null){
				try {
					oIS.close();
				} catch (IOException e) {
					throw new DALException("Error closing pObjectStream!", e);
				}
			}
		}
		return userStore;
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
	public void createUser(UserDTO u) throws DALException {
		// TODO Auto-generated method stub
		users.add(u);
		
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		for (int i = 0; i<users.size();i++) {
			if (users.get(i).getUserId() == user.getUserId()) {
				users.set(i, user);
			}
		}	
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		for (int i = 0; i<users.size();i++) {
			if (users.get(i).getUserId() == userId) {
				users.remove(i);
			}
		}
	}

	public void saveTofile() {
		
		File file = new File(fileName);
		UserStore userStore = new UserStore();
		
		Map<Integer, UserDTO> userMap = new HashMap<Integer, UserDTO>();
		
		for (int i = 0; i<users.size();i++) {
			userMap.put(i, users.get(i));
		}
		
		userStore.setUsers(userMap);
		
		try (FileOutputStream saveFile = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            ObjectOutputStream oos = new ObjectOutputStream(saveFile);
            oos.writeObject(userStore);
            oos.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
		
		
	}
}
