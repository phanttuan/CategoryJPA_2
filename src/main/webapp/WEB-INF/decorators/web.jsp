<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap CSS CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <header class="mb-4">
            <%@ include file="/commons/web/header.jsp"%>
        </header>
        <main>
            <div class="row justify-content-center">
                <div class="col-lg-8 col-md-10">
                    <div class="card shadow-lg rounded-4 border-0 my-4 bg-white bg-opacity-75">
                        <div class="card-body p-5">
                            <sitemesh:write property="body" />
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <footer class="mt-4">
            <%@ include file="/commons/web/footer.jsp"%>
        </footer>
    </div>
    <!-- Bootstrap JS Bundle CDN -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoA6DQD1P1QnYh6N0lKpHnE6Q0k0p1hQF0Q5F5F5F5F5F5F" crossorigin="anonymous"></script>
</body>
</html>