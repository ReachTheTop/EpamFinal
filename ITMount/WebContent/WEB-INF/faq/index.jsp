<%@ page language="java" contentType="text/html;  charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
<title>FAQ</title>
<jsp:include page="../page/head.jsp" />

<script src="assets/js/jquery.bootpag.min.js"></script>

<script src="assets/js/jquery.bootpag.min.js"></script>
</head>
<body>
	<jsp:include page="../page/header.jsp" />



	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>
						FAQ
						<c:if test="${user.role == 'admin' }">
							<a class="btn btn-primary" href="<c:url value="/FaqServlet?action=new" />"><t:i18n id="faq.new"/></a>
						</c:if>
					</h1>

				</div>
			</div>
		</div>
	</div>


	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12 faq-wrapper">
					<div class="panel-group" id="accordion2">
						<c:forEach items="${categories}" var="category">
							<h3>
								<c:out value="${category.category }" />
							</h3>

							<c:forEach items="${category.faq }" var="qa">
								<div class="panel panel-default">
									<div class="panel-heading">
										<a class="accordion-toggle" data-toggle="collapse"
											data-parent="#accordion2" href="#collapse${qa.id }"> <c:out
												value="${qa.header }" />

										</a>
										<c:if test="${user.role == 'admin' }">
											<a class="pull-right"
												href="<c:url value="/FaqServlet?action=edit&faq_id=${qa.id }" />"><i
												class="glyphicon glyphicon-edit"></i> </a>
										</c:if>

									</div>
									<div id="collapse${qa.id }" class="accordion-body collapse">
										<div class="accordion-inner">

											<p>${qa.data }</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="../page/footer.jsp" />

	<script type="text/javascript">
		$(function() {
			$('nav.mainmenu  li.active').removeAttr('class');
			$('nav.mainmenu  li#faq').attr("class", "active");
		});
	</script>
</body>
</html>