<%@ page language="java" contentType="text/html;  charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
<title>${group.name }'sJournal</title>
<jsp:include page="../page/head.jsp" />
<link rel="stylesheet" href="resources/css/journal.css">
<link rel="stylesheet" href="resources/css/iCheck/blue.css">
<script src="resources/js/icheck.min.js"></script>
</head>
<body>
	<jsp:include page="../page/header.jsp" />
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>Journal</h1>
				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<table class="container table-bordered table-nonfluid">
			<thead>
				<th class="col-md-3">Student's Name</th>
				<c:forEach items="${list[0].attendanceQueue}" var="queue">
					<th class="col-md-1">
						<c:set var = "simpleDate" value="${queue.value.simpleDate}"/>
						<c:set var = "shortDate" value = "${fn:substring(simpleDate,0,5)}"/>
						<c:out value = "${shortDate }"/>
					</th>
				</c:forEach>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="userAttendance">
					<tr>
						<td><c:out value="${userAttendance.user.name }" /> <c:out
								value="${userAttendance.user.surname }" /></td>
						<c:forEach items="${userAttendance.attendanceQueue }"
							var="attendance">

							<td
								<%--title="${attendance.value.description}"--%> 
								class="col-md-1">
									<c:choose>
										<c:when test="${attendance.value.visit == 'true'}">
											<input type="checkbox" name="${attendance.value.id }" checked>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="${attendance.value.id }" unchecked>
										</c:otherwise>
									</c:choose>
							</td>

						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="newCourse" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">New course</h4>
				</div>
				<div class="modal-body">
					<form action="CourseServlet?action=create" method="post"
						enctype="multipart/form-data" role="form" role="form">
						<div class="form-group">
							<label for="login-username"><i class="icon-user"></i> <b>Course
									Name</b></label> <input name="name" class="form-control"
								id="login-username" type="text" placeholder="">
						</div>
						<div class="form-group">
							<label for="login-password"><i class="icon-lock"></i> <b>Icon</b></label>
							<input name="icon" class="form-control" id="file" type="file"
								required="required" placeholder="">
						</div>


						<div class="form-group">
							<label for="login-password"><i class="icon-lock"></i> <b>Description</b></label>
							<input name="description" class="form-control" type="text"
								rows="5" id="comment" placeholder="">
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
	<jsp:include page="../page/footer.jsp" />
	<script>
		$(document).ready(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
			});
		});
	</script>
	<script src="resources/js/journal.js"></script>
</body>
</html>