<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration page</title>
<jsp:include page="head.jsp" />
</head>
<body>
	<jsp:include page="header.jsp" />

	<!-- Page Title -->
	<div class="section section-breadcrumbs"> 
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1><t:i18n id="registration.registration"/></h1>
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

						<form action="<c:url value="/registration" />" role="form" method="post" id="registarionForm"
							enctype="multipart/form-data">
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>Email</b></label> <input class="form-control" name="email" value="${email}" id="register-username" 
									type="email" placeholder="">
							</div>
							<div class="form-group">
								<label for="register-password"><i class="icon-lock"></i>
									<b><t:i18n id="registration.password"/></b></label> <input class="form-control"
									id="register-password" name="password" type="password"   placeholder="">
							</div>
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b><t:i18n id="registration.name"/></b></label> <input class="form-control" name="name" value="${ name}" id="register-username"
									type="text" placeholder="" >
							</div>
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b><t:i18n id="registration.MiddleName"/></b></label> <input class="form-control" name="midlename" value="${ midlename}"
									id="register-username" type="text" placeholder="">
							</div>
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b><t:i18n id="registration.surname"/></b></label> <input class="form-control" name="surname" value="${ surname}"
									id="register-username" type="text" placeholder="" >
							</div>
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b><t:i18n id="registration.birthday"/></b></label> <input class="form-control" name="date" value="${date}"
									id="register-username" type="date" placeholder="" >
							</div>
							
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b><t:i18n id="registration.telephone"/></b></label> <input class="form-control" name="tel" value="${tel}"
									id="register-username" type="tel" placeholder="" >
							</div>
							
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>Skype</b></label> <input class="form-control" name="skype" value="${skype}"
									id="register-username" type="text" placeholder="" >
							</div>
							
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b><t:i18n id="registration.photo"/></b></label> <input class="form-control"
									id="register-username" type="file" placeholder="" name="photo"  value="${ photo}">
							</div>

							<div class="form-group">
								<button type="submit" class="btn pull-right"><t:i18n id="registration.register"/></button>
								<div class="clearfix"></div>
							</div>
						</form>
					</div>
				</div>
				<div class="col-sm-6 col-sm-offset-1 social-login">
					<p><t:i18n id="registration.YouCanUseYourFacebookForRegistration"/></p>
					<div class="social-login-buttons">
						<a href="<c:url value="/FbLoginServlet?action=fb" />" class="btn-facebook-login">Use Facebook</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
		<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#registarionForm')
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
															},
												 notEmpty: {
								                        message: 'The email is required and cannot be empty'
								                    }
														}
													},
													name: {
										                message: 'The Name is not valid',
										                validators: {
										                    notEmpty: {
										                        message: 'The Name is required and cannot be empty'
										                    },
										                    regexp: {
										                        regexp: /^[a-zA-Z0-9А-Яіа-я_\.]+$/,
										                        message: 'The username can only consist of alphabetical, number, dot and underscore'
										                    },
										                    different: {
										                        field: 'password',
										                        message: 'The username and password cannot be the same as each other'
										                    }
										                }
										            },
										            midlename: {
										                message: 'The Name is not valid',
										                validators: {
										                    notEmpty: {
										                        message: 'The Name is required and cannot be empty'
										                    },
										                    regexp: {
										                        regexp: /^[a-zA-ZА-Яа-я0-9_\.]+$/,
										                        message: 'The username can only consist of alphabetical, number, dot and underscore'
										                    },
										                    different: {
										                        field: 'password',
										                        message: 'The username and password cannot be the same as each other'
										                    }
										                }
										            },
										            surname: {
										                message: 'The Name is not valid',
										                validators: {
										                    notEmpty: {
										                        message: 'The Name is required and cannot be empty'
										                    },
										                    regexp: {
										                        regexp: /^[a-zA-ZА-Яа-я0-9_\.]+$/,
										                        message: 'The username can only consist of alphabetical, number, dot and underscore'
										                    },
										                    different: {
										                        field: 'password',
										                        message: 'The username and password cannot be the same as each other'
										                    }
										                }
										            },
										           date: {
										                validators: {
										                    notEmpty: {
										                        message: 'The date is required and cannot be empty'
										                    },
										                 
										                }
										            },
										            skype: {
										                validators: {
										                	 regexp: {
											                        regexp: /^[a-zА-Яа-я0-9_-]{3,15}$/,
											                        message: 'Invalid skype name'
											                    },
										                 
										                }
										            },
										            tel: {
										                validators: {
										                	 regexp: {
											                        regexp: /^\+?([0-9]{2})\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})$/,
											                        message: 'Invalid phone number'
											                    },
										                 
										                }
										            },
										            photo: {
										                validators: {
										                    file: {
										                        extension: 'png,jpg',
										                        maxSize: 5*1024*1024,
										                        message: 'Please choose a image file with a size less than 5M.'
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
																max : 20,
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