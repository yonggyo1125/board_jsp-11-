<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="scriptless" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@ attribute name="siteTitle" %>
<fmt:setBundle basename="messages.common" />
<layout:common siteTitle="${siteTitle}">
<jsp:attribute name="header">
	<section id="site_top">
		<div class='left'>
			<a href="<c:url value='/' />">HOME</a>
		</div>
		<div class='right'>
			<c:if test="${isLogin}">
				<fmt:message key="member.login.status">
					<fmt:param value="${sessionScope.member.userNm}" />
					<fmt:param value="${sessionScope.member.userId}" />
				</fmt:message>
				
				<a href="<c:url value='/member/logout' />">
					<fmt:message key="member.logout" />
				</a>
			</c:if>
			<c:if test="${!isLogin}">
				<a href="<c:url value='/member/login' />">
					<fmt:message key="member.login" />
				</a>
				<a href="<c:url value='/member/join' />">
					<fmt:message key="member.jointitle" />
				</a>
			</c:if>
		</div>
	</section>	

</jsp:attribute>
<jsp:attribute name="footer">
	<h1>푸터</h1>
</jsp:attribute>
<jsp:body>
	<jsp:doBody />	
</jsp:body>
</layout:common>