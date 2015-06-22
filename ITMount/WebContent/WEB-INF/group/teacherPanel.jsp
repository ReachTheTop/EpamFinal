<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-default">
	<div class="panel-heading">Teacher Panel</div>
	<div class="panel-body">
		<div class="row-fluid">
			<form action="GroupServlet?group_id=${group.id }" id='rebase-group'
				method="post">

				
					<div class="input-append">
						<input id="group" hidden="true" value="${group.id }"> <input
							class="span2" id="myAutocomplete" type="text" name="users" />
						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
								Actions<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a data-toggle="modal" href="#deleteUserFromGroup">Delete
										users</a></li>
								<li><a data-toggle="modal" href="#addUserToGroup">Add
										users</a></li>
								<li><a data-toggle="modal" id="groupEditModal"
									href="#confirmParticipates">Confirm Participates</a></li>

								<li><a data-toggle="modal" id="groupEditModal"
									href="#editGroup">Rebase Users</a></li>
							</ul>
						</div>
					
				</div>
				<jsp:include page="deleteUser.jsp"></jsp:include>
				<jsp:include page="addUser.jsp"></jsp:include>
				<jsp:include page="submitUsers.jsp"></jsp:include>
				<jsp:include page="rebasePartial.jsp" />
			</form>
		</div>
	</div>
</div>

