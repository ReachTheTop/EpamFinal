<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="head.jsp" />
</head>
<body>
	<jsp:include page="header.jsp" />

	<!-- Page Title -->
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>Register</h1>
				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-sm-5">
					<div class="basic-login">
						<c:if test="${errorRegistration!=null}">
							<div class="alert alert-danger">
								<strong>Error!</strong>${errorRegistration}
							</div>
						</c:if>

						<form action="<c:url value="/registration" />" role="form" method="post"
							enctype="multipart/form-data">
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>Email</b></label> <input class="form-control" name="email" value="${email}" id="register-username"
									type="email" placeholder="">
							</div>
							<div class="form-group">
								<label for="register-password"><i class="icon-lock"></i>
									<b>Password</b></label> <input class="form-control"
									id="register-password" name="password" type="password" placeholder="">
							</div>
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>Name</b></label> <input class="form-control" name="name" value="${ name}" id="register-username"
									type="text" placeholder="">
							</div>
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>Midle name</b></label> <input class="form-control" name="midlename" value="${ midlename}"
									id="register-username" type="text" placeholder="">
							</div>
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>Surname</b></label> <input class="form-control" name="surname" value="${ surname}"
									id="register-username" type="text" placeholder="">
							</div>
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>birthday</b></label> <input class="form-control" name="date" value="${date}"
									id="register-username" type="date" placeholder="">
							</div>
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>Photo</b></label> <input class="form-control"
									id="register-username" type="file" placeholder="" name="photo" value="${ photo}">
							</div>

							<div class="form-group">
								<button type="submit" class="btn pull-right">Register</button>
								<div class="clearfix"></div>
							</div>
						</form>
					</div>
				</div>
				<div class="col-sm-6 col-sm-offset-1 social-login">
					<p>You can use your Facebook or Twitter for registration</p>
					<div class="social-login-buttons">
						<a href="#" class="btn-facebook-login">Use Facebook</a> <a
							href="#" class="btn-twitter-login">Use Twitter</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<jsp:include page="script.jsp" />
</body>
</html>