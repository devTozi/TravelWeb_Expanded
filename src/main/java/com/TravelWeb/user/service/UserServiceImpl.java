package com.TravelWeb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TravelWeb.user.model.UserDAO;
import com.TravelWeb.user.model.UserVO;

public class UserServiceImpl implements UserService {

	// 컨트롤러의 역할을 분담
	// 가입처리
	public int join(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		int birth = Integer.parseInt(request.getParameter("birth"));

		// 아이디 or 이메일 중복 검사 후 가입처리
		UserDAO dao = UserDAO.getInstance();
		int result = dao.idCheck(id, email);

		if(result >= 1) { // 중복 x -> 가입
			return 1; // 중복의 의미 1
		}else {
			UserVO vo = new UserVO(id, pw, name, email, gender, birth);
			dao.join(vo);
			return 0; // 성공의 의미 0
		}

	}

	@Override
	public UserVO login(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		UserDAO dao = UserDAO.getInstance();
		UserVO vo = dao.login(id, pw);

		return vo;
	}

	@Override
	public UserVO getInfo(HttpServletRequest request, HttpServletResponse response) {

		String id = (String)request.getSession().getAttribute("user_id");

		return UserDAO.getInstance().getInfo(id);
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");

		UserDAO.getInstance().update(id, pw, name, gender);

		// 업데이트 성공 시 세션값 변경
		HttpSession session = request.getSession();
		session.setAttribute("user_name", name);

	}

	@Override
	public int delete(HttpServletRequest request, HttpServletResponse response) {

		// id가 필요
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("user_id");

		int result = UserDAO.getInstance().delete(id);

		if(result == 1) { // 삭제 성공
			session.invalidate();
		}

		return result;
	}



}
