<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />

 <!-- Page Title -->
		<div class="section section-breadcrumbs">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<h1>Portfolio Item Description</h1>
					</div>
				</div>
			</div>
		</div>

 <div class="section">
	    	<div class="container">
				<div class="row">
					<!-- Image Column -->
					<div class="col-sm-6">
						<div class="portfolio-item">
							<div class="portfolio-image">
								<a href="#"><img src="upload/${course.icon }" style="min-widt:250px; max-widt:250px; min-height:300px; max-height:300px" alt="Course"></a>
							</div>
						</div>
					</div>
					<!-- End Image Column -->
					<!-- Project Info Column -->
					<div class="portfolio-item-description col-sm-6">
						<h3>${course.name}</h3>	
						<ul class="no-list-style">
							<li><b>Description:</b> ${course.description}</li>
							<li class="portfolio-visit-btn"> <a
								href="<c:url value="/CourseServlet?action=register&course_id=${course.id }" />"
								class="btn">Register On Course</a>
							
						</ul>
						
					</div>
					<!-- End Project Info Column -->
				</div>
				<!-- Related Projects -->
				<h3>Other course</h3>
				<div class="row">
					<c:forEach items="${courses }" var="cours">
				
					
			<div class="col-md-4 col-sm-6">
						<div class="portfolio-item">
							<div class="portfolio-image">
								<a href="#"><img src="upload/${cours.icon }" style="min-widt:250px; max-widt:250px; min-height:200px; max-height:200px" alt="Project Name"></a>
							</div>
							<div class="portfolio-info-fade">
								<ul>
									<li class="portfolio-project-name">${cours.name }</li>
									<li class="read-more"><a href="<c:url value="/CourseServlet?action=readMore&course_id=${cours.id }"/>"  class="btn">Read more</a></li>
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