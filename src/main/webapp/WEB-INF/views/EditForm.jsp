<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.div.dto.UserDTO" %>
<%@page isELIgnored="false"%>
<%@ page import="com.div.util.ImageUtil"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Details</title>
</head>
<body>
    <h2>Edit Details</h2>
    <% UserDTO user = (UserDTO) session.getAttribute("loggedInUser"); %>
    <h3>Welcome, <%= user.getUsername() %></h3>
    
    <form action="updateDetails" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${formDetail.id}"/>
        <input type="hidden" name="userId" value="${user.id}"/>
        
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" value="${formDetail.title}"/><br/>
        
        <label for="speakers">Speakers:</label>
        <input type="text" id="speakers" name="speakers" value="${formDetail.speakers}"/><br/>
        
        <label for="publicURL">Public URL:</label>
        <input type="text" id="publicURL" name="publicURL" value="${formDetail.publicURL}"/><br/>
        
        <label for="videoDate">Video Date:</label>
        <input type="date" id="videoDate" name="videoDate" value="${formDetail.videoDate}"/><br/>
        
        <label for="publishedDate">Published Date:</label>
        <input type="date" id="publishedDate" name="publishedDate" value="${formDetail.publishedDate}"/><br/>
        
        <label for="description">Description:</label>
        <textarea id="description" name="description">${formDetail.description}</textarea><br/>
        
        <label for="synopsis">Synopsis:</label>
        <textarea id="synopsis" name="synopsis">${formDetail.synopsis}</textarea><br/>
        
        <label for="banner">Current Banner:</label>
        <c:if test="${not empty formDetail.banner}">
            <img src="${ImageUtil.getBase64EncodedImage(formDetail.banner, formDetail.contentType)}" width="50" height="50" />
        </c:if></br>
        
        <label for="banner">Update Banner:</label>
        <input type="file" id="banner" name="banner"/><br/>
        
        <label for="videoUrl">Video URL:</label>
        <input type="text" id="videoUrl" name="videoUrl" value="${formDetail.videoUrl}"/><br/>
        
        <label for="previewVideoUrl">Preview Video URL:</label>
        <input type="text" id="previewVideoUrl" name="previewVideoUrl" value="${formDetail.previewVideoUrl}"/><br/>
        
       <label for="accessCategory">Access Category:</label>
	   <select id="accessCategory" name="accessCategory" required>
       <option value="free" ${formDetail.accessCategory == 'free' ? 'selected' : ''}>Free</option>
       <option value="Premium" ${formDetail.accessCategory == 'Premium' ? 'selected' : ''}>Premium</option>
	   </select><br/>

        
        <label for="freeViewExpiry">Free View Expiry:</label>
        <input type="date" id="freeViewExpiry" name="freeViewExpiry" value="${formDetail.freeViewExpiry}"/><br/>
        
        <label for="isPremium">Premium:</label>
        <input type="checkbox" id="isPremium" name="isPremium" ${formDetail.isPremium() ? 'checked' : ''}/><br/>
        
        <input type="submit" value="Submit"/>
    </form>
    <form action="viewAll" method="get" style="text-align: center; margin-top: 20px;">
        <input type="submit" value="View All"/>
    </form>
</body>
</html>
