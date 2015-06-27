<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create new task</title>

<!--  <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet"> -->
<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">


<%-- <jsp:include page="../page/head.jsp" /> --%>
<link href="responsive.css" rel="stylesheet">

</head>
<body>



	<div class="container" style="">
		<div class="row">
			<div class="col-sm-6">
				<div class="panel-group" id="accordion" role="tablist"
					aria-multiselectable="true">

					<c:if test="${empty listtasks}">
						<h3>No Tasks</h3>
					</c:if>
					<c:if test="${not empty listtasks}">
						<h3>All Tasks</h3>
					</c:if>


					<c:forEach items="${requestScope.listtasks}" var="tasks">

						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingThree">
								<h4 class="panel-title">
									<a class="collapsed" role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapse${tasks.key.id}"
										aria-expanded="false" aria-controls="collapse${tasks.key.id}">
										${tasks.key.name} </a>
								</h4>
							</div>
							<div id="collapse${tasks.key.id}" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingThree">
								<div class="panel-body">
									<div class="list-group">

										<table class="table table-bordered">
											<tr>
												<td>Task name</td>
												<td>${tasks.key.name }</td>
											</tr>
											<tr>
												<td>Task description</td>
												<td>${tasks.key.description }</td>
											</tr>
											<tr>
												<td>Task deadline</td>
												<td>${tasks.key.deadline }</td>
											</tr>
											<tr>
												<td>Task</td>
												<td>
													<p>
														<a
															href="<c:url value="/downloadFile?file=${tasks.key.file}"/>"
															class="btn btn-primary btn-sm">Download</a>
													</p>


												</td>
											</tr>

											<c:if
												test="${user.role =='student' || user.role =='applicant' }">
												<tr>
													<td>Your work</td>
													<td><c:choose>
															<c:when test="${tasks.key.getAvailable() == false}">
															Time out
														</c:when>

															<c:otherwise>
																<c:choose>

																	<c:when test="${tasks.value !=null}">
																		<p>
																			<a data-toggle="modal" href="#updateHomeWork"
																				data-delete="${tasks.value.getData()}"
																				data-homework="${tasks.value.getId()}"
																				class="open-updateHomeWork btn btn-primary btn-sm">Update</a>
																		</p>
																	</c:when>

																	<c:otherwise>
																		<p>
																			<a data-toggle="modal" href="#uploadHomeWork"
																				data-task="${tasks.key.getId()}"
																				class="open-uploadHomeWork btn btn-primary btn-sm">Add
																			</a>
																		</p>

																	</c:otherwise>
																</c:choose>
															</c:otherwise>
														</c:choose></td>

													<%-- 												<c:if test="${tasks.key.getAvailable() == false}">It's false!</c:if> --%>

												</tr>
											</c:if>
										</table>

									</div>

								</div>
							</div>
						</div>

					</c:forEach>

				</div>


			</div>
		</div>
	</div>

	<div id="uploadHomeWork" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Add new task</h4>
				</div>
				<div class="modal-body">
					<form action="<c:url value="/Homework?action=upload"/>"
						method="post" enctype="multipart/form-data" id="uploadHomework"
						role="form">

						<div class="form-group">
							<input class="form-control" id="idTask" name="task_id"
								type="hidden" value="">
						</div>

						<input type="hidden" name="user_id" value="${user.id}" />


						<div class="form-group">
							<label for="register-username"><i class="icon-user"></i>
								<b>File</b></label> <input class="form-control" id="register-username"
								type="file" placeholder="" name="file">
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


	<div id="updateHomeWork" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Add file</h4>
				</div>
				<div class="modal-body">
					<form action="<c:url value="/Homework?action=update"/>"
						method="post" enctype="multipart/form-data" id="uploadHomework"
						role="form">


						<div class="form-group">
							<input class="form-control" id="idDelete" name="uploadFile"
								type="hidden" value="">
						</div>
						<div class="form-group">
							<input class="form-control" id="idHomework" name="id_homework"
								type="hidden" value="">
						</div>

						<div class="form-group">
							<label for="register-username"><i class="icon-user"></i>
								<b>File</b></label> <input class="form-control" id="register-username"
								type="file" placeholder="" name="file">
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

	<script>
		$(document).on("click", ".open-updateHomeWork", function() {

			var filedelete = $(this).data('delete');
			var homeworkid = $(this).data('homework');
			$(".form-group #idDelete").val(filedelete);
			$(".form-group #idHomework").val(homeworkid);

		});
	</script>

	<script>
		$(document).on("click", ".open-uploadHomeWork", function() {

			var taskid = $(this).data('task');
			$(".form-group #idTask").val(taskid);

		});
	</script>


</body>
</html>