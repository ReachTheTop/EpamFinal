<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<div id="notificationHistory" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title"><t:i18n id='notification.history'/></h4>
			</div>

			<div class="modal-body"
				style="max-height: calc(100vh - 210px); overflow-y: auto;">
				<c:forEach items="${ history}" var="message">
					<div>
						<div class="date">
							<fmt:formatDate value="${message.sendDate }"
								pattern="MM/dd/yyyy HH:mm" />
						</div>
						<h4>
							<c:out value="${message.subject }" />
						</h4>
						<p>
							<c:out value="${ message.content}" />
						</p>
					</div>
					<hr>
				</c:forEach>
			</div>

		</div>
	</div>
</div>
<script type="text/javascript">
	$(window).load(function() {
		$('#notificationWindov').modal('show');
	});
</script>

