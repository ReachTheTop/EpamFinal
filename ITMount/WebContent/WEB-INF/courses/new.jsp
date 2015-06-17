<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
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
						<form action="CourseServlet?action=create" method="post" enctype="multipart/form-data"
							role="form" role="form">
							<div class="form-group">
								<label for="login-username"><i class="icon-user"></i> <b>Course
										Name</b></label> <input name="name" class="form-control"
									id="login-username" type="text" placeholder="">
							</div>
							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Icon</b></label>
								<input name="icon" class="form-control" id="file" type="file" required="required"
									placeholder="">
							</div>


							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Description</b></label>
								<input name="description" class="form-control"
									type="text" rows="5" id="comment" placeholder="">
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
	

</body>
</html>