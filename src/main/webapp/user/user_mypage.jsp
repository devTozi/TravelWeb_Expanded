<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>

	<div align="center">
		
		<div class="profile-user">
			<img src="<%= request.getContextPath() %>/img/20190730_213728.jpg" 
			alt="dummy" class="profile-user-img" style="width: 200px; height: 200px; border-radius: 100%;">
		</div>
		<br><br>
		<b> ${sessionScope.user_id } (${sessionScope.user_name })</b>님 환영합니다.
		
		<br><br>	
		<div>
			<a href="user_modify.user"><input type="submit" value="정보수정" class="btn btn-success"></a>
			<a href="user_logout.user"><input type="submit" value="로그아웃" class="btn btn-success"></a>
			<a href="user_delete.user"><input type="submit" value="탈퇴" class="btn btn-success"></a>
		</div>
	</div>

<%@ include file="../include/footer.jsp"%>