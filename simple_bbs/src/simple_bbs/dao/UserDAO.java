package simple_bbs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import simple_bbs.dto.UserDTO;

public class UserDAO {
	public static final int JOIN_DATABASE_ERROR = -1;
	public static final int LOGIN_PASSWORD_NOT_MATCH = 0;
	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_NO_EXIST_ID = -1;
	public static final int LOGIN_DATABASE_ERROR = -2;
	public static final int MODIFY_DATABASE_ERROR = -1;
	

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbID = "scott";
			String dbPassword = "TIGER";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int join(UserDTO user) {
		
		String sql = "INSERT INTO simple_user VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return JOIN_DATABASE_ERROR;
	}
	
	public int login(String userId, String userPw) {
		String sql = "SELECT userPw FROM simple_user WHERE userId=?"; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("userPw").equals(userPw)) {
					return LOGIN_SUCCESS;
				}else {
					return LOGIN_PASSWORD_NOT_MATCH;
				}
			}else {
				return LOGIN_NO_EXIST_ID;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return LOGIN_DATABASE_ERROR;
	}
	
	public UserDTO selectUser(String userId) {
		UserDTO user = null;
		String sql = "SELECT * FROM simple_user WHERE userId=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new UserDTO();
				user.setUserId(rs.getString("userId"));
				user.setUserPw(rs.getString("userPw"));
				user.setUserName(rs.getString("userName"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserEmail(rs.getString("userEmail"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return user;
	}
	
	public int modify(UserDTO user) {
		String sql = "UPDATE simple_user SET userPw=?, userName=?, userGender=?, userEmail=? WHERE userId=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserPw());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserGender());
			pstmt.setString(4, user.getUserEmail());
			pstmt.setString(5, user.getUserId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return MODIFY_DATABASE_ERROR;
	}
}
