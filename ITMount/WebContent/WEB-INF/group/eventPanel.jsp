<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="panel panel-default">
	<div class="panel-heading">
		<a data-toggle="modal" id="createEventModal" href="#createEvent"><i
			class='glyphicon glyphicon-plus'></i></a> Events
	</div>
	<table class="table">
		<c:forEach items="${ events}" var="event" varStatus="loop">
			<tr>
				<td><c:out value="${event.date }" /></td>
				<td><c:out value="${event.description }" /></td>
				<td><a data-toggle="modal" class='editEvent' id='${event.id }'
					href="#editEvent"><i class='glyphicon glyphicon-edit'></i></a></td>
				<td><a class='editEvent'
					href="<c:url value='EventServlet?action=delete&event_id=${event.id }' />"><i
						class='glyphicon glyphicon-minus'></i></a></td>

			</tr>

		</c:forEach>
	</table>
</div>

<div id="createEvent" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">New event</h4>
			</div>
			<div class="modal-body">
				<form action="EventServlet?action=create" id='create-event-form'
					method="post" role="form" role="form">
					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b>Event
								Description</b></label>
						<textarea name="description" class="form-control" type="text"
							placeholder=""></textarea>
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b>Date</b></label>
						<input name="date" class="form-control" id="comment"
							type="datetime-local" required="required" placeholder="">
					</div>

					<div class="form-group" hidden="true">

						<input name="group_id" class="form-control" id="login-username"
							type="text" placeholder="" value="${group.id }">
					</div>
					<div class="form-group">

						<button type="submit" id='submit-event-create'
							class="btn pull-right">Update</button>
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
				<h4 class="modal-title">New event</h4>
			</div>
			<div class="modal-body">
				<form action="EventServlet?action=update" id='create-event-form'
					method="post" role="form" role="form">
					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b>Event
								Description</b></label>
						<textarea name="description" id="eventDescription"
							class="form-control" type="text" placeholder=""></textarea>
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b>Date</b></label>
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
							class="btn pull-right">Update</button>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$('a.editEvent').click(function() {
		var index = $(this).attr('id');
		$("#event_id").val(index);
		$.get('EventServlet?action=show&event_id=' + index, function(response) {
			$('#eventDescription').val(response.description);
			
			$("#eventDate").val(response.date);
		});
	});
</script>


