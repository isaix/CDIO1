package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dal.IUserDAO.DALException;
import dto.UserDTO;
import ful.UserFunction;

public class FunctionTest {
	private List<String> role = new ArrayList<String>();
	private UserDTO u = new UserDTO(11, "Frederik", role,"Dtu2018", "171094-2077");
	UserFunction us;
	
	
	
	@Test
	public void testEditName() {
		us.editName(u.getUserId(), "Tobias");
		String expected = "Frederik";
		String actual = u.getUserName();
		assertEquals("Navnene passer ikke",expected, actual);
	}

	@Test
	public void testEditPassword() {
		us.editPassword(u.getUserId(), "Dtu2019");
		String expected = "Dtu2019";
		String actual = u.getPassword();
		assertEquals("Passwordene stemmer ikke overens",expected, actual);
	}

	@Test
	public void testEditRoles() {
		ArrayList<String> roleNew = (ArrayList<String>) u.getRoles();
		roleNew.add("admin");
		us.editRoles(u.getUserId(), roleNew);
		List<String> expected = roleNew;
		List<String> actual = u.getRoles(); 
		assertEquals("Fail! noob...", actual, expected);
	}

	@Test
	public void testDeleteUser() {
		us.deleteUser(u.getUserId());
		UserDTO expected = null;
		UserDTO actual = us.findUser(u.getUserId());
		assertEquals("Fail!!!",actual,expected);
	}

}
