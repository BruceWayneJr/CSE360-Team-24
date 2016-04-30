<h1># CSE360-Team-24</h1>
<h3>Portals</h3> 

Its an exciting dice game which is played between 2 or more players having numbered gridded squares.
The number "portals" and "bounty Tile" are displayed on the checkered board. The objective of the game is for each 
player to navigate all their "pawns", according to the die roll from the start(bottom square) to the finish(top 
square) helped or hindered by the portals.

<b>Trello link</b> 

    - https://trello.com/b/o6AiO1BM/cse360-team-24


<b>Dependency Installation:</b> 

  libgdx library
  
    - https://libgdx.badlogicgames.com/documentation.html 

This project requires installation of Gradle. The steps to install Gradle in Eclipse is explained below,

Installing Gradle

-	Open Eclipse, click Help -> Eclipse Marketplace
-	Type in ‘gradle’ in the find text box and click Search
-	Click Install from the search results for gradle

Importing the Project

-	Go to File -> Import -> Gradle -> Gradle Project
-	Click Browse and navigate to the root folder of your project, then click Build Model.
-	Select all the projects and click Finish. 

Java - MySQL Database Connectivity

-   Install Latest MySQL Database 

    To connect to MySQL from Java, you have to use the JDBC driver from MySQL. The MySQL JDBC driver is called MySQL Connector/J. You find the latest MySQL JDBC driver under the following URL: http://dev.mysql.com/downloads/connector/j.
    The download contains a JAR file which we require later.

-   Create a lib folder and copy the JDBC driver into this folder. Add the JDBC driver to your classpath. 
    See Adding jars to the classpath for details.
    -   http://www.vogella.com/tutorials/Eclipse/article.html#classpath

-   Create a new database called game and start using it with the following command.

    create database game

-   Create a user with the following command.
    
    CREATE USER cse360 IDENTIFIED BY 'cse360'; 
    
    grant usage on *.* to cse360@localhost identified by 'cse360'; 
    
    grant all privileges on feedback.* to cse360@localhost; 
    
    Now create a table named 'scorecard' following SQL statement.
    
    CREATE TABLE scorecard (
        Player_Name VARCHAR(30) NOT NULL,
        Games_Won INT NOT NULL, 
        Games_Played INT NOT NULL,
        Win_Rate FLOAT NOT NULL);

    <h2>Note:</h2>
        -   The game will not run without the jdbc library. So include the library to Portals-and-Timemachines-code folder in the         IDE and run the project as mentioned below.
        -   The game will run without the mysql connection but the users cannot see or check with the scorecard.
        
Running the Project

-	Right click the desktop project, Run As -> Java Application.
-	Select DesktopLauncher.java

  -or-
  
- Right click DesktopLauncher.java from 'Portals-and-Timemachines-desktop' folder in package explorer, Run As -> Java Application.

Code files:

-	Game.java
-	GameBoard.java
-	GamePiece.java
-	DesktopLauncher.java

<h3>Team - 24 - Members:</h3>    
<ul>
    <li>Ashwin Murthy</li>     
    <li>Balaji Chandrasekaran</li>    
    <li>Christopher Lavoy</li>
    <li>Naveen Kumar Subbiah</li>    
    <li>Sriram Poondi Chinappa</li>
</ul>

