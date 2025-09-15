<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap CSS -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Bootstrap JS -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<!-- Bootstrap Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">CategoryJPA</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/CategoryJPA">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Contact</a>
        </li>
        <li class="nav-item">
          <c:choose>
            <c:when test="${sessionScope.role eq 1}">
              <a class="nav-link" href="${pageContext.request.contextPath}/user/profile">Profile</a>
            </c:when>
            <c:when test="${sessionScope.role eq 2}">
              <a class="nav-link" href="${pageContext.request.contextPath}/manager/profile">Profile</a>
            </c:when>
            <c:when test="${sessionScope.role eq 3}">
              <a class="nav-link" href="${pageContext.request.contextPath}/admin/profile">Profile</a>
            </c:when>
          </c:choose>
        </li>
        <li class="nav-item">
          <a class="nav-link text-danger" href="${pageContext.request.contextPath}/logout">Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

</body>
</html>