<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/include/header.jsp"%>

<div align="center" class="div_center">
	<h3>글 수정</h3>
	

	<form action="updateform.board" method="post">
		<input type="hidden" name="bno" value="${vo.bno }">
		
		<table border="1" width="600">
			
			<tr>
				<td align="center"><b>작성자 [${sessionScope.user_id }]</b></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="text" name="title" size="65" value="${vo.title }" required>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<textarea rows="10" style="width: 100%;" name="content" placeholder="수정 내용을 입력해주세요."></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">사진 등록 &nbsp;&nbsp;
				<input type="file" name="image" value="사진 등록">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정" class="btn btn-success">&nbsp;&nbsp;
					<input type="button" value="취소" class="btn btn-success"
					onclick="location.href='index.board'">
				</td>
			</tr>
		</table>
	</form>
				
			
</div>

<%@ include file="/include/footer.jsp"%>