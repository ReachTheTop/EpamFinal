<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ group.name }</title>
<jsp:include page="../page/head.jsp" />


<script src="${pageContext.request.contextPath}/assets/js/project.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/jquery.autocomplete.multiselect.js"></script>

<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.0/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.0/js/bootstrap-toggle.min.js"></script>

<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">

<link rel="stylesheet" href="resources/css/font-awesome.css">
<link rel="stylesheet" href="resources/css/animate.css">


<link rel="stylesheet" href="resources/css/styleEvent.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/font-awesome.css"
	type="text/css">

<title>${ group.name }</title>

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
}
</style>

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
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<ul class="nav nav-pills nav-stacked">
						<li  class="active" ><a
						href="<c:url value="/GroupServlet?action=show&group_id=11" />"><i
							class="fa fa-home fa-fw"></i>Main</a></li>
					<li ><a
						href="<c:url value="/GroupServlet?action=showTasks&group_id=11" />"><i
							class="fa fa-tasks fa-fw"></i>Tasks</a></li>
					<li ><a
						href="<c:url value="/GroupServlet?action=showEvents&group_id=11" />"><i
							class="fa fa-users fa-fw"></i>Events</a></li>
					<li><a
						href="<c:url value="/GroupServlet?action=showExams&group_id=11" />"><i
							class="fa fa-check fa-fw"></i>Exams</a></li>





				</ul>
			</div>
			<div class="col-md-9">

				<c:if
					test="${not empty group.teacher_id && user.id  == group.teacher_id }">
					<jsp:include page="teacherPanel.jsp"></jsp:include>
				</c:if>


				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#myModal" class="btn btn-sm btn-primary"
							data-toggle="modal">Create Task</a>
					</div>

					<div class="panel-footer">
						<c:choose>
							<c:when
								test="${user.role == 'lecturer' && group.teacher_id == user.id}">
								<c:choose>
									<c:when test="${group.isConfirmed == true}">
										<a class='btn btn-danger'
											href="<c:url value="GroupServlet?action=delete&group_id=${group.id }" />">Disband
											group</a>
									</c:when>
									<c:otherwise>
										<a class='btn btn-success' id='confirmation'>Confirm group</a>

										<script type="text/javascript">
											$('#confirmation')
													.click(
															function() {
																$(this)
																		.remove();
																$
																		.get(
																				'AdminServlet?action=confirm&group_id='
																						+ "${group.id}",
																				function(
																						response) {

																				});
																var $a = $("<a class='btn btn-danger' href='GroupServlet?action=delete&group_id=${group.id}'>Disband group</a>");
																$(
																		"div#panel-footer")
																		.append(
																				$a);
															});
										</script>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when
								test="${user.role == 'student' || user.role == 'applicant' }">
								<a
									href="<c:url value="GroupUserServlet?action=leaveGroup&group_id=${group.id }&user_id=${user.id }" />">Leave
									group</a>

							</c:when>
						</c:choose>
					</div>
				</div>

				<div class="container">
					<c:if test="${user.role =='student' || user.role =='applicant' }">
						<c:choose>
							<c:when
								test="${ empty association.exam && association.isActive == false}">
								<c:choose>
									<c:when test="${ empty exams }">
										<jsp:include page="emptyExam.jsp"></jsp:include>
									</c:when>
									<c:otherwise>
										<jsp:include page="chouseExam.jsp"></jsp:include>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ association.isActive == false }">
								<jsp:include page="examination.jsp" />
							</c:when>
						</c:choose>
					</c:if>
				</div>


				<c:forEach items="${users}" var="user">
					<div class="col-lg-6">
						<div class="contact-box">
							<a href="profile.html">
								<div class="col-sm-4">
									<div class="text-center">
										<img alt="image" class="img-circle m-t-xs img-responsive"
											src="upload/${user.image }">
										<!--                             <div class="m-t-xs font-bold">Graphics designer</div> -->
									</div>
								</div>
								<div class="col-sm-8">
									<h4>
										<strong>${user.name } <br> ${user.surname}
										</strong>
									</h4>

									<strong>${user.email }</strong><br>


									<p>
										<i class="fa fa-birthday-cake"></i>
										<fmt:formatDate pattern="yyyy-MM-dd" value="${user.birtday }" />
									</p>
									<p>
										<i class=" fa fa-skype"></i>&nbsp; ${user.contacts.skype }
									</p>
									<p>
										<i class=" fa fa-mobile-phone"></i>&nbsp;
										${user.contacts.phone }
									</p>


								</div>
								<div class="clearfix"></div>
							</a> <a class="btn btn-sm btn-primary" style = "margin-left: 36%;"
								href="<c:url value="/Homework?action=show&group_id=${group.id }&users_id=${user.id }" />">Show
								homework</a>
						</div>

					</div>


				</c:forEach>

			</div>
		</div>
	</div>


	<jsp:include page="../page/footer.jsp" />
</body>
</html>