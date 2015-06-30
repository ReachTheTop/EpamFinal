<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="head.jsp" />

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
					<h2 class="title">Feature Rich</h2> <!-- Slide Text -->
					<h3 class="subtitle">Huge amount of components and over 30
						sample pages!</h3> <!-- Slide Image --> <img class="slide-img"
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
				<!-- Team Member -->
				<div class="col-md-4 col-sm-6">
					<div class="team-member">
						<!-- Team Member Photo -->
						<div class="team-member-image">
							<img src="resources/img/homepage-slider/foto1.png"
								alt="Name Surname">
						</div>
						<div class="team-member-info">
							<ul>
								<!-- Team Member Info & Social Links -->
								<li class="team-member-name">Volodya Lobachov</li>
								<li>Capitan, Web Developer <!-- Team Member Social Links -->
									<span class="team-member-social"> <a href="#"><i
											class="icon-facebook"></i></a> <a href="#"><i
											class="icon-github"></i></a> <a href="#"><i
											class="icon-tumblr"></i></a>
								</span>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- End Team Member -->
				<div class="col-md-4 col-sm-6">
					<div class="team-member">
						<div class="team-member-image">
							<img src="resources/img/homepage-slider/foto1.png"
								alt="Name Surname">
						</div>
						<div class="team-member-info">
							<ul>
								<li class="team-member-name">Maxim Borsky</li>
								<li>Web Developer <span class="team-member-social">
										<a href="#"><i class="icon-facebook"></i></a> <a href="#"><i
											class="icon-dribbble"></i></a> <a href="#"><i
											class="icon-tumblr"></i></a>
								</span>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-6">
					<div class="team-member">
						<div class="team-member-image">
							<img src="resources/img/homepage-slider/foto1.png"
								alt="Name Surname">
						</div>
						<div class="team-member-info">
							<ul>
								<li class="team-member-name">Petro Andrushchak</li>
								<li>Web Developer <span class="team-member-social">
										<a href="#"><i class="icon-facebook"></i></a> <a href="#"><i
											class="icon-dribbble"></i></a> <a href="#"><i
											class="icon-tumblr"></i></a>
								</span>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-6">
					<div class="team-member">
						<div class="team-member-image">
							<img src="resources/img/homepage-slider/foto1.png"
								alt="Name Surname">
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
				<div class="col-md-4 col-sm-6">
					<div class="team-member">
						<div class="team-member-image">
							<img src="resources/img/homepage-slider/foto1.png"
								alt="Name Surname">
						</div>
						<div class="team-member-info">
							<ul>
								<li class="team-member-name">Viktor Plutus</li>
								<li>Web Developer <span class="team-member-social">
										<a href="#"><i class="icon-facebook"></i></a> <a href="#"><i
											class="icon-dribbble"></i></a> <a href="#"><i
											class="icon-tumblr"></i></a>
								</span>
								</li>
							</ul>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>


	<!-- End Testimonials -->

	<div class="section">
		<div class="container">
			<h2>Contacts</h2>
			<div class="col-sm-6">

				<!-- Map -->
				<div>

					<iframe
						src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1286.692502484877!2d24.00872000000001!3d49.83522299999999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x473add78e0c6eefb%3A0xa7174b01c2d82f72!2z0LLRg9C7LiDQodGC0LXQv9Cw0L3QsCDQkdCw0L3QtNC10YDQuCwgMjjQkCwg0JvRjNCy0ZbQsiwg0JvRjNCy0ZbQstGB0YzQutCwINC-0LHQu9Cw0YHRgtGM!5e0!3m2!1suk!2sua!4v1435667996338"
						width="450" height="300" frameborder="0" style="border: 0"
						allowfullscreen></iframe>
				</div>
				<!-- End Map -->

			</div>

			<div class="col-sm-5">
				<!-- Contact Form -->
				<h3>Contact information</h3>
				<div class="contact-form-wrapper">
					<p class="contact-us-details">
						<b>Address:</b> Stepana Bandery 28A, Lviv, Ukraine<br />
						<b>Phone:</b> +380986463663<br /> <b>Skype:</b> EpamLabItMount<br />
						<b>Email:</b> <a href="mailto:getintoutch@yourcompanydomain.com">epamlabfinalproject@gmail.com </a>
					</p>
				</div>
				<!-- End Contact Info -->
			</div>


		</div>
	</div>
	
	<div class="section">
		<div class="container">
			<h2>Technology we have worked</h2>
			<div class="clients-logo-wrapper text-center row">
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6"><a href="#"><img src="resources/img/technology/log4j.png" alt="Client Name"></a></div>  
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6"><a href="#"><img src="resources/img/technology/apache_maven.png" alt="Client Name"></a></div>
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6"><a href="#"><img src="resources/img/technology/eclipse.png" alt="Client Name"></a></div>
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6"><a href="#"><img src="resources/img/technology/junit.png" alt="Client Name"></a></div>
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6"><a href="#"><img src="resources/img/technology/mysql.png" alt="Client Name"></a></div>  
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6"><a href="#"><img src="resources/img/technology/javaservlet.png" alt="Client Name"></a></div>
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6"><a href="#"><img src="resources/img/technology/jsp.png" alt="Client Name"></a></div>
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6"><a href="#"><img src="img/logos/ea.png" alt="Client Name"></a></div>
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6"><a href="#"><img src="img/logos/ea.png" alt="Client Name"></a></div>
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6"><a href="#"><img src="img/logos/ebay.png" alt="Client Name"></a></div>
						</div>


		</div>
	</div>

	<script src="http://cdn.leafletjs.com/leaflet-0.5.1/leaflet.js"></script>
	<script src="resources/js/jquery.fitvids.js"></script>
	<script src="resources/js/jquery.sequence-min.js"></script>
	<script src="resources/js/jquery.bxslider.js"></script>
	<script src="resources/js/main-menu.js"></script>
	<script src="resources/js/template.js"></script>

	<jsp:include page="footer.jsp" />

</body>
</html>