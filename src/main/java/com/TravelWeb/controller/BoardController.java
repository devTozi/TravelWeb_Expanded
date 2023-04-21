package com.TravelWeb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TravelWeb.board.model.BoardVO;
import com.TravelWeb.board.model.CommentVO;
import com.TravelWeb.board.service.BoardService;
import com.TravelWeb.board.service.BoardServiceImpl;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String command = uri.substring(path.length());
		System.out.println("요청경로: "+command);

		// 서비스 객체
		BoardService service = new BoardServiceImpl();

		// 세션 객체
		HttpSession session = request.getSession();

		// 메인 페이지 이동 - 모든 게시글 리스트 출력
		if(command.equals("/index.board")) {

			ArrayList<BoardVO> list =service.getList(request, response);
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("index.jsp").forward(request, response);

		}else if(command.equals("/board_write.board")){
			// 글쓰기 페이지 이동
			if(session.getAttribute("user_id") == null) {

				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 후 이용해주세요.')");
				out.println("location.href='"+path+"/user/user_login.user';");
				out.println("</script>");
				return;
			}

			request.getRequestDispatcher("/board_write.jsp").forward(request, response);

		}else if(command.equals("/registform.board")) {
			// 글 등록
			service.regist(request, response);
			response.sendRedirect(path+"/index.board");

		}else if(command.equals("/board_content.board")) {
			// 글 내용보기
			BoardVO vo = service.getContent(request, response);
			// 좋아요 여부 체크
			boolean check = service.like_check(request, response);
			// 댓글 가져오기
			ArrayList<CommentVO> commentsList = service.getComments(request, response);
			
			request.setAttribute("check", check);
			request.setAttribute("vo", vo);
			request.setAttribute("commentsList", commentsList);
			
			request.getRequestDispatcher("/board_content.jsp").forward(request, response);
			
		}else if(command.equals("/board_modify.board")) { // 수정
			// 조회한 글에 대한 정보 조회 재활용
			BoardVO vo = service.getContent(request, response);

			request.setAttribute("vo", vo);
			request.getRequestDispatcher("/board_modify.jsp").forward(request, response);
			
		}else if(command.equals("/updateform.board")) {
			// 게시글 수정
			service.update(request, response);

			response.sendRedirect("board_content.board?bno="+request.getParameter("bno"));
			
		}else if(command.equals("/board_delete.board")){
			// 게시글 삭제
			int result = service.delete(request, response);

			String msg = "";
			if(result == 1) msg = "삭제 되었습니다.";
			else msg = "삭제 실패";

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+ msg +"')");
			out.println("location.href='index.board';");
			out.println("</script>");

		}else if(command.equals("/board_like_insert.board")) {
			// 좋아요 추가
			String bno = request.getParameter("bno");
			service.like_insert(request, response);
			
			response.sendRedirect(path+"/board_content.board?bno="+bno);

		}else if(command.equals("/board_like_delete.board")){
			// 좋아요 취소
			String bno = request.getParameter("bno");
			service.like_delete(request, response);
			
			response.sendRedirect(path+"/board_content.board?bno="+bno);
			
		}else if(command.equals("/board_order.board")) {
			// 정렬 조회
			ArrayList<BoardVO> list =service.getList_order(request, response);

			request.setAttribute("list", list);
			request.getRequestDispatcher("index.jsp").forward(request, response);

		}else if(command.equals("/board_mygallery.board")) {
			// 내가 올린 리스트
			ArrayList<BoardVO> myList = service.getMyList(request, response);
			
			request.setAttribute("myList", myList);
			request.getRequestDispatcher("board_mygallery.jsp").forward(request, response);
		}else if(command.equals("/board_myfavorite.board")) {
			// 좋아요한 리스트
			ArrayList<BoardVO> likeList = service.getLikeList(request, response);
			
			request.setAttribute("likeList", likeList);
			request.getRequestDispatcher("board_myfavorite.jsp").forward(request, response);
		}else if(command.equals("/board_add_comment.board")) {
			// 댓글 등록
			String bno = request.getParameter("bno");
			service.addComment(request, response);
			
			response.sendRedirect(path + "/board_content.board?bno=" + bno);
		}

		
	}// doAction 끝

}
