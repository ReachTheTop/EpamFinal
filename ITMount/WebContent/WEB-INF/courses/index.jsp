<%@ page language="java" contentType="text/html;  charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<h1>Courses</h1>
					<a data-toggle="modal" href="#newCourse">Create
						New Course</a>

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
								<a href="<c:url value="/CourseServlet?action=readMore&course_id=${course.id }"/>"><img src="upload/${course.icon }"  style="min-widt:250px; max-widt:250px; min-height:230px; max-height:230px" alt="Course"></a>
							</div>
							<div class="portfolio-info">
								<ul>
									<li class="portfolio-project-name">${course.name }</li>
									<li class="read-more"><a href="<c:url value="/CourseServlet?action=readMore&course_id=${course.id }"/>" class="btn">Read more</a>
									 <a
								href="<c:url value="/CourseServlet?action=triger&course_id=${course.id }" />"
								class="btn">Close Course</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
	    
					
					
				</c:forEach>
			</div>
		</div>
	</div>
	
	
	
	
	<div id="newCourse" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">New course</h4>
				</div>
				<div class="modal-body">
					<form action="CourseServlet?action=create" method="post" enctype="multipart/form-data"
							role="form" role="form">
							<div class="form-group">
								<label for="login-username"><i class="icon-user"></i> <b>Course
										Name</b></label> <input name="name" class="form-control"
									id="login-username" type="text" placeholder="">
							</div>
							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Icon</b></label>
								<input name="icon" class="form-control" id="file" type="file" required="required"
									placeholder="">
							</div>


							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Description</b></label>
								<input name="description" class="form-control"
									type="text" rows="5" id="comment" placeholder="">
							</div>




							<div class="form-group">

								<button type="submit" class="btn pull-right">Create</button>
								<div class="clearfix"></div>
							</div>
						</form>
				</div>

			</div>
		</div>
	
	</div>
      
	<jsp:include page="../page/footer.jsp" />

</body>
</html>