<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ group.name }</title>
<jsp:include page="../page/head.jsp" />


<script src="assets/js/project.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script src="assets/js/jquery.autocomplete.multiselect.js"></script>

<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.0/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.0/js/bootstrap-toggle.min.js"></script>

<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">

<link rel="stylesheet" href="resources/css/font-awesome.css">
<link rel="stylesheet" href="resources/css/animate.css">

<link rel="stylesheet" href="resources/css/sticky-footer.css">
<style type="text/css">
.nav-pills>li.active>a {
	background-color: #4B4B4C;
}

.nav-pills>li.active>a:hover {
	background-color: #4B4B4C;
}

a {
	color: #514D61;
	text-decoration: none;
}

.sidebar-nav .navbar li a {
	padding-top: 12px;
	padding-bottom: 12px;
}

@media (max-width: 1500px){}
.table-responsive {
  width: 100%;
  margin-bottom: 15px;
  overflow-y: hidden;
  overflow-x: scroll;
  border: 1px solid #ddd;
}



@media (max-width: 767px){}
.table-responsive>.table>thead>tr>th{
  white-space: nowrap;

}


}
</style>


</head>
<body>

<div id="wrap" >
	<jsp:include page="../page/header.jsp" />

	
		<div class="section section-breadcrumbs">
			<div class="container">
				<div class="row-fluid">
					<div class="col-md-12">
						<h1>
							<c:out value="${group.name }" />
							Visiting
						</h1>
					</div>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<ul class="nav nav-pills nav-stacked">
						<li><a
							href="<c:url value="/GroupServlet?action=show&group_id=${group.id }" />"><i
								class="fa fa-home fa-fw"></i> <t:i18n id='group.main' /></a></li>
						<li><a
							href="<c:url value="/GroupServlet?action=showTasks&group_id=${group.id }" />"><i
								class="fa fa-tasks fa-fw"></i> <t:i18n id='group.tasks' /></a></li>
						<li><a
							href="<c:url value="/GroupServlet?action=showEvents&group_id=${group.id }" />"><i
								class="fa fa-users fa-fw"></i> <t:i18n id='group.events' /></a></li>

						<li><a
							href="<c:url value="/GroupServlet?action=showExams&group_id=${group.id }" />"><i
								class="fa fa-check fa-fw"></i> <t:i18n id='group.exams' /></a></li>
						<li><a
							href="<c:url value="/GroupServlet?action=chat&group_id=${group.id }" />"><i
								class="fa fa-weixin"></i> <t:i18n id='group.chat' /></a></li>

						<li class="active"><a
							href="<c:url value="/GroupServlet?action=showVisiting&group_id=${group.id }" />"><i
								class="fa fa-dashcube"></i> Visiting</a></li>
					</ul>
				</div>

				<div class="col-md-9" style="padding-bottom: 10px">

					<div class="panel panel-default">

						<div class="panel-heading">
							Visiting Panel
							
							
							<a data-toggle="modal" href="#addVisiting"><i
								class="fa fa-plus fa-lg"></i></a>
						
							
						</div>

						<div class="panel-body">

							<div class="table-responsive">
								<table class="table table-hover">
									
									<thead>
										<tr>
											<th>Name</th>
											<c:forEach items="${requestScope.listUserVisit}" var="listUserVisit" end="0">
											
												<c:forEach items="${listUserVisit.dayVisit}" var="app">
											
													<th>${app.dayLesson }</th>
											
											</c:forEach>
											</c:forEach>
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${requestScope.listUserVisit}"
											var="listUserVisit">

											<tr>
												<td>${listUserVisit.userName } ${listUserVisit.userSurname } </td>

												<c:forEach items="${listUserVisit.dayVisit}" var="app">


													<c:if test="${app.present == true }">
														<td><i class="glyphicon glyphicon-ok"
															style="color: green; font-size: 18pt;"></i></td>
													</c:if>

													<c:if test="${app.present == false }">
														<td><i class="glyphicon glyphicon-remove"
															style="color: red; font-size: 18pt;"></i></td>
													</c:if>

											
												</c:forEach>												
											</tr>

										</c:forEach>
										
										
									</tbody>
								</table>								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	
		<jsp:include page="../page/footer.jsp" />
	
	
	<div id="addVisiting" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					Add New Lesson
				</h4>
			</div>
			<div class="modal-body">

					<div class="form-group">

						<label for="login-username"><i class="icon-user"></i> <b>
								Enter date lesson </b></label> <input name="dateLesson" class="form-control"
							id="dateLesson" type="date">
					</div>						

					<form action="UserServlet?action=update" id='editUserForm'
					method="post" enctype="multipart/form-data" role="form" role="form">


						<table id="tableVisit" class="table table-user-information">
							<tbody>
							
							<c:forEach items="${requestScope.listUserVisit}" var="user">
							
								<tr>

									<td >${user.userSurname }  ${user.userName }</td>
									
									<td id="userName"><p>
											<input type="checkbox" id = "${user.userId }" data-width="100"  data-size="small" checked data-toggle="toggle" data-on ="Present" data-off="Absent">
										</p></td>
								</tr>
							
							</c:forEach>					

							</tbody>


						</table>



						<div class="form-group">

						<button id="addLesson" type="submit" class="btn btn-primary pull-right">
							Add
						</button>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>

		</div>
	</div>

</div>

<script type="text/javascript">

$(document).ready(function() {
    $('#addLesson').click(function() {
        var names = [];
        $('#tableVisit input:checked').each(function() {
        	 alert($(this).val());
        	 alert($(this).attr("id"))
        });
        
    });
});


</script>



</body>
</html>