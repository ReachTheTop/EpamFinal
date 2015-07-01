<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<div id="examWindov" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">

				<h4 class="modal-title"><t:i18n id='group.exam.list'/></h4>
			</div>
			<div class="modal-body">
				<p><t:i18n id='group.exam.list.empty'/></p>
			</div>
			<div class="modal-footer">
				<a href="<c:url value="UserServlet" />" class='btn btn-primary'><t:i18n id='group.exam.list.back'/></a>
				
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	$('#examWindov').modal({
		backdrop : 'static',
		keyboard : true
	})
</script>
