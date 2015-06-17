<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="head.jsp" />
</head>
<body>
	<jsp:include page="header.jsp" />

	<!-- Homepage Slider -->
	<div class="homepage-slider">
		<div id="sequence">
			<ul class="sequence-canvas">
				<!-- Slide 1 -->
				<li class="bg4">
					<!-- Slide Title -->
					<h2 class="title">Responsive</h2> <!-- Slide Text -->
					<h3 class="subtitle">It looks great on desktops, laptops,
						tablets and smartphones</h3> <!-- Slide Image --> <img
					class="slide-img" src="resources/img/homepage-slider/slide1.png"
					alt="Slide 1" />
				</li>
				<!-- End Slide 1 -->
				<!-- Slide 2 -->
				<li class="bg3">
					<!-- Slide Title -->
					<h2 class="title">Color Schemes</h2> <!-- Slide Text -->
					<h3 class="subtitle">Comes with 5 color schemes and it's easy
						to make your own!</h3> <!-- Slide Image --> <img class="slide-img"
					src="resources/img/homepage-slider/slide2.png" alt="Slide 2" />
				</li>
				<!-- End Slide 2 -->
				<!-- Slide 3 -->
				<li class="bg1">
					<!-- Slide Title -->
					<h2 class="title">Feature Rich</h2> <!-- Slide Text -->
					<h3 class="subtitle">Huge amount of components and over 30
						sample pages!</h3> <!-- Slide Image --> <img class="slide-img"
					src="resources/img/homepage-slider/slide3.png" alt="Slide 3" />
				</li>
				<!-- End Slide 3 -->
			</ul>
			<div class="sequence-pagination-wrapper">
				<ul class="sequence-pagination">
					<li>1</li>
					<li>2</li>
					<li>3</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- End Homepage Slider -->

	<jsp:include page="footer.jsp" />
	<jsp:include page="script.jsp" />
</body>
</html>