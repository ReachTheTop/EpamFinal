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
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.0/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.0/js/bootstrap-toggle.min.js"></script>

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

			<div class="btn-group">
				<button type="submit" class="btn btn-primary" name="action"
					value="remove">Remove Users</button>
				<button type="submit" class="btn btn-primary" name="action"
					value="add">Add Users</button>
				<button type="submit" class="btn btn-primary" name="action"
					value="leave">Leave Users</button>
				<a data-toggle="modal" class="btn btn-primary" id="groupEditModal"
					href="#editGroup">Rebase Users</a>
			</div>


			<div id="editGroup" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title">Group settings</h4>
						</div>
						<div class="modal-body">
							<div class="form-group" hidden="true">
								<input name="group_id" class="form-control" id="group-id-edit"
									type="text" placeholder="">
							</div>

							<input id="toggle-event" checked name="marker" type="checkbox" data-toggle="toggle">
							<div id="console-event"></div>
							<script type="text/javascript">
								$(function() {
									
									$.get("GroupServlet?action=getTeacherGroups&group_id=${group.id}", function(response){
										var $option ;
										$.each(response, function(index,item){
											$option = $("<option id='teacher-id'>");
											$option.val(item['id']);
											$option.text(item['name']);
											$('#group-teacher-edit').append($option);		
										});
										$('#group-teacher-edit').val('${group.id}');
									});
									
									$('#create-group').hide();
									
									$('#toggle-event').change(function() {
										if ($(this).prop('checked')) {
											$('#create-group').hide();
											$('#select-group').show();
										} else {
											$('#create-group').show();
											$('#select-group').hide();
										}
									});
								});
							</script>
							<div class="form-group" id='create-group'>
								<label for="login-username"><i class="icon-user"></i> <b>Create
										Group</b></label> <input name="name" class="form-control"
									id="group-name-edit" type="text" placeholder="">
							</div>

							<div class="form-group" id="select-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Select
										Group</b></label> <select name="new_group_id" class="form-control"
									id="group-teacher-edit"></select>
							</div>
							<div class="form-group">

								<button type="submit" name="action" value="rebase"
									class="btn pull-right">Rebase</button>
								<div class="clearfix"></div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				$('button#submit-group-edit').click(function() {
					$('#editGroup').modal('hide');
				});
			</script>



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

	<jsp:include page="../page/footer.jsp" />


</body>
</html>