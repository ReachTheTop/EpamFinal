<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>

<style type="text/css">
.nav-pills>li.active>a {
	background-color: #4B4B4C;
}

.nav-pills>li.active>a:hover {
	background-color: #4B4B4C;
}

li>a {
	color: #514D61;
	text-decoration: none;
}

.sidebar-nav .navbar li a {
	padding-top: 12px;
	padding-bottom: 12px;
}

</style>

<div class="container">
	<div class="col-sm-3">
		<nav class="nav-sidebar">
			<ul class="nav nav-pills nav-stacked">
				<li class="active"><a href="#tab1" data-toggle="tab">Profile</a></li>


				<li class=""><a class="courses" data-toggle="tab" href="#tab4"><t:i18n
							id="admin.courses" /></a></li>
				<li class=""><a class="groups" data-toggle="tab" href="#tab5"><t:i18n
							id="admin.groups" /></a></li>

				<li class=""><a class="users" data-toggle="tab" href="#tab6"><t:i18n
							id="admin.users" /></a></li>

				<li class=""><a class="language" data-toggle="tab"
					href="#tab7"><t:i18n id="admin.languages" /></a></li>


				<li><jsp:include page="statistic.jsp" /></li>
				<li><a href="<c:url value='FeedbackServlet?action=inbox' />"><t:i18n
							id="admin.feedback" /></a></li>


			</ul>
		</nav>
	</div>
	<div class="col-sm-9">
		<div class="tab-content">
			<div class="tab-pane active text-style" id="tab1">
				<jsp:include page="profileTab.jsp" />
			</div>

			<div class="tab-pane text-style" id="tab4">
				<jsp:include page="courseTab.jsp" />
			</div>
			<div class="tab-pane text-style" id="tab5">
				<jsp:include page="groupTab.jsp" />
			</div>
			<div class="tab-pane text-style" id="tab6">
				<jsp:include page="userTab.jsp" />
			</div>
			<div class="tab-pane text-style" id="tab7">
				<jsp:include page="languageTab.jsp" />
			</div>
		</div>

	</div>
</div>