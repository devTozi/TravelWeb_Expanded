package com.TravelWeb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TravelWeb.user.service.UserService;
import com.TravelWeb.user.service.UserServiceImpl;
import com.TravelWeb.user.model.UserDAO;
import com.TravelWeb.user.model.UserVO;

@WebServlet("*.user")
public class UserController extends HttpServlet {
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

		// 서비스 영역
		UserService service = new UserServiceImpl();

		if(command.equals("/user/user_join.user")) {

			// 회원가입 페이지 이동
			request.getRequestDispatcher("user_join.jsp").forward(request, response);

		}else if(command.equals("/user/user_login.user")){

			// 로그인 페이지 이동
			request.getRequestDispatcher("/user/user_login.jsp").forward(request, response);

		}else if(command.equals("/user/joinform.user")) {

			// 회원가입
			int result = service.join(request, response);

			if(result >= 1) { // 아이디 중복
				request.setAttribute("msg", "중복된 아이디 or email입니다.");
				request.getRequestDispatcher("user_join.jsp").forward(request, response);
			}else { // 가입 성공
				response.sendRedirect("user_login.user"); 
			}

		}else if(command.equals("/user/loginform.user")) {
			// 로그인 진행
			UserVO vo = service.login(request, response);

			if(vo == null) { // 로그인 실패
				request.setAttribute("msg", "아이디 or 비밀번호를 확인하세요.");
				request.getRequestDispatcher("user_login.jsp").forward(request, response);
			}else { // 로그인 성공
				// 세션에 아이디, 이름을 저장
				HttpSession session = request.getSession();
				session.setAttribute("user_id", vo.getId());
				session.setAttribute("user_name", vo.getName());

				response.sendRedirect(path+"/index.board");
			}

		}else if(command.equals("/user/user_logout.user")) {
			// 로그아웃
			HttpSession session = request.getSession();
			session.invalidate(); // 세션 무효화

			response.sendRedirect( path + "/index.board");

		}else if(command.equals("/user/user_mypage.user")) {
			// 마이페이지 이동
			request.getRequestDispatcher("user_mypage.jsp").forward(request, response);

		}else if(command.equals("/user/user_modify.user")) {
			// 정보 수정 화면 이동
			UserVO vo = service.getInfo(request, response);

			request.setAttribute("vo", vo);
			request.getRequestDispatcher("user_modify.jsp").forward(request, response);

		}else if(command.equals("/user/updateform.user")) {
			
			// 정보 수정 실행
			service.update(request, response);

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('정보가 수정되었습니다.')");
			out.println("location.href='user_mypage.user';");
			out.println("</script>");

		}else if(command.equals("/user/user_delete.user")) {
			// 회원 탈퇴
			service.delete(request, response);
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('탈퇴가 완료되었습니다.')");
			out.println("location.href='"+path+"/index.board';");
			out.println("</script>");
		}

	}//doAction 끝

}
