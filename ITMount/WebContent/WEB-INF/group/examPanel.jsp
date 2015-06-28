<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<c:if test="${group.teacher_id == user.id }">
			<a data-toggle="modal" id="createEventModal" href="#createExam"><i
				class='glyphicon glyphicon-plus'></i></a>
		</c:if>
		<t:i18n id='group.exams'/>
	</div>
	<table class="table">
		<c:forEach items="${ exams}" var="exam">
			<tr>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${exam.exam_date }" /> <fmt:formatDate pattern="hh:mm"
						value="${exam.exam_date }" /></td>
				<td></td>
				<td><c:out value="${exam.description }" /></td>
				<c:if test="${group.teacher_id == user.id }">
					<td><a data-toggle="modal" id="${exam.id }" class="updateExam"
						href="#updateExam"><i class='glyphicon glyphicon-edit'></i></a></td>
					<td><a data-toggle="modal" id="${exam.id }" class="updateExam"
						href="<c:url value="GroupExamServlet?action=toCSV&exam_id=${exam.id }" />"><i
							class='glyphicon glyphicon-file'></i></a></td>
				</c:if>
			</tr>

		</c:forEach>
	</table>
</div>

<div id="createExam" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title"><t:i18n id='group.exam.new'/></h4>
			</div>
			<div class="modal-body">
				<form action="GroupExamServlet?action=create" data-async
					id='create-event-form' method="post" role="form" role="form">
					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id='group.exam.description'/></b></label>
						<textarea name="description" class="form-control" type="text"
							placeholder=""></textarea>
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id='group.exam.date'/></b></label>
						<input name="exam_date" class="form-control" id="comment"
							type="datetime-local" required="required" placeholder="">
					</div>

					<div class="form-group" hidden="true">

						<input name="group_id" class="form-control" id="login-username"
							type="text" placeholder="" value="${group.id }">
					</div>
					<div class="form-group">

						<button type="submit" id='submit-event-create'
							class="btn pull-right"><t:i18n id='group.exam.create'/></button>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	/* 	$('form[data-async]').on('submit', function(event) {
	 var $form = $(this);
	 var $target = $($form.attr('data-target'));

	 $.ajax({
	 type : $form.attr('method'),
	 url : $form.attr('action'),
	 data : $form.serialize(),

	 success : function(data, status) {

	 }
	 });

	 event.preventDefault();
	 });

	 $('button#submit-event-create').click(function() {
	 $('#createEvent').modal('hide');
	 }); */
</script>


<div id="updateExam" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title"><t:i18n id='group.exam.update.title'/></h4>
			</div>
			<div class="modal-body">
				<form action="GroupExamServlet?action=update" data-async
					id='create-event-form' method="post" role="form" role="form">
					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id='group.exam.description'/></b></label>
						<textarea name="description" id="examDescription"
							class="form-control" type="text" placeholder=""></textarea>
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n id='group.exam.date'/></b></label>
						<input name="date" class="form-control" id="examDate"
							type="datetime-local" required="required" placeholder="">
					</div>

					<div class="form-group" hidden="true">

						<input name="exam_id" class="form-control" id="exam_id"
							type="text" placeholder="">
					</div>
					<div class="form-group">

						<button type="submit" id='submit-event-create'
							class="btn pull-right"><t:i18n id='group.exam.update'/></button>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$('a.updateExam')
			.click(
					function() {
						var index = $(this).attr('id');
						$('#exam_id').val(index);
						$
								.get(
										'GroupExamServlet?action=show&exam_id='
												+ index,
										function(response) {
											$('#examDescription').val(
													response.description);

											var dateString = response.exam_date;

											var date = new Date(Date
													.parse(dateString));

											var year = date.getFullYear()
													.toString();
											var month = addZero(
													date.getMonth() + 1)
													.toString();
											var day = addZero(date.getDate())
													.toString();
											var hours = addZero(date.getHours())
													.toString();
											var minutes = addZero(
													date.getMinutes())
													.toString();

											var correctDate = year.concat("-",
													month, "-", day, "T",
													hours, ":", minutes);

											$("#examDate").val(correctDate);

										});
					});

	function addZero(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	}
</script>


