<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><t:i18n id='knowledgebase'/></title>
<jsp:include page="../page/head.jsp" />
<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">
<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">

<script src="resources/js/toastr.js"></script>
</head>
<body>
	<jsp:include page="../page/header.jsp" />



	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1><t:i18n id='knowledgebase'/>
					<c:if test="${is_lecturer==true}"><span>
					<a data-toggle="modal" class="btn btn-primary" href="#addKnbase"><t:i18n id='knowledgebase.add'/></a>
						</span>
						</c:if>
</h1>
				</div>
			</div>
		</div>
	</div>



	<div class="container">
		



		<c:forEach items="${base }" var="kbase">
			<div class="col-md-3 col-sm-6">
				<div class="service-wrapper">

					<h3>
						<script type="text/javascript">
							var Regex = /\d[_].*$/g;
							var Regex2 = /[^_]{2}.{0,21}$/g;

							var fileName1 = Regex
									.exec("${kbase.key.getPath()}");
							var fileName = Regex2.exec(fileName1);
							document.write(fileName)
						</script>
					</h3>
					<c:choose>
					<c:when test="${kbase.key.getBox_id()!=null }">
					<a id="document"
							href="#boxView" data-toggle="modal" 
							 data-session="${kbase.key.getBox_session()}"><img src="resources/img/fileicon/${kbase.value }.png"
						onerror="this.src='resources/img/fileicon/other.png'" 
						
						class="img-rounded" width="128" height="128" alt="file"></a>
					</c:when>
					<c:when test="${kbase.key.getBox_id()==null }">
					<img src="resources/img/fileicon/${kbase.value }.png"
						onerror="this.src='resources/img/fileicon/other.png'" 
						
						class="img-rounded" width="128" height="128" alt="file">
					</c:when>
					</c:choose>
					
					
					<p>
							<a
							href="<c:url value="/downloadFile?file=${kbase.key.getPath()}"/>"
							class="btn btn-warning glyphicon glyphicon-download-alt"></a>
								<c:if test="${kbase.key.getBox_id()!=null }">
								<a id="document"
							href="#boxView" data-toggle="modal" 
							class="btn btn-primary glyphicon glyphicon-eye-open" data-session="${kbase.key.getBox_session()}"></a>
								</c:if>
								
					<c:if test="${is_lecturer==true}">
						<a
							id="deleteKnowladge"
							data-delete="${kbase.key.getId()}"
							
							class="btn btn-danger glyphicon glyphicon-trash"></a>
							
						<c:choose>
						<c:when test="${kbase.key.getAvailable()==true}">
						<a id="activeKnowladge" data-active="${kbase.key.getId()}"
						
							class="btn btn-danger glyphicon glyphicon-thumbs-down"></a>
						</c:when>
						<c:otherwise>
						<a id="activeKnowladge"
							data-active="${kbase.key.getId()}"
							class="btn btn-success glyphicon glyphicon-thumbs-up"></a>
						</c:otherwise>
						</c:choose>	
							</c:if>
					</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<!-- End Pricing Table -->

	<div id="addKnbase" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title"><t:i18n id='knowledgebase.add'/></h4>
				</div>
				<div class="modal-body">
					<form action="KnowledgeBaseServlet?action=new" method="post"
						enctype="multipart/form-data" role="form" id="uploadFile">

						<div class="form-group">
							<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id='knowledgebase.file'/></b></label>
							<input name="file" class="form-control" type="file"
								required="required" placeholder="">
						</div>

						<div class="form-group">
							<input class="form-control" name="cours_id" type="hidden"
								id="cours_id" value="${course_id}">
						</div>
						<div class="form-group">

							<button type="submit" class="btn btn-primary pull-right"><t:i18n id='knowledgebase.add.button'/></button>
							<div class="clearfix"></div>
						</div>
					</form>
				</div>

			</div>
		</div>

	</div>
	
	<div id="boxView" class="modal fade">
		<div class="modal-dialog modal-lg " >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					
				</div>
				<div class="modal-body">
					<iframe id="frameView" style="width: 840px; height: 500px; border-radius: 5px; border: 1px solid #d9d9d9;"  allowfullscreen="allowfullscreen"></iframe>
				</div>

			</div>
		</div>

	</div>
	
	
	<script type="text/javascript">
	
	
	$("a#document").click(function(){
		var link =$(this);
		$("#frameView").attr("src","https://view-api.box.com/1/sessions/"+link.data('session')+"/view?theme=dark");
	});
	
	</script>
	
	<script type="text/javascript">
	
	$("a#deleteKnowladge").click(
			
			function() {
				 var object =$(this);
				  swal({
					  title: "<t:i18n id='bootstrap.AreYouSure'/>",
					  text: "<t:i18n id='bootstrap.NotAbleRecoverFile'/>!",
					  type: "warning",
					  showCancelButton: true,
					  confirmButtonClass: "btn-danger",
					  confirmButtonText: "<t:i18n id='bootstrap.Yes'/>!",
					  cancelButtonText: "<t:i18n id='bootstrap.No'/>!",
					  closeOnConfirm: true,
					  closeOnCancel: true
					},
					
					function(isConfirm) {
						  
						  if (isConfirm) {
							  
							
							  $.get('KnowledgeBaseServlet?action=delete&id_kbase='
										+object.data("delete"), function(response) {
									if (response.success) {
										object.parent().parent().remove();
										showToaast(response.success, 1);

									}
								});
						   
						  }
				

			});
				});
	
	$("a#activeKnowladge").click(
			function() {

				var object = $(this);
				$.get('KnowledgeBaseServlet?action=active&id_kbase='
						+ $(this).data('active'), function(response) {
					if (response.success) {
						if(response.available){
							object.attr('class',"btn btn-danger glyphicon glyphicon-thumbs-down");	
						}else{
							object.attr('class',"btn btn-success glyphicon glyphicon-thumbs-up");	
							
						}
						showToaast(response.success, 1);

					}
				});

			});
		$(document)
				.ready(
						function() {
							$('#uploadFile')
									.bootstrapValidator(
											{
												message : "<t:i18n id='bootstrap.ThisValueIsNotValid'/>",
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
										            file: {
										                validators: {
										                    file: {
										                        extension: 'jpeg,png,jpg,pmp,mp3,doc,docx,zip,rar,7z,pdf,doc,docx,ppt,pptx,odp,xml,js,jar,сss,exl,exlx,sql',
										                
										                        message: "<t:i18n id='knowledgebase.error.format'/>"
										                    }
										                }
										            }
												
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
	<jsp:include page="../page/footer.jsp" />
</body>
</html>