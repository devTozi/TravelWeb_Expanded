package com.TravelWeb.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.TravelWeb.util.JDBCUtil;


public class UserDAO {

	private static UserDAO instance = new UserDAO();

	private UserDAO() {
		// 드라이버 클래스 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버클래스 로드에러");
		}
	}

	public static UserDAO getInstance() {
		return instance;
	}

	public String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public String UID = "hp";
	public String UPW = "hp";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 5. id, email 중복 체크 메서드
	public int idCheck(String id, String email) {

		int result = 0;

		String sql = "select count(*) as total from users where id=? or email=?";

		try {
			// 1. conn 객체
			conn = DriverManager.getConnection(URL, UID, UPW);

			// 2. pstmt 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);

			// 3. 실행
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getInt("total");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return result;
	}

	// 6. 회원 가입 메서드
	public void join(UserVO vo) {

		String sql = "insert into users values(?,?,?,?,?,?)";

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getGender());
			pstmt.setInt(6, vo.getBirth());

			pstmt.executeUpdate(); // 실행(성공 시 1, 실패 시 0 반환)

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

	}

	// 로그인 메서드
	public UserVO login(String id, String pw) {

		UserVO vo = null;

		String sql = "select * from users where id=? and pw=?";

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			rs = pstmt.executeQuery();

			// 로그인 성공 후 vo에 회원정보 저장
			if(rs.next()) { 
				String id2 = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				int birth = rs.getInt("birth");

				vo = new UserVO(id2, null, name, email, gender, birth);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return vo;
	}

	// 회원 정보 조회 및 저장 메서드
	public UserVO getInfo(String id) {

		UserVO vo = null;

		String sql = "select * from users where id=?";

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if(rs.next()) { 
				String id2 = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				int birth = rs.getInt("birth");

				vo = new UserVO(id2, null, name, email, gender, birth);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return vo;
	}
	
	// 회원 정보 수정 메서드
	public int update(String id, String pw, String name, String gender) {
		
		String sql = "update users set pw = ?, name = ?, gender = ? where id = ?";
		int check = 0;
		
		try {

			conn = DriverManager.getConnection(URL, UID, UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, gender);
			pstmt.setString(4, id);

			check = pstmt.executeUpdate(); // 실행(성공 시 1, 실패 시 0 반환)

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return check;
		
	}
	
	// 회원 탈퇴 메서드
	public int delete(String id) {
		
		int result = 0;
		
		String sql = "delete from users where id = ?";
		
		try {
			
			conn = DriverManager.getConnection(URL, UID, UPW);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
}
