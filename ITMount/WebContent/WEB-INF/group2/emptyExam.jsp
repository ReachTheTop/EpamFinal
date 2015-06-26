<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="examWindov" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">

				<h4 class="modal-title">Exam list</h4>
			</div>
			<div class="modal-body">
				<p>The date of exam has not been set yet. Please visit this page
					later.</p>
			</div>
			<div class="modal-footer">
				<a href="<c:url value="UserServlet" />" class='btn btn-primary'>Back</a>
				
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
