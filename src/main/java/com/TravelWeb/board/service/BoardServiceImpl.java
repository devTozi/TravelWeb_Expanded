package com.TravelWeb.board.service;


import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TravelWeb.board.model.BoardDAO;
import com.TravelWeb.board.model.BoardVO;
import com.TravelWeb.board.model.CommentVO;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BoardServiceImpl implements BoardService{

	// 쿠키 변수
	private static int idx = 0;

	// 글 등록 메서드
	public void regist(HttpServletRequest request, HttpServletResponse response) {

		int size = 1024*1024*8;
		String path = "/img/";
		String realPath = request.getServletContext().getRealPath(path);

		try {
			MultipartRequest multi = new MultipartRequest(request, realPath, size, "utf-8", new DefaultFileRenamePolicy());

			String id = (String)request.getSession().getAttribute("user_id");
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String image = multi.getFilesystemName("image");
			if(image == null) image = "dummy.png";

			BoardDAO.getInstance().regist(id, title, content, path + image);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 메인 게시글 메서드
	public ArrayList<BoardVO> getList(HttpServletRequest request, HttpServletResponse response) {

		// 검색 요청 처리
		String search = request.getParameter("search");
		if(search != null) {
			return BoardDAO.getInstance().getList(search);
		}

		return BoardDAO.getInstance().getList();
	}

	// 글 내용보기
	public BoardVO getContent(HttpServletRequest request, HttpServletResponse response) {

		String bno = request.getParameter("bno");
		
		// 조회수 처리 - 쿠키
		Cookie[] arr = request.getCookies();
		if(arr != null) {
			// 쿠키에 있는 bno라면 조회수를 증가시키지 않고 게시글 vo만 리턴시킴
			for(Cookie c : arr)
				if(c.getValue().equals(bno))
					return BoardDAO.getInstance().getContent(bno);
		}

		// arr이 null이거나 bno와 일치하는 쿠키가 없다면 
		// 조회수 증가
		BoardDAO.getInstance().hit(bno);
		// 쿠키 생성
		Cookie c = new Cookie(String.valueOf(idx), bno);
		c.setMaxAge(60*60*24);
		response.addCookie(c);
		idx++;

		return BoardDAO.getInstance().getContent(bno);
	}

	// 글 수정 메서드
	public void update(HttpServletRequest request, HttpServletResponse response) {

		String bno = request.getParameter("bno");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		BoardDAO.getInstance().update(bno, title, content);
	}

	//글 삭제 메서드
	public int delete(HttpServletRequest request, HttpServletResponse response) {

		String bno = request.getParameter("bno");

		return BoardDAO.getInstance().delete(bno);
	}

	// 나의 게시글 리스트 메서드
	public ArrayList<BoardVO> getMyList(HttpServletRequest request, HttpServletResponse response) {

		String id = (String)request.getSession().getAttribute("user_id");

		return BoardDAO.getInstance().getMyList(id);
	}
	
	// 내가 좋아요한 리스트 메서드
	public ArrayList<BoardVO> getLikeList(HttpServletRequest request, HttpServletResponse response) {

		String id = (String)request.getSession().getAttribute("user_id");

		return BoardDAO.getInstance().getLikeList(id);
	}

	// 좋아요 체크
	@Override
	public boolean like_check(HttpServletRequest request, HttpServletResponse response) {

		String bno = request.getParameter("bno");
		String id = (String)request.getSession().getAttribute("user_id");

		return BoardDAO.getInstance().like_check(bno, id);
	}

	// 좋아요 추가
	public void like_insert(HttpServletRequest request, HttpServletResponse response) {

		String bno = request.getParameter("bno");
		String id = (String)request.getSession().getAttribute("user_id");

		BoardDAO.getInstance().like_insert(bno, id);
	}

	// 좋아요 취소
	@Override
	public void like_delete(HttpServletRequest request, HttpServletResponse response) {

		String bno = request.getParameter("bno");
		String id = (String)request.getSession().getAttribute("user_id");

		BoardDAO.getInstance().like_delete(bno, id);
	}

	//정렬
	@Override
	public ArrayList<BoardVO> getList_order(HttpServletRequest request, HttpServletResponse response) {

		String order = request.getParameter("order");

		return BoardDAO.getInstance().getList_order(order);
	}
	
	// 댓글 등록
	@Override
	public void addComment(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		String id = (String)request.getSession().getAttribute("user_id");
		String comment = request.getParameter("comment");
		
		BoardDAO.getInstance().addComment(id, bno, comment);
	}

	@Override
	public ArrayList<CommentVO> getComments(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		
		return BoardDAO.getInstance().getComments(bno);
	}


}
