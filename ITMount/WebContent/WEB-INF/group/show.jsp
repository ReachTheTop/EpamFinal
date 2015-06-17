<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ group.name }</title>
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />

	<h3>
		<c:out value="${group.name }" />
	</h3>
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
						href="<c:url value="/Homework?action=show&group_id=${group.id }&users_id=${user.id }" />">Show homework</a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<c:if test="${not group.isConfirmed  }">
		<a
			href="<c:url value="/GroupServlet?action=confirm&group_id=${group.id }" />">Confirm
			Group </a>
	</c:if>
	<jsp:include page="../page/footer.jsp" />
	<jsp:include page="../page/script.jsp" />

</body>
</html>