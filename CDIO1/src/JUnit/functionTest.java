package JUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dal.IUserDAO.DALException;
import dal.PersistentStorage;
import dto.UserDTO;
import ful.UserFunction;

import java.util.ArrayList;
import java.util.List;

public class functionTest {
	
	private List<String> role = new ArrayList<String>();
	private UserDTO u = new UserDTO(11, "Frederik", role,"Dtu2018", "171094-2077");
	PersistentStorage storage = new PersistentStorage("testSave");
	UserFunction us = new UserFunction(storage);

	@Before
	public void setUp() throws Exception {
		role.add("Admin");
		//storage.createUser(u);
		storage.saveTofile();
		//storage.loadUsers();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testEditName() throws DALException {
//		us.editName(u.getUserId(), "Mads");
//		String expected = "Mads";
//		//storage.loadUsers();
//		String actual = storage.getUser(u.getUserId()).getUserName();
//		assertEquals("Navnene passer ikke",expected, actual);
//	}
//
//	@Test
//	public void testEditPassword() throws DALException {
//		us.editPassword(u.getUserId(), "Dtu2019");
//		String expected = "Dtu2019";
//		String actual = storage.getUser(u.getUserId()).getPassword();
//		assertEquals("Passwordene stemmer ikke overens",expected, actual);
//	}
//
//	@Test
//	public void testEditRoles() throws DALException {
//		ArrayList<String> roleNew = new ArrayList<String>();
//		roleNew.add("noob");
//		us.editRoles(u.getUserId(), roleNew);
//		List<String> expected = roleNew;
//		List<String> actual = storage.getUser(u.getUserId()).getRoles(); 
//		assertEquals("Fail! noob...", expected, actual);
//	}

	@Test
	public void testDeleteUser() throws DALException {
		us.deleteUser(u.getUserId());
		UserDTO actual = us.findUser(u.getUserId());
		assertTrue(actual == null);
	}
}
