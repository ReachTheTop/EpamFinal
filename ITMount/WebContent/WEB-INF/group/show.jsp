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

<title>${ group.name }</title>

</head>
<body>
	<jsp:include page="../page/header.jsp" />



	<h3>
		<c:out value="${group.name }" />
	</h3>


	<div class="row-fluid">
  <a href="<c:url value="/openCreateTask"/>">Create Task</a>

 <h3>
  <c:out value="${group.name }" />
 </h3>




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

	<jsp:include page="../page/footer.jsp" />


</body>
</html>