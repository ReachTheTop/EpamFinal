<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update event</title>
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />


	<!-- Page Title -->
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>Login</h1>
				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-sm-5">
					<div class="basic-login">
						<form action="EventServlet?action=update&event_id=${event.id }" 
							method="post" role="form" role="form">
							
							<!--  <div class="form-group">
							
								<label for="login-username"><i class="icon-user"></i> <b>Event
										ID</b></label> <input name="name" class="form-control"
									id="login-username" value="${event.id }" type="text"
									placeholder="">
							</div> -->
							

							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Description</b></label>
								<input name="description" value="${event.description }"
									class="form-control" type="text" rows="5" id="comment"
									placeholder="">
							</div>
							
							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Date</b></label>
								<input name="date" class="form-control" value="${event.date }"
									id="login-username" type="datetime-local" placeholder="" >
							</div>
							
							<div class="form-group">
								<label for="login-username"><i class="icon-user"></i> <b>Group ID</b></label> <input name="group_id" class="form-control"
									id="login-username" value="${event.group_id }" type="text" placeholder="">
							</div>	
							
							<div class="form-group">
								<label for="login-username"><i class="icon-user"></i> <b>Is active</b></label> <input name="is_active" class="form-control"
									id="login-username" value="${event.is_active }" type="text" placeholder="">
							</div>	
							
							<div class="form-group">

								<button type="submit" class="btn pull-right">Update</button>
								<div class="clearfix"></div>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>




	<jsp:include page="../page/footer.jsp" />
	<jsp:include page="../page/script.jsp" />

</body>
</html>