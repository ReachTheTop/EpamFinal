<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Courses</title>
<jsp:include page="../page/head.jsp" />
<script src="${pageContext.request.contextPath}/assets/js/admin.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/jquery.bootpag.min.js"></script>
<link rel="stylesheet" href="resources/css/tabPanel.css"></link>
</head>
<body>
	<jsp:include page="../page/header.jsp" />



	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>My Page</h1>
				</div>
			</div>
		</div>
	</div>

	<p>${user }</p>


	<c:choose>
		<c:when test="${user.role != 'admin' }">
			<c:forEach items="${groups }" var="group">
				<div>
					<a
						href="<c:url
							value="/GroupServlet?action=show&group_id=${group.id }" />">
						<label><c:out value="${group.course.name }" /> </label> <label><c:out
								value="${group.name }" /> </label>
					</a>
				</div>
			</c:forEach>
		</c:when>

		<c:otherwise>

>

			<div class="container">
				<h2>Admin Panel</h2>
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" class="courses"
									data-parent="#accordion" href="#collapse1">Courses</a>
							</h4>
						</div>
						<div id="collapse1" class="panel-collapse collapse">
							<div class="panel-body" id="courses-body">

								<div class="row-fluid">

									<div class="form-group">

										<input type="text" id="search-field"
											placeholder="Course Search" class='form-control'>
									</div>


								</div>
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Name</th>
											<th>Is active</th>
											<th>Triger course</th>
										<tr>
									</thead>
									<tbody id="courses-body">

									</tbody>
								</table>
								<div class="row" align="center">

									<div id="page-selection"></div>

								</div>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" class='groups'
									data-parent="#accordion" href="#collapse2">Groups</a>
							</h4>
						</div>
						<div id="collapse2" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="row-fluid">

									<div class="form-group">

										<input type="text" id="search-groups"
											placeholder="Group Search" class='form-control'>
									</div>


								</div>
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Course</th>
											<th>Group Name</th>
											<th>Teacher</th>
											<th>Confirmed</th>
										<tr>
									</thead>
									<tbody id="groups-body">

									</tbody>
								</table>
								<div class="row" align="center">

									<div id="group-page-selection"></div>

								</div>


							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" class="users" data-parent="#accordion"
									href="#collapse3">Users</a>
							</h4>
						</div>
						<div id="collapse3" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="row-fluid">

									<div class="form-group">

										<input type="text" id="search-users"
											placeholder="Group Search" class='form-control'>
									</div>


								</div>
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Name</th>
											<th>Surname</th>
											<th>Role</th>
											<th>Email</th>
											<th>Phone</th>
											<th>Skype</th>
											<th>Active</th>
										<tr>
									</thead>
									<tbody id="users-body">

									</tbody>
								</table>
								<div class="row" align="center">

									<div id="user-page-selection"></div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</c:otherwise>
	</c:choose>



	<jsp:include page="../page/footer.jsp" />

</body>
</html>