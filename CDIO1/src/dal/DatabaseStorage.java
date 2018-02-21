package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import dto.UserDTO;

public class DatabaseStorage implements IUserDAO {
	    private Connection connect = null;
	    private Statement statement = null;
	    private PreparedStatement preparedStatement = null;
	    private ResultSet resultSet = null;
	    final String url = "jdbc:mysql://77.72.0.142:3306/eywa_database?useSSL=true";
	    final String database = "eywa_database.user"; //Write in format DATABASE.TABLE, Change only here
	    final String username = "eywa_user"; //database user username 
	    final String password = "Climate2020";
	    
	    //simple database connection test - deprecated
	    public void testConnection() {
		    System.out.println("Connecting database...");
            // Setup the connection with the DB
		    try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    		connect = connection;
		        System.out.println("Database connected!");
		    } catch (SQLException e) {
		        throw new IllegalStateException("Cannot connect the database!", e);
		    }
	    }
	    
	    // Read from database
	    public void readDataBase() throws Exception {
		    	try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    		connect = connection;
			        System.out.println("Database connected!");
	            // Statements allow to issue SQL queries to the database
		    		statement = connect.createStatement();
		    		// Result set get the result of the SQL query
		    		//prints table before write
	            resultSet = statement.executeQuery("select * from " + database);
	            writeResultSet(resultSet);
	            System.out.println("_____________________");
	            
//	            writeToTable();
	            
	            //Prints table after write 
//	            preparedStatement = connect.prepareStatement("SELECT name, initials, role, password, cpr from " + database);
//	            resultSet = preparedStatement.executeQuery();
//	            writeResultSet(resultSet);
		    	} catch (SQLException e) {
			        throw new IllegalStateException("Cannot connect the database!", e);   
		    } catch (Exception e) {
	            throw e;
	        } finally {
	            close();
	        }
	    }
	    
	    private void writeToTable(String name, String initials, String role, String password, int cpr) throws Exception {
	        // PreparedStatements can use variables and are more efficient
	    		// "?" can be used as placeholder for values
            preparedStatement = connect.prepareStatement("insert into " + database + " (name, initials, role, password, cpr) values (?, ?, ?, ?, ?)");
            // "name, initials, role, password, cpr from database.user");
            // Parameters start with 1
            preparedStatement.setString(1, "nameTest");
            preparedStatement.setString(2, "initialsTest");
            preparedStatement.setString(3, "roleTest");
            preparedStatement.setString(4, "passwordTest");
            preparedStatement.setInt(5, 12345678);
            preparedStatement.executeUpdate();

	    }

	    private void writeResultSet(ResultSet resultSet) throws SQLException {
	        // ResultSet is initially before the first data set
	        while (resultSet.next()) {
	            // It is possible to get the columns via name
	            // also possible to get the columns via the column number
	            // which starts at 1
	            // e.g. resultSet.getString(2);
	            String name = resultSet.getString("name");
	            String initials = resultSet.getString("initials");
	            String role = resultSet.getString("role");
	            String password = resultSet.getString("password");
	            int cpr = resultSet.getInt("cpr");
	            System.out.println("Name: " + name);
	            System.out.println("Initials: " + initials);
	            System.out.println("Role: " + role);
	            System.out.println("Password: " + password);
	            System.out.println("CPR: " + cpr);

	        }
	    }
//	    private void writeUser(ResultSet resultSet, int userId) throws SQLException {
//	    		while (resultSet.next()) {
//	    			if (resultSet.getInt(1) == userId) {
//	    				UserDTO user = new UserDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.get, resultSet.getString(5), resultSet.getInt(6));
//	    				
//	    			}
//	    		}
//	    }
	    
	 // You need to close the resultSet
	    private void close() {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }

	            if (statement != null) {
	                statement.close();
	            }

	            if (connect != null) {
	                connect.close();
	            }
	        } catch (Exception e) {

	        }
	    }
	    
	    /////////////////////
		@Override
		public UserDTO getUser(int userId) throws DALException {
			// TODO Auto-generated method stub
			
			return null;
		}


		@Override
		public List<UserDTO> getUserList() throws DALException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void createUser(UserDTO user) throws DALException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateUser(UserDTO user) throws DALException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void deleteUser(int userId) throws DALException {
			// TODO Auto-generated method stub
			
		}
}
