<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ group.name }</title>


<jsp:include page="../page/head.jsp" />
<script src="assets/js/project.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script src="assets/js/jquery.autocomplete.multiselect.js"></script>

<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.0/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.0/js/bootstrap-toggle.min.js"></script>

<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">

<link rel="stylesheet" href="resources/css/font-awesome.css">
<link rel="stylesheet" href="resources/css/animate.css">

<title>${ group.name }</title>

<style type="text/css">
.nav-pills>li.active>a {
	background-color: #4B4B4C;
}

.nav-pills>li.active>a:hover {
	background-color: #4B4B4C;
}

a {
	color: #514D61;
	text-decoration: none;
}

.sidebar-nav .navbar li a {
	padding-top: 12px;
	padding-bottom: 12px;
}
}
</style>

</head>
<body>
	<jsp:include page="../page/header.jsp" />

	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row-fluid">
				<div class="col-md-12">
					<h1>
						<c:out value="${group.name }" />
						<t:i18n id='group.tasks' />
					</h1>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row-fluid">
			<div class="col-md-3">
				<ul class="nav nav-pills nav-stacked">
					<li><a
						href="<c:url value="/GroupServlet?action=show&group_id=${group.id }" />"><i
							class="fa fa-home fa-fw"></i> <t:i18n id='group.main' /></a></li>
					<li class="active"><a
						href="<c:url value="/GroupServlet?action=showTasks&group_id=${group.id }" />"><i
							class="fa fa-tasks fa-fw"></i> <t:i18n id='group.tasks' /></a></li>
					<li><a
						href="<c:url value="/GroupServlet?action=showEvents&group_id=${group.id }" />"><i
							class="fa fa-users fa-fw"></i> <t:i18n id='group.events' /></a></li>

					<li><a
						href="<c:url value="/GroupServlet?action=showExams&group_id=${group.id }" />"><i
							class="fa fa-check fa-fw"></i> <t:i18n id='group.exams' /></a></li>
					<li><a
						href="<c:url value="/GroupServlet?action=chat&group_id=${group.id }" />"><i
							class="fa fa-weixin"></i>
						<t:i18n id='group.chat' /></a></li>
					<c:if test="${user.role == 'student' }">
						<li><a
							href="<c:url value="/Homework?action=show&group_id=${group.id }&users_id=${user.id }" />"><i
								class="fa fa-list fa-fw"></i> <t:i18n id='group.homework' /></a></li>
					</c:if>
					<c:if
						test="${not empty group.teacher_id && user.id == group.teacher_id}">
						<li><a href="#myModal" class="btn btn-sm btn-primary"
							data-toggle="modal"><t:i18n id='group.task.create' /></a></li>
					</c:if>
				</ul>
			</div>
			<div class="col-md-9">

				<jsp:include page="AllTaskUser.jsp"></jsp:include>

			</div>
		</div>
	</div>


	<jsp:include page="../page/footer.jsp" />


	<div id="myModalUpdate" class="modal fade bs-example-modal-sm">

		<div class="modal-dialog">
			<div class="modal-content">



				<form action="TaskServlet?action=updateTask&group_id=${group.id }"
					id='editTask' method="post" enctype="multipart/form-data"
					role="form" role="form">

					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
							<t:i18n id='group.task.update' />
						</h4>
					</div>
					<div class="modal-body">


						<div class="form-group">
							<label for="login-username"><i class="icon-user"></i> <b><t:i18n
										id='group.task.name' /></b></label> <input name="task_name"
								class="form-control" id="task_name" type="text" placeholder="">
						</div>
						<div class="form-group">
							<label for="login-username"><i class="icon-user"></i> <b><t:i18n
										id='group.task.description' /></b></label>
							<p>
								<textarea class="form-control" name="task_description"
									id="task_description" rows="3" name="text"></textarea>
							</p>
						</div>

						<div class="form-group">
							<label for="login-username"><i class="icon-user"></i> <b><t:i18n
										id='group.task.deadline' /></b></label> <input name="task_deadline"
								class="form-control" id="taskDeadline" type="datetime-local">
						</div>


						<div class="form-group">
							<label for="register-username"><i class="icon-user"></i>
								<b><t:i18n id='group.task.file' /></b></label> <input
								class="form-control" id="task_file1" type="file" placeholder=""
								name="fileUpdate">
						</div>

						<div class="form-group" hidden="true">

							<input name="task_id" class="form-control" id="task_id"
								type="text" placeholder="">
						</div>

						<div class="modal-footer">
							<button type="submit" class="btn btn-primary"
								data-dismiss="modal">
								<t:i18n id='group.task.close' />
							</button>
							<button type="submit" class="btn btn-primary">
								<t:i18n id='group.task.update' />
							</button>
						</div>

					</div>

				</form>
			</div>
		</div>
	</div>

	<script>
		var form2 = $('#editTask');
		form2.submit(function(e) {

			e.preventDefault();
			e.stopImmediatePropagation();
			request = $.ajax({
				type : form2.attr('method'),
				url : form2.attr('action'),

				data : new FormData(this),
				processData : false,
				contentType : false,

				success : function(text) {
					$('#form1, #editTask').data('bootstrapValidator').resetForm();
					$("a#task"+$("#task_id").val()).text(text.name);
					$("td#taskName"+$("#task_id").val()).text(text.name);
					$("td#taskDesc"+$("#task_id").val()).text(text.description);
					$("a#taskFile"+$("#task_id").val()).attr("href","/ITMount/downloadFile?file="+text.file);
					$("#incorectData2").hide();

					$('#myModalUpdate').modal('hide');
					showToaast("<t:i18n id='group.task.success'/>", 1);

				},
				error : function() {
					
					showToaast("<t:i18n id='group.task.error.update'/>", 0);

				}
			});

			return false;
		});
	</script>



	<script type="text/javascript">
		$('a.updateTask').click(
				function() {
					var index = $(this).attr('id');
					$('#task_id').val(index);
					$.get('TaskServlet?action=show&task_id=' + index, function(
							response) {

						$('#task_name').val(response.name);
						$("#task_description").val(response.description);

						var dateString = response.deadline;

						var date = new Date(Date.parse(dateString));

						var year = date.getFullYear().toString();
						var month = addZero(date.getMonth() + 1).toString();
						var day = addZero(date.getDate()).toString();
						var hours = addZero(date.getHours()).toString();
						var minutes = addZero(date.getMinutes()).toString();

						var correctDate = year.concat("-", month, "-", day,
								"T", hours, ":", minutes);

						$("#taskDeadline").val(correctDate);

					});
				});

		function addZero(i) {
			if (i < 10) {
				i = "0" + i;
			}
			return i;
		}
	</script>


	<script>
		$(document).ready(function() {
			$("#myModalUpdate").on("hidden.bs.modal", function() {

				$("#incorectData2").hide();

			});
		});
	</script>


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

	<div id="myModal" class="modal fade bs-example-modal-sm">


		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 					<form name=" form1" -->
				<%-- 						action="TaskServlet?action=createTask&group_id=${group_id }" --%>
				<!-- 						method="post" enctype="multipart/form-data" role="form" -->
				<!-- 						role="form" id="form1"> -->

				<form name=" form1"
					action="<c:url value="/TaskServlet?action=createTask&group_id=${group.id }"/>"
					method="post" enctype="multipart/form-data" id="form1" role="form">

					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
							<t:i18n id='group.task.new' />
						</h4>
					</div>
					<div class="modal-body">

						


						<div class="form-group">
							<label for="login-username"><i class="icon-user"></i> <b><t:i18n
										id='group.task.name' /></b></label> <input name="task_name"
								class="form-control" id="login-username" type="text"
								placeholder="">
						</div>
						<div class="form-group">
							<label for="login-username"><i class="icon-user"></i> <b><t:i18n
										id='group.task.description' /></b></label>

							<textarea class="form-control" name="task_description" rows="3"
								name="text"></textarea>

						</div>

						<div class="form-group">
							<label for="login-username"><i class="icon-user"></i> <b><t:i18n
										id='group.task.deadline' /></b></label> <input name="task_deadline"
								class="form-control" id="login-username" type="datetime-local">
						</div>


						<div class="form-group">
							<label for="register-username"><i class="icon-user"></i>
								<b><t:i18n id='group.task.file' /></b></label> <input
								class="form-control" id="task_file" type="file" placeholder=""
								name="file">
						</div>

						<div class="modal-footer">
							<button type="submit" class="btn btn-primary"
								data-dismiss="modal">
								<t:i18n id='group.task.close' />
							</button>
							<button type="submit" id="addTask" class="btn btn-primary">
								<t:i18n id='group.task.add' />
							</button>
						</div>

					</div>

				</form>
			</div>
		</div>
	</div>

	<script>
		var form1 = $('#form1');
		form1
				.submit(function(e) {
					e.preventDefault();
					e.stopImmediatePropagation();
					request = $
							.ajax({
								type : form1.attr('method'),
								url : form1.attr('action'),

								data : new FormData(this),
								processData : false,
								contentType : false,

								//data : form.serialize(),
								success : function(text) {
									$('#form1, #editTask').data('bootstrapValidator').resetForm();
									$("#incorectData").hide();
									document.getElementById("form1").reset();
									$('#myModal').modal('hide');
									showToaast(
											"<t:i18n id='group.task.create.success'/>",
											1);
									window.location.href = '/ITMount/GroupServlet?action=showTasks&group_id=${group.id}';

								},
								error : function() {
									$("#incorectData").show();
									showToaast(
											"<t:i18n id='group.task.create.error.update'/>",
											0);

								}
							});

					return false;
				});
	</script>


	<script>
		$(document).ready(function() {
			$("#myModal").on("hidden.bs.modal", function() {
				document.getElementById("form1").reset();
				

			});
		});
	</script>




	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#form1, #editTask')
									.bootstrapValidator(
											{
												message : '<t:i18n id="group.validation"/>',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},submitHandler: function(validator, form, submitButton) {
										          
										        },
												fields : {
													email : {
														validators : {
															emailAddress : {
																message : "<t:i18n id='group.validation.email'/>"
															}

														}
													},
													task_name : {
														message : "<t:i18n id='group.validation.name'/>",
														validators : {
															notEmpty : {
																message : "<t:i18n id='group.validation.name.empty'/>"
															},
										                    stringLength: {
										                        min: 2,
										                        max: 70,
										                        message: "<t:i18n id='task.validation.name'/>"
										                    }
														}
													},
													task_description : {
														message : "<t:i18n id='group.validation.description'/>",
														validators : {
															notEmpty : {
																message : "<t:i18n id='group.validation.description.empty'/>",
																
															},
										                    stringLength: {
										                        min: 6,
										                        max: 400,
										                        message: "<t:i18n id='task.validation.desc'/>"
										                    }

														}
													},
													task_deadline : {
														validators : {
															notEmpty : {
																message : "<t:i18n id='group.validation.deadline'/>"
															},
														}
													},
												}
											});
							
						});
	</script>




</body>
</html>