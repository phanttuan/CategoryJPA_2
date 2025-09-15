<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Video</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Chỉnh sửa Video</h2>
    <form action="${pageContext.request.contextPath}/admin/video" method="post">
        <input type="hidden" name="action" value="edit"/>
        <input type="hidden" name="id" value="${video.id}"/>
        <div class="mb-3">
            <label class="form-label">Tiêu đề:</label>
            <input type="text" class="form-control" name="title" value="${video.title}" required/>
        </div>
        <div class="mb-3">
            <label class="form-label">Mô tả:</label>
            <textarea class="form-control" name="description" rows="3">${video.description}</textarea>
        </div>
        <div class="mb-3">
            <label class="form-label">URL:</label>
            <input type="text" class="form-control" name="url" value="${video.url}" required/>
        </div>
        <button type="submit" class="btn btn-warning">Cập nhật Video</button>
    </form>
    <a href="${pageContext.request.contextPath}/admin/video" class="btn btn-secondary mt-2">Quay lại danh sách</a>
</div>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
</body>
</html>