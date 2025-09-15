<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <title>Quản lý người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { font-family: Arial, sans-serif; margin: 24px; }
        .actions a { margin-right: 8px; }
    </style>
</head>
<body>
<div class="container-fluid py-3">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="mb-0">Quản lý người dùng</h2>
        <div>
            <a class="btn btn-outline-primary me-2" href="${pageContext.request.contextPath}/admin/category">Quản lý Danh mục</a>
            <a class="btn btn-success" href="${pageContext.request.contextPath}/admin/user?action=add">Thêm người dùng</a>
        </div>
    </div>
    <div class="card shadow-sm">
        <div class="card-header bg-primary text-white">
            <h5 class="mb-0">Danh sách người dùng</h5>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle mb-0 text-center">
                    <thead class="table-light">
                        <tr>
                            <th style="width: 60px;">ID</th>
                            <th>Tên đăng nhập</th>
                            <th>Vai trò</th>
                            <th style="min-width: 180px;">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.id}</td>
                                <td class="text-start">${user.username}</td>
                                <td>${user.role}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/user?action=edit&id=${user.id}" class="btn btn-sm btn-warning">Cập nhật</a>
                                    <a href="${pageContext.request.contextPath}/admin/user?action=delete&id=${user.id}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc muốn xóa người dùng này?')">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>