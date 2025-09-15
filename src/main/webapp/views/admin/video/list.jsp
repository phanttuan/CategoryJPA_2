<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quản lý Video</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">
<div class="container my-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="mb-0">Danh sách Video</h2>
        <a href="${pageContext.request.contextPath}/admin/video?action=add" class="btn btn-success">+ Thêm Video mới</a>
    </div>
    <form method="get" action="${pageContext.request.contextPath}/admin/video" class="row g-2 mb-3">
        <input type="hidden" name="action" value="search"/>
        <div class="col-auto">
            <input type="text" name="keyword" class="form-control" placeholder="Tìm kiếm theo tiêu đề"/>
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
        </div>
    </form>
    <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle text-center bg-white">
            <thead class="table-light">
            <tr>
                <th>ID</th>
                <th>Tiêu đề</th>
                <th>Mô tả</th>
                <th>URL</th>
                <th>Ngày tạo</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="video" items="${videos}">
                <tr>
                    <td>${video.id}</td>
                    <td class="text-start">${video.title}</td>
                    <td class="text-start">${video.description}</td>
                    <td><a href="${video.url}" target="_blank">${video.url}</a></td>
                    <td>${video.createdDate}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/video?action=edit&id=${video.id}" class="btn btn-warning btn-sm">Sửa</a>
                        <form action="${pageContext.request.contextPath}/admin/video" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="id" value="${video.id}"/>
                            <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa video này?');">Xóa</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>