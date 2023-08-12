package com.oracle.ojdbc8;
import java.sql.*;

public class oracle {

	public static void main(String[] args) {
		try {
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
		String sql = "SELECT * FROM POKEMON";
		String query = "SELECT Name, Sp.ATK FROM POKEMON WHERE Type = ? AND Sp.ATK > ?";
		String inserting = "INSERT INTO POKEMON (Name, Type, Sp.ATK) VALUES ('Charizard','Fire',150)";
		Statement st = conn.createStatement(ResultSet.CONCUR_UPDATABLE,ResultSet.TYPE_SCROLL_INSENSITIVE); //the resultset from this statement is updatable and 
		                                                                                                   //immune to changes others make to the underlying database
		
		Statement stmt = conn.createStatement(); //by default, the resultset from this statement cannot be updated and cursor only moves forward
		stmt.executeUpdate(inserting); //executeUpdate for DML statements such as insert,update,delete (returns # of rows affected)
		PreparedStatement ps = conn.prepareStatement(query); //query is pre-compiled here by the DBMS
		ps.setString(1, "Fire");
		ps.setInt(2, 150);
		ResultSet rs1 = st.executeQuery(sql); //using regular statement object, need to insert sql statement into argument here
		ResultSet rs2 = ps.executeQuery(); //using preparedstatement object, 
		//processing ResultSets
		//Updating resultset & consequently the database
		rs1.moveToInsertRow(); //inserting a new row into the resultset with the below items (staging area)
		rs1.updateString(1, "Charizard"); 
		rs1.updateString(2, "Dragon");
		rs1.updateInt(3, 150);
		rs1.insertRow(); //completes the insertion of the row [Charizard, Dragon, 150] into both the resultset & the database
		rs1.moveToCurrentRow(); //moves cursor out of the insert staging area back into the resultset
		
		rs1.absolute(4); //moves cursor to the 4th row of the resultset
		rs1.updateString("Name","Arcanine"); //changes the name column of 4th row of the resultset to Arcanine
		rs1.updateInt(5, 200); //changes 5th column of 4th row of resultset to 200
		rs1.updateRow(); //completes the update of 4th row. changes are reflected in the underlying database
		//looping through the resultset and getting values
		while (rs2.next()) {
			String pkmn_name = rs2.getString(1); //returns the first column of each row from the resultset (which is the name of fire pokemon)
			int Sp_ATK = rs2.getInt("Sp.ATK"); // can also specify the name of the column instead of the number of the column
			System.out.println(pkmn_name + " has a Sp. Atk of " + Sp_ATK);
			
		}
		System.out.println(conn.isValid(10)); //checks if you have a valid connection for 10 seconds before giving up
		
		
		}
		catch (SQLException e) {
			e.getMessage();
		}
		
		
		
		

	}

}
