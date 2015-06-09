<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${ course.name }course</title>
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />

	<h3>
		<c:out value="${course }" />
	</h3>
	<h3><a href="<c:url value="/CourseServlet?action=edit&course_id=${course.id }" />">Edit course</a></h3>
	


	<jsp:include page="../page/footer.jsp" />
	<jsp:include page="../page/script.jsp" />

</body>
</html>