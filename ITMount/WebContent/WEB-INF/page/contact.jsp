<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../page/head.jsp" />
<link rel="stylesheet" href="resources/css/tabPanel.css"></link>
<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">
<script src="resources/js/toastr.js"></script>
<link rel="stylesheet" href="resources/css/styleOurTeam.css">
<link rel="stylesheet" href="resources/css/font-awesome.css"
	type="text/css">
</head>
<body>
	<jsp:include page="header.jsp" />


	<!-- End Testimonials -->

	<div class="section">
		<div class="container">
			<h2>Contacts</h2>
			<div class="col-sm-6">

				<!-- Map -->
				<div>

					<iframe
						src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1286.692502484877!2d24.00872000000001!3d49.83522299999999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x473add78e0c6eefb%3A0xa7174b01c2d82f72!2z0LLRg9C7LiDQodGC0LXQv9Cw0L3QsCDQkdCw0L3QtNC10YDQuCwgMjjQkCwg0JvRjNCy0ZbQsiwg0JvRjNCy0ZbQstGB0YzQutCwINC-0LHQu9Cw0YHRgtGM!5e0!3m2!1suk!2sua!4v1435667996338"
						width="450" height="300" frameborder="0" style="border: 0"
						allowfullscreen></iframe>
				</div>

				<p class="contact-us-details">
					<b>Address:</b> Stepana Bandery 28A, Lviv, Ukraine<br /> <b>Phone:</b>
					+380986463663<br /> <b>Skype:</b> EpamLabItMount<br /> <b>Email:</b>
					<a href="mailto:getintoutch@yourcompanydomain.com">epamlabfinalproject@gmail.com
					</a>
				</p>
				<!-- End Map -->

			</div>

			<div class="col-sm-5">
				<!-- Contact Form -->
				<!-- Contact Form -->
				<h3>Send Us a Message</h3>
				<div class="contact-form-wrapper">
					<form id="sendUserMessage" action="<c:url value="/FeedbackServlet?action=sendUser"/>" class="form-horizontal" method="get"  role="form">
						<div class="form-group">
							<label for="Name" class="col-sm-3 control-label"><b>Your
									name</b></label>
							<div class="col-sm-9">
								<input class="form-control"  name="name" type="text" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="contact-email"  class="col-sm-3 control-label"><b>Your
									Email</b></label>
							<div class="col-sm-9">
								<input class="form-control"  name="email" id="contact-email" type="text"
									placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="contact-message" class="col-sm-3 control-label"><b>Select
									Topic</b></label>
							<div class="col-sm-9">
								<select name = "type" class="form-control" id="prependedInput">
									<option placeholder="Please select topic..."></option>
									<option value="Сomplaint">Сomplaint</option>
									<option value ="Bug">Bug</option>
									<option value ="Message">Message</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="contact-message" class="col-sm-3 control-label"><b>Message</b></label>
							<div class="col-sm-9">
								<textarea class="form-control"  name="message" rows="5" id="contact-message"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<button type="submit" class="btn pull-right">Send</button>
							</div>
						</div>
					</form>
				</div>
				<!-- End Contact Info -->
			</div>


		</div>
	</div>

	
	<script>
	$(document)
	.ready(
			function() {
				$('#sendUserMessage')
						.bootstrapValidator(
								{
									message : 'This value is not valid',
									feedbackIcons : {
										valid : 'glyphicon glyphicon-ok',
										invalid : 'glyphicon glyphicon-remove',
										validating : 'glyphicon glyphicon-refresh'
									},
									fields : {
										email : {
											validators : {
												emailAddress : {

													message : '<t:i18n id="bootstrap.TheInputIsNotAValidEmailAddress"/>'
												},
												notEmpty : {
													message : 'The email is required and cannot be empty'
												}
											}
										},
										name : {
											message : '<t:i18n id="bootstrap.TheNameIsNotValid"/>',
											validators : {
												notEmpty : {
													message : '<t:i18n id="bootstrap.TheNameIsRequiredAndCannotBeEmpty"/>'
												},
												regexp : {
													regexp : /^[a-zA-ZА-ЯІіЇїЄєа-я]*$/,
													message : '<t:i18n id="bootstrap.TheUsernameCanOnlyConsistOfAlphabeticalNumberDotUnderscore"/>'
												},
				
												stringLength : {
													min : 1,
													max : 30,
													message : '<t:i18n id="bootstrap.Length"/>'
												}
											}
										},
										type : {
											message : '<t:i18n id="bootstrap.TheNameIsNotValid"/>',
											validators : {
												notEmpty : {
													message : '<t:i18n id="bootstrap.TheNameIsRequiredAndCannotBeEmpty"/>'
												}
											}
											},
										message : {
											message : '<t:i18n id="bootstrap.TheNameIsNotValid"/>',
											validators : {
												notEmpty : {
													message : '<t:i18n id="bootstrap.TheNameIsRequiredAndCannotBeEmpty"/>'
												},
												regexp : {
													regexp : /^[^<^>]*$/,
													message : '<t:i18n id="bootstrap.message.visitor"/>'
												},
				
												stringLength : {
													min : 1,
													max : 900,
													message : '<t:i18n id="bootstrap.Length"/>'
												}
											}
										},
									
									}
								});
			});


	var form = $('#sendUserMessage');

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
					$('#sendUserMessage').trigger('reset');
					showToaast(data.success, 1);

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
	<script src="http://cdn.leafletjs.com/leaflet-0.5.1/leaflet.js"></script>
	<script src="resources/js/jquery.fitvids.js"></script>
	<script src="resources/js/jquery.sequence-min.js"></script>
	<script src="resources/js/jquery.bxslider.js"></script>
	<script src="resources/js/main-menu.js"></script>
	

	<jsp:include page="footer.jsp" />

</body>
</html>