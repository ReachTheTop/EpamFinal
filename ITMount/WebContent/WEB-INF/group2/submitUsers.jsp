<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="confirmParticipates" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Confirm group participates</h4>
			</div>
			<div class="modal-body">
				<div class="form-group" hidden="true">
					<input name="group_id" class="form-control" id="group-id-edit"
						type="text" placeholder="" value="${group.id }">
				</div>

				<div class="form-group" id='message-subject'>
					<label for="login-username"><i class="icon-user"></i> <b>Message
							subject</b></label> <input name="subject" class="form-control"
						id="group-name-edit" type="text" placeholder="">
				</div>

				<div class="form-group" id='passed'>
					<label for="login-username"><i class="icon-user"></i> <b>
							Passed</b></label> <textarea name="messagePassed" class="form-control"
						id="group-name-edit"  placeholder=""></textarea>
				</div>
				
				<div class="form-group" id='failed'>
					<label for="login-username"><i class="icon-user"></i> <b>
							Failed</b></label> <textarea name="messageFailed" class="form-control"
						id="group-name-edit" placeholder=""></textarea>
				</div>


				<div class="form-group">
					<button type="submit" class="btn btn-primary pull-right" name="action"
						value="leave">Submit students</button>
					<div class="clearfix"></div>
				</div>

			</div>
		</div>
	</div>
</div>
