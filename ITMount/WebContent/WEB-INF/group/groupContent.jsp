<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row">
	<div class="col-md-8">
		<c:if
			test="${not empty group.teacher_id && user.id  == group.teacher_id }">
			<jsp:include page="teacherPanel.jsp"></jsp:include>
		</c:if>
		<!-- ПАНЕЛЬ ПОДІЙ  -->
		<jsp:include page="eventPanel.jsp"></jsp:include>
		<!-- ПАНЕЛЬ ПОДІЙ  -->
	</div>



	<div class="col-md-4">
		<jsp:include page="userPanel.jsp"></jsp:include>
	</div>
	<c:if
		test="${not empty group.teacher_id && user.id == group.teacher_id}">
		<div class="col-md-4">
			<jsp:include page="examPanel.jsp"></jsp:include>
		</div>
	</c:if>
	
	<div class="col-md-8">
		<jsp:include page="AllTaskUser.jsp"></jsp:include>
	</div>
	
</div>
