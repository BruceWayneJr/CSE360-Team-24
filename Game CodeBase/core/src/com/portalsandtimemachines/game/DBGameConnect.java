package com.portalsandtimemachines.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

public class DBGameConnect {
	void dbConnect(HashMap dbInput) {
		String url = "jdbc:mysql://localhost:3306/game";
		String username = "cse360";
		String password = "cse360";
		String insertQuery;
		String selectQuery;
		String updateQuery;
		String pName;
		int gPlay;
		String tempName = "";
		int tempInt = 0;
		ArrayList<String> playerName = (ArrayList<String>) dbInput.get("pname");
		ResultSet rs;
		System.out.println("Loading driver...");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}

		System.out.println("Connecting database...");

		insertQuery = "INSERT INTO `scorecard` (`Player_Name`, `Games_Won`, `Games_Played`, `Win_Rate`) VALUES (" + "'"
				+ tempName + "'" + ", '0', '1', '0')";
		selectQuery = "SELECT `Player_Name`, `Games_Played` FROM `scorecard`";
		updateQuery = "UPDATE `scorecard` SET `Player_Name`=" + tempName
				+ " ,`Games_Won`=[value-2],`Games_Played`=[value-3],`Win_Rate`=[value-4] WHERE 1";
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);
			// System.out.println("Boolean" + rs.getBoolean("Player_Name"));
			while (rs.next()) {
				pName = rs.getString("Player_Name");				
				System.out.println(pName);
				gPlay = rs.getInt("Games_Played");
				System.out.println(gPlay);
				
				if(playerName.contains(pName)) {
					tempInt = gPlay + 1;
					updateQuery = "UPDATE `scorecard` SET `Games_Won`= '0',`Games_Played`=" + "'" + tempInt + "'" + ",`Win_Rate`='0'  WHERE `Player_Name`=" + "'" + pName + "'";
					stmt2.execute(updateQuery);
					playerName.remove(pName);
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
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
}
