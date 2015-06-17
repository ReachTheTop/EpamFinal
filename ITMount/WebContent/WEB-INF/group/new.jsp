<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Group</title>
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />



	<!-- Page Title -->
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>New Group</h1>
				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-sm-5">
					<div class="basic-login">
						<form action="GroupServlet?action=create" method="post"
							role="form" role="form">

							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Group
										Name</b></label> <input name="name" class="form-control" id="file"
									type="text" placeholder="">
							</div>


							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Group
										Teacher</b></label> <select name="teacher_id">
									<c:forEach items="${ teachers}" var="teacher">
										<option value="${teacher.id}"><c:out
												value="${teacher.name } ${teacher.surName }"></c:out>
										</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Group
										Course</b></label> <select name="course_id">
									<c:forEach items="${ courses}" var="course">
										<option value="${course.id}"><c:out
												value="${course.name }"></c:out>
										</option>
									</c:forEach>
								</select>
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