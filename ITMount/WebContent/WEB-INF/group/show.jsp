<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ group.name }</title>
<jsp:include page="../page/head.jsp" />


<script src="${pageContext.request.contextPath}/assets/js/project.js"></script>
<!-- block for autokomplite -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/jquery.autocomplete.multiselect.js"></script>
<!-- block for autokomplite -->
<!-- <link rel="stylesheet" type="text/css"
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" /> -->
<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main.css"> --%>
<!-- end block for autokomplite -->

<title>${ group.name }</title>

</head>
<body>
	<jsp:include page="../page/header.jsp" />



	<h3>
		<c:out value="${group.name }" />
	</h3>


	<div class="row-fluid">



		<form action="GroupServlet?group_id=${group.id }" method="post">
			<input id="group" hidden="true" value="${group.id }">
			<div class="row-fluid">
				<input id="myAutocomplete" type="text" name="users" />
			</div>
			<button type="submit" name="action" value="remove">Remove
				Users</button>
			<button type="submit" name="action" value="add">Add Users</button>
			<button type="submit" name="action" value="leave">Leave
				Users</button>
		</form>
	</div>









	<div class="container">
		<h2>Group Users</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.name }${user.surname}</td>
						<td>${user.email }</td>
						<td><a
							href="<c:url value="/Homework?action=show&group_id=${group.id }&users_id=${user.id }" />">Show
								homework</a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>



	<c:choose>
		<c:when test="${user.role == 'lecturer' }">
			<a href="#myModal" class="btn btn-lg btn-primary"
				style="margin-left: 13px;" data-toggle="modal">New Exam</a>

			<!-- Modal HTML -->
			<div id="myModal" class="modal fade bs-example-modal-sm">


				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<form name=" form1"
							action="GroupExamServlet?action=create&group_id=${group.id}"
							method="post" enctype="multipart/form-data" role="form"
							role="form" id="form1">

							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">Create new exam</h4>
							</div>
							<div class="modal-body">

								<div id="incorectData" style="display: none;"
									class="alert alert-danger">
									<strong>Incorect data!</strong>
								</div>



								<div class="form-group">
									<label for="login-username"><i class="icon-user"></i> <b>Exam
											Description</b></label>
									<p>
										<textarea class="form-control" name="description" rows="3"
											name="text"></textarea>
									</p>
								</div>

								<div class="form-group">
									<label for="login-username"><i class="icon-user"></i> <b>Exam
											Date</b></label> <input name="exam_date" class="form-control"
										id="login-username" type="datetime-local">
								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary">Set exam</button>
								</div>

							</div>

						</form>
					</div>
				</div>
			</div>
			<script>
				$(document).ready(function() {
					$(".modal").on("hidden.bs.modal", function() {
						document.getElementById("form1").reset();
						$("#incorectData").hide();
					});
				});
			</script>

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
							alert("Exam was successfully created");

						},
						error : function() {
							$("#incorectData").show();
						}
					});

					return false;
				});
			</script>

		</c:when>
		<c:when test="${user.role == 'student' || user.role == 'applicant' }">
			<c:if test="${ not empty association}">
				<c:choose>
					<c:when test="${empty association.exam_date}">
						<form
							action="GroupUserServlet?action=chousExam&group_id=${group.id }&user_id=${user.id}"
							method="post">
							<div class="form-group">
								
									<select name="exam_date">
										<c:forEach items="${exams }" var="exam">
											<option value="${exam.id }"><c:out value="${exam.exam_date }" /> </option>
										</c:forEach>
									</select>
									<input type="submit" value="Chouse exam date">
							</div>
						</form>
					</c:when>
					<c:otherwise>
						<div>
							<c:out value="${association.exam_date }"></c:out>
						</div>
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:when>
	</c:choose>


	<jsp:include page="../page/footer.jsp" />


</body>
</html>