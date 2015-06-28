<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
<title><t:i18n id='article'/></title>
<jsp:include page="../page/head.jsp" />
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




	<div class="section">
		<div class="container">
			<div class="row">
				<!-- Blog Post -->
				<div class="col-sm-12">
					<div class="blog-post blog-single-post">
						<div class="single-post-title">
							<h3>
								<c:out value=" ${article.header }" />
							</h3>
						</div>
						<div class="single-post-info">
							<i class="glyphicon glyphicon-time"></i><span><c:out value="${article.date }"></c:out> </span>
						</div>

						<div class="single-post-content">${article.data }</div>
						<!-- Comments -->
						<div class="post-coments">
							<h4><t:i18n id='article.comments'/></h4>
							<c:forEach items="${comments }" var="comment">
								<ul class="post-comments">

									<li>
										<div class="comment-wrapper">
											<div class="comment-author">
												<a class="link" href="<c:url value='UserServlet?user_id=${comment.sender.id }' />"> 
												<img src="upload/${comment.sender.image }" alt="User Name">
												<c:out value="${comment.sender.name }" />
												<c:out value="${comment.sender.surname }" /></a> 
											</div>
											<p>
												<c:out value="${comment.content }" />
											</p>
											<!-- Comment Controls -->
											<div class="comment-actions">
												<span class="comment-date"> <fmt:formatDate
														value="${article.date }" pattern="MM.dd.yyyy HH:mm" /></span>

											</div>
										</div>
									</li>
								</ul>
							</c:forEach>
							<!-- Comments Form -->
							<h4><t:i18n id='article.leave.comment'/></h4>
							<c:choose>
								<c:when test="${not empty user }">
									<div class="comment-form-wrapper">
										<form
											action="ArticleServlet?action=comment&article_id=${article.id }"
											method="post">

											<div class="form-group">
												<label for="comment-message"><i
													class="glyphicon glyphicon-comment"></i> <b><t:i18n id='article.comment.message'/></b></label>
												<textarea class="form-control" name="content" rows="5"
													id="comment-message"></textarea>
											</div>
											<div class="form-group">
												<button type="submit" class="btn btn-large pull-right"><t:i18n id='article.comment.send'/></button>
											</div>
											<div class="clearfix"></div>
										</form>
									</div>
								</c:when>
								<c:otherwise>
								<div class="alert alert-info" role="alert">
									<t:i18n id='article.comment.authorize'/><a href="<c:url value="/login" />"><t:i18n id='login.login'/></a>
									
								</div>
								</c:otherwise>
							</c:choose>
							<!-- End Comment Form -->
						</div>
						<!-- End Comments -->
					</div>
				</div>
				<!-- End Blog Post -->
				<!-- Sidebar -->
			</div>
		</div>
	</div>



	<jsp:include page="../page/footer.jsp" />


</body>
</html>