<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="empty" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="value" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="checkedValue"  %>
<% if (checkedValue != null && checkedValue.indexOf(value) != -1)  {%>
<c:set var="checked" value=" checked" />
<% } %>
<input type="checkbox" name="${name}" value="${value}" id="${name}_${value}"${checked}>
<label for="${name}_${value}">${label}</label>
