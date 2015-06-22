<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="examWindov" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">

				<h4 class="modal-title">Exam list</h4>
			</div>
			<div class="modal-body">
				<c:forEach items="${exams }" var="exam">
					<p id="description${exam.id }" hidden="true">
						<c:out value="${exam.description }" />
					</p>
				</c:forEach>
				<form action="GroupUserServlet?action=chousExam"
					id='create-event-form' method="post" role="form" role="form">

					<div class="form-group" hidden="true">

						<input name="group_id" class="form-control" id="login-username"
							type="text" placeholder="" value="${group.id }">
					</div>
					<div class="form-group" hidden="true">

						<input name="user_id" class="form-control" id="login-username"
							type="text" placeholder="" value="${user.id }">
					</div>
					<div class="form-group">
						<select name="exam_date" class="form-control" id="chouseExam"
							placeholder="">
							<c:forEach items="${exams }" var="exam">
								<option value="${exam.id }"><c:out
										value="${exam.exam_date }" />
								</option>
								<p id="description">
									<c:out value="${exam.description }" />
								</p>
							</c:forEach>
						</select>

					</div>
					<div class="modal-footer">
						<div class="form-group">

							<button type="submit" id='submit-event-create'
								class="btn pull-right">Chouse date</button>
							<div class="clearfix"></div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	var previous;
	$('p#description${exams[0].id}').show();
	$("select#chouseExam").on('focus', function() {
		// Store the current value on focus and on change
		previous = $(this).val();
	}).change(function() {
		// Do something with the previous value after the change
		$("p#description" + previous).hide();
		$('p#description' + $(this).val()).show();
		previous = $(this).val();
	});

	$('#examWindov').modal({
		backdrop : 'static',
		keyboard : true
	})
</script>
