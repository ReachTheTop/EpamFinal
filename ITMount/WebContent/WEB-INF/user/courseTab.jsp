
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>








<div class="panel-body" id="courses-body">

	<div class="row">
		<div class="col-xs-12 col-md-10">

			<div class="form-group">

				<input type="text" id="search-field"
					placeholder="<t:i18n id='admin.course.search'/>"
					class='form-control'>

			</div>
		</div>
		<div class="col-xs-4 col-sm-2">
			<a data-toggle="modal" class='btn btn-primary' href="#newCourse"><t:i18n
					id="admin.course.create.new" /></a> <a data-toggle="modal"
				id="courseEditModal" href="#editCourse"><i
				class='glyphicon glyphicon-edit'></i></a>
		</div>

	</div>
	<div class="contact-box">
		<table class="table table-striped">
			<thead>
				<tr>
					<th><t:i18n id="admin.course.name" /></th>
					<th><t:i18n id="admin.course.activity" /></th>
					<th><t:i18n id="admin.course.triger" /></th>
					<th><t:i18n id="admin.course.edit" /></th>
				<tr>
			</thead>
			<tbody id="courses-body">

			</tbody>
		</table>
	</div>
	<div class="row" align="center">

		<div id="page-selection"></div>

	</div>
</div>















<div id="newCourse" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<t:i18n id="admin.course.new" />
				</h4>
			</div>
			<div class="modal-body">
				<form action="CourseServlet?action=create" id='create-new-course'
					method="post" enctype="multipart/form-data" role="form" role="form">
					<div class="form-group">
						<label for="login-username"><i class="icon-user"></i> <b><t:i18n
									id="admin.course.name" /></b></label> <input name="name"
							class="form-control" id="login-username" type="text"
							placeholder="">
					</div>
					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n
									id="admin.course.image" /></b></label> <input name="icon"
							class="form-control" id="file" type="file" required="required"
							placeholder="">
					</div>


					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n
									id="admin.course.description" /></b></label>
						<textarea name="description" class="form-control" id="comment"
							placeholder=""></textarea>
					</div>




					<div class="form-group">

						<button type="submit" class="btn pull-right">
							<t:i18n id="admin.course.create" />
						</button>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>

		</div>
	</div>

</div>



<div id="editCourse" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<t:i18n id="admin.course.settings" />
				</h4>
			</div>
			<div class="modal-body">
				<form action="CourseServlet?action=update" data-async
					id='edit-course-form' method="post" enctype="multipart/form-data"
					role="form" role="form">
					<div class="form-group" hidden="true">
						<input class="form-control" type="text" name='course_id'
							id='edit-course-id'>
					</div>
					<div class="form-group">
						<label for="login-username"><i class="icon-user"></i> <b><t:i18n
									id="admin.course.name" /></b></label> <input name="name"
							class="form-control" id="course-name-edit" type="text"
							placeholder="">
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n
									id="admin.course.image" /></b></label> <input name="image"
							class="form-control" id="course-icon-edit" type="file"
							placeholder="">
					</div>
					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n
									id="admin.course.description" /></b></label>
						<textarea name="description" rows="10" class="form-control"
							id="course-description-edit" placeholder=""> </textarea>
					</div>
					<div class="form-group">

						<button type="submit" id='submit-course-edit'
							class="btn pull-right">
							<t:i18n id="admin.course.update" />
						</button>
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
		event.preventDefault();
		event.stopImmediatePropagation();
		$.ajax({
			type : $form.attr('method'),
			url : $form.attr('action'),
			data : $form.serialize(),

			success : function(data, status) {
				$target.html(data);
			}
		});

	});

	$('button#submit-course-edit').click(function() {
		$('#editCourse').modal('hide');
		showToaast("<t:i18n id='admin.course.toast'/>", 1);
	});
</script>