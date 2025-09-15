<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <title>Thông tin cá nhân</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">
<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-7 col-lg-6">
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">Thông tin cá nhân</h4>
                </div>
                <div class="card-body">
                    <form method="post" action="${pageContext.request.contextPath}/manager/profile" enctype="multipart/form-data">
                        <div class="mb-3 text-center">
                            <c:choose>
                                <c:when test="${not empty user.images}">
                                    <div style="width:120px;height:120px;display:inline-block;overflow:hidden;border-radius:12px;border:2px solid #dee2e6;background:#f8f9fa;">
                                        <img src="${pageContext.request.contextPath}/image?fname=${user.images}" alt="avatar" style="width:100%;height:100%;object-fit:cover;object-position:center;"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div style="width:120px;height:120px;display:inline-block;overflow:hidden;border-radius:12px;border:2px solid #dee2e6;background:#f8f9fa;">
                                        <img src="https://via.placeholder.com/120x120?text=Avatar" style="width:100%;height:100%;object-fit:cover;object-position:center;"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Tên đăng nhập</label>
                            <input type="text" class="form-control" value="${user.username}" readonly />
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Họ tên</label>
                            <input type="text" class="form-control" name="fullname" value="${user.fullname}" required />
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Số điện thoại</label>
                            <input type="text" class="form-control" name="phone" value="${user.phone}" />
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Ảnh đại diện (chọn file mới nếu muốn thay đổi)</label>
                            <input type="file" class="form-control" name="imageFile" accept="image/*" />
                        </div>
                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-success">Cập nhật</button>
                            <a href="${pageContext.request.contextPath}/manager/category" class="btn btn-secondary">Quay lại</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>