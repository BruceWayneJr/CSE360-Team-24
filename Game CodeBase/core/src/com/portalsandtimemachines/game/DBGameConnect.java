package com.portalsandtimemachines.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DBGameConnect {
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
	
	@SuppressWarnings("finally")
	void dbConnect(HashMap dbInput) {
		String url = "jdbc:mysql://localhost:3306/game";
		String username = "cse360";
		String password = "cse360";
		String insertQuery;
		String selectQuery;
		String updateQuery;
		String fetchQuery;
		String pName;
		int gPlay;
		int gWon;
		float gRate;
		String tempName = "";
		int tempInt = 0;
		int tempScore = 0;
		ArrayList<List> resultDisplay = new ArrayList<List>();
		
		System.out.println("Loading driver...");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}

		System.out.println("Connecting database...");

		selectQuery = "SELECT `Player_Name`, `Games_Played`, `Games_Won` FROM `scorecard`";
		fetchQuery = "SELECT * FROM `scorecard`";
		
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			ArrayList<String> playerName = (ArrayList<String>) dbInput.get("pname");
			boolean gameWon = (boolean) dbInput.get("gWon");
			ResultSet rs;
			System.out.println("Database connected!");
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			Statement stmt3 = conn.createStatement();
			if((boolean) dbInput.get("fetchFlag")) {
				String[] col = {"Player_Name","Games_Played","Games_Won","Win_Rate"};
				rs = stmt.executeQuery(fetchQuery);
				JTable table = new JTable(buildTableModel(rs));
//				int columnsNumber = rsmd.getColumnCount();
//				while (rs.next()) {
//					//Print one row 
//					String fetchedRows = "";
//					ArrayList<String> tempList = new ArrayList<String>();
//					for(int i = 1 ; i <= columnsNumber; i++){
//					      tempList.add(rs.getString(i));
//					}
//					resultDisplay.add(tempList);
//				}

			    JOptionPane.showMessageDialog(null, new JScrollPane(table));
			}
			else{
				rs = stmt.executeQuery(selectQuery);
				// System.out.println("Boolean" + rs.getBoolean("Player_Name"));
				while (rs.next()) {
					pName = rs.getString("Player_Name");				
					System.out.println(pName);
					gPlay = rs.getInt("Games_Played");
					System.out.println(gPlay);
					gWon = rs.getInt("Games_Won");
					gRate = (gWon/gPlay)*100;
					if(playerName.contains(pName)) {
						tempInt = gPlay + 1;
						if(gameWon){
							int temp = gWon+1;
							updateQuery = "UPDATE `scorecard` SET `Games_Won`= "+ "'" + temp + "'" + ",`Games_Played`=" + "'" + tempInt + "'" + ",`Win_Rate`="+"'"+ gRate +"'"+"  WHERE `Player_Name`=" + "'" + pName + "'";
							stmt2.execute(updateQuery);
							playerName.remove(pName);
						}
						else {
							updateQuery = "UPDATE `scorecard` SET `Games_Won`= "+ "'" + gWon + "'" + ",`Games_Played`=" + "'" + tempInt + "'" + ",`Win_Rate`="+"'"+ gRate +"'"+"  WHERE `Player_Name`=" + "'" + pName + "'";
							stmt2.execute(updateQuery);
							playerName.remove(pName);
						}
					}		
				}
				if (playerName.size() > 1) {
					for (int i = 0; i < playerName.size(); i++) {
						insertQuery = "INSERT INTO `scorecard` (`Player_Name`, `Games_Won`, `Games_Played`, `Win_Rate`) VALUES ("
								+ "'" + playerName.get(i) + "'" + ", '0', '1', '0')";
						stmt.execute(insertQuery);
					}
				} else {
					insertQuery = "INSERT INTO `scorecard` (`Player_Name`, `Games_Won`, `Games_Played`, `Win_Rate`) VALUES ("
							+ "'" + playerName.get(0) + "'" + ", '0', '1', '0')";
					stmt.execute(insertQuery);
				}
				
	
				stmt.close();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR!!! Database connection error due to invalid credentials. Still you will be able to play the game but scorecard will not be updated!!!" + e);
		} finally {
			return;
		}
	}
}
