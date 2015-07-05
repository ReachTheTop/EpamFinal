<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../page/head.jsp" />
<link rel="stylesheet" href="resources/css/tabPanel.css"></link>
<link rel="stylesheet" href="resources/css/styleOurTeam.css">
<link rel="stylesheet" href="resources/css/font-awesome.css"
	type="text/css">
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
					class="slide-img" src="resources/img/homepage-slider/foto1.png"
					alt="Slide 1" />
				</li>
				<!-- End Slide 1 -->
				<!-- Slide 2 -->
				<li class="bg3">
					<!-- Slide Title -->
					<h2 class="title">Color Schemes</h2> <!-- Slide Text -->
					<h3 class="subtitle">Comes with 5 color schemes and it's easy
						to make your own!</h3> <!-- Slide Image --> <img class="slide-img"
					src="resources/img/homepage-slider/foto2.png" alt="Slide 2" />
				</li>
				<!-- End Slide 2 -->
				<!-- Slide 3 -->
				<li class="bg1">
					<!-- Slide Title -->
					<h2 class="title">Openness</h2> <!-- Slide Text -->
					<h3 class="subtitle">We promote educational opportunity and social 
      justice by providing high-quality  education to all who wish to realise their 
      ambitions and fulfil their potential. We are committed to promoting equal opportunities
      for all, and close monitoring makes sure that we live up to our ideals.</h3> <!-- Slide Image --> <img class="slide-img"
					src="resources/img/homepage-slider/foto3.png" alt="Slide 3" />
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


	<div class="section">
		<div class="container">
			<h2>Our Team</h2>
			
			<div class="row">
				<blockquote class="pull-right">
					<p>The world is a dangerous place to live; not because of the
						people who are evil, but because of the people who don't do
						anything about it.</p>
					<small>by <cite>Albert Einstein</cite></small>
				</blockquote>

			</div>

			<div class="row">
				<!-- Team Member -->
				<div class="col-md-3 col-sm-5">
					<div class="team-member">
						<!-- Team Member Photo -->
						<div class="team-member-image">
							<img src="resources/img/team/vova.jpg" alt="Name Surname">
						</div>
						<div class="team-member-info">
							<ul>
								<!-- Team Member Info & Social Links -->
								<li class="team-member-name">Volodya Lobachov</li>
								<li>Capitan, Web Developer <!-- Team Member Social Links -->
									<span class="team-member-social"> <a href="#"><i
											class="fa fa-facebook"></i></a> <a href="#"><i
											class="fa fa-vk"></i></a> <a href="#"><i
											class="fa fa-linkedin"></i></a>
								</span>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- End Team Member -->
				<div class="col-md-3 col-sm-5">
					<div class="team-member">
						<div class="team-member-image">
							<img src="resources/img/team/maks.jpg" alt="Name Surname">
						</div>
						<div class="team-member-info">
							<ul>
								<li class="team-member-name">Maxim Borsky</li>
								<li>Web Developer <span class="team-member-social">
										<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
											class="fa fa-vk"></i></a> <a href="#"><i
											class="fa fa-linkedin"></i></a>
								</span>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-sm-5">
					<div class="team-member">
						<div class="team-member-image">
							<img src="resources/img/team/petro.jpg" alt="Name Surname">
						</div>
						<div class="team-member-info">
							<ul>
								<li class="team-member-name">Petro Andrushchak</li>
								<li>Web Developer <span class="team-member-social">
										<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
											class="fa fa-vk"></i></a> <a href="#"><i
											class="fa fa-linkedin"></i></a>
								</span>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-sm-5">
					<div class="team-member">
						<div class="team-member-image">
							<img src="resources/img/team/dana.jpg" alt="Name Surname">
						</div>
						<div class="team-member-info">
							<ul>
								<li class="team-member-name">Dana Tsapovska</li>
								<li>Web Developer <span class="team-member-social">
										<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
											class="fa fa-vk"></i></a> <a href="#"><i
											class="fa fa-linkedin"></i></a>
								</span>
								</li>
							</ul>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>




	<div class="section">
		<div class="container">
			<h2>Technology we have worked</h2>
			<div class="clients-logo-wrapper text-center row">
			
			<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
					<a href="#"><img src="resources/img/technology/bootstrap.png" alt="Client Name"></a>
				</div>
				
				<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
					<a href="#"><img src="resources/img/technology/log4j.png"
						alt="Client Name"></a>
				</div>
				<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
					<a href="#"><img
						src="resources/img/technology/apache_maven.png" alt="Client Name"></a>
				</div>
				<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
					<a href="#"><img src="resources/img/technology/eclipse.png"
						alt="Client Name"></a>
				</div>
				<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
					<a href="#"><img src="resources/img/technology/junit.png"
						alt="Client Name"></a>
				</div>
				<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
					<a href="#"><img src="resources/img/technology/mysql.png"
						alt="Client Name"></a>
				</div>
				<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
					<a href="#"><img src="resources/img/technology/javaservlet.png"
						alt="Client Name"></a>
				</div>
				<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
					<a href="#"><img src="resources/img/technology/jsp.png"
						alt="Client Name"></a>
				</div>
				<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
						<a href="#"><img src="resources/img/technology/xml.png" alt="Client Name"></a>
				</div>
				
				<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
					<a href="#"><img src="resources/img/technology/json.png" alt="Client Name"></a>
				</div>
			</div>


		</div>
	</div>
	
	
	<script src="http://cdn.leafletjs.com/leaflet-0.5.1/leaflet.js"></script>
	<script src="resources/js/jquery.fitvids.js"></script>
	<script src="resources/js/jquery.sequence-min.js"></script>
	<script src="resources/js/jquery.bxslider.js"></script>
	<script src="resources/js/main-menu.js"></script>
	

	<jsp:include page="footer.jsp" />

</body>
</html>