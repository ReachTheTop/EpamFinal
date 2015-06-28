<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resources</title>
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />



	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>Resources</h1>
					<c:if test="${is_lecturer==true}">
					<a data-toggle="modal" class="btn" href="#addKnbase">Add
						knowledgebase</a>
						</c:if>

				</div>
			</div>
		</div>
	</div>



	<div class="container">
		<h2>knowledge base</h2>



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
					<img src="resources/img/fileicon/${kbase.value }.png"
						onerror="this.src='resources/img/fileicon/other.png'" 
						
						class="img-rounded" width="128" height="128" alt="file">
					
					<p>
							<a
							href="<c:url value="/downloadFile?file=${kbase.key.getPath()}"/>"
							class="btn glyphicon glyphicon-download-alt"></a>
					<c:if test="${is_lecturer==true}">
						<a
							href="<c:url value="/KnowledgeBaseServlet?action=delete&deleteFile=${kbase.key.getPath()}&id_kbase=${kbase.key.getId()}"/>"
							class="btn glyphicon glyphicon-trash"></a>
							
						<c:choose>
						<c:when test="${kbase.key.getAvailable()==true}">
						<a
							href="<c:url value="/KnowledgeBaseServlet?action=active&active=false&id_kbase=${kbase.key.getId()}"/>"
							class="btn glyphicon glyphicon-thumbs-down"></a>
						</c:when>
						<c:otherwise>
						<a
							href="<c:url value="/KnowledgeBaseServlet?action=active&active=true&id_kbase=${kbase.key.getId()}"/>"
							class="btn glyphicon glyphicon-thumbs-up"></a>
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
					<h4 class="modal-title">Add kbase</h4>
				</div>
				<div class="modal-body">
					<form action="KnowledgeBaseServlet?action=new" method="post"
						enctype="multipart/form-data" role="form" id="uploadFile">

						<div class="form-group">
							<label for="login-password"><i class="icon-lock"></i> <b>File</b></label>
							<input name="file" class="form-control" type="file"
								required="required" placeholder="">
						</div>

						<div class="form-group">
							<input class="form-control" name="cours_id" type="hidden"
								id="cours_id" value="${course_id}">
						</div>
						<div class="form-group">

							<button type="submit" class="btn pull-right">Add</button>
							<div class="clearfix"></div>
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
							$('#uploadFile')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
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
										                
										                        message: 'Please choose corect file'
										                    }
										                }
										            }
												
												}
											});
						});
	</script>
	<jsp:include page="../page/footer.jsp" />
</body>
</html>