<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm danh mục - User</title>
    <style>body{font-family:Arial;margin:20px} label{display:block;margin-top:8px}</style>
</head>
<body>
<h2>Thêm danh mục của tôi</h2>
<p><a href="${pageContext.request.contextPath}/user/home">Back</a> | <a href="${pageContext.request.contextPath}/logout">Logout</a></p>
<form method="post" action="${pageContext.request.contextPath}/user/category/add" enctype="multipart/form-data">
    <label>Tên danh mục</label>
    <input type="text" name="categoryname" required />

    <label>Hình ảnh</label>
    <input type="file" name="images" accept="image/*" />

    <button type="submit">Thêm</button>
    <a href="${pageContext.request.contextPath}/user/category" class="text-decoration-none">Hủy</a>
</form>
</body>
</html>