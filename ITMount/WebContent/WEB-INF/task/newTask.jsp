<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create new task</title>
<jsp:include page="../page/head.jsp" />
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

		<!-- Modal HTML -->
		<div id="myModal" class="modal fade bs-example-modal-sm">
			
			
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<form name=" form1"
						action="TaskServlet?action=createTask&id_group=${id_group }"
						method="post" enctype="multipart/form-data" role="form"
						role="form" id="form1">

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
								<label for="login-password"><i class="icon-lock"></i> <b>File</b></label>
								<input name="task_file" class="form-control" id="file"
									type="file" placeholder="">
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary">Add task</button>
							</div>

						</div>

					</form>
				</div>
			</div>
		</div>
	</div>



	<script>
		var form = $('#form1');
		form.submit(function() {

			request = $.ajax({
				type : form.attr('method'),
				url : form.attr('action'),
				data : form.serialize(),
				success : function(text) {

					$("#incorectData").hide();
					document.getElementById("form1").reset();
					$('#myModal').modal('hide');
					alert("Task was successfully created");

				},
				error : function() {
					$("#incorectData").show();
				}
			});

			return false;
		});
	</script>

	<script>
		$(document).ready(function() {
			$(".modal").on("hidden.bs.modal", function() {
				document.getElementById("form1").reset();
				$("#incorectData").hide();
			});
		});
	</script>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-sm-5">
					<div class="basic-login">
						<form action="TaskServlet?action=createTask&id_group=${id_group }"
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
								<input name="task_file" class="form-control" id="file"
									type="file" placeholder="">
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
	<jsp:include page="../page/script.jsp" />

</body>
</html>