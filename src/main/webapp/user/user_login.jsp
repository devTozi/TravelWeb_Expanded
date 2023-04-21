<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
	<section class="grid-wrap">
	<div align="center" class="user-wrap">
		<form action="loginform.user" method="post" class="user-form">
			<h3 style="color: #3D8EF2;">
				<b>LOGIN</b>
			</h3>
			<p>
				안녕하세요.<br>
				<span>TRAVELER</span>와 함께 <br>여행 사진을 공유해보세요!
			</p>
			<br> <span style="color: red;">${msg }</span><br> <input
				type="text" name="id" placeholder="ID"><br> <input
				type="password" name="pw" placeholder="PASSWORD"><br> <br>
			<input type="submit" value="로그인" class="login_btn">
			<br><br>
				<a href="https://kauth.kakao.com/oauth/authorize?
						client_id=17e8ca8df2a7473f1cdf327f16ae7839&
						redirect_uri=http://localhost:8081/TravelWeb/?cmd=callback&response_type=code">
				<img height="35px" src="../img/kakao_login_medium_narrow.png" />
				</a>
		</form>

	</div>
</section>

<script>
 	var msg = '${msg}';
 	if(msg != ''){
 		alert(msg);
 	}
</script>
<%@ include file="../include/footer.jsp"%>