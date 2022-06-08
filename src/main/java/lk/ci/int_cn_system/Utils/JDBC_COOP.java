package lk.ci.int_cn_system.Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC_COOP {
	 static String  url="jdbc:mysql://172.20.10.20:3306/coop_sys?zeroDateTimeBehavior=convertToNull";
	 //static String  url="jdbc:mysql://localhost:3306/coop_sys?zeroDateTimeBehavior=convertToNull";


	    public static Connection con() throws Exception {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection c=DriverManager.getConnection(url,"COOP_DB", "ciclsw");
	       
	        return c;
	    }
}
