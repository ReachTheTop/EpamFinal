<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../page/head.jsp" />
<script src="resources/js/toastr.js"></script>

<link rel="stylesheet" href="resources/css/tabPanel.css"></link>
<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">
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
		        			<c:if test="${home.key.getRating()>=0}">
		        			<p>Rating: ${home.key.getRating()}</p>
		        			</c:if>
		        			
		        			<p><a href="<c:url value="/downloadFile?file=${home.key.getData()}"/>" class="btn btn-warning glyphicon glyphicon-download-alt"></a>
		        			<c:if test="${user.role =='lecturer' }">
		        			<a id="delete-homework"  data-delete="${home.key.getId()}" class="btn btn-danger glyphicon glyphicon-trash"></a>
		        			</c:if>
		        			
		        			<c:if test="${user.role =='student' && home.key.getRating()<0}">
		        			<a data-toggle="modal" href="#updateHomeWork" data-delete="${home.key.getData()}" data-homework="${home.key.getId()}" class="open-updateHomeWork btn btn-success glyphicon glyphicon-edit"></a>
		        			</c:if>
		        			<%-- <p><a data-toggle="modal" href="#uploadHomeWork"  data-userId="${home.key.getUser()}" data-TaskId="${home.key.getTask()}" class="open-uploadHomeWork btn">Upload</a></p>--%>
		        			<c:if test="${user.role =='lecturer' && home.key.getRating()<0  }">
		        			<a data-toggle="modal" href="#retingHomeWork"  data-homework="${home.key.getId()}" class="open-ratingHomeWork btn">Rating</a>
		        			</c:if>
		        			</p> 
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
						id="updateHomework" role="form">

						
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
									<b>Archive</b></label> <input class="form-control"
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
									<b>Archive</b></label> <input class="form-control"
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
					<h4 class="modal-title">Set rating</h4>
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
	
	<script type="text/javascript">
	$(document)
	.ready(
			function() {
				$('#ratingHomework')
						.bootstrapValidator(
								{
									message : '<t:i18n id="bootstrap.ThisValueIsNotValid"/>',
									feedbackIcons : {
										valid : 'glyphicon glyphicon-ok',
										invalid : 'glyphicon glyphicon-remove',
										validating : 'glyphicon glyphicon-refresh'
									},
									fields : {
										rating : {
											validators : {
												notEmpty: {
						                            message: 'The arcgive is required'
						                        },
												regexp : {
													regexp : /[10|0-9]/,
													message : 'Enter 0-10 '
												}
											}
										}
										
									}
								});
			});
		$(document)
				.ready(
						function() {
							$('#updateHomeWork')
									.bootstrapValidator(
											{
												message : '<t:i18n id="bootstrap.ThisValueIsNotValid"/>',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													file : {
														validators : {
															notEmpty: {
									                            message: 'The arcgive is required'
									                        },
															file : {
																extension : 'zip,7z,rar',
																message : 'Please, upload archive'
															}
														}
													}
													
												}
											});
						}); 
		
	</script>
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
	<script>
	
	
	var form = $('#ratingHomework');
	 
	  form.submit(function(e) {
	   e.preventDefault();
	      e.stopImmediatePropagation();
	   request = $.ajax({
	    type : form.attr('method'),
	    url : form.attr('action'),
	    data : new FormData(this),
		processData : false,
		contentType : false,
				success : function(data) {
					if (data.success) {
						$('#retingHomeWork').modal('hide');
						
						homeworkid.parent().parent().reset();
						showToaast(data.success, 1);
					
					} else {
						$('#retingHomeWork').modal('hide');
						showToaast(data.fail, 0);

					}
				}
			});
		});
	  
	  var formU = $('#updateHomework');
		 
	  formU.submit(function(e) {
	   e.preventDefault();
	      e.stopImmediatePropagation();
	   request = $.ajax({
	    type : formU.attr('method'),
	    url : formU.attr('action'),
	    data : new FormData(this),
		processData : false,
		contentType : false,
				success : function(data) {
					if (data.success) {
						$('#updateHomeWork').modal('hide');
						showToaast(data.success, 1);
					
					} else {
						$('#updateHomeWork').modal('hide');
						showToaast(data.fail, 0);

					}
				}
			});
		});
	
	$("a#delete-homework").click(function() {
		
				
				var object = $(this);
				 $.get('Homework?action=delete&id_homework=' +
						 $(this).data('delete'),
				  function(response) {
                         if (response.success) {
                        	 object.parent().parent().remove();
                        		showToaast(response.success, 1);
                        	 
                         } else {                     
                        	 showToaast(response.fail, 1);

                         }
				  });
				
			});
	
		function showToaast(message, issucces) {
			var i = -1;
			var toastCount = 0;
			var $toastlast;

			var shortCutFunction;
			if (issucces == 1) {
				shortCutFunction = "success";
			}

			if (issucces == 0) {
				shortCutFunction = "error";
			}

			var msg = $('#message').val();
			var title = $('#title').val() || '';
			var $showDuration = $('#showDuration');
			var $hideDuration = $('#hideDuration');
			var $timeOut = $('#timeOut');
			var $extendedTimeOut = $('#extendedTimeOut');
			var $showEasing = $('#showEasing');
			var $hideEasing = $('#hideEasing');
			var $showMethod = $('#showMethod');
			var $hideMethod = $('#hideMethod');
			var toastIndex = toastCount++;

			toastr.options = {

				closeButton : true,
				debug : true,
				newestOnTop : false,
				progressBar : false,
				positionClass : "toast-top-right",
				preventDuplicates : false,
				onclick : null,
				timeOut : 10000,
				showDuration : 300,
				hideDuration : 1000,
				extendedTimeOut : 1000,

				showEasing : "swing",
				hideEasing : "linear",
				showMethod : "fadeIn",
				hideMethod : "fadeOut"

			};

			msg = message;

			$('#toastrOptions').text(
					'Command: toastr["' + shortCutFunction + '"]("' + msg
							+ (title ? '", "' + title : '')
							+ '")\n\ntoastr.options = '
							+ JSON.stringify(toastr.options, null, 2));

			var $toast = toastr[shortCutFunction](msg, title); // Wire up an event handler to a button in the toast, if it exists
			$toastlast = $toast;

			if (typeof $toast === 'undefined') {
				return;
			}

		}
		
	</script>
	
</body>
</html>