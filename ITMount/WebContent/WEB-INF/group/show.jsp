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
<script src="${pageContext.request.contextPath}/assets/js/group.js"></script>



<title>${ group.name }</title>

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




	<a href="<c:url value="/openCreateTask"/>">Create Task</a>



	<%-- <c:if test="${user.role == 'lecturer' }"> --%>
		<jsp:include page="teacherPanel.jsp"></jsp:include>
	<%-- </c:if> --%>
	<!-- ПАНЕЛЬ ПОДІЙ  -->
	<div>
		<div class="col-md-10 column">
			<div class="panel panel-default">
				<div class="panel-heading">
					<a data-toggle="modal" href="#addEvent"><i
						class="glyphicon glyphicon-plus"></i></a>Events
				</div>
				<div class="panel-body">

					<ul class="list-group">
						<c:forEach items="${ events}" var="event">
							<li class="list-group-item"><c:out
									value="${event.description }" /> <c:out value="${event.date }" /></li>
						</c:forEach>

					</ul>

				</div>

			</div>



		</div>


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