package com.bank.additional;

import java.sql.*;
import java.io.*;

/**
 * @author ujjwa
 *
 */
public class Image_insert {
	public static void main(String[] args) throws Exception {
//		final String MySQLDRIVER_CLASS = "com.mysql.jdbc.Driver";
		final String MySQLURL = "jdbc:mysql://localhost/imageDB";
		final String MySQLUSER = "root";
		final String MySQLPASSWORD = "1234";

		try {
			Connection con = null;
			PreparedStatement pst = null;

			File f = new File("E:\\Sort this out\\web\\practice\\HTML\\is.jpg");
			FileInputStream fis = new FileInputStream(f);

			con = DriverManager.getConnection(MySQLURL, MySQLUSER, MySQLPASSWORD);
			String q = "INSERT INTO info(name,path,image_name,image) VALUES(?,?,?,?,?);";
			pst = con.prepareStatement(q);
			pst.setString(2, "RDJ");
			pst.setString(3, f.getAbsolutePath());
			pst.setString(4, "is");
			pst.setBinaryStream(5, fis);
			int res = pst.executeUpdate();
			if (res == 1) {
				System.out.println("Record Inserted");
			} else
				System.out.println("Failure");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
