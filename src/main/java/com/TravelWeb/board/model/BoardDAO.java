package com.TravelWeb.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.TravelWeb.util.JDBCUtil;

public class BoardDAO {

	private static BoardDAO instance = new BoardDAO();

	private BoardDAO() {
		//드라이버 클래스 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버클래스 로드에러");
		}
	}

	public static BoardDAO getInstance() {
		return instance;
	}

	// 필요한 데이터베이스 변수 선언
	public String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public String UID = "hp";
	public String UPW = "hp";

	// 원래는 지역변수로 만들어야 함
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	//작성글 등록
	public void regist(String id, String title, String content, String image) {

		String sql = "insert into board(bno, id, title, content, image) values(board_seq.nextval, ?, ?, ?, ?)";

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setString(4, image);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

	}

	// 메인에 출력될 게시글 가져오기
	public ArrayList<BoardVO> getList(){

		ArrayList<BoardVO> list = new ArrayList<>();

		String sql = "select * from board order by hit desc";

		try {
			conn = DriverManager.getConnection(URL,UID, UPW);

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setId(rs.getString("id"));
				vo.setLikes(rs.getInt("likes"));
				vo.setImage(rs.getString("image"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return list;
	}

	// 메인에 출력될 게시글 메서드 오버로드 - 검색
	public ArrayList<BoardVO> getList(String search){

		ArrayList<BoardVO> list = new ArrayList<>();

		String sql = "select * from board where title like '%' || ? || '%' or content like '%' || ? || '%'";

		try {
			conn = DriverManager.getConnection(URL,UID, UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setId(rs.getString("id"));
				vo.setLikes(rs.getInt("likes"));
				vo.setImage(rs.getString("image"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return list;
	}

	//글 세부내용
	public BoardVO getContent(String bno) {

		BoardVO vo = null;

		String sql = "select * from board where bno = ?";

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,bno); 

			rs = pstmt.executeQuery();

			if(rs.next()) {
				vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setImage(rs.getString("image"));
				vo.setId(rs.getString("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setLikes(rs.getInt("likes"));
				vo.setRegdate(rs.getTimestamp("regdate"));
			}

		} catch(Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		} 

		return vo;
	}


	// 수정 메서드
	public void update(String bno, String title, String content) {

		String sql = "update board set title = ?, content = ? where bno =?";

		try {
			conn = DriverManager.getConnection(URL,UID,UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, bno);

			pstmt.executeUpdate();

		} catch(Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

	}

	// 삭제 기능
	public int delete(String bno) {

		int result = 0;

		String sql = "delete from board where bno = ?";

		try {
			conn = DriverManager.getConnection(URL,UID,UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);

			result = pstmt.executeUpdate(); //성공시 1반환, 실패시 0반환

		} catch(Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return result;
	}

	// 조회수 기능
	public void hit(String bno) {

		String sql = "update board set hit = (select hit from board where bno = ?)+1 where bno = ?";

		try {
			conn = DriverManager.getConnection(URL,UID,UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.setString(2, bno);

			pstmt.executeUpdate(); //성공시 1반환, 실패시 0반환

		} catch(Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
	}

	// 나의 게시글 리스트용
	public ArrayList<BoardVO> getMyList(String id){

		ArrayList<BoardVO> list = new ArrayList<>();

		String sql = "select * from board where id = ? order by bno desc";

		try {
			conn = DriverManager.getConnection(URL,UID, UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setId(rs.getString("id"));
				vo.setLikes(rs.getInt("likes"));
				vo.setImage(rs.getString("image"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return list;
	}

	// 내가 좋아요한 게시글 리스트용
	public ArrayList<BoardVO> getLikeList(String id){

		ArrayList<BoardVO> list = new ArrayList<>();

		String sql = "select a.bno, b.id, b.image, b.title,b.likes\r\n"
				+ "from (select * from likes where id = ?) a\r\n"
				+ "inner join board b on b.bno = a.bno";
		try {
			conn = DriverManager.getConnection(URL,UID, UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setId(rs.getString("id"));
				vo.setLikes(rs.getInt("likes"));
				vo.setImage(rs.getString("image"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return list;
	}

	// 좋아요 여부 체크
	public boolean like_check(String bno, String id) {

		boolean check = false;

		String sql = "SELECT * FROM LIKES WHERE ID = ? AND BNO = ?";

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id); 
			pstmt.setString(2,bno); 

			rs = pstmt.executeQuery();

			if(rs.next()) check = true;

		} catch(Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		} 

		return check;
	}

	// 좋아요 추가
	public void like_insert(String bno, String id) {

		String sql1 = "INSERT INTO LIKES VALUES(?, ?)";
		String sql2 = "UPDATE BOARD SET LIKES = (select likes from board where bno = ?)+1 WHERE BNO = ?";

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			// likes 테이블 insert
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1,id); 
			pstmt.setString(2,bno); 
			pstmt.executeUpdate();
			// board 테이블 update
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1,bno); 
			pstmt.setString(2,bno); 
			pstmt.executeUpdate();

		} catch(Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		} 
	}

	// 좋아요 취소
	public void like_delete(String bno, String id) {

		String sql1 = "delete from likes where id = ? and bno = ?";
		String sql2= "UPDATE BOARD SET LIKES = (select likes from board where bno = ?)-1 WHERE BNO = ?";

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			// likes 테이블 삭제
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1,id); 
			pstmt.setString(2,bno); 
			pstmt.executeUpdate();
			// board 테이블 업데이트
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1,bno); 
			pstmt.setString(2,bno); 
			pstmt.executeUpdate();

		} catch(Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

	}

	// 메인에 출력될 게시글 좋아요,조회수순으로 가져오기
	public ArrayList<BoardVO> getList_order(String order){

		ArrayList<BoardVO> list = new ArrayList<>();

		String sql = "select * from board order by ";

		if(order.equals("bno")) {
			sql += "bno";
		}else if(order.equals("likes")) {
			sql += "likes desc";
		}else if(order.equals("hit")) {
			sql += "hit desc";
		}

		try {
			conn = DriverManager.getConnection(URL,UID, UPW);

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setId(rs.getString("id"));
				vo.setLikes(rs.getInt("likes"));
				vo.setImage(rs.getString("image"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return list;
	}
	
	// 댓글 등록
	public void addComment(String id, String bno, String comment) {
		
		String sql = "insert into comments values(comments_seq.nextval, ?, ?, ?, sysdate)";
		
		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,bno); 
			pstmt.setString(2,id); 
			pstmt.setString(3,comment); 
			
			pstmt.executeUpdate();

		} catch(Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
	}
	
	// 댓글리스트 가져오기
	public ArrayList<CommentVO> getComments(String bno){
		
		ArrayList<CommentVO> commentsList = new ArrayList<>();
		
		String sql = "select * from comments where bno = ?";
		
		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			// likes 테이블 insert
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,bno); 
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentVO vo = new CommentVO();
				vo.setCno(rs.getInt("cno"));
				vo.setBno(rs.getInt("bno"));
				vo.setId(rs.getString("id"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				commentsList.add(vo);
			}

		} catch(Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return commentsList;
	}

}