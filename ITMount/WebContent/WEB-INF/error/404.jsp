<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><t:i18n id="404.errorPage"/></title>
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />

	<!-- Page Title -->
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1><t:i18n id="404.PageNotFound"/></h1>
				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div class="error-page-wrapper">
						<p><t:i18n id="404.SorryThePageYouAreLookingForIsNotHereOrNeverWas"/></p>
						<p>
							<t:i18n id="404.WhyDon'tYouTryThe"/> <a href="<c:url value="/home" />"><t:i18n id="404.Homepage"/></a>?
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="../page/footer.jsp" />

</body>
</html>