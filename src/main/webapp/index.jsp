<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>

<div>
	<section class="grid-wrap">
		
		<div align="right">
			<form action="index.board" class="form-inline">
				<input type="text" name="search" placeholder="제목 or 내용 입력" required>
				<input type="submit" value="검색" class="btn btn-default"> <input
					type="button" value="글쓰기" class="btn btn-default"
					onclick="location.href= 'board_write.board' ">
			</form>
		</div>
		
		<form action="board_order.board" method="post">
			<select name="order">
				<option value="hit">조회수순</option>
				<option value="likes">❤좋아요순</option>
				<option value="bno">오래된순</option>
			</select> <input type="submit" value="정렬">
		</form>
		
		<!-- 게시글 라인 -->
		<ul class="grid swipe-right" id="grid">
		
			<c:if test="${list.size() == 0 }">
				<br>
				<br>
				<br>
				<br>
				<br>
				<h3 align="center">등록된 게시글이 없습니다.</h3>
				<div align="center">
					<a class="current-demo" href="board_write.board"><b>▶글 쓰러
							가기</b></a>
				</div>
			</c:if>
		
			<c:forEach var="vo" items="${list }" varStatus="num">
				<li><a href="${pageContext.request.contextPath}/board_content.board?bno=${vo.bno }"> <img
						src="<%= request.getContextPath() %>${vo.image}" alt="dummy"
						style="width: 314px; height: 380px;">
						<h3>${vo.title}<span class="id" align="right">${vo.id}</span>
						</h3> <span class="like" style="color: LightCoral;">❤${vo.likes}</span>
				</a></li>
			</c:forEach>
		</ul>

	</section>
</div>

<%@ include file="/include/footer.jsp"%>