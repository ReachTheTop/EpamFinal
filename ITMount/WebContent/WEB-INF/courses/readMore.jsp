<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${course.name }" /> </title>
<jsp:include page="../page/head.jsp" />
<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">
</head>
<body>
	<jsp:include page="../page/header.jsp" />

	<!-- Page Title -->
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<h1>
						<t:i18n id='courses'/>
					</h1>

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
							<a href="#"><img src="upload/${course.icon }"
								style="min-widt: 250px; max-widt: 250px; min-height: 300px; max-height: 300px"
								alt="Course"></a>
						</div>
					</div>
				</div>
				<!-- End Image Column -->
				<!-- Project Info Column -->
				<div class="portfolio-item-description col-sm-6">
					<h3>
						<c:out value="${course.name}" />
					</h3>
					<ul class="no-list-style">
						<li><c:out value="${course.description}" /></li>
						<li class="portfolio-visit-btn"><c:if
								test="${user == null || registationOFF==true}">
								<form
									action="<c:url value="/CourseServlet?action=register&course_id=${course.id }" />"
									method="get" id="form1" role="form">

									<button id="registration" type="submit" class="btn btn-primary"><t:i18n id='courses.register'/></button>

								</form>
							</c:if> <c:if test="${user!=null && knowladeTrue==true}">
								<a
									href="<c:url value="/KnowledgeBaseServlet?action=index&course_id=${course.id }" />"
									class="btn btn-primary"><t:i18n id='courses.knowledgebase'/></a>
							</c:if>
					</ul>

				</div>
				<!-- End Project Info Column -->
			</div>
			<!-- Related Projects -->
			<h3><t:i18n id='courses.other'/></h3>
			<div class="row">
				<c:forEach items="${courses }" var="cours">


					<div class="col-md-4 col-sm-6">
						<div class="portfolio-item">
							<div class="portfolio-image">
								<a href="#"><img src="upload/${cours.icon }"
									style="min-widt: 250px; max-widt: 250px; min-height: 200px; max-height: 200px"
									alt="Project Name"></a>
							</div>
							<div class="portfolio-info-fade">
								<ul>
									<li class="portfolio-project-name"><c:out
											value="${cours.name }" /></li>
									<li class="read-more"><a
										href="<c:url value="/CourseServlet?action=readMore&course_id=${cours.id }"/>"
										class="btn btn-primary"><t:i18n id='courses.more'/></a></li>
								</ul>
							</div>
						</div>
					</div>



				</c:forEach>
			</div>
		</div>


	</div>


	<script src="resources/js/toastr.js"></script>


	<script type="text/javascript">
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
				"closeButton" : true,
				"debug" : false,
				"newestOnTop" : false,
				"progressBar" : true,
				"positionClass" : "toast-top-right",
				"preventDuplicates" : false,
				"onclick" : null,
				"showDuration" : "300",
				"hideDuration" : "1000",
				"timeOut" : "5000",
				"extendedTimeOut" : "1000",
				"showEasing" : "swing",
				"hideEasing" : "linear",
				"showMethod" : "fadeIn",
				"hideMethod" : "fadeOut"
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

	<script>
		var form = $('#form1');

		form
				.submit(function(e) {
					e.preventDefault();
					e.stopImmediatePropagation();
					request = $
							.ajax({
								type : form.attr('method'),
								url : form.attr('action'),

								success : function(data) {

									$("#incorectData").hide();
									$("#registration").hide();
									document.getElementById("form1").reset();
									window.location.href = '/ITMount/GroupServlet?action=show&group_id='
											+ "" + data.key;

								},
								error : function() {
									$("#incorectData").show();
									showToaast(
											'<form name="myform" method="post" action="<c:url value="/UserServlet?action=index" />" role="form"><input type="hidden" name="showEdit" value="true"><a href="javascript: submitform()">Please upload your cv</a></form>',
											0);

								}
							});

					return false;
				});
	</script>

	<script type="text/javascript">
		function submitform() {
			document.myform.submit();
		}
	</script>

	<jsp:include page="../page/footer.jsp" />
</body>
</html>