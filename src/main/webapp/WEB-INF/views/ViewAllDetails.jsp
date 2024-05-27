<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page isELIgnored="false"%>
<%@ page import="com.div.util.ImageUtil"%>

<!DOCTYPE html>
<html>
<head>
<title>View All Details</title>
<style>
.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #f1f1f1
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	background-color: #334FFF
}
</style>
</head>
<body>
	<h2>View All Details</h2>

	<form action="viewAll" method="get">
		<label for="searchTitle">Search by Title:</label> <input type="text"
			id="searchTitle" name="searchTitle"
			value="${searchCriteria.searchTitle}"> <input type="hidden"
			name="sortField" value="${searchCriteria.sortField}"> <input
			type="hidden" name="sortOrder" value="${searchCriteria.sortOrder}">
		<input type="submit" value="Search">
	</form>

	<table border="1">
		<thead>
			<tr>
				<th>Title
					<div class="dropdown">
						<button class="dropbtn">Sort</button>
						<div class="dropdown-content">
							<a
								href="viewAll?searchTitle=${searchCriteria.searchTitle}&sortField=title&sortOrder=asc">Ascending</a>
							<a
								href="viewAll?searchTitle=${searchCriteria.searchTitle}&sortField=title&sortOrder=desc">Descending</a>
						</div>
					</div>
				</th>
				<th>Public URL
					<div class="dropdown">
						<button class="dropbtn">Sort</button>
						<div class="dropdown-content">
							<a
								href="viewAll?searchTitle=${searchCriteria.searchTitle}&sortField=publicURL&sortOrder=asc">Ascending</a>
							<a
								href="viewAll?searchTitle=${searchCriteria.searchTitle}&sortField=publicURL&sortOrder=desc">Descending</a>
						</div>
					</div>
				</th>
				<th>Access Category
					<div class="dropdown">
						<button class="dropbtn">Sort</button>
						<div class="dropdown-content">
							<a
								href="viewAll?searchTitle=${searchCriteria.searchTitle}&sortField=accessCategory&sortOrder=asc">Ascending</a>
							<a
								href="viewAll?searchTitle=${searchCriteria.searchTitle}&sortField=accessCategory&sortOrder=desc">Descending</a>
						</div>
					</div>
				</th>
				<th>Published Date</th>
				<th>Banner</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="detail" items="${details}">
				<tr>
					<td>${detail.title}</td>
					<td>${detail.publicURL}</td>
					<td>${detail.accessCategory}</td>
					<td><fmt:formatDate value="${detail.publishedDate}"
							pattern="yyyy-MM-dd" /></td>
					<td><c:if test="${not empty detail.banner}">
							<img
								src="${ImageUtil.getBase64EncodedImage(detail.banner, detail.contentType)}"
								width="120" height="100" />
						</c:if></td>
					<td><a href="deleteDetail?id=${detail.id}" class="btn-delete">Delete</a>
						<a href="editDetail?id=${detail.id}" class="btn-edit">Edit</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<form action="signup" method="get"
		style="text-align: center; margin-top: 20px;">
		<input type="submit" value="Home" />
	</form>
	<form action="addDetails" method="get"
		style="text-align: center; margin-top: 20px;">
		<input type="submit" value="Go To Form">
	</form>
</body>
</html>