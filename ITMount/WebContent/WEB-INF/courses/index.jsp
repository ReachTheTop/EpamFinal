<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Courses</title>
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />



	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>Courses</h1>
					<a href="<c:url value="/CourseServlet?action=new" />">Create
						New Course</a>

				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row">
				<c:forEach items="${courses }" var="course">
					<div class="col-md-4 col-sm-6">
						<div class="service-wrapper">
							<img src="upload/${course.icon }" alt="Service Name" class="img-thumbnail" width="254" height="254">
							<h3>
								<c:out value="${course.name }" />
							</h3>
							<p>Nutshell</p>
							<a
								href="<c:url value="/CourseServlet?action=show&course_id=${course.id }" />"
								class="btn">Read more</a> <a
								href="<c:url value="/CourseServlet?action=register&course_id=${course.id }" />"
								class="btn">Register On Course</a> <a
								href="<c:url value="/CourseServlet?action=triger&course_id=${course.id }" />"
								class="btn">Close Course</a>


						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>



	<jsp:include page="../page/footer.jsp" />
	<jsp:include page="../page/script.jsp" />
</body>
</html>