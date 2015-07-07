<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User page</title>
<jsp:include page="../page/head.jsp" />
<script src="assets/js/admin.js"></script>
<script src="assets/js/jquery.bootpag.min.js"></script>

<script src="resources/js/toastr.js"></script>

<!-- <link rel="stylesheet" href="resources/css/tabPanel.css"></link> -->
<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">

<link rel="stylesheet" href="resources/css/sticky-footer.css">




</head>
<body>
	<jsp:include page="../page/header.jsp" />
	<c:if test="${ current_user.id == user.id }">
		<c:if test="${not empty messages }">
			<jsp:include page="groupNotification.jsp" />
		</c:if>
	</c:if>
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>
						<t:i18n id="home.MyPage" />
						<c:if test="${current_user.id == user.id }">
							<a data-toggle="modal" href="#editUser"><i
								class="glyphicon glyphicon-edit"></i></a>
						</c:if>
					</h1>
				</div>
			</div>
		</div>
	</div>



	<c:choose>
		<c:when test="${user.id == current_user.id && user.role =='admin' }">


			<jsp:include page="tabPanel.jsp" />

		</c:when>
		<c:otherwise>
			<jsp:include page="profileTab.jsp" />
		</c:otherwise>
	</c:choose>

	<jsp:include page="../page/footer.jsp" />
	
	
	
	<label hidden="true" id="confirm"><t:i18n
			id='admin.group.confirm' /></label>
	<label hidden="true" id="ban"><t:i18n id='admin.user.ban' /></label>
	<label hidden="true" id="activate"><t:i18n
			id='admin.user.activate' /></label>
	<label hidden="true" id="close"><t:i18n id='admin.course.close' /></label>
	<label hidden="true" id="open"><t:i18n id='admin.course.open' /></label>


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

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('form#edit-group-form')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													name : {
														validators : {
															notEmpty : {
																message : "<t:i18n id='validation.not.empty'/>",
																min : 5,
																max : 30,
																message : '<t:i18n id="validation.size"/>'
															}

														}
													},
													teacher_id : {
														validators : {
															notEmpty : {
																message : "<t:i18n id='validation.not.empty'/>"

															}

														}
													}
												}
											});
						});
	</script>


	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('form#edit-course-form, form#create-new-course')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													name : {
														validators : {
															notEmpty : {
																message : "<t:i18n id='validation.not.empty'/>"
																
															},
															stringLength : {
																min : 2,
																max : 30,
																message : "<t:i18n id='validation.course.name.size'/>"
															}

														}
													},
													image : {
														validators : {
															file : {
																extension : 'png,jpg',
																maxSize : 5 * 1024 * 1024,
																message : "<t:i18n id='validation.course.image'/>"
															}
														}
													},
													description : {
														validators : {
															notEmpty : {
																message : "<t:i18n id='validation.not.empty'/>"
															},
															stringLength : {
																min : 2,
																max : 1000,
																message : "<t:i18n id='validation.course.description'/>"
															}

														}
													}
												}
											});
						});
	</script>


	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#editUserForm')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													name : {
														validators : {
															notEmpty : {
																message : "<t:i18n id='validation.not.empty'/>"
															},
															stringLength : {
																min : 2,
																max : 30,
																message : '<t:i18n id="event.validate.title.length"/>'
															}

														}
													},
													surname : {
														validators : {
															notEmpty : {
																message : "<t:i18n id='validation.not.empty'/>",

															},
															stringLength : {
																min : 2,
																max : 30,
																message : "<t:i18n id='validation.surname'/>"
															}

														}
													},
													email : {
														validators : {
															emailAddress : {
																message : "<t:i18n id='validation.email'/>"
															}
														}
													},
													skype : {
														validators : {
															regexp : {
																regexp : /^[a-zА-Яа-я0-9_-]{3,15}$/,
																message : "<t:i18n id='validation.skype'/>"
															},

														}
													},
													phone : {
														validators : {
															regexp : {
																regexp : /^\+?([0-9]{2})\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})$/,
																message : "<t:i18n id='validation.phone'/>"
															},

														}
													},
													image : {
														validators : {
															file : {
																extension : 'png,jpg',
																maxSize : 5 * 1024 * 1024,
																message : "<t:i18n id='validation.course.image'/>"
															}
														}
													},
													cv : {
														validators : {
															file : {
																extension : 'pdf,doc,docx',
																maxSize : 5 * 1024 * 1024,
																message : "<t:i18n id='validation.cv'/>"
															}
														}
													}
												}
											});
						});
	</script>

	<c:if test="${showEditModal!=null }">
	${showEditModal=null }
	<script type="text/javascript">
		$(document).ready(function() {

			$("#editUser").modal('show');

		});
	</script>
	</c:if>





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


	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('form#add-language-form')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													name : {
														validators : {
															notEmpty : {
																message : "<t:i18n id='validation.not.empty'/>",
															},
															regexp : {
																regexp : /^[a-zA-ZА-ЯІіЇїЄєа-я]*$/,

																message : '<t:i18n id="bootstrap.TheUsernameCanOnlyConsistOfAlphabeticalNumberDotUnderscore"/>'

															},
															stringLength : {
																min : 1,
																max : 15,
																message : "<t:i18n id='validation.language.name'/>"

															}

														}
													},
													language : {
														validators : {

															stringLength : {
																min : 1,
																max : 2,
																message : "<t:i18n id='validation.language'/>"
															},

															regexp : {
																regexp : /^[a-z]*$/,
																message : "<t:i18n id='validation.language.lower'/>"
															}

														}
													},
													country : {
														validators : {
															notEmpty : {
																message : "<t:i18n id='validation.not.empty'/>"

															},
															stringLength : {
																min : 1,
																max : 2,
																message : "<t:i18n id='validation.language'/>"
															},
															regexp : {
																regexp : /^[A-Z]*$/,
																message : "<t:i18n id='validation.language.uper'/>"
															}
														}
													},
													bandle : {
														validators : {
															notEmpty : {
																message : "<t:i18n id='validation.not.empty'/>"
															},
															file : {
																extension : 'properties',

																message : "<t:i18n id='validation.language.file'/>"
															}
														}
													}

												}
											});
						});
	</script>




</body>
</html>
