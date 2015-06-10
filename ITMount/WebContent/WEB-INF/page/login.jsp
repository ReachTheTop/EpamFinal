<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>

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
						<c:if test="${errorLogin!=null}">
					<div class="alert alert-danger">
							<strong>Error!</strong>${errorLogin}
						</div>
					</c:if>
							<form action="<c:url value="/login" />"  method="post" role="form">
								<div class="form-group">
		        				 	<label for="login-username"><i class="icon-user"></i> <b>Username or Email</b></label>
									<input class="form-control" id="login-username" name="emaill"  type="email" value="${emaill}" placeholder="">
								</div>
								<div class="form-group">
		        				 	<label for="login-password"><i class="icon-lock"></i> <b>Password</b></label>
									<input class="form-control" id="login-password" name="password" type="password" placeholder="">
								</div>
								<div class="form-group">
									<label class="checkbox">
										<input type="checkbox" name="check"> Remember me
									</label>
									<a href="<c:url value="/reset" />" class="forgot-password">Forgot password?</a>
									<button type="submit" class="btn pull-right">Login</button>
									<div class="clearfix"></div>
								</div>
							</form>
						</div>
					</div>
					<div class="col-sm-7 social-login">
						<p>Or login with your Facebook or Twitter</p>
						<div class="social-login-buttons">
							<a href="#" class="btn-facebook-login">Login with Facebook</a>
							<a href="#" class="btn-twitter-login">Login with Twitter</a>
						</div>
						<div class="clearfix"></div>
						<div class="not-member">
							<p>Not a member? <a href="<c:url value="/registration" />">Register here</a></p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${confirmemail!=null}" >
		${confirmemail=null}
		<script type="text/javascript">
	$(document).ready(function(){
		$("#myModal").modal('show');
	});
</script>
		</c:if>

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Confirmation</h4>
            </div>
            <div class="modal-body">
                <p>Підтвердіть свій email ітд</p>
                <p class="text-warning"><small>Щоб відправити силку для підтвредження ще раз нажміть на конпу сенд</small></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <a href="<c:url value="/sendconfirm" />" class="btn btn-primary">Send</a>
               
            </div>
        </div>
    </div>
    </div>
    
<jsp:include page="footer.jsp"/>
<jsp:include page="script.jsp"/>
</body>
</html>