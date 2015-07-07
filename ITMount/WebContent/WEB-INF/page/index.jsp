<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITMount</title>
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
					<h2 class="title"><t:i18n id="index.stability"/></h2> <!-- Slide Text -->
					<h3 class="subtitle"><t:i18n id="index.stability.text"/></h3> <!-- Slide Image --> <img
					class="slide-img" src="resources/img/homepage-slider/foto1.png"
					alt="Slide 1" />
				</li>
				<!-- End Slide 1 -->
				<!-- Slide 2 -->
				<li class="bg3">
					<!-- Slide Title -->
					<h2 class="title"><t:i18n id="index.creativity"/></h2> <!-- Slide Text -->
					<h3 class="subtitle"><t:i18n id="index.creativity.text"/></h3> <!-- Slide Image -->
					<img class="slide-img"
					src="resources/img/homepage-slider/foto2.png" alt="Slide 2" />
				</li>
				<!-- End Slide 2 -->
				<!-- Slide 3 -->
				<li class="bg1">
					<!-- Slide Title -->
					<h2 class="title"><t:i18n id="index.openness"/></h2> <!-- Slide Text -->
					<h3 class="subtitle"><t:i18n id="index.openness.text"/></h3> <!-- Slide Image -->
					<img class="slide-img"
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
			<h2><t:i18n id="index.aboutUs"/></h2>
			<div class="clients-logo-wrapper text-center row">

				<div class="container">
					<div class="row">
						<div class="col-sm-6">
							<h3><t:i18n id="index.OurMission"/></h3>
							<p><t:i18n id="index.OurMission.text"/></p>
							<h3><t:i18n id="index.HowItWorks"/></h3>
							<p class="text-left" style="text-indent: 25px">     	<t:i18n id="index.HowItWorks.text1"/> <t:i18n id="index.HowItWorks.text2"/> <t:i18n id="index.HowItWorks.text3"/></p>
						</div>
						<div class="col-sm-6">
							<div class="service-image">
								<img src="resources/img/teamSucces.jpg" alt="Service Name">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="section">
		<div class="container">
			<h2><t:i18n id="index.OurTeam"/></h2>

			<div class="row">
				<blockquote class="pull-right">
					<p><t:i18n id="index.quote"/></p>
					<small><cite><t:i18n id="index.author"/></cite></small>
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
								<li class="team-member-name"><t:i18n id="index.vova"/></li>
								<li><t:i18n id="index.captain"/>, Web <t:i18n id="index.developer"/> <!-- Team Member Social Links -->
									<span class="team-member-social"> <a href="#"><i
											class="fa fa-facebook"></i></a> <a href="https://vk.com/id14722710"><i
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
								<li class="team-member-name"><t:i18n id="index.max"/></li>
								<li>Web <t:i18n id="index.developer"/> <span class="team-member-social">
										<a href="https://www.facebook.com/profile.php?id=100003795194298"><i class="fa fa-facebook"></i></a> <a href="https://vk.com/id116171656"><i
											class="fa fa-vk"></i></a> <a href="https://www.linkedin.com/profile/view?id=408414786&trk=hp-identity-name"><i
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
								<li class="team-member-name"><t:i18n id="index.petro"/></li>
								<li>Web <t:i18n id="index.developer"/> <span class="team-member-social">
										<a href="https://www.facebook.com/petro.chelsea"><i class="fa fa-facebook"></i></a> <a href="https://vk.com/mister_9"><i
											class="fa fa-vk"></i></a> <a href="https://www.linkedin.com/profile/preview?locale=ru_RU&trk=prof-0-sb-preview-primary-button"><i
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
								<li class="team-member-name"><t:i18n id="index.dana"/></li>
								<li>Web <t:i18n id="index.developer"/> <span class="team-member-social">
										<a href="https://www.facebook.com/dana.tsapovska"><i class="fa fa-facebook"></i></a> <a href="https://vk.com/id29325641"><i
											class="fa fa-vk"></i></a> <a href="https://www.linkedin.com/pub/dana-tsapovska/b1/4b0/6b3"><i
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
			<h2><t:i18n id="index.technology"/></h2>
			<div class="clients-logo-wrapper text-center row">

				<div class="row">
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
						<a href="#"><img src="resources/img/technology/bootstrap.png"
							alt="Client Name"></a>
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
						<a href="#"><img
							src="resources/img/technology/javaservlet.png" alt="Client Name"></a>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
						<a href="#"><img src="resources/img/technology/jsp.png"
							alt="Client Name"></a>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
						<a href="#"><img src="resources/img/technology/xml.png"
							alt="Client Name"></a>
					</div>

					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
						<a href="#"><img src="resources/img/technology/json.png"
							alt="Client Name"></a>
					</div>

					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
						<a href="#"><img src="resources/img/technology/ajax.png"
							alt="Client Name"></a>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6">
						<a href="#"><img src="resources/img/technology/tomcat.png"
							alt="Client Name"></a>
					</div>

				</div>

				<div class="row">

					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-6"
						style="float: left;">
						<a href="#"><img src="resources/img/technology/jdbc.png"
							alt="Client Name"></a>
					</div>

					<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
						<a href="#"><img src="resources/img/technology/jquery.png"
							alt="Client Name"></a>
					</div>

					<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
						<a href="#"><img src="resources/img/technology/gitHub.png"
							alt="Client Name"></a>
					</div>

					<div class="col-lg-2 col-md-1 col-sm-1 col-xs-1">
						<a href="#"><img src="resources/img/technology/1.png"
							alt="Client Name"></a>
					</div>

					<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
						<a href="#"><img src="resources/img/technology/AsyncIO.png"
							alt="Client Name"></a>
					</div>


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