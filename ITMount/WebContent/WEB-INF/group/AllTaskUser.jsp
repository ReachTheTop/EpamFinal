<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<div class="panel-group" id="accordion" role="tablist"
	aria-multiselectable="true">

	<c:forEach items="${requestScope.listtasks}" var="tasks">

		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingThree">
				<h4 class="panel-title">

					<a class="collapsed" role="button" data-toggle="collapse"
						data-parent="#accordion" href="#collapse${tasks.key.id}"
						aria-expanded="false" id="task${tasks.key.id }" aria-controls="collapse${tasks.key.id}">
						<c:out value="${tasks.key.name} " />
					</a>
					<c:if test="${group.teacher_id == user.id }">
						<a data-toggle="modal" id="${tasks.key.id}" class="updateTask pull-right"
							style="" href="#myModalUpdate"><i class="fa fa-edit"></i></a>
					</c:if>

				</h4>
			</div>
			<div id="collapse${tasks.key.id}" class="panel-collapse collapse"
				role="tabpanel" aria-labelledby="headingThree">
				<div class="panel-body">
					<div class="list-group">

						<table class="table table-bordered">
							<tr>
								<td><t:i18n id='group.task.name' /></td>
								<td id="taskName${tasks.key.id }" ><c:out value="${tasks.key.name }" /></td>
							</tr>
							<tr>
								<td><t:i18n id='group.task.description' /></td>
								<td id="taskDesc${tasks.key.id }" ><c:out value="${tasks.key.description }" /></td>
							</tr>
							<tr>
								<td><t:i18n id='group.task.deadline' /></td>
								<td id="taskLine${tasks.key.id }" >${tasks.key.deadline }</td>
							</tr>
							<tr>
								<td><t:i18n id='group.task' /></td>
								<td>
									<p>
										<a id="taskFile${tasks.key.id }"
											href="<c:url value="/downloadFile?file=${tasks.key.file}"/>"
											class="btn btn-primary btn-sm"><t:i18n
												id='group.task.download' /></a>
									</p>


								</td>
							</tr>

							<c:if test="${user.role =='student' || user.role =='applicant' }">
								<tr>
									<td><t:i18n id='group.task.homework' /></td>
									<td><c:choose>
											<c:when test="${tasks.key.getAvailable() == false}">
												<t:i18n id='group.task.timeout' />
											</c:when>

											<c:otherwise>
												<c:choose>

													<c:when test="${tasks.value !=null}">
														<p>
															<a data-toggle="modal" href="#updateHomeWork"
																data-delete="${tasks.value.getData()}"
																data-homework="${tasks.value.getId()}"
																class="open-updateHomeWork btn btn-primary btn-sm"><t:i18n
																	id='group.task.update' /></a>
														</p>
													</c:when>

													<c:otherwise>
														<p>
															<a data-toggle="modal" href="#uploadHomeWork"
																data-task="${tasks.key.getId()}"
																class="open-uploadHomeWork btn btn-primary btn-sm"><t:i18n
																	id='group.task.homework.add' /> </a>
														</p>

													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose></td>

									<%-- 												<c:if test="${tasks.key.getAvailable() == false}">It's false!</c:if> --%>

								</tr>
							</c:if>
						</table>

					</div>

				</div>
			</div>
		</div>

	</c:forEach>

</div>



<div id="uploadHomeWork" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<t:i18n id='group.task.new' />
				</h4>
			</div>
			<div class="modal-body">
				<form action="<c:url value="/Homework?action=upload"/>"
					method="post" enctype="multipart/form-data" id="uploadHomework"
					role="form">

					<div class="form-group">
						<input class="form-control" id="idTask" name="task_id"
							type="hidden" value="">
					</div>

					<input type="hidden" name="user_id" value="${user.id}" />


					<div class="form-group">
						<label for="register-username"><i class="icon-user"></i> <b><t:i18n
									id='group.task.file' /></b></label> <input class="form-control"
							id="register-username" type="file" placeholder="" name="file">
					</div>


					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<t:i18n id='group.task.close' />
						</button>
						<button type="submit" class="btn btn-primary">
							<t:i18n id='group.task.homework.add' />
						</button>
					</div>
				</form>
			</div>

		</div>
	</div>

</div>


<div id="updateHomeWork" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<t:i18n id='group.task.file.add' />
				</h4>
			</div>
			<div class="modal-body">
				<form action="<c:url value="/Homework?action=update"/>"
					method="post" enctype="multipart/form-data" id="uploadHomework"
					role="form">


					<div class="form-group">
						<input class="form-control" id="idDelete" name="uploadFile"
							type="hidden" value="">
					</div>
					<div class="form-group">
						<input class="form-control" id="idHomework" name="id_homework"
							type="hidden" value="">
					</div>

					<div class="form-group">
						<label for="register-username"><i class="icon-user"></i> <b><t:i18n
									id='group.task.file' /></b></label> <input class="form-control"
							id="register-username" type="file" placeholder="" name="file">
					</div>


					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<t:i18n id='group.task.close' />
						</button>
						<button type="submit" class="btn btn-primary">
							<t:i18n id='group.task.update' />
						</button>
					</div>
				</form>
			</div>

		</div>
	</div>

</div>

<script>
	$(document).on("click", ".open-updateHomeWork", function() {

		var filedelete = $(this).data('delete');
		var homeworkid = $(this).data('homework');
		$(".form-group #idDelete").val(filedelete);
		$(".form-group #idHomework").val(homeworkid);

	});
</script>

<script>
	$(document).on("click", ".open-uploadHomeWork", function() {

		var taskid = $(this).data('task');
		$(".form-group #idTask").val(taskid);

	});
</script>

