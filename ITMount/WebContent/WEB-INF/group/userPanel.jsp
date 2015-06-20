<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="col-md-10 column">
	<div class="panel panel-default">
		<div class="panel-heading">Teacher Panel</div>
		<div class="panel-body">
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
	</div>
</div>