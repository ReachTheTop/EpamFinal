<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create new task</title>

<!--  <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet"> -->



<jsp:include page="../page/head.jsp" />
<link href="responsive.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../page/header.jsp" />

	<!-- Page Title -->
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>New Task</h1>
				</div>
			</div>
		</div>
	</div>

	<div class="bs-example">
		<!-- Button HTML (to Trigger Modal) -->
		<a href="#myModal" class="btn btn-lg btn-primary"
			style="margin-left: 13px;" data-toggle="modal">Create Task</a>

		<!-- 		<button type="button" class="btn btn-primary" id="showtoast">Show Toast</button> -->


		<!-- Modal HTML -->
		<div id="myModal" class="modal fade bs-example-modal-sm">


			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<!-- 					<form name=" form1" -->
					<%-- 						action="TaskServlet?action=createTask&group_id=${group_id }" --%>
					<!-- 						method="post" enctype="multipart/form-data" role="form" -->
					<!-- 						role="form" id="form1"> -->

					<form name=" form1"
						action="<c:url value="/TaskServlet?action=createTask&group_id=${group_id }"/>"
						method="post" enctype="multipart/form-data" id="form1" role="form">

						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title">Create new Task</h4>
						</div>
						<div class="modal-body">

							<div id="incorectData" style="display: none;"
								class="alert alert-danger">
								<strong>Incorect data!</strong>
							</div>


							<div class="form-group">
								<label for="login-username"><i class="icon-user"></i> <b>Task
										Name</b></label> <input name="task_name" class="form-control"
									id="login-username" type="text" placeholder="">
							</div>
							<div class="form-group">
								<label for="login-username"><i class="icon-user"></i> <b>Task
										Description</b></label>
								<p>
									<textarea class="form-control" name="task_description" rows="3"
										name="text"></textarea>
								</p>
							</div>

							<div class="form-group">
								<label for="login-username"><i class="icon-user"></i> <b>Deadline</b></label>
								<input name="task_deadline" class="form-control"
									id="login-username" type="datetime-local">
							</div>


							<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>File</b></label> <input class="form-control" id="register-username"
									type="file" placeholder="" name="file">
							</div>

							<div class="modal-footer">
								<button type="submit" class="btn btn-primary"
									data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary">Add task</button>
							</div>

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
	
		var form = $('#form1');
		form.submit(function() {

			request = $.ajax({
				type : form.attr('method'),
				url : form.attr('action'),

				data : new FormData(this),
				processData : false,
				contentType : false,

				//data : form.serialize(),
				success : function(text) {

					$('#myModal').modal('hide');
					//showToaast("Task was  successfully created", 1);
					

				},
				error : function() {
					$("#incorectData").show();
					showToaast("Task was not  created", 0);
					
				}
			});

			return false;
		});
	</script>

	<script>
		$(document).ready(function() {
			$("#myModal").on("hidden.bs.modal", function() {
				document.getElementById("form1").reset();
				$("#incorectData").hide();

			});
		});
	</script>


	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#form1')
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
													task_name : {
														message : 'The Task name is not valid',
														validators : {
															notEmpty : {
																message : 'The Task name cannot be empty'
															},
														}
													},
													task_description : {
														message : 'The Task description  is not valid',
														validators : {
															notEmpty : {
																message : 'The Task description  cannot be empty'
															},
															
														}
													},
													task_deadline : {
														validators : {
															notEmpty : {
																message : 'The date is required and cannot be empty'
															},
																													}
													},
													photo : {
														validators : {
															file : {
																extension : 'png,jpg',
																maxSize : 5 * 1024 * 1024,
																message : 'Please choose a image file with a size less than 5M.'
															}
														}
													},
												}
											});
						});
	</script>


	<div class="container" style="">
		<div class="row">
			<div class="col-sm-6">
				<div class="panel-group" id="accordion" role="tablist"
					aria-multiselectable="true">

					<c:forEach items="${requestScope.listTask}" var="tasks">

						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingThree">
								<h4 class="panel-title">
									<a class="collapsed" role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapse${tasks.id}"
										aria-expanded="false" aria-controls="collapse${tasks.id}">
										${tasks.name} </a>
								</h4>
							</div>
							<div id="collapse${tasks.id}" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingThree">
								<div class="panel-body">
									<div class="list-group">

										<table class="table table-bordered">
											<tr>
												<td>Task name</td>
												<td>${tasks.name }</td>
											</tr>
											<tr>
												<td>Task description</td>
												<td>${tasks.description }</td>
											</tr>
											<tr>
												<td>Task deadline</td>
												<td>${tasks.deadline }</td>
											</tr>

										</table>

										<div class="form-group">
											<input name="task_file" class="form-control" id="file"
												type="file" placeholder="">
										</div>
										<div class="form-group">
											<button type="submit" class="btn btn-primary">Download
												file</button>
											<button type="submit" class="btn btn-primary">Add
												task</button>
										</div>
									</div>
								</div>
							</div>
						</div>

					</c:forEach>

				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-sm-5">
					<div class="basic-login">
						<form action="TaskServlet?action=createTask&group_id=${group_id }"
							method="post" enctype="multipart/form-data" role="form"
							role="form">

							<div class="form-group">
								<label for="login-username"><i class="icon-user"></i> <b>Task
										Name</b></label> <input name="task_name" class="form-control"
									id="login-username" type="text" placeholder="">
							</div>
							<div class="form-group">
								<label for="login-username"><i class="icon-user"></i> <b>Task
										Description</b></label>
								<p>
									<textarea name="task_description" rows="3" cols="55"
										name="text"></textarea>
								</p>
							</div>

							<div class="form-group">
								<label for="login-username"><i class="icon-user"></i> <b>Deadline</b></label>
								<input name="task_deadline" class="form-control"
									id="login-username" type="datetime-local">
							</div>


							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>File</b></label>
								<input name="file" class="form-control" id="file" type="file"
									placeholder="">
							</div>


							<div class="form-group">

								<button type="submit" class="btn pull-right">Add task</button>
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