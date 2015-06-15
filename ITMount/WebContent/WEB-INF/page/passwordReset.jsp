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
					<h1>Password Reset</h1>
				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
					<div class="basic-login">
						<form action="<c:url value="/reset" />" method="post" role="form"
							id="resetpasswordForm">
							<div class="form-group">
								<label for="restore-email"><i class="icon-envelope"></i>
									<b>Enter Your Email</b></label> <input class="form-control"
									id="restore-email" name="resetemail" type="email"
									placeholder="">
							</div>
							<div class="form-group">
								<button type="submit" class="btn pull-right">Reset
									Password</button>
								<div class="clearfix"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="infoModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Info</h4>
				</div>
				<div class="modal-body">
					<p>${inforeset}</p>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>


				</div>
			</div>
		</div>
	</div>


	<div id="resetModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Info</h4>
				</div>
				<div class="modal-body">
					<form action="<c:url value="/NewPassword" />" method="post"
						id="newPasswordForm" role="form">

						<div class="form-group">
							<label for="login-password"><i class="icon-lock"></i> <b>New
									password</b></label> <input class="form-control" id="login-password"
								name="passwordReset" type="password" required="required"
								placeholder="">
						</div>
						<div class="form-group">
							<label for="login-password"><i class="icon-lock"></i> <b>Password
									confirmation</b></label> <input class="form-control" id="login-password"
								name="passwordReset1" type="password" required="required"
								placeholder="">
						</div>




						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Ok</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>

	<c:if test="${passwordReset!=null}">
		${passwordReset=null}
		<script type="text/javascript">
			$(document).ready(function() {
				$("#resetModal").modal('show');
			});
		</script>
	</c:if>


	<c:if test="${modalreset!=null}">
		${modalreset=null}
		<script type="text/javascript">
			$(document).ready(function() {
				$("#infoModal").modal('show');
			});
		</script>
	</c:if>
	<jsp:include page="footer.jsp" />

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#newPasswordForm')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {

													passwordReset : {
														validators : {
															notEmpty : {
																message : 'The password is required and cannot be empty'
															},
															identical : {
																field : 'passwordReset1',
																message : 'The password and its confirm are not the same'
															}

														}
													},
													passwordReset1 : {
														validators : {
															notEmpty : {
																message : 'The password is required and cannot be empty'
															},
															identical : {
																field : 'passwordReset',
																message : 'The password and its confirm are not the same'
															}

														}
													}

												}
											});
						});
	</script>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#resetpasswordForm')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													resetemail : {
														validators : {
															emailAddress : {
																message : 'The input is not a valid email address'
															}

														}
													},
													passwordReset : {
														validators : {
															notEmpty : {
																message : 'The password is required and cannot be empty'
															},
															identical : {
																field : 'passwordReset1',
																message : 'The password and its confirm are not the same'
															}

														}
													},
													passwordReset1 : {
														validators : {
															notEmpty : {
																message : 'The password is required and cannot be empty'
															},
															identical : {
																field : 'passwordReset1',
																message : 'The password and its confirm are not the same'
															}

														}
													}

												}
											});
						});
	</script>
</body>
</html>