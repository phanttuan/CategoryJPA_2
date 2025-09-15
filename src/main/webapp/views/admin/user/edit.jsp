<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <title>Cập nhật người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-header bg-warning text-white">
                    <h4 class="mb-0">Cập nhật người dùng</h4>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/admin/user" method="post">
                        <input type="hidden" name="action" value="edit"/>
                        <input type="hidden" name="id" value="${user.id}"/>
                        <div class="mb-3">
                            <label class="form-label">Tên đăng nhập</label>
                            <input type="text" class="form-control" name="username" value="${user.username}" required/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Mật khẩu</label>
                            <input type="password" class="form-control" name="password" value="${user.password}" required/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Vai trò</label>
                            <select class="form-select" name="role">
                                <option value="USER" <c:if test="${user.role eq 'USER'}">selected</c:if>>User</option>
                                <option value="MANAGER" <c:if test="${user.role eq 'MANAGER'}">selected</c:if>>Manager</option>
                                <option value="ADMIN" <c:if test="${user.role eq 'ADMIN'}">selected</c:if>>Admin</option>
                            </select>
                        </div>
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-warning">Cập nhật</button>
                            <a href="${pageContext.request.contextPath}/admin/user" class="btn btn-secondary">Quay lại danh sách</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>