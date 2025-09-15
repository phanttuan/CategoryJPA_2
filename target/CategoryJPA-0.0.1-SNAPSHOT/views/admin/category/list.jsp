<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>

<html lang="vi">
<head>
<meta charset="UTF-8" />
<title>Quản lý danh mục</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+Knujsl5/5hb7xZ5/5hb7xZ5/5hb7xZ5/5hb7xZ5/5hb7xZ5"
	crossorigin="anonymous">
<style>
body {
	font-family: Arial, sans-serif;
	margin: 24px;
}

.container {
	display: grid;
	grid-template-columns: 2fr 1fr;
	gap: 24px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
}

th {
	background: #eaf3ff;
}

.actions a {
	margin-right: 8px;
}

.panel {
	border: 1px solid #c6d5e3;
	border-radius: 6px;
}

.panel h3 {
	margin: 0;
	padding: 10px 12px;
	background: #d7ebf7;
}

.panel .body {
	padding: 12px;
}

input[type=text] {
	width: 100%;
	padding: 8px;
	margin: 6px 0 12px;
	box-sizing: border-box;
}

button {
	padding: 8px 14px;
}
</style>
</head>
<body>
	<div class="container-fluid py-3">
		<div class="role-info alert alert-warning d-inline-block mb-3 fw-bold">
			Vai trò đăng nhập:
			<c:choose>
				<c:when test="${sessionScope.role eq 1}">User</c:when>
				<c:when test="${sessionScope.role eq 2}">Manager</c:when>
				<c:when test="${sessionScope.role eq 3}">Admin</c:when>
				<c:otherwise>Không xác định</c:otherwise>
			</c:choose>
		</div>
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h2 class="mb-0">Quản lý danh mục</h2>
			<a class="btn btn-outline-danger"
				href="${pageContext.request.contextPath}/logout">Logout</a>
		</div>
		<div class="row g-4">
			<!-- Danh sách danh mục -->
			<div class="col-12">
				<div class="card shadow-sm h-100">
					<div class="card-header bg-primary text-white">
						<h5 class="mb-0">Danh sách danh mục</h5>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table
								class="table table-bordered table-hover align-middle mb-0 text-center">
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
											<td class="text-start">${cate.categoryname}</td>
											<td><c:choose>
													<c:when test="${not empty cate.images}">
														<img
															src="${pageContext.request.contextPath}/image?fname=${cate.images}"
															alt="image"
															style="height: 32px; border-radius: 4px; object-fit: cover; max-width: 60px;" />
													</c:when>
													<c:otherwise>-</c:otherwise>
												</c:choose></td>
											<td>
												<div
													class="d-flex flex-nowrap justify-content-center align-items-center gap-2">
													<c:if test="${cate.user.id == sessionScope.user.id}">
														<a
															href="<c:url value='/admin/category/edit?id=${cate.id}'/>"
															class="btn btn-sm btn-warning">Cập nhật</a>
														<a
															href="<c:url value='/admin/category/delete?id=${cate.id}'/>"
															class="btn btn-sm btn-danger"
															onclick="return confirm('Xóa danh mục này?')">Xóa</a>
													</c:if>
													<c:if test="${cate.user.id != sessionScope.user.id}">
														<span class="text-muted">Không thể sửa/xóa</span>
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
			</div>

			<!-- Thêm / Cập nhật danh mục -->
			<div class="col-12">
				<div class="card shadow-sm h-100">
					<div class="card-header bg-info text-white">
						<h5 class="mb-0">
							<c:if test="${empty category}">Thêm danh mục</c:if>
							<c:if test="${not empty category}">Cập nhật danh mục</c:if>
						</h5>
					</div>
					<div class="card-body">
						<c:set var="isEdit" value="${not empty category}" />
						<form method="post"
							action="${pageContext.request.contextPath}<c:out value='${isEdit ? "/admin/category/update" : "/admin/category/add"}'/>">
							<c:if test="${isEdit}">
								<input type="hidden" name="id" value="${category.id}" />
							</c:if>
							<div class="mb-3">
								<label class="form-label">Tên danh mục</label> <input
									type="text" class="form-control" name="categoryname"
									value="<c:out value='${isEdit ? category.categoryname : ""}'/>"
									required />
							</div>
							<div class="mb-3">
								<label class="form-label">Hình ảnh (tên file)</label> <input
									type="text" class="form-control" name="images"
									value="<c:out value='${isEdit ? category.images : ""}'/>" />
							</div>
							<button type="submit" class="btn btn-success w-100">Submit</button>
							<c:if test="${isEdit}">
								<a href="${pageContext.request.contextPath}/admin/category"
									class="btn btn-secondary w-100 mt-2">Hủy</a>
							</c:if>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-kenU1KFdBIe4zVF8yU8z8+K+2z5/5hb7xZ5/5hb7xZ5/5hb7xZ5/5hb7xZ5/5hb7xZ5"
			crossorigin="anonymous"></script>
</body>
</html>