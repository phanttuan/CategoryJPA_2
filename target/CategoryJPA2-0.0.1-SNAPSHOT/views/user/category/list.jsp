<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8" />
<title>Quản lý danh mục - User</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet" crossorigin="anonymous">
</head>
<body>
	<div class="container-fluid py-3">
		<div class="role-info alert alert-info d-inline-block mb-3 fw-bold">
			Vai trò đăng nhập: User
		</div>
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h2 class="mb-0">Quản lý danh mục (User)</h2>
			<a class="btn btn-outline-danger"
				href="${pageContext.request.contextPath}/logout">Logout</a>
		</div>
		<div class="card shadow-sm mb-4">
			<div class="card-header bg-primary text-white">
				<h5 class="mb-0">Danh sách danh mục</h5>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table
						class="table table-bordered table-hover align-middle mb-0 text-center"
						style="min-width: 700px;">
						<thead class="table-light">
							<tr>
								<th style="width: 60px;">ID</th>
								<th style="width: 30%;">Tên danh mục</th>
								<th style="width: 120px;">Hình ảnh</th>
								<th style="min-width: 180px;">Hành động</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="cate" items="${categories}">
								<tr>
									<td>${cate.id}</td>
									<td class="text-start">${cate.categoryname}
									</td>
									<td>
										<c:choose>
											<c:when test="${not empty cate.images}">
												<img
													src="${pageContext.request.contextPath}/image?fname=${cate.images}"
													alt="image"
													style="height: 40px; border-radius: 4px; object-fit: cover; max-width: 80px;" />
											</c:when>
											<c:otherwise>-</c:otherwise>
										</c:choose>
									</td>
									<td>
										<div class="d-flex justify-content-center gap-2">
											<c:if test="${cate.user.id == sessionScope.user.id}">
												<a
													href="${pageContext.request.contextPath}/user/category/edit?id=${cate.id}"
													class="btn btn-sm btn-warning">Cập nhật</a>
												<a
													href="${pageContext.request.contextPath}/user/category/delete?id=${cate.id}"
													class="btn btn-sm btn-danger"
													onclick="return confirm('Xóa danh mục này?')">Xóa</a>
											</c:if>
											<c:if test="${cate.user.id != sessionScope.user.id}">
												<span class="text-muted">Không thể sửa/xóa
												</span>
											</c:if>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="card shadow-sm" style="max-width:400px;margin:auto;">
			<div class="card-header bg-info text-white">
				<h5 class="mb-0">Thêm danh mục</h5>
			</div>
			<div class="card-body">
				<form method="post"
					action="${pageContext.request.contextPath}/user/category/add"
					enctype="multipart/form-data">
					<div class="mb-3">
						<label class="form-label">Tên danh mục</label> <input
							type="text" class="form-control" name="categoryname" required />
					</div>
					<div class="mb-3">
						<label class="form-label">Hình ảnh</label> <input type="file"
							class="form-control" name="images" accept="image/*" />
					</div>
					<button type="submit" class="btn btn-success w-100">Thêm</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>