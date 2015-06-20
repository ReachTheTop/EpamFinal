<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<div class="panel panel-default">
			<div class="panel-heading">Teacher Panel</div>
			<div class="panel-body">
				<div class="row-fluid">
					<form action="GroupServlet?group_id=${group.id }" id='rebase-group'
						method="post">
						<input id="group" hidden="true" value="${group.id }">
						<div class="row-fluid">
							<input id="myAutocomplete" type="text" name="users" />
						</div>

						<div class="btn-group">
							<button type="submit" class="btn btn-primary" name="action"
								value="remove">Remove Users</button>
							<button type="submit" class="btn btn-primary" name="action"
								value="add">Add Users</button>
							<button type="submit" class="btn btn-primary" name="action"
								value="leave">Leave Users</button>
							<a data-toggle="modal" class="btn btn-primary"
								id="groupEditModal" href="#editGroup">Rebase Users</a>
						</div>

						<div id="editGroup" class="modal fade">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title">Group settings</h4>
									</div>
									<div class="modal-body">
										<div class="form-group" hidden="true">
											<input name="group_id" class="form-control"
												id="group-id-edit" type="text" placeholder="">
										</div>

										<input id="toggle-event" checked name="marker" type="checkbox"
											data-toggle="toggle">
										<div id="console-event"></div>
										<script type="text/javascript">
											$(function() {
												$.get("GroupServlet?action=getTeacherGroups&group_id=${group.id}", function(response) {
													var $option;
													$.each(response, function(index, item) {
													$option = $("<option id='teacher-id'>");
													$option.val(item['id']);
													$option.text(item['name']);
													$('#group-teacher-edit').append($option);
												});
												});	
												$('#create-group').hide();

												$('#toggle-event')
														.change(
																function() {
																	if ($(this)
																			.prop(
																					'checked')) {
																		$(
																				'#create-group')
																				.hide();
																		$(
																				'#select-group')
																				.show();
																	} else {
																		$(
																				'#create-group')
																				.show();
																		$(
																				'#select-group')
																				.hide();
																	}
																});
											});
										</script>
										<div class="form-group" id='create-group'>
											<label for="login-username"><i class="icon-user"></i>
												<b>Create Group</b></label> <input name="name" class="form-control"
												id="group-name-edit" type="text" placeholder="">
										</div>

										<div class="form-group" id="select-group">
											<label for="login-password"><i class="icon-lock"></i>
												<b>Select Group</b></label> <select name="new_group_id"
												class="form-control" id="group-teacher-edit"></select>
										</div>
										<div class="form-group">

											<button type="submit" name="action" value="rebase"
												class="btn pull-right">Rebase</button>
											<div class="clearfix"></div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	
