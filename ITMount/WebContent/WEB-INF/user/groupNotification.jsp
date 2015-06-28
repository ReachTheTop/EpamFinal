<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<div id="notificationWindov" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title"><t:i18n id='notification'/></h4>
			</div>
			<c:forEach items="${ messages}" var="message">
				<div class="modal-body">
					<h4>
						<c:out value="${message.subject }" />
					</h4>
					<p>
						<c:out value="${ message.content}" />
					</p>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(window).load(function() {
		$('#notificationWindov').modal('show');
	});
</script>

