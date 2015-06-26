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

<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">


<title>${ group.name }</title>

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
		<c:if test="${user.role =='student' || user.role =='applicant' }">
			<c:choose>
				<c:when test="${ empty association.exam && association.isActive == false}">
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
	<jsp:include page="groupContent.jsp"></jsp:include>





	<jsp:include page="../page/footer.jsp" />
</body>
</html>