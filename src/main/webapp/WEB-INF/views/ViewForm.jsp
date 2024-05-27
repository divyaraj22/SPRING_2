<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.div.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Details</title>
</head>
<body>
    <h2>Add Details</h2>
    <% UserDTO user = (UserDTO) session.getAttribute("loggedInUser"); %>
    <h3>Welcome, <%= user.getUsername() %></h3>
    
    <form action="addDetails" method="post" enctype="multipart/form-data">
        <input type="hidden" name="userId" value="${user.id}"/>
        <label for="title">Title:</label>
        <input type="text" id="title" name="title"/><br/>

        <label for="speakers">Speakers:</label>
        <input type="text" id="speakers" name="speakers"/><br/>

        <label for="publicURL">Public URL:</label>
        <input type="text" id="publicURL" name="publicURL"/><br/>
        
        <label for="videoDate">Video Date:</label>
        <input type="date" id="videoDate" name="videoDate"/><br/>
        
        <label for="publishedDate">Published Date:</label>
        <input type="date" id="publishedDate" name="publishedDate"/><br/>

        <label for="description">Description:</label>
        <textarea id="description" name="description"></textarea><br/>

        <label for="synopsis">Synopsis:</label>
        <textarea id="synopsis" name="synopsis"></textarea><br/>

        <label for="banner">Banner:</label>
        <input type="file" id="banner" name="banner" accept="image/jpeg, image/png, image/jpg"/><br/>

        <label for="videoUrl">Video URL:</label>
        <input type="text" id="videoUrl" name="videoUrl"/><br/>
        
        <label for="previewVideoUrl">Preview Video URL:</label>
        <input type="text" id="previewVideoUrl" name="previewVideoUrl"/><br/>

        <label for="accessCategory">Access Category:</label>
        <select id="accessCategory" name="accessCategory" required>
            <option value="free">Free</option>
            <option value="Premium">Premium</option>
        </select><br/>

        <label for="freeViewExpiry">Free View Expiry:</label>
        <input type="date" id="freeViewExpiry" name="freeViewExpiry"/><br/>
        
		<label for="premiumCheckbox">Is Premium:</label>
        <input type="checkbox" id="premiumCheckbox" name="premiumCheckbox"/><br/>
	
        <input type="submit" value="Submit"/>
    </form>
    <form action="viewAll" method="get" style="text-align: center; margin-top: 20px;">
        <input type="submit" value="View All"/>
    </form>
        <form action="upload-csv" method="get" style="text-align: center; margin-top: 20px;">
        <input type="submit" value="Add CSV File"/>
    </form>
</body>
</html>