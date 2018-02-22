package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dal.IUserDAO;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 4545864587995944260L;
	private int	userId;                     
	private String userName;                
	private String ini;                 
	private List<String> roles;
	private String password;
	private String cpr;
	
	public UserDTO(int userId, String userName, List<String> roles, String password, String cpr) {
		this.userId = userId;
		this.userName = userName;
		this.roles = roles;
		this.password = password;
		this.cpr = cpr;
	}
	
	public String toString() {
		return String.format("ID:%-4d Username:%-20s Initials:%-4s Password:%-12s Cpr:%-14s Roller: " + roles, userId, userName, getIni(), password, cpr);
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setIni(String ini) {
		this.ini = ini;
	}

	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role){
		roles.add(role);
	}
	/**
	 * 
	 * @param role
	 * @return true if role existed, false if not
	 */
	public boolean removeRole(String role){
		return this.roles.remove(role);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpr() {
		return cpr;
	}
	public void setCpr(String cpr) {
		this.cpr = cpr;
	}
	
	public String getIni() {
		String ini = "";
		for(int i = 0; i < userName.length() && ini.length() < 4; i++) {
			if(Character.isUpperCase(userName.charAt(i))) {
				ini = ini + userName.charAt(i);
			}
		} return ini;
	}
	
	
	
}
