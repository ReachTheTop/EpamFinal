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
						<c:if test="${errorLogin!=null}">
							<div class="alert alert-danger">
								<strong>Error!</strong>${errorLogin}
							</div>
						</c:if>
						<form action="<c:url value="/login" />" id="loginForm"
							method="post" role="form">
							<div class="form-group">
								<label for="login-username"><i class="icon-user"></i> <b>Username
										or Email</b></label> <input class="form-control" id="login-username"
									name="emaill" type="email" value="${emaill}" placeholder="">

							</div>

							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Password</b></label>
								<input class="form-control" id="login-password" name="password"
									type="password">
							</div>
							<div class="form-group">
								<div class="col-md-9 col-md-offset-3">
									<div id="messages"></div>
								</div>
							</div>
							<div class="form-group">
								<a href="<c:url value="/reset" />" class="forgot-password">Forgot
									password?</a>
								<button type="submit" class="btn pull-right">Login</button>
								<div class="clearfix"></div>
							</div>
						</form>
					</div>
				</div>
				<div class="col-sm-7 social-login">
					<p>Or login with your Facebook</p>
					<div class="social-login-buttons">
						<a href="<c:url value="/FbLoginServlet?action=fb" />" class="btn-facebook-login">Login with Facebook</a> 
					</div>
					<div class="clearfix"></div>
					<div class="not-member">
						<p>
							Not a member? <a href="<c:url value="/registration" />">Register
								here</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="myModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Confirmation</h4>
				</div>
				<div class="modal-body">
					<p>Підтвердіть свій email ітд</p>
					<p class="text-warning">
						<small>Щоб відправити силку для підтвредження ще раз
							нажміть на конпу сенд</small>
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<a href="<c:url value="/sendconfirm" />" class="btn btn-primary">Send</a>

				</div>
			</div>
		</div>
	</div>


	<div id="confirmModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Successful</h4>
				</div>
				<div class="modal-body">
					<p>Ви успішно підтвердили свій емейл!</p>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>


				</div>
			</div>
		</div>
	</div>

	<c:if test="${infoconfirm!=null}">
		${infoconfirm=null}
		<script type="text/javascript">
			$(document).ready(function() {
				$("#confirmModal").modal('show');
			});
		</script>
	</c:if>
	<c:if test="${confirmemail!=null}">
		${confirmemail=null}
		<script type="text/javascript">
			$(document).ready(function() {
				$("#myModal").modal('show');
			});
		</script>
	</c:if>



	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#loginForm')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													email : {
														validators : {
															emailAddress : {
																message : 'The input is not a valid email address'
															}
															
														}
													},
													password : {
														validators : {
															notEmpty : {
																message : 'The password is required and cannot be empty'
															},
															stringLength : {
																min : 6,
																max : 30,
																message : 'The username must be more than 6 and less than 30 characters long'
															}
														}
													}
												}
											});
						});
	</script>
	<jsp:include page="footer.jsp" />
	
</body>
</html>