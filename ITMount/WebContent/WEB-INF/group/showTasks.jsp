<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ group.name }</title>
<jsp:include page="../page/head.jsp" />


<script src="${pageContext.request.contextPath}/assets/js/project.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/jquery.autocomplete.multiselect.js"></script>

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
			<div class="row">
				<div class="col-md-12">
					<h1>
						<c:out value="${group.name }" />
					</h1>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<ul class="nav nav-pills nav-stacked">
						<li  ><a
						href="<c:url value="/GroupServlet?action=show&group_id=11" />"><i
							class="fa fa-home fa-fw"></i>Main</a></li>
					<li class="active" ><a
						href="<c:url value="/GroupServlet?action=showTasks&group_id=11" />"><i
							class="fa fa-tasks fa-fw"></i>Tasks</a></li>
					<li ><a
						href="<c:url value="/GroupServlet?action=showEvents&group_id=11" />"><i
							class="fa fa-users fa-fw"></i>Events</a></li>
					<li><a
						href="<c:url value="/GroupServlet?action=showExams&group_id=11" />"><i
							class="fa fa-check fa-fw"></i>Exams</a></li>
				</ul>
			</div>
			<div class="col-md-9">
			
			<a href="#myModal" class="btn btn-sm btn-primary" data-toggle="modal">Create
				Task</a>

				<jsp:include page="AllTaskUser.jsp"></jsp:include>

			</div>
		</div>
	</div>
	
	
<jsp:include page="../page/footer.jsp" />




<div id="myModal" class="modal fade bs-example-modal-sm">


	<div class="modal-dialog modal-sm">
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
						<label for="register-username"><i class="icon-user"></i> <b>File</b></label>
						<input class="form-control" id="task_file" type="file"
							placeholder="" name="file">
					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Add task</button>
					</div>

				</div>

			</form>
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
	form
			.submit(function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				request = $
						.ajax({
							type : form.attr('method'),
							url : form.attr('action'),

							data : new FormData(this),
							processData : false,
							contentType : false,

							//data : form.serialize(),
							success : function(text) {

								$("#incorectData").hide();
								document.getElementById("form1").reset();
								$('#myModal').modal('hide');
								showToaast("Task was  successfully created", 1);

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
	


	
</body>
</html>