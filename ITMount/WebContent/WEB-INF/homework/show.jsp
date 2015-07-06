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
<link rel="stylesheet" href="resources/css/bootstrap.min.css">

<link rel="stylesheet" href="resources/css/font-awesome.css">
<link rel="stylesheet" href="resources/css/animate.css">
<style type="text/css">
.nav-pills>li.active>a {
	background-color: #4B4B4C;
}

.nav-pills>li.active>a:hover {
	background-color: #4B4B4C;
}

a {
	color: #514D61;
	text-decoration: none;
}

.sidebar-nav .navbar li a {
	padding-top: 12px;
	padding-bottom: 12px;
}
}
</style>
</head>
<body>
	<jsp:include page="../page/header.jsp" />

	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>
						<c:out value="${group.name }" />
						<t:i18n id='group.homework' />
					</h1>
				</div>
			</div>
		</div>
	</div>

	<div class="container">

		<div class="row">
			<div class="col-md-3">
				<ul class="nav nav-pills nav-stacked">
					<li><a
						href="<c:url value="/GroupServlet?action=show&group_id=${group.id }" />"><i
							class="fa fa-home fa-fw"></i> <t:i18n id='group.main' /></a></li>
					<li><a
						href="<c:url value="/GroupServlet?action=showTasks&group_id=${group.id }" />"><i
							class="fa fa-tasks fa-fw"></i> <t:i18n id='group.tasks' /></a></li>
					<li><a
						href="<c:url value="/GroupServlet?action=showEvents&group_id=${group.id }" />"><i
							class="fa fa-users fa-fw"></i> <t:i18n id='group.events' /></a></li>
					<li><a
						href="<c:url value="/GroupServlet?action=showExams&group_id=${group.id }" />"><i
							class="fa fa-check fa-fw"></i> <t:i18n id='group.exams' /></a></li>
					<li><a
						href="<c:url value="/GroupServlet?action=chat&group_id=${group.id }" />"><i
							class="fa fa-weixin"></i>
						<t:i18n id='group.chat' /></a></li>
					<c:if test="${user.role == 'student' }">
						<li class="active"><a
							href="<c:url value="/Homework?action=show&group_id=${group.id }&users_id=${user.id }" />"><i
								class="fa fa-list fa-fw"></i> <t:i18n id='group.homework' /></a></li>
					</c:if>
				</ul>
			</div>
			<div class="col-md-14">


				<c:forEach items="${homeworks}" var="home">

					<div class="col-md-3 col-sm-3">
						<div class="service-wrapper">
							<img src="resources/img/service-icon/box.png" alt="Task">
							<h3>${home.value.getName()}</h3>
							<p>${home.value.getDescription()}</p>
							<c:if test="${home.key.getRating()>=0}">
								<p>Rating: ${home.key.getRating()}</p>
							</c:if>
							<p id="ratingData" data-ratingHomework="${home.key.getId()}"></p>
							<p>
								<a
									href="<c:url value="/downloadFile?file=${home.key.getData()}"/>"
									class="btn btn-warning glyphicon glyphicon-download-alt"></a>
								<c:if test="${user.role =='lecturer' }">
									<a id="delete-homework" data-delete="${home.key.getId()}"
										class="btn btn-danger glyphicon glyphicon-trash"></a>
								</c:if>

								<c:if test="${user.role =='student' && home.key.getRating()<0}">
									<a data-toggle="modal" href="#updateHomeWork"
										data-delete="${home.key.getData()}"
										data-homework="${home.key.getId()}"
										class="open-updateHomeWork btn btn-success glyphicon glyphicon-edit"></a>
								</c:if>
							</p>
							<%-- <p><a data-toggle="modal" href="#uploadHomeWork"  data-userId="${home.key.getUser()}" data-TaskId="${home.key.getTask()}" class="open-uploadHomeWork btn">Upload</a></p>--%>
							<p>
								<c:if
									test="${user.role =='lecturer' && home.key.getRating()<0  }">
									<a id="setReting" data-toggle="modal" href="#retingHomeWork"
										data-homework="${home.key.getId()}"
										class="open-ratingHomeWork btn"><t:i18n
											id='group.homework.rating' /></a>
								</c:if>
							</p>
						</div>
					</div>

				</c:forEach>

			</div>
		</div>
	</div>





	<div id="updateHomeWork" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">
						<t:i18n id='group.homework.update' />
					</h4>
				</div>
				<div class="modal-body">
					<form action="<c:url value="/Homework?action=update"/>"
						method="post" enctype="multipart/form-data" id="updateHomework"
						role="form">


						<div class="form-group">
							<input class="form-control" id="idDelete" name="uploadFile"
								type="hidden" value="">
						</div>
						<div class="form-group">
							<input class="form-control" id="idHomework" name="id_homework"
								type="hidden" value="">
						</div>

						<div class="form-group">
							<label for="register-username"><i class="icon-user"></i>
								<b><t:i18n id='group.homework.archive' /></b></label> <input
								class="form-control" id="register-username" type="file"
								placeholder="" name="file">
						</div>


						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<t:i18n id='group.homework.update.close' />
							</button>
							<button type="submit" class="btn btn-primary">
								<t:i18n id='group.homework.update.button' />
							</button>
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
					<h4 class="modal-title">
						<t:i18n id='group.homework.upload' />
					</h4>
				</div>
				<div class="modal-body">
					<form action="<c:url value="/Homework?action=upload"/>"
						method="post" enctype="multipart/form-data" id="uploadHomework"
						role="form">



						<div class="form-group">
							<input class="form-control" id="idHomework" name="id_homework"
								type="hidden" value="">
						</div>

						<div class="form-group">
							<label for="register-username"><i class="icon-user"></i>
								<b><t:i18n id='group.homework.archive' /></b></label> <input
								class="form-control" id="register-username" type="file"
								placeholder="" name="file">
						</div>


						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<t:i18n id='group.homework.update.close' />
							</button>
							<button type="submit" class="btn btn-primary">
								<t:i18n id='group.homework.upload.button' />
							</button>
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
					<h4 class="modal-title">
						<t:i18n id='group.homework.rating.set' />
					</h4>
				</div>
				<div class="modal-body">
					<form action="<c:url value="/Homework?action=rating"/>"
						method="post" enctype="multipart/form-data" id="ratingHomework"
						role="form">



						<div class="form-group">
							<input class="form-control" id="idHomework" name="id_homework"
								type="hidden" value="">
						</div>

						<div class="form-group">
							<label for="register-username"><i class="icon-user"></i>
								<b><t:i18n id='group.homework.rating' /></b></label> <input
								class="form-control" id="register" type="number" placeholder=""
								name="rating">
						</div>


						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<t:i18n id='group.homework.rating.set.close' />
							</button>
							<button type="submit" class="btn btn-primary">
								<t:i18n id='group.homework.rating.set.button' />
							</button>
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
															notEmpty : {
																message : "<t:i18n id='group.homework.validation.rating' />"
															},
															regexp : {
																regexp : /^(10)$|^[0-9]$/,
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
															notEmpty : {
																message : "<t:i18n id='group.homework.validation.file' />"
															},
															file : {
																extension : 'zip,7z,rar',
																message : "<t:i18n id='group.homework.validation.archive' />"
															}
														}
													}

												}
											});
						});
	</script>
	<script>
		$(document).on("click", ".open-updateHomeWork", function() {
			var filedelete = $(this).data('delete');
			var homeworkid = $(this).data('homework');
			$(".form-group #idDelete").val(filedelete);
			$(".form-group #idHomework").val(homeworkid);
		});
	</script>

	<script>
		$(document).on("click", ".open-uploadHomeWork", function() {
			var homeworkid = $(this).data('homework');
			$(".form-group #idHomework").val(homeworkid);
		});
	</script>
	<script>
		$(document).on("click", ".open-ratingHomeWork", function() {
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
						$('body').find(
								"[data-ratinghomework='"
										+ $('#idHomework').val() + "']").text(
								"Rating:" + $('#register').val());
						$('body').find(
								"[data-homework='" + $('#idHomework').val()
										+ "']").hide();
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

		$("a#delete-homework").click(
				
					function() {
						 var object =$(this);
						  swal({
							  title: "Are you sure?",
							  text: "You will not be able to recover this file!",
							  type: "warning",
							  showCancelButton: true,
							  confirmButtonClass: "btn-danger",
							  confirmButtonText: "Yes, delete it!",
							  cancelButtonText: "No, cancel plx!",
							  closeOnConfirm: true,
							  closeOnCancel: true
							},
							
							function(isConfirm) {
								  
								  if (isConfirm) {
									  
									
									  $.get('Homework?action=delete&id_homework='
												+ object.data('delete'), function(response) {
											if (response.success) {
												object.parent().parent().remove();
												showToaast(response.success, 1);

											} else {
												showToaast(response.fail, 0);

											}
										});

								   
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