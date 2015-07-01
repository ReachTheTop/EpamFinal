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

				<p class="contact-us-details">
					<b>Address:</b> Stepana Bandery 28A, Lviv, Ukraine<br /> <b>Phone:</b>
					+380986463663<br /> <b>Skype:</b> EpamLabItMount<br /> <b>Email:</b>
					<a href="mailto:getintoutch@yourcompanydomain.com">epamlabfinalproject@gmail.com
					</a>
				</p>
				<!-- End Map -->

			</div>

			<div class="col-sm-5">
				<!-- Contact Form -->
				<!-- Contact Form -->
				<h3>Send Us a Message</h3>
				<div class="contact-form-wrapper">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="Name" class="col-sm-3 control-label"><b>Your
									name</b></label>
							<div class="col-sm-9">
								<input class="form-control" id="Name" type="text" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="contact-email" class="col-sm-3 control-label"><b>Your
									Email</b></label>
							<div class="col-sm-9">
								<input class="form-control" id="contact-email" type="text"
									placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="contact-message" class="col-sm-3 control-label"><b>Select
									Topic</b></label>
							<div class="col-sm-9">
								<select class="form-control" id="prependedInput">
									<option>Please select topic...</option>
									<option>General</option>
									<option>Services</option>
									<option>Orders</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="contact-message" class="col-sm-3 control-label"><b>Message</b></label>
							<div class="col-sm-9">
								<textarea class="form-control" rows="5" id="contact-message"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<button type="submit" class="btn pull-right">Send</button>
							</div>
						</div>
					</form>
				</div>
				<!-- End Contact Info -->
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
	<script src="resources/js/template.js"></script>

	<jsp:include page="footer.jsp" />

</body>
</html>