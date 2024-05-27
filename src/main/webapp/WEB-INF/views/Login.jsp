<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<title>Login</title>
</head>
<body>
	<h2>Login</h2>
    <form action="Login" method="post">
        <label>Email : </label>
        <input type="text" name="email" /><br/>

        <label>Password : </label>
        <input type="password" name="password" /><br/>

        <input type="submit" value="Login"/>
    </form>
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
    <button><a href="signup">Signup</a></button>
</body>
</html>
