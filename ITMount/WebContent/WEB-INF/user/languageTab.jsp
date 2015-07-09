
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>


<div class="container-fluid">
	<div class="row-fluid">

		<div class="form-group">
			<a
				href="<c:url value="/downloadFile?file=WEB-INF\\classes\\i18n.properties"/>"
				class="btn btn-default btn-xs dropdown-toggle"><t:i18n
					id="admin.language.pattern" /></a>
			<button type="submit" class="btn btn-default btn-xs dropdown-toggle"
				data-toggle="modal" href="#updatePatternLanguage">
				<t:i18n id="admin.language.pattern.update" />
			</button>
			<button type="submit" class="btn btn-default btn-xs dropdown-toggle"
				data-toggle="modal" href="#addLanguage">
				<t:i18n id="admin.language.add" />
			</button>

			<a data-toggle="modal" id="languageEditModal" href="#languageEdit"><i
				class='btn glyphicon glyphicon-edit'></i></a>

		</div>


	</div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>№</th>
				<th><t:i18n id="admin.language.name" /></th>
				<th><t:i18n id="admin.language" /></th>
				<th><t:i18n id="admin.language.country" /></th>
				<th><t:i18n id="admin.language.image" /></th>

				<th><t:i18n id="admin.language.activity" /></th>
				<th><t:i18n id="admin.language.actions" /></th>
			<tr>
		</thead>
		<tbody id="language-body">

		</tbody>
	</table>

</div>




<div id="addLanguage" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<t:i18n id="admin.language.new" />
				</h4>
			</div>
			<div class="modal-body">
				<form
					action="<c:url value="/LanguageUploadServlet?action=upload" />"
					data-async id='add-language-form' method="post"
					enctype="multipart/form-data" role="form">

					<div class="form-group">
						<label for="login-username"><i class="icon-user"></i> <b><t:i18n
									id="admin.language.name" /></b></label> <input name="name"
							class="form-control" id="new-name" type="text" placeholder="">
					</div>

					<div class="form-group">
						<label for="login-username"><i class="icon-user"></i> <b><t:i18n
									id="admin.language" /></b></label> <input name="language"
							class="form-control" id="new-language" type="text" placeholder="">
					</div>
					<div class="form-group">
						<label for="login-username"><i class="icon-user"></i> <b><t:i18n
									id="admin.language.country" /></b></label> <input name="country"
							class="form-control" id="new-language" type="text" placeholder="">
					</div>
					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n
									id="admin.language.file" /></b></label> <input name="bandle"
							class="form-control" id="new-file" type="file" placeholder="">
					</div>
					<div class="form-group">

						<button type="submit" id='submit-upload-language'
							class="btn pull-right">
							<t:i18n id="admin.language.create" />
						</button>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<div id="updatePatternLanguage" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<t:i18n id="admin.language.pattern.update" />
				</h4>
			</div>
			<div class="modal-body">
				<form
					action="<c:url value="/LanguageUploadServlet?action=updateP" />"
					data-async id='update-pattern-language-form' method="post"
					enctype="multipart/form-data" role="form">

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n
									id="admin.language.file" /></b></label> <input name="bandle"
							class="form-control" type="file" placeholder="">
					</div>
					<div class="form-group">

						<button type="submit" id='submit-upload-language'
							class="btn pull-right">
							<t:i18n id="admin.language.update" />
						</button>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div id="languageEdit" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<t:i18n id="admin.language.update.title" />
				</h4>
			</div>
			<div class="modal-body">
				<form
					action="<c:url value="/LanguageUploadServlet?action=update" />"
					data-async id='update-language-form' method="post"
					enctype="multipart/form-data" role="form">

					<div class="form-group" hidden="true">
						<input name="language_id" class="form-control"
							id="language-id-edit" type="text" placeholder="">
					</div>
					<div class="form-group">
						<label for="login-username"><i class="icon-user"></i> <b><t:i18n
									id="admin.language.name" /></b></label> <input name="name"
							class="form-control" id="language-name-edit" type="text"
							placeholder="">
					</div>

					<div class="form-group">
						<label for="login-password"><i class="icon-lock"></i> <b><t:i18n
									id="admin.language.file" /></b></label> <input name="bandle"
							class="form-control" type="file" placeholder="">
					</div>
					<div class="form-group">

						<button type="submit" id='submit-upload-language'
							class="btn pull-right">
							<t:i18n id="admin.language.update" />
						</button>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script>
$('body').on(
		'click',
		'a#delete-language',
		function() {
			 var object =$(this);
			  swal({
				  title: "<t:i18n id='bootstrap.AreYouSure'/>",
				  text: "<t:i18n id='bootstrap.NotAbleRecoverLanguage'/>",
				  type: "warning",
				  showCancelButton: true,
				  confirmButtonClass: "btn-danger",
				  confirmButtonText: "<t:i18n id='bootstrap.button.Yes'/>!",
				  cancelButtonText: "<t:i18n id='bootstrap.button.No'/>!",
				  closeOnConfirm: true,
				  closeOnCancel: true
				},
				
				function(isConfirm) {
					  
					  if (isConfirm) {
						  
						  $.get('LanguageUploadServlet?action=delete&language_id=' +
								 object.attr('name'),
								  function(response) {
				                         if (response.success) {
				                        	object.parent().parent().remove();
				                        	 showToaast(response.success, 1);
				                         } else {                     
				                        	 showToaast(response.fail, 0);

				                         }
								  });

					   
					  }
			

		});
		
			
		});
</script>
