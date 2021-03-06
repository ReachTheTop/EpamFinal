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

<script type="text/javascript" src="assets/chat/jquery-2.0.3.js"></script>
<script type="text/javascript" src="assets/chat/jquery.atmosphere.js"></script>
<script type="text/javascript" src="assets/chat/application.js"></script>
<script type="text/javascript" src="assets/chat/chatHistory.js"></script>
<link rel="stylesheet" href="assets/chat/css/chat.css">

<link rel="stylesheet" href="resources/css/sticky-footer.css">



<title><c:out value="${ group.name }" /> <t:i18n
		id='group.chat' /></title>

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


	<div id="wrap">
		<jsp:include page="../page/header.jsp" />

		<div class="section section-breadcrumbs">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h1>
							<c:out value="${group.name }" />
							<t:i18n id='group.chat' />
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
						<li class="active"><a
							href="<c:url value="/GroupServlet?action=chat&group_id=${group.id }" />"><i
								class="fa fa-weixin"></i>
							<t:i18n id='group.chat' /></a></li>

						<c:if test="${user.role == 'lecturer' }">
							<li><a
								href="<c:url value="/GroupServlet?action=showVisiting&group_id=${group.id }" />"><i
									class="fa fa-dashcube"></i> <t:i18n id='group.visiting' /></a></li>
						</c:if>
						<c:if test="${user.role == 'student' }">
							<li><a
								href="<c:url value="/Homework?action=show&group_id=${group.id }&users_id=${user.id }" />"><i
									class="fa fa-list fa-fw"></i> <t:i18n id='group.homework' /></a></li>
						</c:if>
					</ul>
				</div>
				<div class="col-md-9">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<span class="glyphicon glyphicon-comment"></span>
							<t:i18n id='group.chat' />
						</div>
						<div class="panel-body">
							<ul class="chat">
								<li><a id="history"><t:i18n id='group.chat.history' /></a></li>

							</ul>
						</div>
						<div class="panel-footer">
							<div class="form-group">


								<textarea id="input" class="form-control" disabled="disabled"></textarea>
								<input hidden="true" type="text" id="room" value="${group.id }" />
								<input hidden="true" type="text" id="author"
									value="${user.name } ${user.surname}" /> <input hidden="true"
									type="text" id="image" value="${user.image} " />

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="../page/footer.jsp" />
</body>
</html>