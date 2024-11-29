package com.user.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.user.model.user;
public class UserDAO {
	
private String jdbcURL="jdbc:mysql://localhost:30006/userappdb";
private String jdbcUserName="root";
private String jdbcPassword="root";
private static final String INSERT_USER_SQL="INSERT INTO users"+"(uname,email,country,passwd) VALUE "+"(?,?,?,?);";
private static final String SELECT_USER_BY_ID="SELECT * FROM USERS WHERE ID=?;";
private static final String SELECT_ALL_USERS="SELECT * b FROM USERS;";
private static final String DELETE_USERS_SQL="DELETE FROM USERS WHERE ID=?;";
private static final String UPDATE_USERS_SQL="UPDATE USERS SET UNAME=?,EMAIL=?,COUNTRY?,PASSWORD=? WHERE ID=?;";
private static final String preparedStatement preparedStatement = null;
public UserDAO() {
	super();
	// TODO Auto-generated constructor stub
}
 
public Connection getConnection()
{
	Connection connection=null;
	
try 
{
	Class.forName("com.mysql.cj.jdbc.driver");
	connection=DriverManager.getConnection(jdbcURL,jdbcUserName, jdbcPassword);
}
catch(SQL Exception | ClassNotFoundException e)
{
	e.printStackTrace();
}
catch(Exception e)
{
	e.printStackTrace();
}
return connection;
}
 
public void insertUser(User user)
{
	UserDAO dao=new UserDAO();
	try Connection connection =dao.getConnection())
{
	PreparedStatement preparedStatement=connection.prepareStatement(INSERT_USER_SQL);
	PreparedStatement.setString(1,user.getName()); 
	PreparedStatement.setString(2,user.getEmail()); 
	PreparedStatement.setString(3,user.getCountry()); 
	PreparedStatement.setString(4,user.getPassword()); 
	
	preparedStatement.executeUpdate();
	
}
catch (Exception e) {
	e.printStackTrace();
}
}
public User selectUser(int id)
{
	com.user.model.user user =new User();
	UserDAO dao=new UserDAO();
	
	try Connection connection =dao.getConnection())
{
	PreparedStatement preparedStatement=connection.prepareStatement(SELECT_USER_BY_ID);
	preparedStatement.setInt(1, id);
	ResultSet resultSet=preparedStatement.executeQuery();
	
	while(resultSet.next())
	{
		user.setId(id);
		user.setName(resultSet.getString("uname"));
		user.setEmail(resultSet.getString("email"));
		user.setCountry(resultSet.getString("country"));
		user.setPassword(resultSet.getString("passwd"));
	}
}
catch (Exception e) {
	e.printStackTrace();
}
return user;
}
public List<user> selectAllUsers()
{
List <user>users=new ArrayList<user>();
UserDAO dao=new UserDAO();
try Connection connection =dao.getConnection())
{
    PreparedStatement preparedStatement=cconnection.preparedStatement(SELECT_ALL_USERS);
	ResultSet resultSet=preparedstatement.executionQuery();
	
	
	while(resultSet.next())
	{
	int id =resultSet.getInt("id");
	String uname=resultSet.getString("uname");
	String email=resultSet.getString("email");
	String country=resultSet.getString("country");
	String password=resultSet.getString("passwd");
	
	users.add(new User(id,uname,email,country,password));
	}
}
catch (Exception e) {
	e.printStackTrace();
}
    return users;
}
public boolean deleteUser(int id)
{
  boolean status =false;
  UserDAO dao=new UserDAO();
  try Connection connection =dao.getConnection())
{
	PreparedStatement  preparedStatement=connection.prepareStatement(DELETE_USERS_SQL);
	preparedStatement.setInt(1, id);
	status=preparedStatement.execute();
}
catch (Exception e) {
	e.printStackTrace();	
}
   return status;
}
}
