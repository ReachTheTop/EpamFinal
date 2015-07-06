
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>




<div class="panel-body">
	<div class="row-fluid">

		<div class="form-group">

			<input type="text" id="search-groups"
				placeholder="<t:i18n id='admin.group.search'/>" class='form-control'>
		</div>


	</div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th><t:i18n id="admin.group.course" /></th>
				<th><t:i18n id="admin.group.name1" /></th>
				<th><t:i18n id="admin.group.teacher" /></th>
				<th><t:i18n id="admin.group.confirmed" /></th>
				<th><t:i18n id="admin.group.edit" /></th>
			<tr>
		</thead>
		<tbody id="groups-body">

		</tbody>
	</table>
	<div class="row" align="center">

		<div id="group-page-selection"></div>

	</div>
</div>




<a data-toggle="modal" id="groupEditModal" href="#editGroup"><i
	class='glyphicon glyphicon-edit'></i></a>
<div id="editGroup" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<t:i18n id="admin.group.settings" />
				</h4>
			</div>
			<div class="modal-body">
				<form action="GroupServlet?action=update" data-async
					id='edit-group-form' method="post" enctype="multipart/form-data"
					role="form" role="form">

					<div class="form-group" hidden="true">
						<input name="group_id" class="form-control" id="group-id-edit"
							type="text" placeholder="">
					</div>

					<div class="form-group" hidden="true">
						<input class="form-control" type="text" name='group_id'
							id='edit-group-id'>
					</div>
					<div class="form-group">
						<label for="login-username"><i class="icon-user"></i> <b><t:i18n
									id="admin.group.name" /></b></label> <input name="name"
							class="form-control" id="group-name-edit" type="text"
							placeholder="">
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n
									id="admin.group.teacher" /></b></label> <select name="teacher_id"
							class="form-control" id="group-teacher-edit"></select>
					</div>
					<div class="form-group">

						<button type="submit" id='submit-group-edit'
							class="btn pull-right">
							<t:i18n id="admin.group.update" />
						</button>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$('button#submit-group-edit').click(function() {
		$('#edit-group-form').data('bootstrapValidator').resetForm();
		$('#editGroup').modal('hide');
		showToaast("<t:i18n id='admin.group.update.toast'/>", 1);
	});
</script>