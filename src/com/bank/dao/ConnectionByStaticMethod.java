package com.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionByStaticMethod {
	//public static final String MySQLDRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	public static final String MySQLUSER = "root";
	//public static final String MySQLURL = "jdbc:mysql://node42695-safebanking.cloud.cms500.com/iitr";
	//public static final String MySQLPASSWORD = "MGFiyn97763";
	//public static final String MySQLURL = "jdbc:mysql://node42999-safebanking2.cloud.cms500.com/iitr?useSSL=false";
	//public static final String MySQLPASSWORD = "SLDtdk12731";
	public static final String MySQLURL = "jdbc:mysql://localhost/iitr";
	public static final String MySQLPASSWORD = "1234";

	public static Connection getMySQLConnection() {
		Connection mysqlconnection = null;
		/*try {
			Class.forName(MySQLDRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/

		try {
			mysqlconnection = DriverManager.getConnection(MySQLURL, MySQLUSER, MySQLPASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mysqlconnection;
	}

	public static void closeMySQLConnection(Connection mysqlconnection) {
		if (mysqlconnection != null) {
			try {
				mysqlconnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeMySQLPreaparedStatementConnection(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeMySQLResulsetConnection(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
