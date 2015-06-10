<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create new course</title>
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />


	<!-- Page Title -->
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>Login</h1>
				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-sm-5">
					<div class="basic-login">
						<form action="CourseServlet?action=update&course_id=${course.id }" enctype="multipart/form-data"
							method="post" role="form" role="form">
							<div class="form-group">
								<label for="login-username"><i class="icon-user"></i> <b>Course
										Name</b></label> <input name="name" class="form-control"
									id="login-username" value="${course.name }" type="text"
									placeholder="">
							</div>
							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Icon</b></label>
								<input name="icon" class="form-control" value="${course.icon }"
									id="file" type="file" placeholder="">
							</div>


							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Description</b></label>
								<input name="description" value="${course.description }"
									class="form-control" type="text" rows="5" id="comment"
									placeholder="">
							</div>





							<div class="form-group">

								<button type="submit" class="btn pull-right">Create</button>
								<div class="clearfix"></div>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>




	<jsp:include page="../page/footer.jsp" />
	<jsp:include page="../page/script.jsp" />

</body>
</html>