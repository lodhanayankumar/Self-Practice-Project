package booklib;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbconnection {

	    private static final String url = "jdbc:mysql://localhost:3306/libmanager";
	    private static final String user = "root";
	    private static final String pass = "Nayan@2001";

	    public static Connection getConnection() throws Exception {

	        Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);
	    }

	}

	
	
