<%@ page language="java" contentType="text/html;  charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
<title>Feedback</title>
<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/font-awesome.css" rel="stylesheet">
<link href="resources/css/iCheck/custom.css" rel="stylesheet">
<link href="resources/css/animate.css" rel="stylesheet">
<link href="resources/css/styleEvent.css" rel="stylesheet">

<link rel="stylesheet" href="resources/css/icomoon-social.css">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,600,800'
	rel='stylesheet' type='text/css'>


<!--[if lte IE 8]>
      <link rel="stylesheet" href="resources/css/leaflet.ie.css" />
  <![endif]-->
<link rel="stylesheet" href="resources/css/main.css">


<script type="text/javascript" src="resources/js/jquery-1.10.2.js"></script>

<!-- block for autokomplite -->
<link rel="stylesheet" type="text/css"
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" />


<script src="http://cdn.leafletjs.com/leaflet-0.5.1/leaflet.js"></script>

<script src="resources/js/main-menu.js"></script>
<script src="resources/js/template.js"></script>
<script src="resources/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script src="resources/js/toastr.js"></script>

<link rel="stylesheet" href="resources/css/sticky-footer.css">

</head>
<body>
	<jsp:include page="../page/header.jsp" />
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12"></div>
			</div>
		</div>
	</div>
	<div id="wrapper">

		<div id="page">


			<div class="wrapper wrapper-content">
				<div class="row">

					<jsp:include page="menu.jsp" />
					<div class="col-lg-9 animated fadeInRight">
						<div class="mail-box-header">

							<form method="post"
								action="<c:url value='FeedbackServlet?action=search&type=${type }' />"
								class="pull-right mail-search">
								<div class="input-group">
									<input type="text" class="form-control input-sm" name="search"
										placeholder="<t:i18n id="Feedback.index.placeholder.search"/>">
									<div class="input-group-btn">
										<button type="submit" class="btn btn-sm btn-primary">
											<t:i18n id="Feedback.index.Search"/></button>
									</div>
								</div>
							</form>
							<h2>${title}</h2>
							<div class="mail-tools tooltip-demo m-t-md">
								<div class="btn-group pull-right">
									
								<div id="example1-pagination">
	    <a id="example1-previous"  class="btn btn-white btn-sm" href="#"> <i class="fa fa-arrow-left"></i></a> 
	    <a id="example1-next"  class="btn btn-white btn-sm" href="#"> <i class="fa fa-arrow-right"></i></a>
	</div>
									

								</div>
								<button
									onclick="window.location.href='<c:url value='FeedbackServlet?action=refresh' />'"
									class="btn btn-white btn-sm" data-toggle="tooltip"
									data-placement="left" title="<t:i18n id="Feedback.title.RefreshInbox"/> ">
									<i class="fa fa-refresh"></i> <t:i18n id="Feedback.index.Refresh"/> 
								</button>
								<button
								id="addRead" form="sendMail" type="submit" class="btn btn-white btn-sm" data-toggle="tooltip"
									data-placement="top" title="<t:i18n id="Feedback.title.MarkRead"/>">
									<i class="fa fa-eye"></i>
								</button>
								<button id="addImportant" form="sendMail" type="submit"
									class="btn btn-white btn-sm" data-toggle="tooltip"
									data-placement="top" title="<t:i18n id="Feedback.title.MarkImportant"/>">
									<i class="fa fa-exclamation"></i>
								</button>
								<button id="addTrash" form="sendMail" type="submit"
									class="btn btn-white btn-sm" data-toggle="tooltip"
									data-placement="top" title="<t:i18n id="Feedback.title.MoveToTrash"/>">
									<i class="fa fa-trash-o"></i>
								</button>
						

							</div>
						</div>
						<div class="mail-box">
						 <form id="sendMail" action="<c:url value="/FeedbackServlet?action=addTrash"/>" class="form-horizontal" method="get"  role="form">
							<table class="table table-hover table-mail">
								<tbody id="example1">
									<c:forEach items="${feedbacks}" var="feedback">
										<c:choose>
											<c:when test="${feedback.read==false}">
												<tr class="unread">
											</c:when>

											<c:otherwise>
												<tr class="read">
											</c:otherwise>
										</c:choose>
										<td class="check-mail"><input type="checkbox"
											class="i-checks" value="${feedback.getId() }" name="check">
										</td>
										<td class="mail-ontact"><a
											href="<c:url value='FeedbackServlet?action=view&id=${feedback.getId() }' />">${feedback.name}</a>
											<c:choose>
												<c:when test="${feedback.type=='Сomplaint'}">
													<span class="label label-warning pull-right"><t:i18n id="Feedback.index.Complaint"/></span>
												</c:when>
												<c:when test="${feedback.type=='Bug'}">
													<span class="label label-danger pull-right"><t:i18n id="Feedback.index.Bug"/></span>
												</c:when>
												<c:otherwise>
													<span class="label label-info pull-right"><t:i18n id="Feedback.index.Message"/></span>
												</c:otherwise>

											</c:choose></td>
										<td class="mail-subject"><a
											href="<c:url value='FeedbackServlet?action=view&id=${feedback.getId() }' />">${feedback.description}</a></td>
										<td class=""></td>
										<td class="text-right mail-date">${feedback.time}</td>
										</tr>
									</c:forEach>



								</tbody>
							</table>
							
							</form>

						</div>
					</div>
				</div>
			</div>

		</div>
	</div>



	
	

	<!-- Mainly scripts -->
	<script src="resources/js/jquery-2.1.1.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery.metisMenu.js"></script>
	<script src="resources/js/jquery.slimscroll.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="resources/js/inspinia.js"></script>
	<script src="resources/js/pace.min.js"></script>

	<!-- iCheck -->
	<script src="resources/js/icheck.min.js"></script>
	

<script type="text/javascript" src="resources/js/dt/jquery.paginate.js"></script>
<script type="text/javascript">
$(document).ready(function()    {
	$('#example1').paginate({itemsPerPage: 10});
	

	
});
</script>
	<script>
        $(document).ready(function(){
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
    </script>
    
  
	<script>  
	var form = $('#sendMail');
	$("#addTrash").on('click', function () {
	   
	  form.attr("action", "FeedbackServlet?action=addTrash")
	});
    
	$("#addImportant").on('click', function () {
		   
		  form.attr("action", "FeedbackServlet?action=addImportant")
		});
	$("#addRead").on('click', function () {
		   
		  form.attr("action", "FeedbackServlet?action=addRead")
		});
	

	form.submit(function(e) {
		e.preventDefault();
		e.stopImmediatePropagation();
		request = $.ajax({
			type : form.attr('method'),
			url : form.attr('action'),

			data : new FormData(this),
			processData : false,
			contentType : false,
			data : form.serialize(),
			success : function(data) {
				if (data.success) {
					
					showToaast(data.success, 1);
					setTimeout(function () {
						location.reload();
                    }, 500);
					
				} else {
					showToaast(data.fail, 0);

				}
			}
		});

		return false;
	});
     			
     			
				
     	
         </script>

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