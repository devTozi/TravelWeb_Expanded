<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>

<div>

	<!-- 좋아요한 게시글 -->
	<section class="grid-wrap">
		<h3>&#128149;좋아요&nbsp;한 게시글</h3>
		<hr style="height: 3px; border: none; background-color: #D8D8D8">
		
		<ul class="grid swipe-right" id="grid">
			<c:if test="${likeList.size() == 0 }">
				<br>
				<br>
				<br>
				<br>
				<br>
				<h3 align="center">❤좋아요 한 게시글이 없습니다.</h3>
				<div align="center"></div>
			</c:if>

			<c:forEach var="vo" items="${likeList }" varStatus="num">
				<li><a href="board_content.board?bno=${vo.bno }"> <img
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