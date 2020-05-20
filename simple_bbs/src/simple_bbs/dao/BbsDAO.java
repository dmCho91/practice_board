package simple_bbs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import simple_bbs.dto.BbsDTO;

public class BbsDAO {
	
	public final static int DATABASE_ERROR = -1;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public BbsDAO() {
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
	public int write(String userId, String bTitle, String bContent) {
		String sql = "INSERT INTO simple_bbs VALUES(simple_bbs_seq.NEXTVAL, ?, ?, ?,SYSDATE, 0, simple_bbs_seq.CURRVAL, 0, 0, 1)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
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
		return DATABASE_ERROR;
	}
	
	public ArrayList<BbsDTO> bbsLists(int startNo, int endNo){
		ArrayList<BbsDTO> dtos = new ArrayList<BbsDTO>();
		String sql = "SELECT b.* FROM " + 
				"(SELECT ROWNUM rn, a.* FROM " + 
				"    (SELECT * FROM simple_bbs WHERE bAvailable=1 ORDER BY bGroup DESC, bStep ASC)a)b " + 
				"WHERE rn>? and rn<=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BbsDTO dto = new BbsDTO();
				dto.setbId(rs.getInt("bId"));
				dto.setUserId(rs.getString("userId"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbContent(rs.getString("bContent"));
				dto.setbDate(rs.getTimestamp("bDate"));
				dto.setbHit(rs.getInt("bHit"));
				dto.setbGroup(rs.getInt("bGroup"));
				dto.setbStep(rs.getInt("bStep"));
				dto.setbIndent(rs.getInt("bIndent"));
				dto.setbAvailable(rs.getInt("bAvailable"));
				
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dtos;
	}
	
	public BbsDTO view(String bId, int hitUP) {
		if(hitUP == 1) {
			hitUp(bId);
		}
		
		BbsDTO dto = null;
		String sql = "SELECT * FROM simple_bbs WHERE bId=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bId));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new BbsDTO();
				dto.setbId(rs.getInt("bId"));
				dto.setUserId(rs.getString("userId"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbContent(rs.getString("bContent"));
				dto.setbDate(rs.getTimestamp("bDate"));
				dto.setbHit(rs.getInt("bHit"));
				dto.setbGroup(rs.getInt("bGroup"));
				dto.setbStep(rs.getInt("bStep"));
				dto.setbIndent(rs.getInt("bIndent"));
				dto.setbAvailable(rs.getInt("bAvailable"));
			}else {
				System.out.println("null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	//Á¶È¸¼ö
	private void hitUp(String bId) {
		String sql = "UPDATE simple_bbs SET bHit = bHit+1 WHERE bId=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bId));
			int result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public BbsDTO writeComplete(String userId) {
		BbsDTO dto = null;
		String sql = "SELECT * FROM " + 
						"(SELECT * "
						+ "FROM simple_bbs WHERE userId=? ORDER BY bId DESC)" + 
					"WHERE ROWNUM=1";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new BbsDTO();
				dto.setbId(rs.getInt("bId"));
				dto.setUserId(rs.getString("userId"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbContent(rs.getString("bContent"));
				dto.setbDate(rs.getTimestamp("bDate"));
				dto.setbHit(rs.getInt("bHit"));
				dto.setbGroup(rs.getInt("bGroup"));
				dto.setbStep(rs.getInt("bStep"));
				dto.setbIndent(rs.getInt("bIndent"));
				dto.setbAvailable(rs.getInt("bAvailable"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public int bbsDelete(String bId) {
		String sql = "UPDATE simple_bbs SET bAvailable=0 WHERE bId=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bId));
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DATABASE_ERROR;
	}
	
	public int bbsModify(String bId, String bTitle, String bContent ) {
		String sql = "UPDATE simple_bbs SET bTitle=?, bContent=? WHERE bId=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bTitle);
			pstmt.setString(2, bContent);
			pstmt.setInt(3, Integer.parseInt(bId));
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DATABASE_ERROR;
	}
	
	public int getTotal() {
		String sql="SELECT COUNT(*) cnt FROM simple_bbs WHERE bAvailable=1";
		try {
			pstmt = conn.prepareStatement(sql);
			rs =pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DATABASE_ERROR;
	}
	
}
