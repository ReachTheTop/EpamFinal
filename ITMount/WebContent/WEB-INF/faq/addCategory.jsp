<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<div id="addCategory" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">New FAQ category</h4>
			</div>
			<div class="modal-body">
				<form id="addCategory" action="FaqServlet?action=newCategory" method="post">
				
					<div class="form-group" id='message-subject'>
						<label for="login-username"><i class="icon-user"></i> <b>Name</b></label>
						<input name="category" class="form-control" id="category"
							type="text" placeholder="">
					</div>

					<div class="form-group">
						<input type="submit" class="btn btn-primary pull-right"
							value="Add">


						<div class="clearfix"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


	<script>
		var $form2 = $('form#addCategory');
		$form2.submit(function(e) {

			e.preventDefault();
			e.stopImmediatePropagation();
			request = $.ajax({
				type : $form2.attr('method'),
				url : $form2.attr('action'),
				data : $form2.serialize(),
				
				success : function(text) {
					var $option = $("<option value="+text.id+" >"+text.category+"</option>");
					$("select#article-course").append($option);				

					$('div#addCategory').modal('hide');
					
					showToaast("FAQ category was succesfully created",1);
					//showToaast("<t:i18n id='group.task.success'/>", 1);

				},
				error : function() {
					
					//showToaast("<t:i18n id='group.task.error.update'/>", 0);

				}
			});

			return false;
		});
	</script>
	
