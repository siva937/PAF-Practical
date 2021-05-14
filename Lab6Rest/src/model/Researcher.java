package model;

import java.sql.*;
public class Researcher
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertResearcher(String code, String name, String email, String dep)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into researcher(`researcherID`,`researcherCode`,`researcherName`,`researcherEmail`,`researcherDep`) values (?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, code);
 preparedStmt.setString(3, name);
 preparedStmt.setString(4,(email));
 preparedStmt.setString(5, dep);
// execute the statement

 preparedStmt.execute();
 con.close();
 String newResearcher = readResearcher();
 output = "{\"status\":\"success\", \"data\": \"" +newResearcher + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while inserting the researcher.\"}";
 System.err.println(e.getMessage());
 } 
 return output;
 }
public String readResearcher()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
//Prepare the html table to be displayed
output = "<table border=\"1\"><tr><th>Researcher Code</th><th>Researcher Name</th><th>Researcher Email</th><th>Researcher Department</th><th>Update</th><th>Remove</th></tr>";
String query = "select * from researcher";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
String researcherID = Integer.toString(rs.getInt("researcherID"));
String researcherCode = rs.getString("researcherCode");
String researcherName = rs.getString("researcherName");
String researcherEmail = rs.getString("researcherEmail");
String researcherDep = rs.getString("researcherDep");

// Add into the html table
output += "<tr><td>"+ researcherCode + "</td>";
output += "<td>" + researcherName + "</td>";
output += "<td>" + researcherEmail + "</td>";
output += "<td>" + researcherDep + "</td>";
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-researcherid='" + researcherID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-researcherid='" + researcherID + "'></td></tr>";
} 
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the items.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updateResearcher(String ID, String code, String name, String email, String dep)

 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for updating."; }
 // create a prepared statement
 String query = "UPDATE researcher SET researcherCode=?,researcherName=?,researcherEmail=?,researcherDep=?WHERE researcherID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setString(1, code);
 preparedStmt.setString(2, name);
 preparedStmt.setString(3, email);
 preparedStmt.setString(4, dep);
 preparedStmt.setInt(5, Integer.parseInt(ID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newResearcher = readResearcher();
 output = "{\"status\":\"success\", \"data\": \"" +newResearcher + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\": \"Error while updating the researcher.\"}";
 System.err.println(e.getMessage());
 } 
 return output;
 }
public String deleteResearcher(String researcherID)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for deleting."; }
 // create a prepared statement
 String query = "delete from researcher where researcherID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(researcherID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newResearcher = readResearcher();
 output = "{\"status\":\"success\", \"data\": \"" +newResearcher + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
 System.err.println(e.getMessage());
 } 
 return output;
 }
} 