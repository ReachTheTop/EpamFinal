<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><t:i18n id="registration.registrationPage" /></title>
<jsp:include page="head.jsp" />
</head>
<body>
	<jsp:include page="header.jsp" />

	<!-- Page Title -->
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>
						<t:i18n id="registration.registration" />
					</h1>
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
								<strong><t:i18n id="registration.error" /></strong>${errorRegistration}
							</div>
						</c:if>

						<form action="<c:url value="/registration" />" role="form"
							method="post" id="registarionForm" enctype="multipart/form-data">
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>Email</b></label> <input class="form-control" name="email"
									value="${email}" id="register-username" type="email"
									placeholder="">
							</div>
							<div class="form-group">
								<label for="register-password"><i class="icon-lock"></i>
									<b><t:i18n id="registration.password" /></b></label> <input
									class="form-control" id="register-password" name="password"
									type="password" placeholder="">
							</div>
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b><t:i18n id="registration.name" /></b></label> <input
									class="form-control" name="name" value="${ name}"
									id="register-username" type="text" placeholder="">
							</div>
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b><t:i18n id="registration.MiddleName" /></b></label> <input
									class="form-control" name="midlename" value="${ midlename}"
									id="register-username" type="text" placeholder="">
							</div>
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b><t:i18n id="registration.surname" /></b></label> <input
									class="form-control" name="surname" value="${ surname}"
									id="register-username" type="text" placeholder="">
							</div>
							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b><t:i18n id="registration.birthday" /></b></label> <input
									class="form-control" name="date" value="${date}"
									id="register-username" type="date" placeholder="">
							</div>

							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b><t:i18n id="registration.telephone" /></b></label> <input
									class="form-control" name="tel" value="${tel}"
									id="register-username" type="tel" placeholder="">
							</div>

							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>Skype</b></label> <input class="form-control" name="skype"
									value="${skype}" id="register-username" type="text"
									placeholder="">
							</div>

							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b><t:i18n id="registration.photo" /></b></label> <input
									class="form-control" id="register-username" type="file"
									placeholder="" name="photo" value="${ photo}">
							</div>

							<div class="form-group">
								<button type="submit" class="btn pull-right">
									<t:i18n id="registration.register" />
								</button>
								<div class="clearfix"></div>
							</div>
						</form>
					</div>
				</div>
				<div class="col-sm-6 col-sm-offset-1 social-login">
					<p>
						<t:i18n id="registration.YouCanUseYourFacebookForRegistration" />
					</p>
					<div class="social-login-buttons">
						<a href="<c:url value="/FbLoginServlet?action=fb" />"
							class="btn-facebook-login">Use Facebook</a>
					</div>
					<div class="clearfix"></div>
					<div class="not-member">
						<p>
							<t:i18n id="registration.AlreadyHaveAnAccount" />
							<a href="<c:url value="/login" />"> <t:i18n
									id="registration.LogIn" /></a>
						</p>
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
												message : '<t:i18n id="bootstrap.ThisValueIsNotValid"/>',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													email : {
														validators : {
															emailAddress : {

																message : '<t:i18n id="bootstrap.TheInputIsNotAValidEmailAddress"/>'
															},
															notEmpty : {
																message : '<t:i18n id="bootstrap.TheEmailIsRequiredAndCannotBeEmpty"/>'
															}
														}
													},
													name : {
														message : '<t:i18n id="bootstrap.TheNameIsNotValid"/>',
														validators : {
															notEmpty : {
																message : '<t:i18n id="bootstrap.TheNameIsRequiredAndCannotBeEmpty"/>'
															},
															regexp : {
																regexp : /^[a-zA-ZА-ЯІіЇїЄєа-я]*$/,
																message : '<t:i18n id="bootstrap.TheUsernameCanOnlyConsistOfAlphabeticalNumberDotUnderscore"/>'
															},
															different : {
																field : 'password',
																message : '<t:i18n id="bootstrap.TheUsernameAndPasswordCannotBeTheSameAsEachOther"/>'
															},
															stringLength : {
																min : 1,
																max : 50,
																message : '<t:i18n id="bootstrap.Length.user"/>'
															}
														}
													},
													midlename : {
														message : '<t:i18n id="bootstrap.TheMiddleNameIsNotValid"/>',
														validators : {
															notEmpty : {
																message : '<t:i18n id="bootstrap.TheMiddleNameIsRequiredAndCannotBeEmpty"/>'
															},
															regexp : {
																regexp : /^[a-zA-ZА-ЯІіЇїЄєа-я]*$/,

																message : '<t:i18n id="bootstrap.TheUsernameCanOnlyConsistOfAlphabeticalNumberDotUnderscore"/>'

															},
															different : {
																field : 'password',
																message : '<t:i18n id="bootstrap.TheUsernameAndPasswordCannotBeTheSameAsEachOther"/>'
															},
															stringLength : {
																min : 1,
																max : 50,
																message : '<t:i18n id="bootstrap.Length.user"/>'
															}
														}
													},
													surname : {
														message : '<t:i18n id="bootstrap.TheSurnameIsNotValid"/>',
														validators : {
															
															regexp : {

																regexp : /^[a-zA-ZА-ЯІіЇїЄєа-я]*$/,
																message : '<t:i18n id="bootstrap.TheUsernameCanOnlyConsistOfAlphabeticalNumberDotUnderscore"/>'

															},
															notEmpty : {
																message : '<t:i18n id="bootstrap.TheSurnameIsRequiredAndCannotBeEmpty"/>'
															},
															different : {
																field : 'password',
																message : '<t:i18n id="bootstrap.TheUsernameAndPasswordCannotBeTheSameAsEachOther"/>'
															},
															stringLength : {
																min : 1,
																max : 50,
																message : '<t:i18n id="bootstrap.Length.user"/>'
															}
														}
													},
													date : {
														validators : {
															notEmpty : {
																message : '<t:i18n id="bootstrap.TheDateIsRequiredAndCannotBeEmpty"/>'
															}
														}
													},
													skype : {
														validators : {
															regexp : {
																regexp : /^[a-zA-ZА-Яа-я0-9._-]{3,15}$/,
																message : '<t:i18n id="bootstrap.InvalidSkypeName"/>'
															},

														}
													},
													tel : {
														validators : {
															regexp : {
																regexp : /^\s*(?:\+?(\d{1,3}))?([-. (]*(\d{3})[-. )]*)?((\d{3})[-. ]*(\d{2,4})(?:[-.x ]*(\d+))?)\s*$/,
																message : '<t:i18n id="bootstrap.InvalidPhoneNumber"/>'
															},

														}
													},
													photo : {
														validators : {
															file : {
																extension : 'png,jpg',
																maxSize : 5 * 1024 * 1024,
																message : '<t:i18n id="bootstrap.PleaseChooseAImageFileWithASizeLess"/>'
															}
														}
													},
													password : {
														validators : {
															notEmpty : {
																message : '<t:i18n id="bootstrap.ThePasswordIsRequiredAndCannotBeEmpty"/>'
															},
															stringLength : {
																min : 6,
																max : 30,
																message : '<t:i18n id="bootstrap.ThePasswordMustBeMoreThanAndLessThanCharactersLong"/>'
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