<%@ page language="java" contentType="text/html;  charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
<title><t:i18n id='articles'/></title>
<jsp:include page="../page/head.jsp" />

<script
	src="${pageContext.request.contextPath}/assets/js/jquery.bootpag.min.js"></script>

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
						<a href="<c:url value='ArticleServlet' />"><t:i18n id='articles'/></a>
					</h1>

				</div>
			</div>
		</div>
	</div>



	<div class="section blog-posts-wrapper">
		<div class="container">
			<div class="row">
				<div class="col-md-12">

					<form action="<c:url value='/ArticleServlet' />" method="get">
						<div class='form-group'>
							<div class="input-group">
								<input hidden="true" id="pages" value="${pages }"> <input
									class="form-control" type="text" name="token"
									id='article-search' placeholder="<t:i18n id='article.search'/>"
									value="${token }"> <span class="input-group-btn">



									
										<button type="submit" class="btn btn-primary" value="">
											<span class="glyphicon glyphicon-search"></span>
										</button>
										<c:if test="${not empty user && user.role != 'student' }">
											<button type="button" class="btn btn-primary dropdown-toggle"
												data-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false">
												<span class="caret"></span> <span class="sr-only">Toggle
													Dropdown</span>
											</button>
											<ul class="dropdown-menu">
												<li><a
													href="<c:url value="/ArticleServlet?action=new" />"><t:i18n id='article.new'/></a></li>
												<li><a href="<c:url value="/ArticleServlet?action=myarticles"/>"><t:i18n id='article.my'/></a></li>

											</ul>
										</c:if>
									



								</span>
							</div>
						</div>
					</form>
				</div>
			</div>







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
								<span class="pull-left"
									style="margin-bottom: 20px; margin-top: 20px; margin-left: 15px;">
									<i class="glyphicon glyphicon-user"></i> <a
									href="ArticleServlet?token=${article.author.surname }"><c:out
											value="${article.author.name }" /> <c:out
											value="${article.author.surname }" /></a>
								</span>
								
								<a
									href="<c:url value="ArticleServlet?action=show&article_id=${article.id }" />"
									class="btn btn-primary" id='read-mode'><t:i18n id='article.more'/></a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			<div class="row">
				<div class="col-md-12">

					<div align="center" id="page-selection"></div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			$("#article-search").focus(function() {
				$(this).val("");
			})
		});
	</script>
	<script type="text/javascript">
		$('#page-selection').bootpag({
			total : Math.ceil($('#pages').val() / 5),
			page : "${page}"
		})

		$('#page-selection').on(
				"page",
				function(event, page) {
					var token;
					if (!$('article-search').val()) {
						token = "";
					}
					window.location.href = "/ITMount/ArticleServlet?page="
							+ (page - 1) + "&token=" + token;

				});
	</script>


	<jsp:include page="../page/footer.jsp" />

</body>
</html>