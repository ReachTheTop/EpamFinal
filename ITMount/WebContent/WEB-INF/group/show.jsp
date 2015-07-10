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


<link rel="stylesheet" href="resources/css/font-awesome.css">
<link rel="stylesheet" href="resources/css/animate.css">


<link rel="stylesheet" href="resources/css/styleEvent.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/font-awesome.css"
	type="text/css">
<!-- <link rel="stylesheet" href="resources/css/bootstrap.min.css"> -->
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
				<c:if
					test="${user.role != 'student'|| user.role == 'student' && association.isActive == true }">
					<ul class="nav nav-pills nav-stacked">
						<li class="active"><a
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
						<c:if test="${user.role == 'lecturer' }">
							<li><a
								href="<c:url value="/GroupServlet?action=showVisiting&group_id=${group.id }" />"><i
									class="fa fa-dashcube"></i> <t:i18n id='group.visiting' /></a></li>
						</c:if>
						<c:if test="${user.role == 'student'  }">
							<li><a
								href="<c:url value="/Homework?action=show&group_id=${group.id }&users_id=${user.id }" />"><i
									class="fa fa-list fa-fw"></i> <t:i18n id='group.user.homework' /></a></li>
						</c:if>


						<li><c:choose>
								<c:when
									test="${user.role == 'lecturer' && group.teacher_id == user.id}">
									<c:choose>
										<c:when test="${group.isConfirmed == true}">
											<a id="disbandGroup" class='btn btn-danger'
												style="padding-top: 12px; padding-bottom: 12px;"><t:i18n
													id='group.disband' /></a>
										</c:when>
										<c:otherwise>
											<a class='btn btn-success' id='confirmation'><t:i18n
													id='group.confirm' /></a>

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

									<a id="leaveGroup" class='btn btn-danger'><t:i18n
											id='group.user.leave' /></a>

								</c:when>
							</c:choose></li>



					</ul>

				</c:if>
			</div>
			<div class="col-md-9">

				<c:if
					test="${not empty group.teacher_id && user.id  == group.teacher_id }">
					<jsp:include page="teacherPanel.jsp"></jsp:include>
				</c:if>



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
				<c:if
					test="${ user.role != 'student'||user.role == 'student' && association.isActive == true }">
					<div class="contact-box">

						<table class="table table-hover">
							<thead>
								<tr>
									<th><t:i18n id='group.user.image' /></th>
									<th><t:i18n id='group.user.name' /></th>
									<th>Email</th>
									<th><t:i18n id='group.user.contacts' /></th>
									<c:if test="${user.role == 'lecturer' }">
										<th><t:i18n id='group.user.homework' /></th>
									</c:if>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${users}" var="userS">

									<tr>
										<td><img alt="image" class="img-circle img-responsive"
											height="50px" width="50px" src="upload/${userS.image }"></td>
										<td><a
											href="<c:url value="UserServlet?user_id=${userS.id }" />">
												<strong>${userS.name } <br> ${userS.surname}
											</strong>
										</a></td>
										<td><strong>${userS.email }</strong></td>
										<td><i class=" fa fa-skype"></i>&nbsp;
											${userS.contacts.skype } <br> <i
											class=" fa fa-mobile-phone"></i>&nbsp; ${userS.contacts.phone }
										</td>
										<c:if test="${user.role == 'lecturer' }">
											<td><a class="btn btn-sm btn-primary"
												href="<c:url value="/Homework?action=show&group_id=${group.id }&users_id=${userS.id }" />"><t:i18n
														id='group.user.homework.button' /></a></td>
										</c:if>
									</tr>

								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<script>
		$("a#leaveGroup")
				.click(

						function() {
							var object = $(this);
							swal(
									{
										title : "<t:i18n id='bootstrap.AreYouSure'/>",
										text : "<t:i18n id='bootstrap.YouWillRegrate'/>",
										type : "warning",
										showCancelButton : true,
										confirmButtonClass : "btn-danger",
										confirmButtonText : "<t:i18n id='bootstrap.Yes'/>",
										cancelButtonText : "<t:i18n id='bootstrap.No'/>",
										closeOnConfirm : true,
										closeOnCancel : true
									},

									function(isConfirm) {

										if (isConfirm) {

											window.location.href = 'GroupUserServlet?action=leaveGroup&group_id=${group.id }&user_id=${user.id }';

										}

									});

						});

		$("a#disbandGroup")
				.click(

						function() {

							var object = $(this);
							swal(
									{
										title : "<t:i18n id='bootstrap.AreYouSure'/>",
										text : "<t:i18n id='bootstrap.YouWillRegrate'/>",
										type : "warning",
										showCancelButton : true,
										confirmButtonClass : "btn-danger",
										confirmButtonText : "<t:i18n id='bootstrap.Yes'/>",
										cancelButtonText : "<t:i18n id='bootstrap.No'/>",
										closeOnConfirm : true,
										closeOnCancel : true
									},

									function(isConfirm) {

										if (isConfirm) {

											window.location.href = 'GroupServlet?action=delete&group_id=${group.id }';

										}

									});

						});
	</script>

	<jsp:include page="../page/footer.jsp" />
</body>
</html>