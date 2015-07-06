
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>

<div class="container-fluid">
	<div class="row-fluid">

		<div class="form-group">

			<input type="text" id="search-users"
				placeholder="<t:i18n id='admin.user.search'/>" class='form-control'>
		</div>


	</div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th><t:i18n id="admin.user.name" /></th>
				<th><t:i18n id="admin.user.surname" /></th>
				<th><t:i18n id="admin.user.role" /></th>
				<th>Email</th>
				<th><t:i18n id="admin.user.phone" /></th>
				<th>Skype</th>
				<th><t:i18n id="admin.user.activity" /></th>
			<tr>
		</thead>
		<tbody id="users-body">

		</tbody>
	</table>
	<div class="row" align="center">

		<div style="position:relative;" id="user-page-selection"></div>

	</div>
</div>

	<div hidden="true">
		<select class='form-control' id='userRoles' name='role'>
			<option value="2">student</option>
			<option value="3">lecturer</option>
			<option value="4">admin</option>
		</select>
	</div>