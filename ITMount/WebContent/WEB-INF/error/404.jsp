<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${ course.name }course</title>
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />

	<!-- Page Title -->
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>Page Not Found...</h1>
				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div class="error-page-wrapper">
						<p>Sorry, the page you are looking for is not here or never
							was...</p>
						<p>
							Why don't you try the <a href="index.html">Homepage</a>?
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="../page/footer.jsp" />
	<jsp:include page="../page/script.jsp" />

</body>
</html>