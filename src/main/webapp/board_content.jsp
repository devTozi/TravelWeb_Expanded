<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../include/header.jsp"%>

<% boolean check = (boolean)request.getAttribute("check"); %>

<div align="center" class="div_center">

	<h3>${vo.title }</h3>
	<table border="1" width="500">

		<tr>
			<td>작성자</td>
			<td>${vo.id }</td>

			<td>작성일시</td>
			<td><fmt:formatDate value="${vo.regdate }"
					pattern="yyyy-MM-dd HH:mm" /></td>
		</tr>
		<tr>
			<td width="20%">좋아요</td>
			<td width="30%">${vo.likes }</td>

			<td width="20%">조회수</td>
			<td width="30%">${vo.hit }</td>
		</tr>

		<tr>
			<td width="20%">사진</td>
			<td colspan="3" height="120px">
			<img src="<%= request.getContextPath() %>${vo.image}" alt="dummy" style="width:400px; height:300px;">
			</td>
		</tr>
		<tr>
			<td width="20%">글내용</td>
			<td colspan="3" height="120px">${vo.content }</td>
		</tr>

		<tr>
			<td colspan="4" align="center">
			
				<c:if test="${sessionScope.user_id != null && sessionScope.user_id != vo.id }">
					<c:if test="${!check }">
					<input type="button" value="❤좋아요" onclick="location.href='board_like_insert.board?bno=${vo.bno}' ">
					</c:if>
					<c:if test="${check}">
					<input type="button" value="❤좋아요 취소" onclick="location.href='board_like_delete.board?bno=${vo.bno}' ">
					</c:if>
				</c:if> 
				
				&nbsp;&nbsp;
				<input type="button" value="전체목록" onclick="location.href='index.board' ">
				&nbsp;&nbsp;
				<c:if test="${sessionScope.user_id eq vo.id }">
					<input type="button" value="수정"
						onclick="location.href='board_modify.board?bno=${vo.bno}&id=${vo.id }' ">&nbsp;&nbsp;
					<input type="button" value="삭제"
						onclick="location.href='board_delete.board?bno=${vo.bno}&id=${vo.id }' ">&nbsp;&nbsp;
				</c:if>
				
			</td>
		</tr>
	</table>
	<br>
	
	<h3>댓글</h3>
	<!-- 댓글 등록 / cno는 hidden 처리 -->
	<c:if test="${sessionScope.user_id != null }">
		
		<form action="board_add_comment.board?bno=${vo.bno }" method="post">
			<table border="1" width="500">
				<tr>
					<td colspan=2>작성자: ${sessionScope.user_id}</td>
				</tr>
				<tr>
					<td ><input type="text" name="comment"
						placeholder="댓글을 입력해주세요." required size=50; ></td>
					<td align="center"><input type="submit" name="comment"
						value="등록"></td>
				</tr>
			</table>
		</form>
	</c:if>
	<br>
	<!-- 댓글 목록 -->
	<c:if test="${commentsList != null }">
		<c:forEach var="cvo" items="${commentsList }" varStatus="num">
			<table border="1" width="500">
				<tr>
					<td colspan=2>&nbsp;&nbsp;•&nbsp;${cvo.id}&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd HH:mm" />
					</td>
				</tr>
				<tr>
					<td>${cvo.content }</td>
					<td width="25%" align="center"><c:if test="${sessionScope.user_id eq cvo.id  }">
							<input type="button" value="수정"
								onclick="location.href='comment_modify.board?bno=${vo.bno}&id=${vo.id }' ">&nbsp;&nbsp;
					<input type="button" value="삭제"
								onclick="location.href='comment_delete.board?bno=${vo.bno}&id=${vo.id }' ">&nbsp;&nbsp;
					</c:if></td>
				</tr>
			</table>
		</c:forEach>
	</c:if>
</div>

<%@ include file="../include/footer.jsp"%>
