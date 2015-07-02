<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<div id="editCategory" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Edit category</h4>
			</div>
			<div class="modal-body">
				<form id="editCategory" action="FaqServlet?action=editCategory"
					method="post">
					<div hidden="true" class="form-group" id='message-subject'>

						<input name="category_id" class="form-control" id="category_id"
							type="text" placeholder="">
					</div>


					<div class="form-group" id='message-subject'>
						<label for="login-username"><i class="icon-user"></i> <b>Name</b></label>
						<input name="categoryName" class="form-control" id="categoryName"
							type="text" placeholder="">
					</div>

					<div class="form-group">
						<input type="submit" class="btn btn-primary pull-right"
							value="Edit">


						<div class="clearfix"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<script>
	var $form = $('form#editCategory');
	$form.submit(function(e) {

		e.preventDefault();
		e.stopImmediatePropagation();
		request = $.ajax({
			type : $form.attr('method'),
			url : $form.attr('action'),
			data : $form.serialize(),

			success : function(text) {
				var $option = $("<option value="+text.id+" >" + text.category
						+ "</option>");
				$("select#article-course").append($option);

				$('div#editCategory').modal('hide');
				$("select option:selected").text(text.category);
				showToaast("FAQ category was succesfully edited", 1);
				//showToaast("<t:i18n id='group.task.success'/>", 1);

			},
			error : function() {

				//showToaast("<t:i18n id='group.task.error.update'/>", 0);

			}
		});

		return false;
	});
</script>

