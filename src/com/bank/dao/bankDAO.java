package com.bank.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.bank.controller.GetTime;
import com.bank.model.*;

/*
From the documentation of the executeUpdate() method:

Returns: either 
(1) the row count for SQL Data Manipulation Language (DML) statements, or
(2) 0 for SQL statements that return nothing

0 seems like a perfectly natural result (i.e. not an error).
*/


public class bankDAO {

	public int retrieveBankDetails(BankDetails up) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select email,fname,lname,uname from bank where acc = ? and success=2");
			pst.setString(1, up.getAcc());

			rs = pst.executeQuery();
			if (rs.next()) {
				result = 1;
				up.setEmail(rs.getString("email"));
				up.setFname(rs.getString("fname"));
				up.setLname(rs.getString("lname"));
				up.setUname(rs.getString("uname"));
			}
			System.out.println("\n\nTHIS is retrieveBankDetails");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public BeneficiaryDetails retrieveBeneficiaryDetails(String benacc, String uname) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		BeneficiaryDetails ben = new BeneficiaryDetails();
		String payeracc = retrieveAcc(uname);
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select * from beneficiary where benacc = ? and payeracc=?");
			pst.setString(1, benacc);
			pst.setString(2, payeracc);

			rs = pst.executeQuery();
			if (rs.next()) {
				ben.setPayeracc(rs.getString("payeracc"));
				ben.setPayername(rs.getString("payername"));
				ben.setBenfname(rs.getString("benfname"));
				ben.setBenlname(rs.getString("benlname"));
				ben.setBenacc(rs.getString("benacc"));
				ben.setBenlimit(rs.getString("benlimit"));
				ben.setBenbankname(rs.getString("benbankname"));
				ben.setBenbranchname(rs.getString("benbranchname"));
				ben.setBendateadded(rs.getString("bendateadded"));
				ben.setBenapproved(rs.getString("benapproved"));
				ben.setBenlasttransaction(rs.getString("benlasttransaction"));
				ben.setBenifsc(rs.getString("benifsc"));
				ben.setApproved(rs.getInt("approved"));
			}
			System.out.println("\nThis is retrieveBeneficiaryDetails");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return ben;
	}

	public int passAuth(String password, String picid, int effectid, int language, String uname) {
		Connection con = null;
		PreparedStatement pst1 = null, pst2 = null;
		int result = 0;
		ResultSet rs1 = null, rs2 = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst1 = con
					.prepareStatement("select password, picid from bank where uname=? and password=sha1(?)");
			pst2 = con.prepareStatement("select effectid, language from picpreference where uname=?");
			pst1.setString(1, uname);
			pst1.setString(2, password);
			pst2.setString(1, uname);
			rs1 = pst1.executeQuery();
			rs2 = pst2.executeQuery();
			if (rs1.next() && rs2.next()) {
				if (rs1.getString(2).equals(picid) && (rs2.getInt(1) == effectid && rs2.getInt(2) == language)) {
					result = 1;
				}
			}
			System.out.println("\n\nTHIS is passAuth");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst1);
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst2);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int updateBeneficiaryApproved(BeneficiaryApproveLogDetails benLog) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"update beneficiary set benapproved=?, benmodified=?, approved=?, "
					+ "message=? where payeracc=? and benacc=? and approved=0 and success=1");

			pst.setString(1, benLog.getLoginid());
			pst.setString(2, benLog.getLoginid());
			pst.setInt(3, benLog.getSuccess()-1);
			pst.setString(4, benLog.getMessage());
			pst.setString(5, benLog.getPayeracc());
			pst.setString(6, benLog.getBenacc());
			result = pst.executeUpdate();
			
			/*
			 * if (result == 0) { System.out.println("Not Updated"); } else {
			 * System.out.println("Successfully Updated"); }
			 */
			System.out.println("\nTHIS is updateBeneficiaryApproved");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}
	
	public int updateBeneficiaryDeleted(BeneficiaryDeleteLogDetails benLog, int deleted) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"update beneficiary set benmodified=?, success=?, message=? "
					+ "where payeracc=? and benacc=? and success=1");

			pst.setString(1, benLog.getLoginid());
			pst.setInt(2, deleted);
			pst.setString(3, benLog.getMessage());
			pst.setString(4, benLog.getPayeracc());
			pst.setString(5, benLog.getBenacc());
			result = pst.executeUpdate();
			
			/*
			 * if (result == 0) { System.out.println("Not Updated"); } else {
			 * System.out.println("Successfully Updated"); }
			 */
			System.out.println("\nTHIS is updateBeneficiaryDeleted");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int updateBenLastTransaction(TransactionDetails tr) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"update beneficiary set benmodified=?, benlasttransaction=?, benlasttransactiondate=?, "
					+ "message=? where payeracc=? and benacc=? and approved=1 and success=1");

			pst.setString(1, tr.getLoginid());
			pst.setString(2, tr.getTrid());
			pst.setString(3, tr.getTransactiondate());
			pst.setString(4, tr.getMessage());
			pst.setString(5, tr.getPayeracc());
			pst.setString(6, tr.getPayeeacc());
			result = pst.executeUpdate();
			
			/*
			 * if (result == 0) { System.out.println("Not Updated"); } else {
			 * System.out.println("Successfully Updated"); }
			 */
			System.out.println("\nTHIS is updateBenLastTransaction");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}
	
	public int updateBankLastLogin(String loginid, String uname, String message) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"update bank set lastlogin=?, message=? where uname=? and success=2");
			pst.setString(1, loginid);
			pst.setString(2, message);
			pst.setString(3, uname);
			result = pst.executeUpdate();

			/*
			 * if (result == 0) { System.out.println("Not Updated"); } else {
			 * System.out.println("Successfully Updated"); }
			 */
			System.out.println("\nTHIS is updateBankLastLogin");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int updateLogout(String loginid, String uname, String logouttime, String message) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"update loginlog set logouttime=?, success=3, message=? where uname=? and loginid=? and success=2");
			pst.setString(1, logouttime);
			pst.setString(2, message);
			pst.setString(3, uname);
			pst.setString(4, loginid);
			result = pst.executeUpdate();

			/*
			 * if (result == 0) { System.out.println("Not Updated"); } else {
			 * System.out.println("Successfully Updated"); }
			 */
			System.out.println("\nTHIS is updateLogout");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}
	
	public int updateAccountBalance(String acc, int balance) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("update account set savings=? where acc=?");
			pst.setInt(1, balance);
			pst.setString(2, acc);
			result = pst.executeUpdate();

			/*
			 * if (result == 0) { System.out.println("Not Updated"); } else {
			 * System.out.println("Successfully Updated"); }
			 */
			System.out.println("\nTHIS is updateAccountBalance");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int updateBankBalance(String uname, int balance) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("update bank set balance=? where uname=? and success=2 and verified=1");
			pst.setInt(1, balance);
			pst.setString(2, uname);
			result = pst.executeUpdate();

			/*
			 * if (result == 0) { System.out.println("Not Updated"); } else {
			 * System.out.println("Successfully Updated"); }
			 */
			System.out.println("\nTHIS is updateBankBalance");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertLoginAttemptDetails(LoginAttemptDetails loginAttempt, int type) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			if(type==0 || type==-1) {
				pst = con.prepareStatement(
						"insert into loginattempt(uname, loginid, attemptpage1, "
						+ "datepage1, success, message) values (?,?,?,?,?,?)");

				pst.setString(1, loginAttempt.getUname());
				pst.setString(2, loginAttempt.getLoginid());
				pst.setInt(3, loginAttempt.getAttemptpage1());
				pst.setString(4, loginAttempt.getDatepage1());
				pst.setInt(5, loginAttempt.getSuccess());
				pst.setString(6, loginAttempt.getMessage());
			}else if(type==1) {
				pst = con.prepareStatement(
						"update loginattempt set loginid=?, attemptpage1=?, "
						+ "datepage1=?, message=? where uname=? and success=1");
				pst.setString(1, loginAttempt.getLoginid());
				pst.setInt(2, loginAttempt.getAttemptpage1());
				pst.setString(3, loginAttempt.getDatepage1());
				pst.setString(4, loginAttempt.getMessage());
				pst.setString(5, loginAttempt.getUname());
			}else if(type==2) {
				pst = con.prepareStatement(
						"update loginattempt set loginid=?, attemptpage1=?, datepage1=?,"
						+ "attemptpage2=?, datepage2=?, message=? where uname=? and success=1");
				pst.setString(1, loginAttempt.getLoginid());
				pst.setInt(2, loginAttempt.getAttemptpage1());
				pst.setString(3, loginAttempt.getDatepage1());
				pst.setInt(4, loginAttempt.getAttemptpage2());
				pst.setString(5, loginAttempt.getDatepage2());
				pst.setString(6, loginAttempt.getMessage());
				pst.setString(7, loginAttempt.getUname());
			}
			result = pst.executeUpdate();
			System.out.println("\nTHIS is insertLoginAttemptDetails");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int retrieveLoginAttemptPage1(String uname) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int attempt = -1;
		System.out.println("\nThis function is retrieveLoginAttemptPage1");
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"select attemptpage1 from loginattempt where uname=?");

			pst.setString(1, uname);

			rs = pst.executeQuery();
			if (rs.next()) {
				attempt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		System.out.println("No. of attempts left for page1: " + attempt);
		return attempt;
	}

	public int retrieveLoginAttemptPage2(String uname) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int attempt = -1;
		System.out.println("\nThis function is retrieveLoginAttemptPage2");
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select attemptpage2 from loginattempt where uname=?");

			pst.setString(1, uname);

			rs = pst.executeQuery();
			if (rs.next()) {
				attempt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		System.out.println("No. of attempts left for page2: " + attempt);
		return attempt;
	}

	public String generateId() {
		Random r = new Random();
		int rand_int = 100000 + r.nextInt(900000);
		return rand_int + "";
	}

	public String generateOTP() {
		Random r = new Random();
		int rand_int = 100000 + r.nextInt(900000);
		return rand_int + "";
	}

	public String retrieveAcc(String uname) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String acc = null;
		System.out.println("This is retrieveAcc");
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("SELECT acc FROM bank WHERE uname=? and success=2;");

			pst.setString(1, uname);

			rs = pst.executeQuery();
			if (rs.next()) {
				acc = rs.getString(1);
			} else {
				System.out.println("Acc not found");
			}
			System.out.println("Acc: " + acc);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return acc;
	}

	public String retrievePicid(String uname) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String picid = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("SELECT picid FROM bank WHERE uname=?;");

			pst.setString(1, uname);

			rs = pst.executeQuery();
			if (rs.next()) {
				picid = rs.getString("picid");
			}
			System.out.println("This is retrievePicid");
			System.out.println("Picid " + picid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return picid;
	}

	public String retrieveName(String acc) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String name = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("SELECT fname,lname FROM bank WHERE acc=? and success=2;");

			pst.setString(1, acc);

			rs = pst.executeQuery();
			if (rs.next()) {
				name = rs.getString("fname") + " " + rs.getString("lname");
			}
			System.out.println("This is retrieveName");
			System.out.println("Name " + name);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return name;
	}

	public int retrieveViewAttempt(String uname) {
		Connection con = null;
		PreparedStatement pst = null;
		int view = -1;
		ResultSet rs = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst = con.prepareStatement(
					"select attemptpage1 from loginattempt where uname=? and success=1");

			pst.setString(1, uname);

			rs = pst.executeQuery();
			if (rs.next()) {
				view = rs.getInt(1);
			}
			System.out.println("\n\nTHIS IS retrieveViewAttempt function");
			System.out.println("Result " + view + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return view;
	}

	public int insertLoginLogDetails(LoginLogDetails loginLog, int type) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			if (type == 0) {
				pst = con.prepareStatement(
						"insert into loginlog (loginid,loginpagetime, uname, code, utime,"
						+ "rctime, attemptpage1, subtime1,success, message) values (?,?,?,?,?,?,?,?,?,?)");

				pst.setString(1, loginLog.getLoginid());
				pst.setString(2, loginLog.getLoginpagetime());
				pst.setString(3, loginLog.getUname());
				pst.setString(4, loginLog.getCode());
				pst.setString(5, loginLog.getUtime());
				pst.setString(6, loginLog.getRctime());
				pst.setInt(7, loginLog.getAttemptpage1());
				pst.setString(8, loginLog.getSubtime1());
				pst.setInt(9, loginLog.getSuccess());
				pst.setString(10, loginLog.getMessage());
				
			} else if (type == 1) {
				pst = con.prepareStatement(
						"insert into loginlog (loginid,loginpagetime, uname, code, utime,"
						+ "rctime, attemptpage1, subtime1, securitypagetime, securityquestime,"
						+ "securityanstime, securityquestion, securityanswer, verifytime,"
						+ "success, message) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				pst.setString(1, loginLog.getLoginid());
				pst.setString(2, loginLog.getLoginpagetime());
				pst.setString(3, loginLog.getUname());
				pst.setString(4, loginLog.getCode());
				pst.setString(5, loginLog.getUtime());
				pst.setString(6, loginLog.getRctime());
				pst.setInt(7, loginLog.getAttemptpage1());
				pst.setString(8, loginLog.getSubtime1());
				pst.setString(9, loginLog.getSecuritypagetime());
				pst.setString(10, loginLog.getSecurityquestime());
				pst.setString(11, loginLog.getSecurityanstime());
				pst.setString(12, loginLog.getSecurityquestion());
				pst.setString(13, loginLog.getSecurityanswer());
				pst.setString(14, loginLog.getVerifytime());
				pst.setInt(15, loginLog.getSuccess());
				pst.setString(16, loginLog.getMessage());
				
			} else if (type == 2) {
				pst = con.prepareStatement(
						"insert into loginlog (loginid, loginpagetime, uname, code, utime, "
						+ "rctime, attemptpage1, subtime1, securitypagetime, securityquestime, "
						+ "securityanstime, securityquestion, securityanswer, verifytime,"
						+ "loginpage2time, pictime, passtime, attemptpage2, picchosen, effectchosen,"
						+ "languagechosen, subtime2, logintime, success, message) "
						+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, loginLog.getLoginid());
				pst.setString(2, loginLog.getLoginpagetime());
				pst.setString(3, loginLog.getUname());
				pst.setString(4, loginLog.getCode());
				pst.setString(5, loginLog.getUtime());
				pst.setString(6, loginLog.getRctime());
				pst.setInt(7, loginLog.getAttemptpage1());
				pst.setString(8, loginLog.getSubtime1());
				pst.setString(9, loginLog.getSecuritypagetime());
				pst.setString(10, loginLog.getSecurityquestime());
				pst.setString(11, loginLog.getSecurityanstime());
				pst.setString(12, loginLog.getSecurityquestion());
				pst.setString(13, loginLog.getSecurityanswer());
				pst.setString(14, loginLog.getVerifytime());
				pst.setString(15, loginLog.getLoginpage2time());
				pst.setString(16, loginLog.getPictime());
				pst.setString(17, loginLog.getPasstime());
				pst.setInt(18, loginLog.getAttemptpage2());
				pst.setString(19, loginLog.getPicchosen());
				pst.setString(20, loginLog.getEffectchosen());
				pst.setString(21, loginLog.getLanguagechosen());
				pst.setString(22, loginLog.getSubtime2());
				pst.setString(23, loginLog.getLogintime());
				pst.setInt(24, loginLog.getSuccess());
				pst.setString(25, loginLog.getMessage());
			}
			result = pst.executeUpdate();
			/*
			 * if (result == 0) { System.out.println("Not inserted"); } else {
			 * System.out.println("Successfully inserted"); }
			 */
			System.out.println("\nTHIS is insertLoginLogDetails type "+type);
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public PicPreferenceDetails retrievePicPreferenceDetails(String uname) {
		Connection con = null;
		PreparedStatement pst = null;
		PicPreferenceDetails pp = new PicPreferenceDetails();
		ResultSet rs = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select * from picpreference where uname=?");
			pst.setString(1, uname);

			rs = pst.executeQuery();

			if (rs.next()) {
				result = 1;
				pp.setUname(uname);
				pp.setCurve(rs.getInt("effectid"));
				pp.setLanguage(rs.getInt("language"));
				pp.setScaleX(rs.getInt("scaleX"));
				pp.setScaleY(rs.getInt("scaleY"));
				pp.setOpacity(rs.getInt("opacity"));
				pp.setRotation(rs.getInt("rotation"));
				pp.setSkewX(rs.getInt("skewX"));
				pp.setSkewY(rs.getInt("skewY"));
			}
			System.out.println("\nTHIS is retrievePicPreferenceDetails");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return pp;
	}

	public BankDetails fetchProfile(String uname) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		BankDetails up = new BankDetails();
		int result = 0;
		try {

			con = ConnectionByStaticMethod.getMySQLConnection();

			pst = con.prepareStatement("select acc, fname, lname, email,"
					+ "address,phone,lastlogin from bank where uname=?");
			pst.setString(1, uname);

			rs = pst.executeQuery();

			if (rs.next()) {
				result = 1;
				up.setAcc(rs.getString("acc"));
				up.setFname(rs.getString("fname"));
				up.setLname(rs.getString("lname"));
				up.setPhone(rs.getString("phone"));
				up.setEmail(rs.getString("email"));
				up.setAddress(rs.getString("address"));
				up.setLastlogin(rs.getString("lastlogin"));
			}
			System.out.println("\nTHIS is fetchProfile");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return up;
	}

	public boolean existAcc(String acc) {
		Connection con = null;
		PreparedStatement pst = null;
		boolean f = false;
		ResultSet rs = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst = con.prepareStatement("select acc from account where acc=?");

			pst.setString(1, acc);

			rs = pst.executeQuery();
			if (rs.next() && (rs.getString(1)).equals(acc)) {
				f = true;
			}
			System.out.println("\n\nTHIS IS existAcc function");
			System.out.println("Result " + f + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return f;
	}
	public boolean registeredAcc(String acc) {
		Connection con = null;
		PreparedStatement pst = null;
		boolean f = false;
		ResultSet rs = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst = con.prepareStatement("select acc from bank where acc=? and success=2;");

			pst.setString(1, acc);

			rs = pst.executeQuery();
			if (rs.next() && (rs.getString(1)).equals(acc)) {
				f = true;
			}
			System.out.println("\n\nTHIS IS registeredAcc function");
			System.out.println("Result " + f + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return f;
	}
	public int alreadyExistUname(String uname) {
		Connection con = null;
		PreparedStatement pst = null;
		int exist=-1;
		ResultSet rs = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select verified from bank where uname=? and success=2");
			pst.setString(1, uname);
			rs = pst.executeQuery();
			if (rs.next()) {
				exist=rs.getInt(1);
			}
			System.out.println("\n\nTHIS IS alreadyExistUname function");
			System.out.println("Result " + exist + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return exist;
	}
	
	public int alreadyExistEmail(String email) {
		Connection con = null;
		PreparedStatement pst = null;
		int exist=0;
		ResultSet rs = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select email from bank where email=? and success=2");
			pst.setString(1, email);
			rs = pst.executeQuery();
			if (rs.next()) {
				exist=1;
			}
			System.out.println("\n\nTHIS IS alreadyExistEmail function");
			System.out.println("Result " + exist + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return exist;
	}
	
	public int alreadyExistPhone(String phone) {
		Connection con = null;
		PreparedStatement pst = null;
		int exist=0;
		ResultSet rs = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select phone from bank where phone=? and success=2");
			pst.setString(1, phone);
			rs = pst.executeQuery();
			if (rs.next()) {
				exist=1;
			}
			System.out.println("\n\nTHIS IS alreadyExistPhone function");
			System.out.println("Result " + exist + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return exist;
	}

	public int alreadyVerifiedAcc(String acc) {
		Connection con = null;
		PreparedStatement pst = null;
		int verified=-1;
		ResultSet rs = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select verified from bank where acc=? and success=2");
			pst.setString(1, acc);
			rs = pst.executeQuery();
			if (rs.next()) {
				verified=rs.getInt(0);
			}
			System.out.println("\n\nTHIS IS alreadyVerifiedAcc function");
			System.out.println("Result " + verified + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return verified;
	}

	public int alreadyApprovedBeneficiary(String payeracc, String benacc) {
		Connection con = null;
		PreparedStatement pst = null;
		int approved = -1;
		ResultSet rs = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con
					.prepareStatement("select approved from beneficiary where payeracc=? and benacc=? and success=1");
			pst.setString(1, payeracc);
			pst.setString(2, benacc);
			rs = pst.executeQuery();
			if (rs.next()) {
				approved = rs.getInt(1);
			}
			System.out.println("\n\nTHIS IS alreadyApprovedBeneficiary function");
			System.out.println("Result " + approved + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return approved;
	}

	public String checkBlockedAccount(String acc) {
		Connection con = null;
		PreparedStatement pst = null;
		String f = "false";
		ResultSet rs = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select flag from bank where acc=? and success=2 and verified=1");
			pst.setString(1, acc);
			rs = pst.executeQuery();
			if (rs.next())
				f = rs.getString("flag");
			System.out.println("\n\nTHIS IS checkBlockedAccount function");
			System.out.println("Result " + f + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return f;
	}

	public int insertBeneficiaryDetails(String uname, BeneficiaryDetails ben) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"insert into beneficiary(loginid, payeracc, payername, benfname,"
					+ " benlname, benacc, benlimit, benbankname, benbranchname, benifsc,"
					+ " bendateadded, benapproved, benmodified, benlasttransaction, benlasttransactiondate, approved,"
					+ " success, message) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pst.setString(1, ben.getLoginid());
			pst.setString(2, ben.getPayeracc());
			pst.setString(3, ben.getPayername());
			pst.setString(4, ben.getBenfname());
			pst.setString(5, ben.getBenlname());
			pst.setString(6, ben.getBenacc());
			pst.setString(7, ben.getBenlimit());
			pst.setString(8, ben.getBenbankname());
			pst.setString(9, ben.getBenbranchname());
			pst.setString(10, ben.getBenifsc());
			pst.setString(11, ben.getBendateadded());
			pst.setString(12, ben.getBenapproved());
			pst.setString(13, ben.getBenmodified());
			pst.setString(14, ben.getBenlasttransaction());
			pst.setString(15, ben.getBenlasttransactiondate());
			pst.setInt(16, ben.getApproved());
			pst.setInt(17, ben.getSuccess());
			pst.setString(18, ben.getMessage());

			result = pst.executeUpdate();
			System.out.println("\n\nTHIS IS insertBeneficiaryDetails function");
			System.out.println("Result " + result + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertBeneficiaryAddLogDetails(BeneficiaryAddLogDetails benLog) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"insert into benaddlog(loginid, payeracc, benacc, benaddpagetime,"
					+ " ftime, ltime, acctime, cnfacctime, limittime, banknametime, "
					+ "branchnametime, ifsctime, chktime, resettime, subtime, success,"
					+ " message) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pst.setString(1, benLog.getLoginid());
			pst.setString(2, benLog.getPayeracc());
			pst.setString(3, benLog.getBenacc());
			pst.setString(4, benLog.getBenaddpagetime());
			pst.setString(5, benLog.getFtime());
			pst.setString(6, benLog.getLtime());
			pst.setString(7, benLog.getAcctime());
			pst.setString(8, benLog.getCnfacctime());
			pst.setString(9, benLog.getLimittime());
			pst.setString(10, benLog.getBanknametime());
			pst.setString(11, benLog.getBranchnametime());
			pst.setString(12, benLog.getIfsctime());
			pst.setString(13, benLog.getChktime());
			pst.setString(14, benLog.getResettime());
			pst.setString(15, benLog.getSubtime());
			pst.setInt(16, benLog.getSuccess());
			pst.setString(17, benLog.getMessage());

			result = pst.executeUpdate();
			System.out.println("\n\nTHIS IS insertBeneficiaryAddLogDetails function");
			System.out.println("Result " + result + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertBeneficiaryApproveLogDetails(BeneficiaryApproveLogDetails benLog, int type) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			if (type == 0) {
				pst = con.prepareStatement(
						"insert into benapprovelog(loginid, payeracc, benacc, "
						+ "benapprovepagetime, subtime1, success, message) values (?,?,?,?,?,?,?)");

				pst.setString(1, benLog.getLoginid());
				pst.setString(2, benLog.getPayeracc());
				pst.setString(3, benLog.getBenacc());
				pst.setString(4, benLog.getBenapprovepagetime());
				pst.setString(5, benLog.getSubtime1());
				pst.setInt(6, benLog.getSuccess());
				pst.setString(7, benLog.getMessage());
			} else if (type == 1) {
				pst = con.prepareStatement(
						"insert into benapprovelog(loginid, payeracc, benacc, "
						+ "benapprovepagetime, subtime1, benemailapprovepagetime, "
						+ "otptime, otpuser, otpemail, subtime2, benapprovetime, "
						+ "attempt, success, message) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, benLog.getLoginid());
				pst.setString(2, benLog.getPayeracc());
				pst.setString(3, benLog.getBenacc());
				pst.setString(4, benLog.getBenapprovepagetime());
				pst.setString(5, benLog.getSubtime1());
				pst.setString(6, benLog.getBenemailapprovepagetime());
				pst.setString(7, benLog.getOtptime());
				pst.setString(8, benLog.getOTPUser());
				pst.setString(9, benLog.getOTPEmail());
				pst.setString(10, benLog.getSubtime2());
				pst.setString(11, benLog.getBenapprovetime());
				pst.setInt(12, benLog.getAttempt());
				pst.setInt(13, benLog.getSuccess());
				pst.setString(14, benLog.getMessage());
			}
			result = pst.executeUpdate();
			/*
			 * if (result == 0) { System.out.println("Not inserted"); } else {
			 * System.out.println("Successfully inserted"); }
			 */
			System.out.println("This is insertBeneficiaryApproveLogDetails");
			System.out.println("Result= " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}
	
	public int insertBeneficiaryDeleteLogDetails(BeneficiaryDeleteLogDetails benLog, int type) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			if (type == 0) {
				pst = con.prepareStatement(
						"insert into bendeletelog(loginid, payeracc, benacc, bendeletepagetime,"
						+ "subtime1, success, message) values (?,?,?,?,?,?,?)");

				pst.setString(1, benLog.getLoginid());
				pst.setString(2, benLog.getPayeracc());
				pst.setString(3, benLog.getBenacc());
				pst.setString(4, benLog.getBendeletepagetime());
				pst.setString(5, benLog.getSubtime1());
				pst.setInt(6, benLog.getSuccess());
				pst.setString(7, benLog.getMessage());
			} else if (type == 1) {
				pst = con.prepareStatement(
						"insert into bendeletelog(loginid, payeracc, benacc, bendeletepagetime, "
						+ "subtime1, benemaildeletepagetime, otptime, otpuser, otpemail, subtime2,"
						+ " confirmtime, bendeletetime, attempt, success, message) "
						+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, benLog.getLoginid());
				pst.setString(2, benLog.getPayeracc());
				pst.setString(3, benLog.getBenacc());
				pst.setString(4, benLog.getBendeletepagetime());
				pst.setString(5, benLog.getSubtime1());
				pst.setString(6, benLog.getBenemaildeletepagetime());
				pst.setString(7, benLog.getOtptime());
				pst.setString(8, benLog.getOTPUser());
				pst.setString(9, benLog.getOTPEmail());
				pst.setString(10, benLog.getSubtime2());
				pst.setString(11, benLog.getConfirmtime());
				pst.setString(12, benLog.getBendeletetime());
				pst.setInt(13, benLog.getAttempt());
				pst.setInt(14, benLog.getSuccess());
				pst.setString(15, benLog.getMessage());
			}
			result = pst.executeUpdate();
			/*
			 * if (result == 0) { System.out.println("Not inserted"); } else {
			 * System.out.println("Successfully inserted"); }
			 */
			System.out.println("This is insertBeneficiaryApproveLogDetails");
			System.out.println("Result= " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public List<Object> fetchBeneficiary(String payeracc, String action) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		con = ConnectionByStaticMethod.getMySQLConnection();
		List<Object> temp = new ArrayList<Object>();;
		List<BeneficiaryDetails> benList = new ArrayList<BeneficiaryDetails>();
		String ex = retrieveEmail(payeracc, "acc");
		try {
			if (action.equals("delete") || action.equals("show")) {
				pst = con.prepareStatement(
						"select * from beneficiary where payeracc=? and success=1"
						+ " order by approved, bendateadded desc");
			} else if (action.equals("approve")) {
				pst = con.prepareStatement(
						"select * from beneficiary where payeracc=? and approved=0"
						+ " and success=1 order by bendateadded desc");
			} else if (action.equals("transfer")) {
				pst = con.prepareStatement(
						"select * from beneficiary, bank where payeracc=? and verified=1 "
						+ "and approved=1 and beneficiary.success=1 and bank.success=2 "
						+ "and beneficiary.benacc=bank.acc and bank.flag=? "
						+ "order by bendateadded desc");
				pst.setString(2, "true");
			}
			pst.setString(1, payeracc);
			rs = pst.executeQuery();
			while (rs.next()) {
				// CREATE Beneficiary DETAIL OBJECT
				BeneficiaryDetails ben = new BeneficiaryDetails();

				ben.setLoginid(rs.getString("loginid"));
				ben.setPayeracc(rs.getString("payeracc"));
				ben.setPayername(rs.getString("payername"));
				ben.setBenfname(rs.getString("benfname"));
				ben.setBenlname(rs.getString("benlname"));
				ben.setBenacc(rs.getString("benacc"));
				ben.setBenlimit(rs.getString("benlimit"));
				ben.setBenbankname(rs.getString("benbankname"));
				ben.setBenbranchname(rs.getString("benbranchname"));
				ben.setBenifsc(rs.getString("benifsc"));
				ben.setBendateadded(rs.getString("bendateadded"));
				ben.setBenapproved(rs.getString("benapproved"));
				ben.setBenmodified(rs.getString("benmodified"));
				ben.setBenlasttransaction(rs.getString("benlasttransaction"));
				ben.setBenlasttransactiondate(rs.getString("benlasttransactiondate"));
				ben.setApproved(rs.getInt("approved"));
				ben.setSuccess(rs.getInt("success"));

				// ADDED TO THE ARRAYLIST
				benList.add(ben);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		temp.add(benList);
		temp.add(ex);
		return temp;
	}

	public int insertApproveBeneficiary(String uname, String benacc) {
		Connection con = null;
		PreparedStatement pst = null;
		int confirm = 0;
		GetTime obj = new GetTime();
		String payeracc = retrieveAcc(uname);
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"update beneficiary set bendateapproved=?,approved=1 where benacc=? and payeracc=?");
			pst.setString(1, obj.now());
			pst.setString(2, benacc);
			pst.setString(3, payeracc);
			confirm = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return confirm;
	}

	public int insertDeleteBeneficiary(String uname, String benacc) {
		Connection con = null;
		PreparedStatement pst = null;
		int confirm = 0;
		String payeracc = retrieveAcc(uname);
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("delete from beneficiary where benacc=? and payeracc=?");
			pst.setString(1, benacc);
			pst.setString(2, payeracc);
			confirm = pst.executeUpdate();
			System.out.println("This is insertDeleteBeneficiary. This might be wrong. i don't remember this function.");
			System.out.println("Result: "+confirm);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return confirm;
	}

	public List<TransactionDetails> fetchTransactionDetailsList(String acc) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<TransactionDetails> trList = new ArrayList<TransactionDetails>();
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst = con.prepareStatement("select * from transaction where (payeracc=? or payeeacc=?) and success=3");
			pst.setString(1, acc);
			pst.setString(2, acc);
			rs = pst.executeQuery();

			while (rs.next()) {
				// CREATE CUSTOMER DETAIL OBJECT
				TransactionDetails tr = new TransactionDetails();

				// FETCH FROM RESULTSET & STORE VALUE WITIN OBJECT
				tr.setTrid(rs.getString("trid"));
				tr.setLoginid(rs.getString("loginid"));
				tr.setPayeracc(rs.getString("payeracc"));
				tr.setPayeeacc(rs.getString("payeeacc"));
				tr.setPayername(rs.getString("payername"));
				tr.setPayeename(rs.getString("payeename"));
				tr.setTransactiondate(rs.getString("transactiondate"));
				tr.setType(rs.getString("type"));
				tr.setAmount(rs.getInt("amount"));
				tr.setRemBalance(rs.getInt("remBalance"));

				// ADDED TO THE ARRAYLIST
				trList.add(tr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return trList;
	}
	
	public int verifySecurity(String uname, String securityquestion, String securityanswer) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		System.out.println("Result: "+uname);
		System.out.println("Result: "+securityquestion);
		System.out.println("Result: "+securityanswer);
		
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst = con.prepareStatement("select securityquestion, securityanswer from bank"
					+ " where uname=? and success=2 and verified=1");

			pst.setString(1, uname);
			rs = pst.executeQuery();
			if (rs.next() && securityquestion.equals(rs.getString(1)) 
					&& securityanswer.equalsIgnoreCase(rs.getString(2))) {
				result=1;
			}
			System.out.println("This is verifySecurity");
			System.out.println("Result: "+result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int retrieveBalance(String acc) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int savings = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst = con.prepareStatement("select savings from account where acc=?");

			pst.setString(1, acc);

			rs = pst.executeQuery();
			while (rs.next()) {
				savings = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return savings;
	}
	
	public String encodeEmail(String email) {
		int i, len, x=email.lastIndexOf('.');
		len=email.length();
		String ex="";
		for(i=0;i<len;i++){
			if(i<3){
				ex+=email.charAt(i);
			}else if(i<x){
				ex+="X";
			}
			else{
				ex+=email.charAt(i);
			}
		}
		return ex;
	}

	public int updatePassword(BankDetails up) {
		Connection con = null;
		PreparedStatement pst = null;
		int result=0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst = con.prepareStatement("update bank set password=sha1(?), "
					+ "securityquestion=?, securityanswer=?, bankmodified=?, "
					+ "message=? where acc=? and success=2");

			pst.setString(1, up.getPassword());
			pst.setString(2, up.getSecurityquestion());
			pst.setString(3, up.getSecurityanswer());
			pst.setString(4, up.getBankmodified());
			pst.setString(5, up.getMessage());
			pst.setString(6, up.getAcc());

			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertBlockUser(String uname) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst = con.prepareStatement("update bank set flag='false' where uname=? and success=2");

			pst.setString(1, uname);

			result = pst.executeUpdate();
			System.out.println("This is insertBlockUser");
			System.out.println("Result: "+result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertTransactionDetails(TransactionDetails tr) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst = con.prepareStatement("insert into transaction(trid, loginid, payeracc, payeeacc, payername, "
					+ "payeename, transactiondate, type, amount, rembalance, success, message) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?)");
			
			pst.setString(1, tr.getTrid());
			pst.setString(2, tr.getLoginid());
			pst.setString(3, tr.getPayeracc());
			pst.setString(4, tr.getPayeeacc());
			pst.setString(5, tr.getPayername());
			pst.setString(6, tr.getPayeename());
			pst.setString(7, tr.getTransactiondate());
			pst.setString(8, tr.getType());
			pst.setInt(9, tr.getAmount());
			pst.setInt(10, tr.getRemBalance());
			pst.setInt(11, tr.getSuccess());
			pst.setString(12, tr.getMessage());

			result = pst.executeUpdate();
			/*if (confirmation == 1)
				System.out.println("Successfully inserted");
			else
				System.out.println("Not Inserted");*/
			System.out.println("This is insertTransactionDetails");
			System.out.println("Result: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertTransactionConfirmLogDetails(TransactionConfirmLogDetails trconfirmLog, int type) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			if(type==0) {
				pst = con.prepareStatement("insert into trconfirmlog(trid, fundtransferpage2time, amounttime, subtime2, "
						+ "success, message)  values (?,?,?,?,?,?)");
				pst.setString(1, trconfirmLog.getTrid());
				pst.setString(2, trconfirmLog.getFundtransferpage2time());
				pst.setString(3, trconfirmLog.getAmounttime());
				pst.setString(4, trconfirmLog.getSubtime2());
				pst.setInt(5, trconfirmLog.getSuccess());
				pst.setString(6, trconfirmLog.getMessage());
			}else if(type ==1) {
				pst = con.prepareStatement("insert into trconfirmlog(trid, fundtransferpage2time, amounttime, subtime2, "
						+ "confirmpagetime, otptime, otpemail, otpuser, subtime3, "
						+ "success, message) values (?,?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, trconfirmLog.getTrid());
				pst.setString(2, trconfirmLog.getFundtransferpage2time());
				pst.setString(3, trconfirmLog.getAmounttime());
				pst.setString(4, trconfirmLog.getSubtime2());
				pst.setString(5, trconfirmLog.getConfirmpagetime());
				pst.setString(6, trconfirmLog.getOTPTime());
				pst.setString(7, trconfirmLog.getOTPEmail());
				pst.setString(8, trconfirmLog.getOTPUser());
				pst.setString(9, trconfirmLog.getSubtime3());
				pst.setInt(10, trconfirmLog.getSuccess());
				pst.setString(11, trconfirmLog.getMessage());
			}
			result = pst.executeUpdate();
			/*if (confirmation == 1)
				System.out.println("Successfully inserted");
			else
				System.out.println("Not Inserted");*/
			System.out.println("This is insertTransactionConfirmLogDetails");
			System.out.println("Result= "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertTransactionBenLogDetails(TransactionBenLogDetails trLogBen) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("insert into trbenlog(trid, fundtransferbenpagetime, subtime1, "
					+ "success, message) values (?,?,?,?,?)");
			pst.setString(1, trLogBen.getTrid());
			pst.setString(2, trLogBen.getFundtransferbenpagetime());
			pst.setString(3, trLogBen.getSubtime1());
			pst.setInt(4, trLogBen.getSuccess());
			pst.setString(5, trLogBen.getMessage());
			result = pst.executeUpdate();
			/*if (confirmation == 1)
				System.out.println("Successfully inserted");
			else
				System.out.println("Not Inserted");*/
			System.out.println("This is insertTransactionBenLogDetails");
			System.out.println("Result: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	/*public int insertTransactionLogNewUserDetails(TransactionLogNewUserDetails trLogNewUser) {
		Connection con = null;
		PreparedStatement pst = null;
		int confirmation = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst = con
					.prepareStatement("insert into trlognewuser values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pst.setString(1, trLogNewUser.getPayeracc());
			pst.setString(2, trLogNewUser.getPayeeacc());
			pst.setString(3, trLogNewUser.getPayeename());
			pst.setString(4, trLogNewUser.getTransactiondate());
			pst.setString(5, trLogNewUser.getTransactionid());
			pst.setString(6, trLogNewUser.getFundtransfernewuserpagetime());
			pst.setString(7, trLogNewUser.getFtime());
			pst.setString(8, trLogNewUser.getLtime());
			pst.setString(9, trLogNewUser.getAcctime());
			pst.setString(10, trLogNewUser.getCnfacctime());
			pst.setString(11, trLogNewUser.getBanknametime());
			pst.setString(12, trLogNewUser.getBranchnametime());
			pst.setString(13, trLogNewUser.getIfsctime());
			pst.setString(14, trLogNewUser.getIfsctime());
			pst.setString(15, trLogNewUser.getChktime());
			pst.setString(16, trLogNewUser.getResettime());
			pst.setString(17, trLogNewUser.getSubtime1());
			pst.setInt(18, trLogNewUser.getSuccess());
			pst.setString(19, trLogNewUser.getError());
			confirmation = pst.executeUpdate();
			if (confirmation == 1)
				System.out.println("Successfully inserted");
			else
				System.out.println("Not Inserted");
			System.out.println("This is insertTransactionLogNewUserDetails");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return confirmation;
	}*/

	public String generateAccountNo() {
		String rand = null;
		Random r = new Random();
		rand = Integer.toString(100000 + r.nextInt(900000)) + Integer.toString(1000 + r.nextInt(9000));
		return rand;
	}

	public String generatePassword() {
		String rand = "";
		Random r = new Random();
		int i = 10, len;
		String ALPHA_NUMERIC_STRING = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz123456789";
		len = ALPHA_NUMERIC_STRING.length();
		while (i-- > 0) {
			rand += ALPHA_NUMERIC_STRING.charAt(r.nextInt(len));
		}
		return rand;
	}

	public int insertAccountDetails(AccountDetails account) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"insert into account(acc,savings,regimagestring,loginimagestring,"
					+ "counthide,countshow,datecreated,datemodified) values (?,?,?,?,?,?,?,?)");

			pst.setString(1, account.getAcc());
			pst.setInt(2, account.getSavings());
			pst.setString(3, account.getRegimagestring());
			pst.setString(4, account.getLoginimagestring());
			pst.setInt(5, account.getCounthide());
			pst.setInt(6, account.getCountshow());
			pst.setString(7, account.getDatecreated());
			pst.setString(8, account.getDatemodified());

			result = pst.executeUpdate();
			/*
			 * if (result == 0) { System.out.println("Not inserted"); } else {
			 * System.out.println("Successfully inserted"); }
			 */
			System.out.println("This is insertAccountDetails");
			System.out.println("Result " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertBankDetails(BankDetails bank, int type) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			if(type==0) {
				pst = con.prepareStatement(
						"insert into bank(regid, uname, acc, fname, lname, email, phone, address,"
						+ "datecreated, success, message) values (?,?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, bank.getRegid());
				pst.setString(2, bank.getUname());
				pst.setString(3, bank.getAcc());
				pst.setString(4, bank.getFname());
				pst.setString(5, bank.getLname());
				pst.setString(6, bank.getEmail());
				pst.setString(7, bank.getPhone());
				pst.setString(8, bank.getAddress());
				pst.setString(9, bank.getDatecreated());
				pst.setInt(10, bank.getSuccess());
				pst.setString(11, bank.getMessage());
			}else {
				pst = con.prepareStatement(
						"insert into bank(regid, uname, acc, fname, lname, email, phone, address, picid,"
						+ "password, balance, datecreated, success, message) values (?,?,?,?,?,?,?,?,?,sha1(?),?,?,?,?)");
				pst.setString(1, bank.getRegid());
				pst.setString(2, bank.getUname());
				pst.setString(3, bank.getAcc());
				pst.setString(4, bank.getFname());
				pst.setString(5, bank.getLname());
				pst.setString(6, bank.getEmail());
				pst.setString(7, bank.getPhone());
				pst.setString(8, bank.getAddress());
				pst.setString(9, bank.getPicid());
				pst.setString(10, bank.getPassword());
				pst.setInt(11, bank.getBalance());
				pst.setString(12, bank.getDatecreated());
				pst.setInt(13, bank.getSuccess());
				pst.setString(14, bank.getMessage());
				
				/*pst = con.prepareStatement(
						"update bank set picid=?, password=sha1(?), balance=?, success=?, message=?"
						+" where acc=? and success=1 and regid=(Select regid from bank where acc=? and regid=? "
						+ "order by regid desc limit 1)");
				pst.setString(1, bank.getPicid());
				pst.setString(2, bank.getPassword());
				pst.setInt(3, bank.getBalance());
				pst.setInt(4, bank.getSuccess());
				pst.setString(5, bank.getMessage());
				pst.setString(6, bank.getAcc());
				pst.setString(7, bank.getAcc());
				pst.setString(8, bank.getRegid());*/
			}
			result = pst.executeUpdate();
			/*
			 * if (result == 0) { System.out.println("Not inserted"); } else {
			 * System.out.println("Successfully inserted"); }
			 */
			System.out.println("This is insertBankDetails with type = "+type);
			System.out.println("Result= " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertPicPreferenceDetails(PicPreferenceDetails pre) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("insert into picpreference(uname,effectid,language) values (?,?,?)");

			pst.setString(1, pre.getUname());
			pst.setInt(2, pre.getEffectid());
			pst.setInt(3, pre.getLanguage());

			result = pst.executeUpdate();
			/*
			 * if (result == 0) { System.out.println("Not inserted"); } else {
			 * System.out.println("Successfully inserted"); }
			 */
			System.out.println("This is insertPicPreferenceDetails");
			System.out.println("Result= " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertRegistrationAddLogDetails(RegistrationAddLogDetails regLog, int type) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			if (type == 0) {
				pst = con.prepareStatement(
						"insert into regaddlog(regid, acc, uname, regpage1time, utime, ftime, ltime, acctime,"
						+ "emailtime, phonetime, addresstime, chktime, resettime, subtime1, "
						+ "success, message)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, regLog.getRegid());
				pst.setString(2, regLog.getAcc());
				pst.setString(3, regLog.getUname());
				pst.setString(4, regLog.getRegpage1time());
				pst.setString(5, regLog.getUtime());
				pst.setString(6, regLog.getFtime());
				pst.setString(7, regLog.getLtime());
				pst.setString(8, regLog.getAcctime());
				pst.setString(9, regLog.getEmailtime());
				pst.setString(10, regLog.getPhonetime());
				pst.setString(11, regLog.getAddresstime());
				pst.setString(12, regLog.getChktime());
				pst.setString(13, regLog.getResettime());
				pst.setString(14, regLog.getSubtime1());
				pst.setInt(15, regLog.getSuccess());
				pst.setString(16, regLog.getMessage());
			} else if (type == 1) {
				pst = con.prepareStatement(
						"insert into regaddlog(regid, acc, uname, regpage1time, utime, ftime, ltime, acctime,"
						+ "emailtime, phonetime, addresstime, chktime, resettime, subtime1, "
						+ "regpage2time, pictime, effecttime, picid, effectid, language, subtime2, "
						+ "attempt,success,message) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, regLog.getRegid());
				pst.setString(2, regLog.getAcc());
				pst.setString(3, regLog.getUname());
				pst.setString(4, regLog.getRegpage1time());
				pst.setString(5, regLog.getUtime());
				pst.setString(6, regLog.getFtime());
				pst.setString(7, regLog.getLtime());
				pst.setString(8, regLog.getAcctime());
				pst.setString(9, regLog.getEmailtime());
				pst.setString(10, regLog.getPhonetime());
				pst.setString(11, regLog.getAddresstime());
				pst.setString(12, regLog.getChktime());
				pst.setString(13, regLog.getResettime());
				pst.setString(14, regLog.getSubtime1());
				pst.setString(15, regLog.getRegpage2time());
				pst.setString(16, regLog.getPictime());
				pst.setString(17, regLog.getEffecttime());
				pst.setString(18, regLog.getPicid());
				pst.setInt(19, regLog.getEffectid());
				pst.setInt(20, regLog.getLanguage());
				pst.setString(21, regLog.getSubtime2());
				pst.setInt(22, regLog.getAttempt());
				pst.setInt(23, regLog.getSuccess());
				pst.setString(24, regLog.getMessage());
				
				/*pst=con.prepareStatement("update regaddlog set regpage2time=?, pictime=?, effecttime=?, "
						+ "picid=?, effectid=?, language=?, subtime2=?, attempt=?, success=?, message=? where acc=?"
						+ " and regid=? and success=1 and count=(select count from regaddlog where acc=? and regid=?"
						+ " and success=1 order by count desc limit 1)");
				pst.setString(1, regLog.getRegpage2time());
				pst.setString(2, regLog.getPictime());
				pst.setString(3, regLog.getEffecttime());
				pst.setString(4, regLog.getPicid());
				pst.setInt(5, regLog.getEffectid());
				pst.setInt(6, regLog.getLanguage());
				pst.setString(7, regLog.getSubtime2());
				pst.setInt(8, regLog.getAttempt());
				pst.setInt(9, regLog.getSuccess());
				pst.setString(10, regLog.getMessage());
				pst.setString(11, regLog.getAcc());
				pst.setString(12, regLog.getRegid());
				pst.setString(13, regLog.getAcc());
				pst.setString(14, regLog.getRegid());*/
			}
			result = pst.executeUpdate();
			/*
			 * if (result == 0) { System.out.println("Not inserted"); } else {
			 * System.out.println("Successfully inserted"); }
			 */
			System.out.println("This is insertRegistrationAddLogDetails with type ="+type);
			System.out.println("Result= " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}
	
	public int insertRegistrationVerifyLogDetails(RegistrationVerifyLogDetails regLog, int type) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			if (type == 0) {
				pst = con.prepareStatement(
						"INSERT INTO regverifylog (regid, acc, uname, emailverifypagetime, passemail, passuser, oldpasstime, "
						+ "verifytime, attempt, success, message) values (?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, regLog.getRegid());
				pst.setString(2, regLog.getAcc());
				pst.setString(3, regLog.getUname());
				pst.setString(4, regLog.getEmailverifypagetime());
				pst.setString(5, regLog.getPassemail());				
				pst.setString(6, regLog.getPassuser());
				pst.setString(7, regLog.getOldpasstime());				
				pst.setString(8, regLog.getVerifytime());
				pst.setInt(9, regLog.getAttempt());
				pst.setInt(10, regLog.getSuccess());
				pst.setString(11, regLog.getMessage());
			}else if (type == 1) {
				pst = con.prepareStatement(
						"INSERT INTO regverifylog (regid, acc, uname, emailverifypagetime, passemail, passuser, oldpasstime, "
						+ "verifytime, proceedtime, newpasstime, cnfpasstime, securityquestime, securityanstime, "
						+ "subtime, attempt, success, message) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, regLog.getRegid());
				pst.setString(2, regLog.getAcc());
				pst.setString(3, regLog.getUname());
				pst.setString(4, regLog.getEmailverifypagetime());
				pst.setString(5, regLog.getPassemail());				
				pst.setString(6, regLog.getPassuser());
				pst.setString(7, regLog.getOldpasstime());				
				pst.setString(8, regLog.getVerifytime());
				pst.setString(9, regLog.getProceedtime());
				pst.setString(10, regLog.getNewpasstime());
				pst.setString(11, regLog.getCnfpasstime());
				pst.setString(12, regLog.getSecurityquestime());				
				pst.setString(13, regLog.getSecurityanstime());
				pst.setString(14, regLog.getSubtime());
				pst.setInt(15, regLog.getAttempt());
				pst.setInt(16, regLog.getSuccess());
				pst.setString(17, regLog.getMessage());
			}
			result = pst.executeUpdate();
			if (result == 0) {
				System.out.println("Not inserted");
			} else {
				System.out.println("Successfully inserted");
			}
			 
			System.out.println("This is insertRegistrationVerifyLogDetails");
			System.out.println("Result= " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertRegisteringLogDetails(ArrayList<RegisteringLogDetails> regList) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			for (int i = 0; i < regList.size(); i++) {
				RegisteringLogDetails registeringLog = (RegisteringLogDetails) regList.get(i);
				pst = con.prepareStatement(
						"insert into registeringlog(regid, uname, date, action, message) values (?,?,?,?,?)");

				pst.setString(1, registeringLog.getRegid());
				pst.setString(2, registeringLog.getUname());
				pst.setString(3, registeringLog.getDate());
				pst.setString(4, registeringLog.getAction());
				pst.setString(5, registeringLog.getMessage());

				result += pst.executeUpdate();
			}

			System.out.println("This is insertRegisteringLogDetails");
			if (result != regList.size()) {
				System.out.println("Not inserted");
			} else {
				System.out.println("Successfully inserted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertLoggingInLogDetails(ArrayList<LoggingInLogDetails> LoggingInLogList) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			for (int i = 0; i < LoggingInLogList.size(); i++) {
				LoggingInLogDetails loggingInLog = (LoggingInLogDetails) LoggingInLogList.get(i);
				pst = con.prepareStatement(
						"insert into logginginlog(loginid, uname, date, action, message) values (?,?,?,?,?)");

				pst.setString(1, loggingInLog.getLoginid());
				pst.setString(2, loggingInLog.getUname());
				pst.setString(3, loggingInLog.getDate());
				pst.setString(4, loggingInLog.getAction());
				pst.setString(5, loggingInLog.getMessage());

				result += pst.executeUpdate();
			}

			System.out.println("This is insertLoggingInLogDetails");
			if (result != LoggingInLogList.size()) {
				System.out.println("Not inserted");
			} else {
				System.out.println("Successfully inserted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertLoggedInLogDetails(ArrayList<LoggedInLogDetails> loggedInLogList) {
		Connection con = null;
		PreparedStatement pst = null;
		LoggedInLogDetails loggedInLog;
		int result = 0;

		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			for (int i = 0; i < loggedInLogList.size(); i++) {
				loggedInLog = (LoggedInLogDetails) loggedInLogList.get(i);
				pst = con.prepareStatement(
						"insert into loggedinlog(loginid, uname, date, action, message) values (?,?,?,?,?)");

				pst.setString(1, loggedInLog.getLoginid());
				pst.setString(2, loggedInLog.getUname());
				pst.setString(3, loggedInLog.getDate());
				pst.setString(4, loggedInLog.getAction());
				pst.setString(5, loggedInLog.getMessage());

				result += pst.executeUpdate();
			}

			System.out.println("This is insertloggedInLogDetails");
			if (result != loggedInLogList.size()) {
				System.out.println("Not inserted");
			} else {
				System.out.println("Successfully inserted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertEmailVerifyAttempt(EmailVerifyAttemptDetails emailAttempt) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con
					.prepareStatement("insert into emailverifyattempt(uname,regid,date,attempt) values (?,?,?,?)");

			pst.setString(1, emailAttempt.getUname());
			pst.setString(2, emailAttempt.getRegid());
			pst.setString(3, emailAttempt.getDate());
			pst.setInt(4, emailAttempt.getAttempt());

			result = pst.executeUpdate();
			/*
			 * if (result == 0) { System.out.println("Not inserted"); } else {
			 * System.out.println("Successfully inserted"); }
			 */
			System.out.println("This is insertEmailVerifyAttempt");
			System.out.println("Result= " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int updateVerifyEmail(String acc, String regid, String message) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"update bank set verified=1, bankverified=?, bankmodified=?, message=? where acc=? and success=2");

			pst.setString(1, regid);
			pst.setString(2, regid);
			pst.setString(3, message);
			pst.setString(4, acc);

			result = pst.executeUpdate();
			/*
			 * if (result == 0) { System.out.println("Not inserted"); } else {
			 * System.out.println("Successfully inserted"); }
			 */
			System.out.println("This is updateVerifyEmail");
			System.out.println("Result= " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertChangePassword(String uname, String newpass, String securityquestion, String securityanswer,
			String datemodified) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"update bank set password=sha1(?),securityquestion=?,securityanswer=?,datemodified=? where uname=?");

			pst.setString(1, newpass);
			pst.setString(2, securityquestion);
			pst.setString(3, securityanswer);
			pst.setString(4, datemodified);
			pst.setString(5, uname);

			result = pst.executeUpdate();
			/*
			 * if (result == 0) { System.out.println("Not inserted"); } else {
			 * System.out.println("Successfully inserted"); }
			 */
			System.out.println("This is insertChangePassword");
			System.out.println("Result= " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public String retrieveEmail(String str, String type) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String email = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			if(type.equals("uname")) {
				pst = con.prepareStatement("SELECT email FROM bank WHERE uname=? and success=2;");
			}else {
				pst = con.prepareStatement("SELECT email FROM bank WHERE acc=? and success=2;");
			}
			pst.setString(1, str);

			rs = pst.executeQuery();
			if (rs.next()) {
				email = rs.getString(1);
			} else {
				System.out.println("email not found");
			}
			System.out.println("This is retrieveEmail function");
			System.out.println("email: " + email);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return email;
	}
	
	/*public RegistrationVerifyLogDetails retrieveRegistrationVerifyLogDetails(String uname) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		RegistrationAddLogDetails regLog = new RegistrationAddLogDetails();
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"select acc,uname,utime,ftime,ltime,phonetime,emailtime,addresstime,chktime,subtime1,"
					+ "subtime2,pictime,effecttime,attempt from regLog where uname=?");
			pst.setString(1, uname);

			rs = pst.executeQuery();
			if (rs.next()) {
				regLog.setRegid(rs.getString("regid"));
				regLog.setAcc(rs.getString("acc"));
				regLog.setUname(rs.getString("uname"));
				regLog.setRegpage1time(rs.getString("regpage1time"));
				regLog.setUtime(rs.getString("utime"));
				regLog.setFtime(rs.getString("ftime"));
				regLog.setLtime(rs.getString("ltime"));
				regLog.setAcctime(rs.getString("acctime"));
				regLog.setEmailtime(rs.getString("emailtime"));
				regLog.setPhonetime(rs.getString("phonetime"));
				regLog.setAddresstime(rs.getString("addresstime"));
				regLog.setChktime(rs.getString("chktime"));
				regLog.setResettime(rs.getString("resettime"));
				regLog.setSubtime1(rs.getString("subtime1"));
				regLog.setRegpage2time(rs.getString("regpage2time"));
				regLog.setPictime(rs.getString("pictime"));
				regLog.setEffecttime(rs.getString("effecttime"));
				regLog.setPicid(rs.getString("picid"));
				regLog.setEffectid(rs.getInt("effectid"));
				regLog.setSubtime2(rs.getString("subtime2"));
				regLog.setEmailverifypagetime(rs.getString("emailverifypagetime"));
				regLog.setVerifytime(rs.getString("verifytime"));
				regLog.setProceedtime(rs.getString("proceedtime"));
				regLog.setOldpasstime(rs.getString("oldpasstime"));
				regLog.setNewpasstime(rs.getString("newpasstime"));
				regLog.setCnfpasstime(rs.getString("cnfpasstime"));
				regLog.setSecurityquestime(rs.getString("securityquestime"));
				regLog.setSecurityanstime(rs.getString("securityanstime"));
				regLog.setSubtime3(rs.getString("subtime3"));
				regLog.setAttempt(rs.getInt("attempt"));
			} else {
				System.out.println("regLog not found");
			}
			System.out.println("This is retrieveRegistrationLogDetails");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return regLog;
	}

	public RegistrationAddLogDetails retrieveRegistrationAddLogDetails(String acc) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		RegistrationAddLogDetails regLog = new RegistrationAddLogDetails();
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"select acc,uname,utime,ftime,ltime,phonetime,emailtime,addresstime,chktime,subtime1,"
					+ "subtime2,pictime,effecttime,attempt from regLog where uname=?");
			pst.setString(1, acc);

			rs = pst.executeQuery();
			if (rs.next()) {
				regLog.setRegid(rs.getString("regid"));
				regLog.setAcc(rs.getString("acc"));
				regLog.setUname(rs.getString("uname"));
				regLog.setRegpage1time(rs.getString("regpage1time"));
				regLog.setUtime(rs.getString("utime"));
				regLog.setFtime(rs.getString("ftime"));
				regLog.setLtime(rs.getString("ltime"));
				regLog.setAcctime(rs.getString("acctime"));
				regLog.setEmailtime(rs.getString("emailtime"));
				regLog.setPhonetime(rs.getString("phonetime"));
				regLog.setAddresstime(rs.getString("addresstime"));
				regLog.setChktime(rs.getString("chktime"));
				regLog.setResettime(rs.getString("resettime"));
				regLog.setSubtime1(rs.getString("subtime1"));
				regLog.setRegpage2time(rs.getString("regpage2time"));
				regLog.setPictime(rs.getString("pictime"));
				regLog.setEffecttime(rs.getString("effecttime"));
				regLog.setPicid(rs.getString("picid"));
				regLog.setEffectid(rs.getInt("effectid"));
				regLog.setSubtime2(rs.getString("subtime2"));
				regLog.setEmailverifypagetime(rs.getString("emailverifypagetime"));
				regLog.setVerifytime(rs.getString("verifytime"));
				regLog.setProceedtime(rs.getString("proceedtime"));
				regLog.setOldpasstime(rs.getString("oldpasstime"));
				regLog.setNewpasstime(rs.getString("newpasstime"));
				regLog.setCnfpasstime(rs.getString("cnfpasstime"));
				regLog.setSecurityquestime(rs.getString("securityquestime"));
				regLog.setSecurityanstime(rs.getString("securityanstime"));
				regLog.setSubtime3(rs.getString("subtime3"));
				regLog.setAttempt(rs.getInt("attempt"));
			} else {
				System.out.println("regLog not found");
			}
			System.out.println("This is retrieveRegistrationLogDetails");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return regLog;
	}*/

	public int insertPhotoCountDetails(PhotoCountDetails pc) {
		Connection con = null;
		PreparedStatement pst = null, pst1 = null;
		ResultSet rs = null;
		String picid;
		int i, size, result = 0, countchosenreg = 0, countchosenlogin = 0, countreg = 0, countlogin = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			ArrayList<String> list = pc.getList();
			size = list.size();
			for (i = 0; i < size; i++) {
				picid = list.get(i);
				pst = con.prepareStatement(
						"select picid,countchosenreg,countchosenlogin,countreg,countlogin from photocount where picid=?");
				pst.setString(1, picid);
				rs = pst.executeQuery();
				if (rs.next()) {
					countchosenreg = rs.getInt("countchosenreg");
					countchosenlogin = rs.getInt("countchosenlogin");
					countreg = rs.getInt("countreg");
					countlogin = rs.getInt("countlogin");
					if ((pc.getType()).equals("reg")) {
						pst1 = con
								.prepareStatement("update photocount set countreg=?,dateaccessed=? where picid=?");
						pst1.setInt(1, ++countreg);
					} else if ((pc.getType()).equals("login")) {
						pst1 = con
								.prepareStatement("update photocount set countlogin=?,dateaccessed=? where picid=?");
						pst1.setInt(1, ++countlogin);
					} else if ((pc.getType()).equals("choosereg")) {
						pst1 = con.prepareStatement(
								"update photocount set countchosenreg=?,dateaccessed=? where picid=?");
						pst1.setInt(1, ++countchosenreg);
					} else if ((pc.getType()).equals("chooselogin")) {
						pst1 = con.prepareStatement(
								"update photocount set countchosenlogin=?,dateaccessed=? where picid=?");
						pst1.setInt(1, ++countchosenlogin);
					}
					pst1.setString(2, pc.getDateaccessed());
					pst1.setString(3, picid);
					result += pst1.executeUpdate();
				} else {
					pst1 = con.prepareStatement(
							"insert into photocount(picid,countchosenreg,countchosenlogin,"
							+ "countreg,countlogin,dateaccessed,id) values (?,?,?,?,?,?,?)");

					pst1.setString(1, picid);
					pst1.setInt(2, 0);
					pst1.setInt(3, 0);
					if ((pc.getType()).equals("reg")) {
						pst1.setInt(4, 1);
						pst1.setInt(5, 0);
					}else if ((pc.getType()).equals("login")) {
						pst1.setInt(4, 0);
						pst1.setInt(5, 1);
					} 
					pst1.setString(6, pc.getDateaccessed());
					pst1.setString(7, pc.getId());

					result += pst1.executeUpdate();
				}
			}
			/*
			 * if (result != size) { System.out.println("Not inserted"); } else {
			 * System.out.println("Successfully inserted"); }
			 */
			System.out.println("This is insertPhotoCountDetails");
			System.out.println("Result= " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int insertImageLog(ImageLogDetails imgLog) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement(
					"insert into imagelog(acc,imagestring,dateaccessed,actionpage,id) values (?,?,?,?,?)");

			pst.setString(1, imgLog.getAcc());
			pst.setString(2, imgLog.getImagestring());
			pst.setString(3, imgLog.getDateaccessed());
			pst.setString(4, imgLog.getActionpage());
			pst.setString(5, imgLog.getId());

			result = pst.executeUpdate();
			/*
			 * if (result == 0) { System.out.println("Not inserted"); } else {
			 * System.out.println("Successfully inserted"); }
			 */
			System.out.println("\n\nTHIS IS insertImageLog function");
			System.out.println("Result= " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public ArrayList<String> decode(String imagestring, String type, int effect) {
		int i = 0, len, num;
		char c = '0';
		String x = "";// System.out.println(imagestring);
		ArrayList<String> l1 = new ArrayList<String>();
		len = imagestring.length();
		if (type.equals("show")) {
			for (i = 0; (i < len && c != ','); i++) {
				c = imagestring.charAt(i);
				if (Character.isLowerCase(c)) {
					num = (c - 'a');
					// l1.add("hide"+x+num);
					x = "";
				} else if (Character.isUpperCase(c)) {
					num = (c - 'A');
					l1.add("show" + Integer.parseInt(x) + num);
					x = "";
				} else {
					x += c;
				}
			}
		} else if (type.equals("hide")) {
			for (i = 0; i < len; i++) {
				c = imagestring.charAt(i);
				if (Character.isLowerCase(c)) {
					num = (c - 'a');
					l1.add("hide" + Integer.parseInt(x) + num);
					x = "";
				} else if (Character.isUpperCase(c)) {
					num = (c - 'A');
					// l1.add("show"+x+num);
					x = "";
				} else {
					x += c;
				}
			}
		}
		if (effect > -1) {
			int j;
			for (j = 0, i++; i < len; i++, j++) {
				c = imagestring.charAt(i);
				l1.set(j, l1.get(j) + c + effect);
			}
		}
		System.out.println("This is decode with type= " + type + " effect= " + effect);
		System.out.println("Decoded list " + l1);
		return l1;
	}

	public ArrayList<String> decode(String imagestring, int effect) {
		int i, len, num;
		char c = '0';
		String x = "";// System.out.println(imagestring);
		ArrayList<String> l1 = new ArrayList<String>();
		len = imagestring.length();
		for (i = 0; (i < len && c != ','); i++) {
			c = imagestring.charAt(i);
			if (Character.isLowerCase(c)) {
				num = (c - 'a');
				l1.add("hide" + Integer.parseInt(x) + num);
				x = "";
			} else if (Character.isUpperCase(c)) {
				num = (c - 'A');
				l1.add("show" + Integer.parseInt(x) + num);
				x = "";
			} else {
				x += c;
			}
		}
		// Collections.shuffle(l1);
		if (effect > -1) {
			int j;
			for (j = 0; i < len; i++, j++) {
				c = imagestring.charAt(i);
				l1.set(j, l1.get(j) + c + effect);
			}
		}
		System.out.println("This is decode with effect= " + effect);
		System.out.println("Decoded list " + l1);
		return l1;
	}

	public int updateAccountImageCount(String acc, String imagestring, String datemodified, String type) {
		Connection con = null;
		PreparedStatement pst = null, pst1 = null;
		ResultSet rs = null;

		/*
		 * If another login attempt is made irrespective of successful or not before 24
		 * hrs of inactivity then loginimagestring does not change. Hence, no updation
		 * in image counts.
		 */
		if (type.equals("login")) {
			String date = retrieveAccountDateModified(acc);
			long day = 86400000;
			if (compareDates(datemodified, date) <= day) {
				return 1;
			}
		}

		int countshow = 0, counthide = 0, result = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select countshow, counthide from account where acc=?");
			pst.setString(1, acc);
			rs = pst.executeQuery();
			if (rs.next()) {
				countshow = rs.getInt("countshow");
				counthide = rs.getInt("counthide");
			}
			if (type.equals("reg")) {
				countshow = 8;
			} else {
				if (type.equals("loginFirst")) {
					counthide = 10;
				} else {
					counthide += 4;
				}
			}

			pst1 = con
					.prepareStatement("update account set countshow=?, counthide=?, datemodified=? where acc=?");
			pst1.setInt(1, countshow);
			pst1.setInt(2, counthide);
			pst1.setString(3, datemodified);
			pst1.setString(4, acc);

			result += pst1.executeUpdate();
			System.out.println("This is updateAccountImageCount");
			System.out.println("Result= " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public String retrieveRegImageString(String acc) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String imagestring = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select regimagestring from account where acc=?");
			pst.setString(1, acc);

			rs = pst.executeQuery();
			if (rs.next()) {
				imagestring = rs.getString(1);
			} else {
				System.out.println("imagestring not found for acc= " + acc);
			}
			System.out.println("This is retrieveRegImageString");
			System.out.println("Imagestring " + imagestring);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return imagestring;
	}

	public String retrieveLoginImageString(String acc) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String loginimagestring = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select loginimagestring from account where acc=?");
			pst.setString(1, acc);

			rs = pst.executeQuery();
			if (rs.next()) {
				loginimagestring = rs.getString(1);
			}
			System.out.println("This is retrieveLoginImageString");
			System.out.println("LoginImagestring " + loginimagestring);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return loginimagestring;
	}
	
	public String encode(ArrayList<String> list) {
		int i, size;
		String a, x, s1 = "", s2="";
		String key1[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		String key2[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
		char c;
		
		size = list.size();
		for (i = 0; i < size; i++) {
			x = list.get(i);
			c=x.charAt(0);
			a=x.substring(4, x.length()-2);
			if (c=='s') {
				s1+=a.substring(0, a.length()-1)+key1[a.charAt(a.length()-1)-'0'];
			}else if (c=='h') {
				s1+=a.substring(0, a.length()-1)+key2[a.charAt(a.length()-1)-'0'];
			}
			s2+=x.charAt(x.length()-2);
		}
		return s1+","+s2;
	}

	public String encode(String[] key, ArrayList<Integer> list) {
		int i, x, size;
		String s = "";
		size = list.size();
		for (i = 0; i < size; i++) {
			x = list.get(i);
			if (x / 10 == 0)
				s += "0" + key[x];
			else
				s += x / 10 + key[x % 10];
			// System.out.println("random "+x);
		}
		return s;
	}

	public String encode(String[] key, ArrayList<Integer> list, ArrayList<Integer> list2) {
		int i, x, size;
		String s = "";
		size = list.size();
		for (i = 0; i < size; i++) {
			x = list.get(i);
			if (x / 10 == 0)
				s += "0" + key[x];
			else
				s += x / 10 + key[x % 10];
			// System.out.println("random "+x);
		}
		s += ",";
		size = list2.size();
		for (i = 0; i < size; i++) {
			x = list2.get(i);
			s += x;
		}
		return s;
	}

	public String encodeReg(String acc, int show, int effects) {
		/*
		 * For now acc is useless but it can be put to use in future. bcoz for now any
		 * set of 8 images out of total 'show' images are chosen. So, in future we can
		 * make a frequency distribution of images chosen times vs picid and then choose
		 * those with least frequency. Acc use can be thought of for some other task.
		 */

		int i;
		String regimagestring = "";
		String key[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		for (i = 0; i < show; i++) {
			list.add(i, i);
		}
		Collections.shuffle(list);
		for (i = 0; i < 8; i++) {
			list1.add(i, list.get(i));
		}
		Collections.shuffle(list1);
		for (i = 0; i < effects; i++) {
			list2.add(i, i + 1);
		}
		Collections.shuffle(list2);
		// System.out.println(list1);
		regimagestring = encode(key, list1, list2);

		System.out.println("This is encodeReg");
		System.out.println("Encoded string " + regimagestring);
		return regimagestring;
	}

	public String encodeLogin(String acc, int picid, ArrayList<String> list, int effectid, int effects) {
		// if acc is XXXXXXXXXX1206 then n=1206-1201=5;
		// if acc is XXXXXXXXXX1206 then n=1226-1201=25;
		// Hidden Images associated are 3n, 3n+1, ...., 3n+5;

		/*
		 * Now, here we need to choose the loginimagestring for this acc, so we fix the
		 * first picid, then 1/7 images shown during show, then what remains is the 4
		 * images that change on a daily basis and the rest 6 images that are fixed with
		 * this acc. Now, 2 questions. a) How to choose these 6 fixed images? b? The
		 * effect of how many of these fixed images to be fixed?
		 * 
		 * For now, the 6 hidden images are chosen using 3n to 3n+1 rule and effect of
		 * 3/6 (fixed hidden) is chosen same as the effect of picid. And the effect of
		 * 1/7 is for sure kept to be different and the other 3/6 are kept to be same as
		 * that of the 1/7.
		 * 
		 * Advantage is that for phisher, it just introduces some complexity while for
		 * us/user, it is not a problem at all but the phisher is again in thought as
		 * which one of the two is the correct effect.
		 */

		int i, n;
		String s = "";
		String key1[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		String key2[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		ArrayList<Integer> list4 = new ArrayList<Integer>();

		/*
		 * Here list1 is general purpose list. list2 and list3 contains the images and,
		 * list4 contains the effects Now to choose the effect 1. Add the effect of
		 * correct image 2. Add any other effect than that of correct image for category
		 * b image 3. Now x/6 hidden images are here same as picid and rest are that of
		 * category b image
		 */

		System.out.println("Picid " + picid);
		System.out.println("list " + list);
		n = Integer.parseInt(acc.substring(10)) - 1201;

		// Now choosing 1/7 images of category b
		for (i = 0; i < list.size(); i++) {
			int p = Integer.parseInt(list.get(i).substring(4));
			if (p == picid)
				continue;
			list1.add(p);
		}
		Collections.shuffle(list1);
		list2.add(picid);
		list2.add(list1.get(0));

		// Now choosing the different effect of category b image
		list1.clear();
		for (i = 1; i <= effects; i++) {
			if (i == effectid)
				continue;
			list1.add(i);
		}
		Collections.shuffle(list1);
		list4.add(effectid);
		list4.add(list1.get(0));
		/*
		 * This chooses category e, 6 fixed hidden images using the 3n rule and, the
		 * their effects as well
		 */
		for (i = 0; i < 6; i++) {
			list3.add(3 * n + i);
			if (i <= 1) {
				list4.add(effectid);
			} else if (i <= 3) {
				list4.add(list1.get(0));
			} else {
				list4.add(list1.get(1));
			}
		}
		s = encode(key1, list2) + encode(key2, list3, list4);
		System.out.println("This is encodeLogin");
		System.out.println("Encoded string " + s);
		return s;
	}

	public String encodeRandomLogin(String acc, int hide, String now, int effects) {
		// This function selects the random exclusive 4 images to be displayed during
		// login;
		// hide is the current no. of hidden images out of which 4 are to be chosen;
		// Current date format 22-07-2019 12:13:50:425
		long day = 86400000; // Milliseconds in a day
		int x, flag = 0;
		String loginimagestring, logineffects, datemodified;

		loginimagestring = retrieveLoginImageString(acc);
		x=loginimagestring.lastIndexOf(',');
		logineffects = loginimagestring.substring(x + 1);
		loginimagestring = loginimagestring.substring(0, x);
		datemodified = retrieveAccountDateModified(acc);
		/*
		 * System.out.println("loginimagestring "+loginimagestring);
		 * System.out.println("datemodified "+datemodified);
		 */

		if (logineffects.length() == 8) {//This indicates login first time
			loginimagestring = getDistinctRandom(loginimagestring, logineffects, 6, hide, effects);
			flag = 1;
			updateAccountImageCount(acc, loginimagestring, now, "loginFirst");
		} else {
			if (compareDates(datemodified, now) > day) {
				loginimagestring = getDistinctRandom(loginimagestring, logineffects.substring(0, 8), 10, hide, effects);
				flag = 1;
			}
		}
		System.out.println("This is encodeRandomLogin");
		if (flag == 1) {
			System.out.println("loginimagestring " + loginimagestring);
			return loginimagestring;
		}
		System.out.println("loginimagestring " + loginimagestring + "," + logineffects);
		return loginimagestring + "," + logineffects;
	}

	public String getDistinctRandom(String imagestring, String logineffects, int num, int hide, int effects) {
		int i, j;
		String effectorder = "";
		String key[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		ArrayList<String> list = new ArrayList<String>();
		Random ran = new Random();

		list = decode(imagestring, "hide", -1);
		Collections.sort(list);
		System.out.println("List of random " + list);
		/*
		 * Here we are using the fact that images will always be in sorted order
		 */
		for (i = 0, j = 0; i < hide; i++) {
			if (j < num) {
				int p = Integer.parseInt(list.get(j).substring(4));
				if (p == i) {
					j++;
					continue;
				}
			}
			list1.add(i);
		}
		Collections.shuffle(list1);
		for (i = 0; i < 4; i++) {
			list2.add(list1.get(i));
			effectorder += (ran.nextInt(effects) + 1);
		}
		System.out.println("Get Distinct Random");
		System.out.println("List of random " + list2);

		return fixedHidden(imagestring) + encode(key, list2) + "," + logineffects + effectorder;
	}

	public String fixedHidden(String imagestring) {
		String s = "";
		int i, len, j;

		len = imagestring.length();
		for (i = 0, j = 0; i < len && j < 6; i++) {
			if (Character.isLowerCase(imagestring.charAt(i))) {
				j++;
			}
			s += imagestring.charAt(i);
		}
		return s;
	}

	public int updateAccountLoginImageString(String acc, String loginimagestring, String datemodified) {
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;

		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("update account set loginimagestring=?, datemodified=? where acc=?");
			pst.setString(1, loginimagestring);
			pst.setString(2, datemodified);
			pst.setString(3, acc);
			result = pst.executeUpdate();
			/*
			 * if (result == 0) { System.out.println("Not Updated"); } else {
			 * System.out.println("Successfully Updated"); }
			 */
			System.out.println("\nThis function is updateAccountLoginImageString");
			System.out.println("Result =" + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return result;
	}

	public int retrieveAccountCount() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select count from account order by count desc");
			rs = pst.executeQuery();
			/*
			 * This if condition checks if there is any account already in the table
			 * otherwise the first account created is with last four digits as 1201.
			 */
			if (rs.next()) {
				count = rs.getInt(1);
			} else {
				count = 1200;
			}
			System.out.println("This is retrieveAccountCount");
			System.out.println("Count retrieved " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return ++count;
	}

	public int retrieveRegCount() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select count from regaddlog order by count desc");
			rs = pst.executeQuery();
			/*
			 * This if condition checks if there is any regid already in the table otherwise
			 * the first regid created is with last four digits as 1001.
			 */
			if (rs.next()) {
				count = rs.getInt(1);
			} else {
				count = 1000;
			}
			System.out.println("This is retrieveRegCount");
			System.out.println("Count retrieved " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return ++count;
	}

	public int retrieveLoginCount() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select count from loginlog order by count desc");
			rs = pst.executeQuery();
			/*
			 * This if condition checks if there is any loginid already in the table
			 * otherwise the first loginid created is with last four digits as 1001.
			 */
			if (rs.next()) {
				count = rs.getInt(1);
			} else {
				count = 1000;
			}
			System.out.println("This is retrieveLoginCount");
			System.out.println("Count retrieved " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return ++count;
	}

	public String retrieveAccountDateModified(String acc) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String datemodified = null;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("select datemodified from account where acc=?");
			pst.setString(1, acc);
			rs = pst.executeQuery();
			if (rs.next()) {
				datemodified = rs.getString("datemodified");
			}
			System.out.println("This is retrieveAccountDateModified");
			System.out.println("datemodified " + datemodified);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return datemodified;
	}
	
	public String generateTrid(String loginid) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String trid=loginid+"_1_t";
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();
			pst = con.prepareStatement("SELECT trid from transaction where loginid=? order by trid desc");
			pst.setString(1, loginid);
			rs = pst.executeQuery();
			if (rs.next()) {
				trid = rs.getString("trid");
				trid=loginid+"_"+(Integer.parseInt(trid.substring(trid.indexOf('_')+1, trid.length()-2))+1)+"_t";
			}
			System.out.println("This is generateTrid");
			System.out.println("trid " + trid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
			ConnectionByStaticMethod.closeMySQLConnection(con);
		}
		return trid;
	}

	private long compareDates(String first, String second) {
		// int result=0;
		long diff = 0;
		try {
			SimpleDateFormat sdfo = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS");
			java.util.Date d1 = sdfo.parse(first);
			java.util.Date d2 = sdfo.parse(second);
			System.out.println("Date1 : " + sdfo.format(d1));
			System.out.println("Date2 : " + sdfo.format(d2));

			/*
			 * if(d1.after(d2)){ result=1; } else if(d1.before(d2)){ result=-1; } else
			 * if(d1.equals(d2)){ result=0; }
			 */
			diff = d2.getTime() - d1.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("This is compareDates");
		// System.out.println("Result "+result);
		System.out.println("Difference " + diff);
		return diff;
	}
}