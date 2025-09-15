<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Category" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Home - Category List</title>
</head>
<body>
    <h2>All Categories</h2>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Image</th>
            <th>Owner</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td>${category.id}</td>
                <td>${category.categoryname}</td>
                <td>
                    <c:if test="${not empty category.images}">
                        <img src="${pageContext.request.contextPath}/uploads/${category.images}" width="60"/>
                    </c:if>
                </td>
                <td>${category.user.username}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/user/category/view?id=${category.id}">View</a>
                    <c:if test="${category.user.id == sessionScope.user.id}">
                        | <a href="${pageContext.request.contextPath}/user/category/edit?id=${category.id}">Edit</a>
                        | <a href="${pageContext.request.contextPath}/user/category/delete?id=${category.id}" onclick="return confirm('Delete?')">Delete</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="${pageContext.request.contextPath}/user/category/add">Add Category</a>
</body>
</html>
