<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Upload CSV File</title>
</head>
<body>
    <h2>Upload CSV File</h2>
    <form method="post" action="import-csv" enctype="multipart/form-data">
        <label for="file">Choose CSV File:</label>
        <input type="file" id="file" name="file" accept=".csv" required />
        <button type="submit">Upload</button>
    </form>
    <form action="viewAll" method="get" style="text-align: center; margin-top: 20px;">
        <input type="submit" value="View All"/>
    </form>
</body>
</html>