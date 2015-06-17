<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />

	<h3>
		<c:out value="${group.name }" />
	</h3>
	<div class="container">
		<h2>Homework</h2>
		
				<c:forEach items="${homeworks}" var="home">
					<div class="col-md-3 col-sm-6">
	        			<div class="service-wrapper">
		        			<img src="resources/img/service-icon/box.png" alt="Task">
		        			<h3>${home.value.getName()}</h3>
		        			<p>${home.value.getDescription()}</p>
		        			<p>Rating: ${home.key.getRating()}</p>
		        			<p><a href="<c:url value="/downloadFile?file=${home.key.getData()}"/>" class="btn">Download</a></p>
		        			<p><a href="<c:url value="/Homework?action=delete&deleteFile=${home.key.getData()}&id_homework=${home.key.getId()}"/>" class="btn">Delete</a></p>
		        			<p><a data-toggle="modal" href="#updateHomeWork" data-delete="${home.key.getData()}" data-homework="${home.key.getId()}" class="open-updateHomeWork btn">Update</a></p>
		        			<%-- <p><a data-toggle="modal" href="#uploadHomeWork"  data-userId="${home.key.getUser()}" data-TaskId="${home.key.getTask()}" class="open-uploadHomeWork btn">Upload</a></p>--%>
		        			<p><a data-toggle="modal" href="#retingHomeWork"  data-homework="${home.key.getId()}" class="open-ratingHomeWork btn">Rating</a></p> 
		        		</div>
	        		</div>
				</c:forEach>
		
		
	</div>
	
	<div id="updateHomeWork" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Update</h4>
				</div>
				<div class="modal-body">
					<form action="<c:url value="/Homework?action=update"/>" method="post" enctype="multipart/form-data"
						id="uploadHomework" role="form">

						
						<div class="form-group">
							 <input class="form-control" id="idDelete"
								name="uploadFile" type="hidden" value="" >
						</div>
						<div class="form-group">
							 <input class="form-control" id="idHomework"
								name="id_homework" type="hidden" value="" 
								>
						</div>

						<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>Photo</b></label> <input class="form-control"
									id="register-username" type="file" placeholder="" name="file">
						</div>


						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Ok</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	
	</div>
	
<div id="uploadHomeWork" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Upload</h4>
				</div>
				<div class="modal-body">
					<form action="<c:url value="/Homework?action=upload"/>" method="post" enctype="multipart/form-data"
						id="uploadHomework" role="form">

						
					
						<div class="form-group">
							 <input class="form-control" id="idHomework"
								name="id_homework" type="hidden" value="" 
								>
						</div>

						<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>Photo</b></label> <input class="form-control"
									id="register-username" type="file" placeholder="" name="file">
						</div>


						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Ok</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	
	</div>
	
	<div id="retingHomeWork" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Upload</h4>
				</div>
				<div class="modal-body">
					<form action="<c:url value="/Homework?action=rating"/>" method="post" enctype="multipart/form-data"
						id="ratingHomework" role="form">

						
					
						<div class="form-group">
							 <input class="form-control" id="idHomework"
								name="id_homework" type="hidden" value="" 
								>
						</div>

						<div class="form-group">
								<label for="register-username"><i class="icon-user"></i>
									<b>Rating</b></label> <input class="form-control"
									id="register-username" type="number" placeholder="" name="rating">
						</div>


						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Ok</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	
	</div>
	
	
<script>
$(document).on("click", ".open-updateHomeWork", function () {
    var filedelete = $(this).data('delete');
    var homeworkid = $(this).data('homework'); 
    $(".form-group #idDelete").val(filedelete);
     $(".form-group #idHomework").val(homeworkid);
});
</script>

<script>
$(document).on("click", ".open-uploadHomeWork", function () {
    var homeworkid = $(this).data('homework'); 
     $(".form-group #idHomework").val(homeworkid);
});
</script>
<script>
$(document).on("click", ".open-ratingHomeWork", function () {
    var homeworkid = $(this).data('homework'); 
     $(".form-group #idHomework").val(homeworkid);
});
</script>

	<jsp:include page="../page/footer.jsp" />
</body>
</html>