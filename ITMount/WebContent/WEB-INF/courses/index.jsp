<%@ page language="java" contentType="text/html;  charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
<title>Courses</title>
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />



	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1><a href="<c:url value='CourseServlet' />"><t:i18n id='courses'/></a></h1>


				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row ">
				<c:forEach items="${courses }" var="course">


					<div class="col-md-4 col-sm-6">
						<div class="portfolio-item">
							<div class="portfolio-image">

								<a
									href="<c:url value="/CourseServlet?action=readMore&course_id=${course.id }"/>"><img
									src="upload/${course.icon }"
									style="min-widt: 250px; max-widt: 250px; min-height: 230px; max-height: 230px"
									alt="Course"></a>

							</div>
							<div class="portfolio-info">
								<ul>
									<li class="portfolio-project-name"><c:out value="${course.name }" /> </li>
									<li class="read-more"><a
										href="<c:url value="/CourseServlet?action=readMore&course_id=${course.id }"/>"
										class="btn btn-primary"><t:i18n id='courses.more'/></a></li>
								</ul>
							</div>
						</div>
					</div>



				</c:forEach>
			</div>
		</div>
	</div>





	<jsp:include page="../page/footer.jsp" />

</body>
</html>