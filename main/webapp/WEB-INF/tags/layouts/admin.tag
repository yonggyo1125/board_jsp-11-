<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="scriptless" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="siteTitle" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<fmt:setBundle basename="messages.common" />
<layout:common siteTitle="${siteTitle}" isAdmin="true">
	<jsp:attribute name="header">
		<section id="admin_menu">
			<a href="<c:url value='/admin/board' />" class="menu${menuCode == 'boardList' ? ' on' : ''}">
				<i class='xi-list'></i> 게시판 목록
			</a>
			<a href="<c:url value='/admin/board/config' />" class="menu${menuCode == 'boardRegister' ? ' on' : ''}">
				<i class='xi-plus'></i> 게시판 등록
			</a>
		</section>
	</jsp:attribute>
	<jsp:attribute name="footer">
	
	</jsp:attribute>
	<jsp:body>
		<jsp:doBody />
	</jsp:body>
</layout:common>