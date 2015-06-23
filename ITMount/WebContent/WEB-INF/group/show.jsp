<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ group.name }</title>
<jsp:include page="../page/head.jsp" />


<script src="${pageContext.request.contextPath}/assets/js/project.js"></script>
<!-- block for autokomplite -->
<!-- <script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
-->
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/jquery.autocomplete.multiselect.js"></script>


<!-- block for autokomplite -->
<!-- <link rel="stylesheet" type="text/css"
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" /> -->
<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main.css"> --%>
<!-- end block for autokomplite -->
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.0/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.0/js/bootstrap-toggle.min.js"></script>




<title>${ group.name }</title>

</head>
<body>
	<jsp:include page="../page/header.jsp" />

	<div class="section section-breadcrumbs">
		<div class="row">
			<div class="col-md-12">
				<h1>
					<c:out value="${group.name }" />
				</h1>

			</div>
		</div>
	</div>





<%-- 	<a href="<c:url value="/openCreateTask?group_id=${group.id }"/>">Create --%>
<!-- 		Task</a> -->
		
		


	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<%-- <c:if test="${user.role == 'lecturer' }"> --%>
				<jsp:include page="teacherPanel.jsp"></jsp:include>
				
				<jsp:include page="AllTaskUser.jsp"></jsp:include>
				<%-- </c:if> --%>
				<!-- ПАНЕЛЬ ПОДІЙ  -->
				<jsp:include page="eventPanel.jsp"></jsp:include>
				<!-- ПАНЕЛЬ ПОДІЙ  -->
			</div>
			<div class="col-md-4">
				<jsp:include page="userPanel.jsp"></jsp:include>
			</div>
		</div>
	</div>

	<jsp:include page="../page/footer.jsp" />
</body>
</html>