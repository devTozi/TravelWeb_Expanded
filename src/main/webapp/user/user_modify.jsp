<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<section>
	<div align = "center" class = "user-wrap">

		<form action="updateform.user" method="post" class = "user-form">

			<table>
				<h3>EDIT PROFILE</h3>
				<tr>
					<td><input type="text" name="id" placeholder="아이디"
						value="${vo.id}" pattern="\w{4,8}" required readonly> <br>
						<span>*아이디는 영어 대문자,소문자, 4글자 이상 8글자 이하</span>
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" name="pw" placeholder="비밀번호"
						pattern="\w{8,10}" required="required"> <br>
						<span>*비밀번호는 영어 대문자,소문자, 8글자 이상 10글자 이하</span>
					</td>
						
				</tr>
				<tr>
					<td><input type="text" name="name" placeholder="이름"
						pattern="[가-힣]{,5}" value="${vo.name}" required="required"> <br>
						<span>*이름은 한글로 5글자이하</span>
					<td>
				</tr>
					
				<tr>
					<td><input type="email" name="email" placeholder="이메일"
						value="${vo.email}" required="required" readonly></td>
				</tr>
				
				<tr>
					<td>
					<input type="radio" name="gender" value="f" ${vo.gender == 'f' ? 'checked' : '' }>여자
					<input type="radio" name="gender" value="m" ${vo.gender == 'm' ? 'checked' : '' }>남자
					</td>
				</tr>
				<tr>
					<td><input type="number" name="birth"
						value="${vo.birth}" required="required" readonly></td>
				</tr>
				<!-- 프로필 이미지 -->
				<tr>
					<td>프로필 이미지 수정 <input type="file" accept="image/jpeg"
						name="profileimage">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="수정하기" class="btn btn-success">
					<td>
				</tr>
			</table>

		</form>
	</div>
</section>

<%@ include file="../include/footer.jsp"%>