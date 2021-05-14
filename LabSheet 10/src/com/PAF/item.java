package com.PAF;
import java.sql.*;
public class item {
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget","root", "");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	public String InsertItem(int PID, String proj, String name,String dur, String email,String desc) {
		String output = "";
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 System.out.println("ddddd");
		 return "Error while connecting to the database";
		 }
		 System.out.println("sdass");
		 // create a prepared statement
		 String query = " insert into project(`PID`,`itemCode`,`OwnerName`,`Duration`,`Email`,'Description')" + " values (?, ?, ?, ?, ?,?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, proj);
		 preparedStmt.setString(3, name);
		 preparedStmt.setString(4, dur);
		 preparedStmt.setString(5, email);
		 preparedStmt.setString(6, desc);
		//execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		catch (Exception e)
		 {
		 output = "Error while inserting";
		 System.err.println(e.getMessage());
		 }
		return output;
	}
	public String DeleteItem(String PID) {
		String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connectingto the database for deleting.";
		 }
		 // create a prepared statement
		 String query = "delete from project where PID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(PID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newItems = readItems();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newItems + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":"
		 +"Error while deleting the item.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		}

	public String updateItem(String PID, String proj, String name,String dur, String email,String desc)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {
			 return "Error while connecting to the database for updating.";
			 }
			 // create a prepared statement
			 String query = "UPDATE project SET Project=?,OwnerName=?,Duration=?,Email=?,,Description=? WHERE itemID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, proj);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, dur);
			 preparedStmt.setString(4, email);
			 preparedStmt.setString(5, desc);
			 preparedStmt.setInt(6, Integer.parseInt(PID)); 
			
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newItems = readItems();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newItems + "\"}";
			 }
			 catch (Exception e)
			 {
			 output = "{\"status\":\"error\", \"data\":"
			 +"Error while updating the item.\"}";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 } 
	
	public String readItems()
	{
		String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connectingto the database for reading.";
		 }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Project</th><th>OwnerName</th><th>Duration</th>"+"<th>Email</th>"+"<th>Description</th>"+"<th>Update</th><th>Remove</th></tr>";
		 String query = "select * from project";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String PID = Integer.toString(rs.getInt("PID"));
		 String Project = rs.getString("Project"); 
		
		String OwnerName = rs.getString("OwnerName");
		 String Duration = rs.getString("Duration");
		 String Email = rs.getString("Email");
		 String Description = rs.getString("Description");
		 // Add into the html table
		 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate'type='hidden' value='" + PID
				 + "'>" + Project + "</td>";
				 output += "<td>" + OwnerName + "</td>";
				 output += "<td>" + Duration + "</td>";
				 output += "<td>" + Email + "</td>"; 
				 output += "<td>" + Description + "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
				  +"<td><input name='btnRemove'type='button' value='Remove' class='btnRemove btn btn-danger'" + "data-pid='" + PID + "'>" + "</td></tr>";
				
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
	
}
