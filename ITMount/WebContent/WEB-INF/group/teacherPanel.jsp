<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>

<div class="panel panel-default">


					
	<div class="panel-heading"><t:i18n id='group.teacher.panel'/></div>
	 <div class="row">
	<div class="panel-body">
		
			<form action="GroupServlet?group_id=${group.id }" id='rebase-group'
				method="post">

					
					<div class="form-group">
					  <div class="col-sm-10">
						<input id="group" hidden="true" value="${group.id }"> <input
							class="span2" id="myAutocomplete" type="text" name="users" />
							</div>
							 <div class="col-sm-2">
						<div class="btn-group ">
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
								<t:i18n id='group.actions'/><span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a data-toggle="modal" href="#deleteUserFromGroup"><t:i18n id='group.user.delete'/></a></li>
								<li><a data-toggle="modal" href="#addUserToGroup"><t:i18n id='group.user.add'/></a></li>
								<li><a data-toggle="modal" id="groupEditModal"
									href="#confirmParticipates"><t:i18n id='group.user.participates'/></a></li>

								<li><a data-toggle="modal" id="groupEditModal"
									href="#editGroup"><t:i18n id='group.user.rebase'/></a></li>
							</ul>
						</div>
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

