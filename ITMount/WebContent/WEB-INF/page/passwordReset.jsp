<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>

 
        <!-- Page Title -->
		<div class="section section-breadcrumbs">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h1>Password Reset</h1>
					</div>
				</div>
			</div>
		</div>
        
        <div class="section">
	    	<div class="container">
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3">
						<div class="basic-login">
							<form>
								<div class="form-group">
		        				 	<label for="restore-email"><i class="icon-envelope"></i> <b>Enter Your Email</b></label>
									<input class="form-control" id="restore-email" type="text" placeholder="">
								</div>
								<div class="form-group">
									<button type="submit" class="btn pull-right">Reset Password</button>
									<div class="clearfix"></div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
<jsp:include page="footer.jsp"/>
<jsp:include page="script.jsp"/>
</body>
</html>