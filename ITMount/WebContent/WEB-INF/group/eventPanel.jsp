<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>

<link rel="stylesheet" href="resources/css/styleEvent.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/font-awesome.css"
	type="text/css">
	
	<link rel="stylesheet" href="resources/css/sticky-footer.css">
	

<div class="ibox float-e-margins">
	<div class="ibox-title  panel panel-default">
		<h5>
			<c:if
				test="${not empty group.teacher_id && user.id == group.teacher_id}">
				<a data-toggle="modal" id="createEventModal" href="#createEvent"><i
					class='glyphicon glyphicon-plus'></i></a>
			</c:if>
			<t:i18n id='group.events'/>

		</h5>

	</div>

	<div class="ibox-content inspinia-timeline">
		<c:forEach items="${ events}" var="event" varStatus="loop">
			<div class="timeline-item">
				<div class="row">
					<div class="col-xs-3 date">
						<i class="${event.typeEvent }"></i>
						<fmt:formatDate pattern="yyyy-MM-dd" value="${event.date }" />
						<fmt:formatDate pattern="hh:mm" value="${event.date }" />
						<br /> <small class="text-navy"><c:out value="${event.message }" /> </small>


					</div>
					<div class="col-xs-7 content no-top-border">
						<p class="m-b-xs">
							<strong><c:out value=" ${event.nameEvent }" /></strong>
						</p>

						<p><c:out value="${event.description }" /> </p>


					</div>

					<c:if
						test="${user.role == 'lecturer' && group.teacher_id == user.id}">
						<div class="col-xs-2 content no-top-border">
							<p class="m-b-xs">
								<strong><t:i18n id='group.events.options'/></strong>
							</p>

							<c:if
								test="${not empty group.teacher_id && user.id == group.teacher_id}">
								<td><a data-toggle="modal" class='editEvent'
									id='${event.id }' href="#editEvent"><i
										class='glyphicon glyphicon-edit'></i></a></td>
								<td><a class='editEvent'
									href="<c:url value='EventServlet?action=delete&event_id=${event.id }' />"><i
										class='glyphicon glyphicon-minus'></i></a></td>
							</c:if>


						</div>
					</c:if>

				</div>
			</div>
		</c:forEach>

	</div>
</div>



<div id="createEvent" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title"><t:i18n id='group.event.new'/></h4>
			</div>
			<div class="modal-body">
				<form action="EventServlet?action=create" id='create-event-form'
					method="post" role="form" role="form">

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id='group.event.name'/></b></label> <input name="eventName" class="form-control" id="eventName"
							type="text" required="required" placeholder="">
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id='group.event.type'/></b></label> <select class="form-control " name="typeEvent" id="sel1">
							<option value="fa fa-calendar"><t:i18n id='event.other'/></option>
							<option value="fa fa-birthday-cake"><t:i18n id='event.birthday'/></option>
							<option value="fa fa-beer"><t:i18n id='event.beer'/></option>
							<option value="fa fa-futbol-o"><t:i18n id='event.sport'/></option>
							<option value="fa fa-coffee"><t:i18n id='event.coffee'/></option>
							<option value="fa fa-camera"><t:i18n id='event.foto'/></option>
							<option value="fa fa-tree"><t:i18n id='event.holiday'/></option>
							<option value="fa fa-usd"><t:i18n id='event.money'/></option>

						</select>
					</div>


					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id='group.event.description'/></b></label>
						<textarea name="description" class="form-control" type="text"
							placeholder=""></textarea>
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id='group.event.date'/></b></label>
						<input name="date" class="form-control" id="comment"
							type="datetime-local" required="required" placeholder="">
					</div>

					<div class="form-group" hidden="true">

						<input name="group_id" class="form-control" id="login-username"
							type="text" placeholder="" value="${group.id }">
					</div>
					<div class="form-group">

						<button type="submit" id='submit-event-create'
							class="btn pull-right"><t:i18n id='group.event.add'/></button>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	/* $('form[data-async]').on('submit', function(event) {
		var $form = $(this);
		var $target = $($form.attr('data-target'));

		$.ajax({
			type : $form.attr('method'),
			url : $form.attr('action'),
			data : $form.serialize(),

			success : function(data, status) {
				var $tr = $("<tr>");
				var $td1 = $("<td>");
				var $td2 = $("<td>");
				var $td3 = $("<td>");
				
				
				$td1.val(<c:out value="${event.date }" />);
				$td2.val(<c:out value="${event.description }" />);
				
				$tr.append($td1);
				$tr.append($td2);
				$tr.append($td3);
				$('.eventTable').prepend($tr);
			}
		});

		event.preventDefault();
	});

	$('button#submit-event-create').click(function() {
		$('#createEvent').modal('hide');
	}); */
</script>


<div id="editEvent" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title"><t:i18n id='group.event.update'/></h4>
			</div>
			<div class="modal-body">
				<form action="EventServlet?action=update" id='create-event-form'
					method="post" role="form" role="form">

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id='group.event.name'/></b></label> <input name="eventName" class="form-control"
							id="eventName1" type="text" required="required" placeholder="">
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id='group.event.type'/></b></label> <select class="form-control " name="typeEvent"
							id="eventType">
							<option value="fa fa-calendar"><t:i18n id='event.other'/></option>
							<option value="fa fa-birthday-cake"><t:i18n id='event.birthday'/></option>
							<option value="fa fa-beer"><t:i18n id='event.beer'/></option>
							<option value="fa fa-futbol-o"><t:i18n id='event.sport'/></option>
							<option value="fa fa-coffee"><t:i18n id='event.coffee'/></option>
							<option value="fa fa-camera"><t:i18n id='event.foto'/></option>
							<option value="fa fa-tree"><t:i18n id='event.holiday'/></option>
							<option value="fa fa-usd"><t:i18n id='event.money'/></option>

						</select>
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id='group.event.description'/></b></label>
						<textarea name="description" id="eventDescription"
							class="form-control"  placeholder=""></textarea>
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id='group.event.date'/></b></label>
						<input name="date" class="form-control" id="eventDate"
							type="datetime-local" required="required" placeholder="">
					</div>

					<div class="form-group" hidden="true">

						<input name="group_id" class="form-control" id="login-username"
							type="text" placeholder="" value="${group.id }">
					</div>
					<div class="form-group" hidden="true">

						<input name="event_id" class="form-control" id="event_id"
							type="text" placeholder="">
					</div>
					<div class="form-group">

						<button type="submit" id='submit-event-create'
							class="btn pull-right"><t:i18n id='group.event.update.button'/></button>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$('a.editEvent').click(
			function() {
				var index = $(this).attr('id');
				$("#event_id").val(index);
				$.get('EventServlet?action=show&event_id=' + index, function(
						response) {

					$('#eventDescription').val(response.description);

					var dateString = response.date;
					// 			var dateStringTest = "1996-12-19T16:39";

					var date = new Date(Date.parse(dateString));

					var year = date.getFullYear().toString();
					var month = addZero(date.getMonth() + 1).toString();
					var day = addZero(date.getDate()).toString();
					var hours = addZero(date.getHours()).toString();
					var minutes = addZero(date.getMinutes()).toString();

					var correctDate = year.concat("-", month, "-", day, "T",
							hours, ":", minutes);

					$("#eventDate").val(correctDate);

					$('#eventName1').val(response.nameEvent);
					$('#eventType').val(response.typeEvent);
				});
			});

	function addZero(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	}
</script>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#create-event-form, #editTask')
									.bootstrapValidator(
											{
												message : '<t:i18n id="group.validation"/>',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},submitHandler: function(validator, form, submitButton) {
										          
										        },
												fields : {
													eventName : {
														validators : {
															notEmpty : {
																message : '<t:i18n id="event.validate.title"/>'
															},
										                    stringLength: {
										                        min: 2,
										                        max: 70,
										                        message: '<t:i18n id="event.validate.title.length"/>'
										                    }
														}
													},
													description: {
														validators :{
															notEmpty : {
																message : '<t:i18n id="event.validate.desc"/>'
															},
										                    stringLength: {
										                        min: 6,
										                        max: 200,
										                        message: '<t:i18n id="event.validate.desc.length"/>'
										                    }
														}
													},
													date : {
														validators : {
															notEmpty : {
																message : '<t:i18n id="event.validate.date"/>'
															},
														}
													}
												}
											});
							
						});
	</script>



