
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>

<div class="container-fluid">
	<div class="row-fluid">

		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading  panel-heading-custom">
					<h3 class="panel-title" id="userNameSurname">
						<c:out value="${current_user.name }" />
						<c:out value="${current_user.surname }" />

						<%-- 									<c:if test="${current_user.id == user.id }"> --%>
						<!-- 										<a data-toggle="modal" href="#editUser"><i -->
						<!-- 											class="glyphicon glyphicon-edit"></i></a> -->
						<%-- 									</c:if> --%>

					</h3>

				</div>
				<div class="panel-body">
					<div class="row-fluid">
						<div class="col-md-3 col-lg-3 " align="center">
							<img id="fileImage1" src="upload/${current_user.image }" alt=""
								class="img-rounded img-responsive" />

						</div>


						<div class=" col-md-9 col-lg-9 ">
							<table class="table table-user-information">
								<tbody>
									<tr>

										<td><t:i18n id="home.FirstName" /></td>
										<td id="userName"><p>
												<c:out value="${current_user.name }" />
											</p></td>
									</tr>
									<tr>
										<td><t:i18n id="home.MiddleName" /></td>
										<td id="userMiddleName"><c:out
												value="${current_user.middle_name }" /></td>
									</tr>
									<tr>
										<td><t:i18n id="home.Surname" /></td>
										<td id="userSurName"><p>
												<c:out value="${current_user.surname }" />
											</p></td>
									</tr>


									<tr>

										<td><t:i18n id="home.Birthday" /></td>
										<td id="userBirtday"><fmt:formatDate pattern="yyyy-MM-dd"
												value="${current_user.birtday }" /></td>

									</tr>
									<tr>
										<td>Email</td>
										<td><c:out value="${current_user.email}" /></td>
									</tr>
									<tr>

										<td><t:i18n id="home.Phone" /></td>
										<td id="userPhone">${contact.phone}</td>

									</tr>
									<tr>
										<td>Skype</td>
										<td id="userSkype"><p>${contact.skype}</p></td>

									</tr>
									<tr>
										<td><t:i18n id="home.CurriculumVitae" /></td>
										<td><c:choose>
												<c:when
													test="${current_user.curriculum_vitae == null && user.id == current_user.id }">
													<button type="submit"
														class="btn btn-default btn-xs dropdown-toggle"
														data-toggle="modal" href="#editUser">
														<t:i18n id="home.AddCV" />
													</button>
												</c:when>

												<c:otherwise>
													<p>

														<c:if test="${current_user.curriculum_vitae != null}">
															<a id="cvFile1"
																href="<c:url  value="/downloadFile?file=${current_user.curriculum_vitae}"/>"><i
																class="glyphicon glyphicon-file"></i> </a>
														</c:if>

													</p>

												</c:otherwise>

											</c:choose></td>
									</tr>
									<tr>

										<td><t:i18n id="home.AboutMyself" /></td>
										<td id="userDescription"><p>
												<c:out value="${current_user.description}" />
											</p></td>
									</tr>



								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

		</div>

		<jsp:include page="notificationHistory.jsp" />
		<c:if test="${user.role != 'admin' }">
			<div class="col-md-12">

				<h2>
					<t:i18n id="home.MyCourses" />
					<c:if test="${current_user.id == user.id && not empty history }">
						<a data-toggle="modal" href="#notificationHistory"><i
							class="glyphicon glyphicon-th-list"></i> </a>
					</c:if>
				</h2>
				<div class="section">
					<c:forEach items="${groups }" var="group">
						<div class="col-sm-6" style="margin-top: 5px">
							<a
								href="<c:url
							value="/GroupServlet?action=show&group_id=${group.id }" />">


								<img src="upload/${group.course.icon }" class="img-circle"
								alt="Cinque Terre" width="150" height="150"
								alt="<c:out value='${group.name }' />">

							</a>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:if>

	</div>
</div>

<div id="editUser" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<t:i18n id="home.MySettings" />
				</h4>
			</div>
			<div class="modal-body">
				<form action="UserServlet?action=update" id='editUserForm'
					method="post" enctype="multipart/form-data" role="form" role="form">
					<div class="form-group">

						<label for="login-username"><i class="icon-user"></i> <b><t:i18n
									id="home.FirstName" /> </b></label> <input name="name"
							class="form-control" id="userNameModal" type="text"
							placeholder="" value="${user.name }">
					</div>
					<div class="form-group">

						<label for="login-username"><i class="icon-user"></i> <b><t:i18n
									id="home.MiddleName" /> </b></label> <input name="middle_name"
							class="form-control" id="middleNameModal" type="text"
							placeholder="" value="${user.middle_name }">
					</div>
					<div class="form-group">
						<label for="login-username"><i class="icon-user"></i> <b><t:i18n
									id="home.Surname" /> </b></label><input name="surname"
							class="form-control" id="userSurNameModal" type="text"
							placeholder="" value="${user.surname }">

					</div>
					<div class="form-group" style="display: none">
						<label for="login-username"><i class="icon-user"></i> <b>Email</b></label>
						<input name="email" class="form-control" id="login-username"
							type="text" placeholder="" value="${user.email }">
					</div>

					<div class="form-group">
						<label for="login-username"><i class="icon-user"></i> <b>Birthday</b></label>
						<input name="userBirthday" class="form-control" id="userBirthday1"
							type="date">
					</div>

					<div class="form-group">

						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n
									id="home.photo" /> </b></label><input name="image" class="form-control"
							id="fileImage" type="file" placeholder="" value="${user.image }">
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n
									id="home.CurriculumVitae" /> </b></label> <input name="cv"
							class="form-control" id="file" type="file" placeholder=""
							value="${user.curriculum_vitae }">
					</div>



					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n
									id="home.Phone" /> </b></label><input name="phone" class="form-control"
							id="login-username" type="text" placeholder=""
							value="${contact.phone }">
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b>Skype</b></label>
						<input name="skype" class="form-control" id="login-username"
							type="text" placeholder="" value="${contact.skype }">
					</div>


					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n
									id="home.AboutMyself" /> </b></label>
						<textarea name="description" class="form-control" id="comment"
							placeholder=""><c:out value="${user.description }" /> </textarea>
					</div>




					<div class="form-group">

						<button type="submit" class="btn pull-right">
							<t:i18n id="home.Update" />
						</button>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>

		</div>
	</div>

</div>







<div class="form-group" style="display: none">

	<input name="birsday" class="form-control" id="birsday1111" type="text"
		placeholder="" value="${userBirthdayString }">
</div>

<script>
	var dateString = $("#birsday1111").val();
	$("#userBirthday1").val(dateString);
</script>


<script>
	var form = $('#editUserForm');
	form
			.submit(function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				request = $
						.ajax({
							type : form.attr('method'),
							url : form.attr('action'),
							data : new FormData(this),
							processData : false,
							contentType : false,
							dataType : "json",

							success : function(data) {
								
								$('#editUserForm').data('bootstrapValidator').resetForm();
								var nameUser = data.name;
								$("#userName").html(nameUser);
								$("#userNameModal").html(nameUser);
								$("#userNameHeader").html(nameUser);
								var surNameUser = data.surname;
								$("#userSurNameModal").html(surNameUser);
								$("#userSurName").html(surNameUser);
								var userNameSurname = nameUser.concat("  ")
										.concat(surNameUser);
								$("#userNameSurname").html(userNameSurname);
								var userMiddleName = data.middle_name;
								$("#middleNameModal").html(userMiddleName);
								$("#userMiddleName").html(userMiddleName);

								var dateString = data.birthdayString;
								$("#userBirthday2").html(dateString);

								var imageFile = data.image;
								$("#fileImage1").attr("src",
										"upload/" + imageFile);
								var cvFile = data.curriculum_vitae;
								$("#cvFile1").attr("href",
										"/ITMount/downloadFile?file=" + cvFile);
								var phone = data.contacts.phone;
								$("#userPhone").html(phone);
								var skype = data.contacts.skype;
								$("#userSkype").html(skype);
								var description = data.description;
								$("#userDescription").html(description);

								$('#editUser').modal('hide');

								showToaast(
										'<t:i18n id="home.ProfileWasSuccessfullyEdited"/>',
										1);

							},
							error : function() {

								showToaast(
										'<t:i18n id="home.ProfileWasNotEdited"/>',
										0);

							}
						});
				return false;
			});
</script>


