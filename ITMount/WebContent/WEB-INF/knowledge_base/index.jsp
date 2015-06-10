<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resources</title>
<jsp:include page="../page/head.jsp" />
</head>
<body>
	<jsp:include page="../page/header.jsp" />



	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>Resources</h1>
					<a href="<c:url value="/KnowledgeBaseServlet?action=new" />">Add Resource</a>

				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container">
			<div class="row">
				<c:forEach items="${base }" var="item">
					<div class="col-md-4 col-sm-6">
						<div class="service-wrapper">
							<h3>
								<c:out value="${item.path }" />
							</h3>

						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>



	<jsp:include page="../page/footer.jsp" />
	<jsp:include page="../page/script.jsp" />
</body>
</html>