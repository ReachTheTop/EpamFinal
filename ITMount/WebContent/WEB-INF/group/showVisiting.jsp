<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ group.name }</title>
<jsp:include page="../page/head.jsp" />


<script src="assets/js/project.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script src="assets/js/jquery.autocomplete.multiselect.js"></script>

<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.0/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.0/js/bootstrap-toggle.min.js"></script>

<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">

<link rel="stylesheet" href="resources/css/font-awesome.css">
<link rel="stylesheet" href="resources/css/animate.css">

<link rel="stylesheet" href="resources/css/sticky-footer.css">
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

@media (max-width: 1500px){}
.table-responsive {
  width: 100%;
  margin-bottom: 15px;
  overflow-y: hidden;
  overflow-x: scroll;
  border: 1px solid #ddd;
}



@media (max-width: 767px){}
.table-responsive>.table>thead>tr>th{
  white-space: nowrap;

}


}
</style>


</head>
<body>

<div id="wrap" >
	<jsp:include page="../page/header.jsp" />

	
		<div class="section section-breadcrumbs">
			<div class="container">
				<div class="row-fluid">
					<div class="col-md-12">
						<h1>
							<c:out value="${group.name }" />
							<t:i18n id='visiting' />
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
								class="fa fa-weixin"></i> <t:i18n id='group.chat' /></a></li>

						<li class="active"><a
							href="<c:url value="/GroupServlet?action=showVisiting&group_id=${group.id }" />"><i
								class="fa fa-dashcube"></i><t:i18n id='visiting' /></a></li>
					</ul>
				</div>

				<div class="col-md-9" style="padding-bottom: 10px">

					<div class="panel panel-default">

						<div class="panel-heading">
							<t:i18n id='visiting.panel' />
							
							
							<a data-toggle="modal" href="#addVisiting"><i
								class="fa fa-plus fa-lg"></i></a>
						
							
						</div>

						<div class="panel-body">

							<div class="table-responsive">
								<table class="table table-hover">
									
									<thead>
										<tr>
											<th><t:i18n id='visiting.name' /></th>
											<c:forEach items="${requestScope.listUserVisit}" var="listUserVisit" end="0">
											
												<c:forEach items="${listUserVisit.dayVisit}" var="app">
											
													<th class="lessonDate" oldDate = "${app.dayLesson }">${app.dayLesson }</th>
											
											</c:forEach>
											</c:forEach>
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${requestScope.listUserVisit}"
											var="listUserVisit">

											<tr>
												<td>${listUserVisit.userName } ${listUserVisit.userSurname } </td>

												<c:forEach items="${listUserVisit.dayVisit}" var="app">


													<c:if test="${app.present == true }">
														<td >
														
															<i   class="glyphicon glyphicon-ok  dblclickable"
															style="color: green; font-size: 18pt;"    
															jornal_id="${app.idJournal }"
															group_id="${group.id }" user_id="${listUserVisit.userId }" dateVisit="${app.dayLesson }" ></i>
															
															</td>
													</c:if>

													<c:if test="${app.present == false }">
														<td >
														
														<i  class="glyphicon glyphicon-remove dblclickable "
															style="color: red; font-size: 18pt;" 
															jornal_id="${app.idJournal }"
															group_id="${group.id }" user_id="${listUserVisit.userId }" dateVisit="${app.dayLesson }"></i>
															
															</td>
													</c:if>

											
												</c:forEach>												
											</tr>

										</c:forEach>
										
										
									</tbody>
								</table>								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	
		<jsp:include page="../page/footer.jsp" />

	<div id="changeDate" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title"><t:i18n id='visiting.date.change' /></h4>
				</div>

				<div class="modal-body">

					<form action="GroupServlet?action=changeLessonDate&group_id=${group.id }"
						id='changeLessonDate' method="post" role="form" role="form">

						<div class="form-group">

							<label for="login-username"><i class="icon-user"></i> <b>
									<t:i18n id='visiting.date.new' /></b></label> <input name="newDateLesson" class="form-control"
								id="newDateLesson" type="date">						
						</div>
						
						<div class="form-group" hidden="true">
							<input name="oldLessonDate" class="form-control" id="oldLesson"
								type="text" >
						</div>	

						<div class="form-group" >

							<button id="updateLessonDate" type="submit"
								class="btn btn-primary pull-right"><t:i18n id='visiting.date.update' /></button>
							<div class="clearfix"></div>
						</div>
					</form>
				</div>

			</div>
		</div>

	</div>

<script>



$('form#changeLessonDate').on('submit', function(event) {
	var $form = $(this);
	
	event.preventDefault();
	event.stopImmediatePropagation();
	$.ajax({
		type : $form.attr('method'),
		url : $form.attr('action'),
		data : $form.serialize(),

		success : function(data, status) {
			
			setTimeout(showToaast("Updating  lessons date was successful", 1), 5000)

			window.setTimeout(function() {
				location.reload();
			}, 500);
		},
	
	error : function(data) {
		
		showToaast("Error with updating  lessons date", 0)

	}
	});
	
	
});
	</script>


	<div id="addVisiting" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<t:i18n id='visiting.addNewLesson' />
				</h4>
			</div>
			<div class="modal-body">

											

					<form action="GroupServlet?action=addVisit&group_id=${group.id }" id='addVisit'
					method="post" role="form" role="form">
						
						<div class="form-group">

						<label for="login-username"><i class="icon-user"></i> <b>
								<t:i18n id='visiting.EnterDateLesson' /> </b></label> <input name="dateLesson" class="form-control"
							id="dateLesson" type="date">
						</div>

						<table id="tableVisit" class="table table-user-information">
							<tbody>
							
							<c:forEach items="${requestScope.listUserVisit}" var="user">
							
								<tr>
									<td >${user.userSurname }  ${user.userName }</td>
									
									<td id="userName"><p>						
											<input type="checkbox" name = "checkBoxVisit" value="${user.userId }"  data-width="100"  data-size="small" checked data-toggle="toggle" data-on ="<t:i18n id='visiting.Present' />" data-off="<t:i18n id='visiting.Absent' />">
										</p></td>
								</tr>
							
							</c:forEach>					

							</tbody>


						</table>



						<div class="form-group">

						<button id="addLesson" type="submit" class="btn btn-primary pull-right">
							<t:i18n id='visiting.Add' />
						</button>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>

		</div>
	</div>

</div>
<script type="text/javascript">

$('.lessonDate').on('dblclick', function() {
	
	var object = $(this);
	var oldDate = $(this).attr('oldDate');
	//alert(oldDate);
	
	$("#oldLesson").val(oldDate);
	
	$('#changeDate').modal('show');
	
	
	

});


	$('.dblclickable').on('dblclick', function() {

		var object = $(this);
			
		

				$.get('GroupServlet?action=changeVisit&user_id='
						+ $(this).attr('user_id') + '&jornal_id='
						+ $(this).attr('jornal_id'), function(response) {

				    	if(response.visit === true){
						object.attr('class','glyphicon glyphicon-ok  dblclickable');
						object.attr('style', 'color: green; font-size: 18pt;');
						
						  }else{
					    object.attr('class','glyphicon glyphicon-remove dblclickable');
						object.attr('style', 'color: red; font-size: 18pt;');
						  }
				    	
			    	
				    	
				    	if(isEmpty(response) != true){
				    		showToaast("<t:i18n id='visiting.toast.ChangeSuccessfully'/>",1);
				    	}else{
				    		showToaast("<t:i18n id='visiting.toast.ChangeNotSuccessfully'/>",0);
				    	}
				    	
				  
				});

			});
	
	function isEmpty(str) {
	    return (!str || 0 === str.length);
	}

</script>

	<script>
		var form = $('#addVisit');
		form.submit(function(e) {
			e.preventDefault();
			e.stopImmediatePropagation();
					
			var vehicles = [];
			$("input:checked").each(function() {
			  vehicles.push($(this).val());
			});
			
			
			request = $.ajax({
				type : form.attr('method'),
				url : form.attr('action'),
				data: { checkBoxVisit : vehicles ,dateLesson: $("#dateLesson").val() },
				data: { 
					dateLesson: $("#dateLesson").val(),
					checkBoxVisit: JSON.stringify(vehicles) // look here!
				    },
				    
				dataType : "json",			
				success : function(data) {
								
					setTimeout(showToaast("<t:i18n id='visiting.toast.NewAdded'/>", 1), 5000)

					window.setTimeout(function() {
						location.reload();
					}, 500);

				},
				error : function(data) {
					
					showToaast("<t:i18n id='visiting.toast.NewNotAdded'/>", 0)

				}
			});
			return false;
		});
	</script>



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