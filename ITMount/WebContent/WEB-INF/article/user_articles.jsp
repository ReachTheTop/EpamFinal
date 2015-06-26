<%@ page language="java" contentType="text/html;  charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
<title>Courses</title>
<jsp:include page="../page/head.jsp" />

<script
	src="${pageContext.request.contextPath}/assets/js/jquery.bootpag.min.js"></script>

</head>
<body>
	<jsp:include page="../page/header.jsp" />



	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>
						<a href="<c:url value='ArticleServlet' />">Articles</a>
					</h1>

				</div>
			</div>
		</div>
	</div>



	<div class="section blog-posts-wrapper">
		<div class="container">
		

			<c:forEach items="${articles }" var="article">
				<div class="row">
					<div class="col-md-12">
						<div class="blog-post">


							<div class="post-title">
								<h3 align="center">
									<a
										href="<c:url value="ArticleServlet?action=show&article_id=${article.id }" />"
										id='read-mode'><c:out value="${article.header }" /></a>

								</h3>

							</div>

							<div class="post-info">
								<div class="post-date">
									<div class="date">
										<fmt:formatDate value="${article.date }"
											pattern="MM/dd/yyyy HH:mm" />
									</div>
								</div>
							</div>



							<div class="post-summary"
								style="height: 100px; min-height: 100px; overflow: hidden;">${article.data }</div>
							<div class="post-more">
								<c:if test="${user.id == article.user_id }">
									<a class="btn
									btn-primary"
										href="<c:url value='ArticleServlet?action=edit&article_id=${article.id }&course_id=${article.course_id }' />">Edit</a>
									<a class="btn btn-primary" name='${article.id }'
										id="togle-article"> <c:choose>
											<c:when test="${article.is_active == true}">
											Close
										</c:when>
											<c:otherwise>
											Open
										</c:otherwise>
										</c:choose>
									</a>
								</c:if>
								<a
									href="<c:url value="ArticleServlet?action=show&article_id=${article.id }" />"
									class="btn btn-primary" id='read-mode'>Read more</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			
		</div>
	</div>



	<script type="text/javascript">
		$("#togle-article").click(
				function() {
					$.get("ArticleServlet?action=togle&article_id="
							+ $(this).attr('name'), function(response) {
						if (response.active) {
							$("#togle-article").text("Close");
						} else {
							$("#togle-article").text("Open");
						}
					});

				});
	</script>


	
	<jsp:include page="../page/footer.jsp" />

</body>
</html>