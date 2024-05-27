<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>CSV Import Status</title>
</head>
<body>
	<h2>${message}</h2>
	<c:if test="${not empty stackTrace}">
		<pre>
        <c:forEach items="${stackTrace}" var="trace">
            ${trace}<br />
        </c:forEach>
    </pre>
	</c:if>
	<a href="upload-csv">Upload Another File</a>
	
	    <form action="viewAll" method="get" style="text-align: center; margin-top: 20px;">
        <input type="submit" value="View All"/>
    </form>
</body>
</html>