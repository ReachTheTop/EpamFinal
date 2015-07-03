<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
<title><t:i18n id="faq.edit"/></title>




<link rel="apple-touch-icon"
	href="//mindmup.s3.amazonaws.com/lib/img/apple-touch-icon.png" />
<link rel="shortcut icon"
	href="http://mindmup.s3.amazonaws.com/lib/img/favicon.ico">
<link href="assets/editor/external/google-code-prettify/prettify.css"
	rel="stylesheet">
<!-- <link
	href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.no-icons.min.css"
	rel="stylesheet">
 --><link
	href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css"
	rel="stylesheet">

<!-- <script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script> -->

<!-- <script
	src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script> -->

<link href="assets/editor/index.css" rel="stylesheet">

<jsp:include page="../page/head.jsp" />
<script src="assets/editor/external/jquery.hotkeys.js"></script>
<script src="assets/editor/bootstrap-wysiwyg.js"></script>
<script src="assets/editor/external/google-code-prettify/prettify.js"></script>
<script src="resources/js/toastr.js"></script>
<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">
</head>
<body>
	<jsp:include page="../page/header.jsp" />


	<!-- Page Title -->
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1><t:i18n id="faq.edit"/></h1>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="addCategory.jsp" />
<jsp:include page="editCategory.jsp" />
	<div class="section">
		<div class="container">
			<div class="row">

				<jsp:include page="editor.jsp"></jsp:include>
			</div>
		</div>
	</div>

<br><br><br><br>

	<jsp:include page="../page/footer.jsp" />


</body>
</html>