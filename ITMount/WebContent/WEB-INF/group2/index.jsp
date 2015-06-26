<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Courses</title>
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />



	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>Groups</h1>
					<a href="<c:url value="/GroupServlet?action=new" />">Create New
						Group</a>


				</div>
			</div>
		</div>
	</div>

	<table>
		<th>Course</th>
		<th>Teacher</th>
		<th>Group Name</th>
		<th>Active</th>
		<th>Delete Group</th>
		<th>Edit</th>
		<tbody>
			<c:forEach items="${ groups}" var="group">
				<tr>
					<td><c:out value="${group.course.name }" /></td>
					<td><c:out
							value="${group.teacher.name } ${group.teacher.surname } ${group.teacher.middle_name }" /></td>
					<td><c:out value="${group.name }"></c:out></td>
					<td><c:out value="${group.is_active}" /></td>
					<td><a
						href="<c:url
						value="/GroupServlet?action=delete&group_id=${group.id}" />">Delete</a>
					</td>
					<td><a
						href="<c:url
						value="/GroupServlet?action=edit&group_id=${group.id}" />">Edit</a>
					</td>
					<td><a
						href="<c:url
						value="/GroupServlet?action=show&group_id=${group.id}" />">Show</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<jsp:include page="../page/footer.jsp" />
</body>
</html>