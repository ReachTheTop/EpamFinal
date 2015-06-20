<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="panel panel-default">
	<div class="panel-heading">
		<a data-toggle="modal" id="groupEditModal" href="#createEvent"><i
			class='glyphicon glyphicon-plus'></i></a> Events
	</div>
	<div class="panel-body">

		<ul class="list-group">
			<c:forEach items="${ events}" var="event">
				<li class="list-group-item"><c:out
						value="${event.description }" /> <c:out value="${event.date }" /></li>
			</c:forEach>

		</ul>
	</div>
</div>

<div id="createEvent" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Course settings</h4>
			</div>
			<div class="modal-body">
				<form action="EventServlet?action=create" data-async
					id='create-event-form' method="post" role="form" role="form">
					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b>Event
								Description</b></label> <input name="description" class="form-control"
							type="text" placeholder="">
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
	$('form[data-async]').on('submit', function(event) {
		var $form = $(this);
		var $target = $($form.attr('data-target'));

		$.ajax({
			type : $form.attr('method'),
			url : $form.attr('action'),
			data : $form.serialize(),

			success : function(data, status) {
				$target.html(data);
			}
		});

		event.preventDefault();
	});

	$('button#submit-event-create').click(function() {
		$('#createEvent').modal('hide');
	});
</script>


