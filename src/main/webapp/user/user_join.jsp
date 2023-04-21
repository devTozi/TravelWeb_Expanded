<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<section class="grid-wrap">

	<div align = "center" class = "user-wrap">
		<form action="joinform.user" method="post" class = "user-form">
			
			<h3 style="color:#3D8EF2;">JOIN</h3>
			<p>
				반가워요!<br>
				<span>TRAVELER</span>를 이용하시려면<br>
				가입 부탁드려요!
			</p>
			<br>
				<div style="color:red;">${msg }</div>
				
				<input type="text" name="id" placeholder="아이디" pattern="\w{4,8}" required><br>
				&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;<span>* 영어 대문자, 소문자 4글자 이상 8글자 이하</span><br><br>
				
				<input type="password" name="pw" placeholder="비밀번호" pattern="\w{8,10}" required="required"><br>
				&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;<span>* 영어 대문자,소문자 8글자 이상 10글자 이하</span><br><br>
				
				<input type="text" name="name" placeholder="이름 5글자 이하" pattern="[가-힣]{,5}" required="required"><br>
				<br>
					
				<input type="email" name="email" placeholder="이메일" required="required"><br><br>
			
				<span>성별&emsp;</span> <input type="radio" name="gender" value="f" checked> 여자 
				<input type="radio" name="gender" value="m"> 남자<br><br>
			
				<input type="number" name="birth" placeholder="생년월일 ex) 991023" pattern="{6,}" required><br><br>
				
				프로필 이미지<br>
				<input type="file" accept="image/jpeg" name="profileimage" value="프로필이미지"><br><br>
				
				<input type="submit" value="가입하기" class="btn btn-success">
			
		</form>
	</div>
</section>

<!-- 화면에서 사용할 JS -->
<script>
 	var msg = '${msg}';
 	if(msg != ''){
 		alert(msg);
 	}
</script>

<%@ include file="../include/footer.jsp"%>