<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User page</title>
<jsp:include page="../page/head.jsp" />
<script src="${pageContext.request.contextPath}/assets/js/admin.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/jquery.bootpag.min.js"></script>



<script src="resources/js/toastr.js"></script>



<link rel="stylesheet" href="resources/css/tabPanel.css"></link>

<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">

<style type="text/css">
.panel-default>.panel-heading-custom {
	background: #FFFF43;
}
}
</style>


</head>
<body>
	<jsp:include page="../page/header.jsp" />
	<c:if test="${ current_user.id == user.id }">
		<c:if test="${not empty messages }">
			<jsp:include page="groupNotification.jsp" />
		</c:if>
	</c:if>
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>
						<t:i18n id="home.MyPage"/>
						<c:if test="${current_user.id == user.id }">
							<a data-toggle="modal" href="#editUser"><i
								class="glyphicon glyphicon-edit"></i></a>
						</c:if>
					</h1>
				</div>
			</div>
		</div>
	</div>



<div id="editUser" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title"><t:i18n id="home.MySettings"/></h4>
				</div>
				<div class="modal-body">
					<form action="UserServlet?action=update" id='editUserForm'
						method="post" enctype="multipart/form-data" role="form"
						role="form">
						<div class="form-group">
							<label for="login-username"><i class="icon-user"></i> <b><t:i18n id="home.FirstName"/>
							</b></label> <input name="name" class="form-control"
								id="login-username" type="text" placeholder=""
								value="${user.name }">
						</div>
						<div class="form-group">
							<label for="login-username"><i class="icon-user"></i> <b><t:i18n id="home.MiddleName"/>
							</b></label> <input name="middle_name" class="form-control"
								id="login-username" type="text" placeholder=""
								value="${user.middle_name }">
						</div>
						<div class="form-group">
							<label for="login-username"><i class="icon-user"></i> <b><t:i18n id="home.Surname"/>
							</b></label><input name="surname" class="form-control" id="login-username"
								type="text" placeholder="" value="${user.surname }">
						</div>
						<div class="form-group">
							<label for="login-username"><i class="icon-user"></i> <b>Email</b></label>
							<input name="email" class="form-control" id="login-username"
								type="text" placeholder="" value="${user.email }">
						</div>

						<div class="form-group">
							<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id="home.photo"/>
							</b></label><input name="image" class="form-control" id="file" type="file"
								placeholder="" value="${user.image }">
						</div>

						<div class="form-group">
							<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id="home.CurriculumVitae"/>
							</b></label> <input name="cv" class="form-control" id="file"
								type="file" placeholder="" value="${user.curriculum_vitae }">
						</div>
						<div class="form-group">
							<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id="home.Phone"/>
							</b></label><input name="phone" class="form-control" id="login-username"
								type="text" placeholder="" value="${contact.phone }">
						</div>

						<div class="form-group">
							<label for="login-password"><i class="icon-lock"></i> <b>Skype</b></label>
							<input name="skype" class="form-control" id="login-username"
								type="text" placeholder="" value="${contact.skype }">
						</div>


						<div class="form-group">
							<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id="home.AboutMyself"/>
							</b></label><textarea name="description" class="form-control" id="comment"
								placeholder=""><c:out value="${user.description }" /> </textarea>
						</div>




						<div class="form-group">

							<button type="submit" class="btn pull-right"><t:i18n id="home.Update"/></button>
							<div class="clearfix"></div>
						</div>
					</form>
				</div>

			</div>
		</div>

	</div>
	
	<script src="resources/js/toastr.js"></script>
	
	<script type="text/javascript">
		function showToaast(message, issucces) {
			var i = -1;
			var toastCount = 0;
			var $toastlast;

			var shortCutFunction;
			if (issucces == 1) {
				shortCutFunction = "success";
			}

			if (issucces == 0) {
				shortCutFunction = "error";
			}

			var msg = $('#message').val();
			var title = $('#title').val() || '';
			var $showDuration = $('#showDuration');
			var $hideDuration = $('#hideDuration');
			var $timeOut = $('#timeOut');
			var $extendedTimeOut = $('#extendedTimeOut');
			var $showEasing = $('#showEasing');
			var $hideEasing = $('#hideEasing');
			var $showMethod = $('#showMethod');
			var $hideMethod = $('#hideMethod');
			var toastIndex = toastCount++;

			toastr.options = {

				closeButton : true,
				debug : true,
				newestOnTop : false,
				progressBar : false,
				positionClass : "toast-top-right",
				preventDuplicates : false,
				onclick : null,
				timeOut : 10000,
				showDuration : 300,
				hideDuration : 1000,
				extendedTimeOut : 1000,

				showEasing : "swing",
				hideEasing : "linear",
				showMethod : "fadeIn",
				hideMethod : "fadeOut"

			};

			msg = message;

			$('#toastrOptions').text(
					'Command: toastr["' + shortCutFunction + '"]("' + msg
							+ (title ? '", "' + title : '')
							+ '")\n\ntoastr.options = '
							+ JSON.stringify(toastr.options, null, 2));

			var $toast = toastr[shortCutFunction](msg, title); // Wire up an event handler to a button in the toast, if it exists
			$toastlast = $toast;

			if (typeof $toast === 'undefined') {
				return;
			}

		}
	</script>
	
	
	<script>
		var form = $('#editUserForm');
		form.submit(function(e) {
			e.preventDefault();
		    e.stopImmediatePropagation();
			request = $.ajax({
				type : form.attr('method'),
				url : form.attr('action'),

				data : new FormData(this),
				processData : false,
				contentType : false,
				dataType: "json",

				success : function(data) {
					
					var nameUser = data.name;
					$( "#userName" ).html(nameUser);
					$( "#userNameModal" ).html(nameUser);
					var surNameUser = data.surname;
					$( "#userSurNameModal" ).html(surNameUser);
					$( "#userSurName" ).html(surNameUser);
					
					var userNameSurname = nameUser.concat("  ").concat(surNameUser);
					$( "#userNameSurname" ).html(userNameSurname);
					
 					var userMiddleName = data.middle_name;
					$("#middleNameModal").html(userMiddleName);
 					$("#userMiddleName").html(userMiddleName);
					
					var userBirtday = data.birtday;
					$("userBirtday").html(userBirtday);
					
					
					
					$('#editUser').modal('hide');
					showToaast('<t:i18n id="home.ProfileWasSuccessfullyEdited"/>', 1);

				},
				error : function() {
			
					showToaast('<t:i18n id="home.ProfileWasNotEdited"/>', 0);
				}
			});

			return false;
		});
	</script>
	
	
	<script type="text/javascript">
		function showToaast(message, issucces) {
			var i = -1;
			var toastCount = 0;
			var $toastlast;

			var shortCutFunction;
			if (issucces == 1) {
				shortCutFunction = "success";
			}

			if (issucces == 0) {
				shortCutFunction = "error";
			}

			var msg = $('#message').val();
			var title = $('#title').val() || '';
			var $showDuration = $('#showDuration');
			var $hideDuration = $('#hideDuration');
			var $timeOut = $('#timeOut');
			var $extendedTimeOut = $('#extendedTimeOut');
			var $showEasing = $('#showEasing');
			var $hideEasing = $('#hideEasing');
			var $showMethod = $('#showMethod');
			var $hideMethod = $('#hideMethod');
			var toastIndex = toastCount++;

			toastr.options = {

				closeButton : true,
				debug : true,
				newestOnTop : false,
				progressBar : false,
				positionClass : "toast-top-right",
				preventDuplicates : false,
				onclick : null,
				timeOut : 10000,
				showDuration : 300,
				hideDuration : 1000,
				extendedTimeOut : 1000,

				showEasing : "swing",
				hideEasing : "linear",
				showMethod : "fadeIn",
				hideMethod : "fadeOut"

			};

			msg = message;

			$('#toastrOptions').text(
					'Command: toastr["' + shortCutFunction + '"]("' + msg
							+ (title ? '", "' + title : '')
							+ '")\n\ntoastr.options = '
							+ JSON.stringify(toastr.options, null, 2));

			var $toast = toastr[shortCutFunction](msg, title); // Wire up an event handler to a button in the toast, if it exists
			$toastlast = $toast;

			if (typeof $toast === 'undefined') {
				return;
			}

		}
	</script>


	<c:choose>
		<c:when test="${user.id == current_user.id && user.role !='admin' }">
			<div class="container">
				<div class="row">
					<div class="col-md-8">
						<div class="panel panel-default">
							<div class="panel-heading  panel-heading-custom">
								<h3 class="panel-title" id = "userNameSurname" > ${current_user.name }
									${current_user.surname }

<%-- 									<c:if test="${current_user.id == user.id }"> --%>
<!-- 										<a data-toggle="modal" href="#editUser"><i -->
<!-- 											class="glyphicon glyphicon-edit"></i></a> -->
<%-- 									</c:if> --%>

								</h3>

							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-3 col-lg-3 " align="center">
										<img src="upload/${current_user.image }" alt=""
											class="img-rounded img-responsive" />

										<button type="submit"
											class="btn btn-default btn-xs dropdown-toggle"
											data-toggle="modal" href="#editUser" style="margin-top: 10px">
											Update foto</button>
									</div>


									<div class=" col-md-9 col-lg-9 ">
										<table class="table table-user-information">
											<tbody>
												<tr>
													<td><t:i18n id="home.FirstName"/></td>
													<td id = "userName"><p > ${current_user.name } </p></td>
												</tr>
												<tr>
													<td><t:i18n id="home.MiddleName"/></td>
													<td id = "userMiddleName">${current_user.middle_name }</td>
												</tr>
												<tr>
													<td><t:i18n id="home.Surname"/></td>
													<td id = "userSurName"><p>${current_user.surname }</p></td>
												</tr>

												<tr>
												<tr>
													<td><t:i18n id="home.Birthday"/></td>
													<td id="userBirtday">${current_user.birtday}</td>
												</tr>
												<tr>
													<td>Email</td>
													<td>${current_user.email}</td>
												</tr>
												<tr>
													<td><t:i18n id="home.Phone"/></td>
													<td>${contact.phone}</td>
												</tr>
												<tr>
													<td>Skype</td>
													<td>${contact.skype}</td>
												</tr>
												<tr>
													<td><t:i18n id="home.CurriculumVitae"/></td>
													<td><c:choose>
															<c:when test="${current_user.curriculum_vitae == null}">
																<button type="submit"
																	class="btn btn-default btn-xs dropdown-toggle"
																	data-toggle="modal" href="#editUser"><t:i18n id="home.AddCV"/></button>
															</c:when>

															<c:otherwise>
																<p>
																	<a
																		href="<c:url value="/downloadFile?file=${current_user.curriculum_vitae}"/>"
																		class="btn btn-default btn-xs dropdown-toggle"><t:i18n id="home.DownloadCV"/></a>
																</p>

															</c:otherwise>

														</c:choose></td>
												</tr>
												<tr>
													<td><t:i18n id="home.AboutMyself"/></td>
													<td>${current_user.description}</td>
												</tr>



											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

					</div>

					<div class="col-md-4">
						<h2><t:i18n id="home.MyCourses"/></h2>
						<div class="section">
							<c:forEach items="${groups }" var="group">
								<div class="col-sm-6" style="margin-top: 5px">
									<a
										href="<c:url
							value="/GroupServlet?action=show&group_id=${group.id }" />">


										<img src="upload/${group.course.icon }" class="img-circle"
										alt="Cinque Terre" width="150" height="150"
										alt="${group.name }">

									</a>
								</div>
							</c:forEach>
						</div>
					</div>

				</div>
			</div>
		</c:when>
	</c:choose>



	<c:choose>
		<c:when test="${user.id == current_user.id && user.role =='admin' }">

			<div class="container">
				<h2>Admin Panel</h2>


				<div id="editCourse" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">Course settings</h4>
							</div>
							<div class="modal-body">
								<form action="CourseServlet?action=update" data-async
									id='edit-course-form' method="post"
									enctype="multipart/form-data" role="form" role="form">
									<div class="form-group" hidden="true">
										<input class="form-control" type="text" name='course_id'
											id='edit-course-id'>
									</div>
									<div class="form-group">
										<label for="login-username"><i class="icon-user"></i>
											<b>Name</b></label> <input name="name" class="form-control"
											id="course-name-edit" type="text" placeholder="">
									</div>

									<div class="form-group">
										<label for="login-password"><i class="icon-lock"></i>
											<b>Image</b></label> <input name="image" class="form-control"
											id="course-icon-edit" type="file" placeholder="">
									</div>
									<div class="form-group">
										<label for="login-password"><i class="icon-lock"></i>
											<b>Description</b></label>
										<textarea name="description" class="form-control"
											id="course-description-edit" placeholder=""> </textarea>
									</div>
									<div class="form-group">

										<button type="submit" id='submit-course-edit'
											class="btn pull-right">Update</button>
										<div class="clearfix"></div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<script type="text/javascript">
					$('form[data-async]').on('submit', function(event) {
						var $form = $(this);
						var $target = $($form.attr('data-target'));
						event.preventDefault();
						event.stopImmediatePropagation();
						$.ajax({
							type : $form.attr('method'),
							url : $form.attr('action'),
							data : $form.serialize(),

							success : function(data, status) {
								$target.html(data);
							}
						});

					});

					$('button#submit-course-edit').click(function() {
						$('#editCourse').modal('hide');
						showToast("Course successfully updated", 1);
					});
				</script>




				<div id="newCourse" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">New course</h4>
							</div>
							<div class="modal-body">
								<form action="CourseServlet?action=create"
									id='create-new-course' method="post"
									enctype="multipart/form-data" role="form" role="form">
									<div class="form-group">
										<label for="login-username"><i class="icon-user"></i>
											<b>Course Name</b></label> <input name="name" class="form-control"
											id="login-username" type="text" placeholder="">
									</div>
									<div class="form-group">
										<label for="login-password"><i class="icon-lock"></i>
											<b>Icon</b></label> <input name="icon" class="form-control" id="file"
											type="file" required="required" placeholder="">
									</div>


									<div class="form-group">
										<label for="login-password"><i class="icon-lock"></i>
											<b>Description</b></label>
										<textarea name="description" class="form-control" id="comment"
											placeholder=""></textarea>
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


				<a data-toggle="modal" id="groupEditModal" href="#editGroup"><i
					class='glyphicon glyphicon-edit'></i></a>
				<div id="editGroup" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">Group settings</h4>
							</div>
							<div class="modal-body">
								<form action="GroupServlet?action=update" data-async
									id='edit-group-form' method="post"
									enctype="multipart/form-data" role="form" role="form">

									<div class="form-group" hidden="true">
										<input name="group_id" class="form-control" id="group-id-edit"
											type="text" placeholder="">
									</div>

									<div class="form-group" hidden="true">
										<input class="form-control" type="text" name='group_id'
											id='edit-group-id'>
									</div>
									<div class="form-group">
										<label for="login-username"><i class="icon-user"></i>
											<b>Name</b></label> <input name="name" class="form-control"
											id="group-name-edit" type="text" placeholder="">
									</div>

									<div class="form-group">
										<label for="login-password"><i class="icon-lock"></i>
											<b>Teacher</b></label> <select name="teacher_id" class="form-control"
											id="group-teacher-edit"></select>
									</div>
									<div class="form-group">

										<button type="submit" id='submit-group-edit'
											class="btn pull-right">Update</button>
										<div class="clearfix"></div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<script type="text/javascript">
					$('button#submit-group-edit').click(function() {

						$('#editGroup').modal('hide');
						showToast("Group successfully updated", 1);
					});
				</script>




				<div class="panel-group" id="accordion">

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" class="courses"
									data-parent="#accordion" href="#collapse0" aria-expanded="true" aria-controls="collapse0">${current_user.name }
									${current_user.surname }</a> <a data-toggle="modal"
									href="#editUser"><i class="glyphicon glyphicon-edit"></i></a>
							</h4>

						</div>

						<div id="collapse0" class="panel-collapse collapse collapse in">


							<div class="panel-body" id="profile-body">
								<div class="row">
									<div class="col-md-3 col-lg-3 " align="center">
										<img src="upload/${current_user.image }" alt=""
											class="img-rounded img-responsive" />

										<button type="submit"
											class="btn btn-default btn-xs dropdown-toggle"
											data-toggle="modal" href="#editUser" style="margin-top: 10px">
											Update foto</button>
									</div>


									<div class=" col-md-9 col-lg-9 ">
										<table class="table table-user-information">
											<tbody>
												<tr>
													<td>First Name</td>
													<td id = "userName">${current_user.name }</td>
												</tr>
												<tr>
													<td>Middle Name</td>
													<td>${current_user.middle_name }</td>
												</tr>
												<tr>
													<td>Surname</td>
													<td>${current_user.surname }</td>
												</tr>

												<tr>
												<tr>
													<td>Birthday</td>
													<td>${current_user.birtday}</td>
												</tr>
												<tr>
													<td>Email</td>
													<td>${current_user.email}</td>
												</tr>
												<tr>
													<td>Phone</td>
													<td>${contact.phone}</td>
												</tr>
												<tr>
													<td>Skype</td>
													<td>${contact.skype}</td>
												</tr>
												<tr>
													<td>CV</td>
													<td><c:choose>
															<c:when test="${current_user.curriculum_vitae == null}">
																<button type="submit"
																	class="btn btn-default btn-xs dropdown-toggle"
																	data-toggle="modal" href="#editUser">Add CV</button>
															</c:when>

															<c:otherwise>
																<p>
																	<a
																		href="<c:url value="/downloadFile?file=${current_user.curriculum_vitae}"/>"
																		class="btn btn-default btn-xs dropdown-toggle">Download
																		CV</a>
																</p>

															</c:otherwise>

														</c:choose></td>
												</tr>
												<tr>
													<td>Description</td>
													<td>${current_user.description}</td>
												</tr>



											</tbody>
										</table>
									</div>
								</div>


							</div>
						</div>
					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" class="courses"
									data-parent="#accordion" href="#collapse1">Courses</a>
							</h4>

						</div>
						<div id="collapse1" class="panel-collapse collapse">
							<div class="panel-body" id="courses-body">

								<div class="row">
									<div class="col-xs-12 col-md-10">

										<div class="form-group">

											<input type="text" id="search-field"
												placeholder="Course Search" class='form-control'>

										</div>
									</div>
									<div class="col-xs-4 col-sm-2">
										<a data-toggle="modal" class='btn btn-primary'
											href="#newCourse">Create New Course</a> <a
											data-toggle="modal" id="courseEditModal" href="#editCourse"><i
											class='glyphicon glyphicon-edit'></i></a>
									</div>

								</div>
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Name</th>
											<th>Is active</th>
											<th>Triger course</th>
											<th>Edit course</th>
										<tr>
									</thead>
									<tbody id="courses-body">

									</tbody>
								</table>
								<div class="row" align="center">

									<div id="page-selection"></div>

								</div>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" class='groups'
									data-parent="#accordion" href="#collapse2">Groups</a>
							</h4>
						</div>
						<div id="collapse2" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="row-fluid">

									<div class="form-group">

										<input type="text" id="search-groups"
											placeholder="Group Search" class='form-control'>
									</div>


								</div>
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Course</th>
											<th>Group Name</th>
											<th>Teacher</th>
											<th>Confirmed</th>
											<th>Edit</th>
										<tr>
									</thead>
									<tbody id="groups-body">

									</tbody>
								</table>
								<div class="row" align="center">

									<div id="group-page-selection"></div>

								</div>


							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" class="users" data-parent="#accordion"
									href="#collapse3">Users</a>
							</h4>
						</div>
						<div id="collapse3" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="row-fluid">

									<div class="form-group">

										<input type="text" id="search-users"
											placeholder="Group Search" class='form-control'>
									</div>


								</div>
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Name</th>
											<th>Surname</th>
											<th>Role</th>
											<th>Email</th>
											<th>Phone</th>
											<th>Skype</th>
											<th>Active</th>
											
										<tr>
									</thead>
									<tbody id="users-body">

									</tbody>
								</table>
								<div class="row" align="center">

									<div id="user-page-selection"></div>

								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

		</c:when>
	</c:choose>

<div hidden="true" >
	<select class='form-control' id='userRoles' name='role'>
		<option value="2">student</option>
		<option value="3">lecturer</option>
		<option value="4">admin</option>
	</select>
</div>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('form#edit-group-form')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													name : {
														validators : {
															notEmpty : {
																message : 'The field is required and cannot be empty',
																min : 5,
																max : 30,
																message : 'The username must be more than 5 and less than 30 characters long'
															}

														}
													},
													teacher_id : {
														validators : {
															notEmpty : {
																message : 'The field is required and cannot be empty'

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
							$('form#edit-course-form, form#create-new-course')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													name : {
														validators : {
															notEmpty : {
																message : 'The field is required and cannot be empty',
																min : 2,
																max : 30,
																message : 'The username must be more than 2 and less than 30 characters long'
															}

														}
													},
													image : {
														validators : {
															file : {
																extension : 'png,jpg',
																maxSize : 5 * 1024 * 1024,
																message : 'Please choose a image file with a size less than 5M.'
															}
														}
													},
													description : {
														validators : {
															notEmpty : {
																message : 'The field is required and cannot be empty',
																min : 100,
																max : 2000,
																message : 'The username must be more than 100 and less than 2000 characters long'
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
							$('#editUserForm')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													surname : {
														validators : {
															notEmpty : {
																message : 'The field is required and cannot be empty',
																min : 2,
																max : 30,
																message : 'The username must be more than 2 and less than 30 characters long'
															}

														}
													},
													email : {
														validators : {
															emailAddress : {
																message : 'The input is not a valid email address'
															}
														}
													},
													skype : {
														validators : {
															regexp : {
																regexp : /^[a-zА-Яа-я0-9_-]{3,15}$/,
																message : 'Invalid skype name'
															},

														}
													},
													phone : {
														validators : {
															regexp : {
																regexp : /^\+?([0-9]{2})\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})$/,
																message : 'Invalid phone number'
															},

														}
													},
													image : {
														validators : {
															file : {
																extension : 'png,jpg',
																maxSize : 5 * 1024 * 1024,
																message : 'Please choose a image file with a size less than 5M.'
															}
														}
													},
													cv : {
														validators : {
															file : {
																extension : 'pdf,doc,docx',
																maxSize : 5 * 1024 * 1024,
																message : 'Please choose a image file with a size less than 5M.'
															}
														}
													}
												}
											});
						});
	</script>

	<c:if test ="${showEditModal!=null }">
	${showEditModal=null }
	<script type="text/javascript">
			$(document).ready(function() {
				$("#editUser").modal('show');
			});
		</script>
	</c:if>
		
		
	


	<script type="text/javascript">
		function showToast(message, issucces) {
			var i = -1;
			var toastCount = 0;
			var $toastlast;

			var shortCutFunction;
			if (issucces == 1) {
				shortCutFunction = "success";
			}

			if (issucces == 0) {
				shortCutFunction = "error";
			}

			var msg = $('#message').val();
			var title = $('#title').val() || '';
			var $showDuration = $('#showDuration');
			var $hideDuration = $('#hideDuration');
			var $timeOut = $('#timeOut');
			var $extendedTimeOut = $('#extendedTimeOut');
			var $showEasing = $('#showEasing');
			var $hideEasing = $('#hideEasing');
			var $showMethod = $('#showMethod');
			var $hideMethod = $('#hideMethod');
			var toastIndex = toastCount++;

			toastr.options = {

				closeButton : true,
				debug : true,
				newestOnTop : false,
				progressBar : false,
				positionClass : "toast-top-right",
				preventDuplicates : false,
				onclick : null,
				timeOut : 10000,
				showDuration : 300,
				hideDuration : 1000,
				extendedTimeOut : 1000,

				showEasing : "swing",
				hideEasing : "linear",
				showMethod : "fadeIn",
				hideMethod : "fadeOut"

			};

			msg = message;

			$('#toastrOptions').text(
					'Command: toastr["' + shortCutFunction + '"]("' + msg
							+ (title ? '", "' + title : '')
							+ '")\n\ntoastr.options = '
							+ JSON.stringify(toastr.options, null, 2));

			var $toast = toastr[shortCutFunction](msg, title); // Wire up an event
			// handler to a button
			// in the toast, if it
			// exists
			$toastlast = $toast;

			if (typeof $toast === 'undefined') {
				return;
			}

		}
	</script>



	<jsp:include page="../page/footer.jsp" />


</body>
</html>
