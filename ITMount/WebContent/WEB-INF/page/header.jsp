<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
       <%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
 <div class="mainmenu-wrapper">
	        <div class="container">
	        	<div class="menuextras">
					<div class="extras">
						<ul>
							<li>
							 <div class="btn-group">
                            <button data-toggle="dropdown" class="btn btn-primary btn-xs dropdown-toggle"><t:i18n id="hader.language"/><span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                	<c:forEach items="${languageList}" var="lang" >
                                	<li role="menuitem"><a href="<c:url value="/lenguage?language=${lang.getLanguage() }&country=${lang.getCountry()}" />"><img src="resources/img/flags/${lang.getImage()}"  onerror="this.src='resources/img/flags/up.png'" alt="${lang.getName()}">  ${lang.getName()}</a></li>
                                	
                                	</c:forEach>
                                	
									
                            </ul>
                        </div>
							 </li>
							 <!--Приклад силки для завантаження файлу  -->
							<%-- 	<li><a href="<c:url value="/downloadFile?file=photo\1892596744_author.png" />">Download Link</a></li>
 --%>
							
							
							<c:if test="${user!=null}">
							<li class="shopping-cart-items"><a href="<c:url value="/UserServlet" />"><i class="glyphicon glyphicon-user icon-white"><b> ${user.getName()}</b></i></a> </li>
							<li class="shopping-cart-items"><a href="<c:url value="/exit" />"><i class="glyphicon glyphicon-log-out icon-white"><b> Exit</b></i></a> </li>
							</c:if>
							<c:if test="${user==null }">
								<li class="shopping-cart-items"><a href="<c:url value="/login" />"><i class="glyphicon glyphicon-lock icon-white"> <b>Login</b></i></a></li>
							</c:if>
			        	
			        		
			        	</ul>
			        	
			        		
					</div>
		
		        </div>
		        
		        <nav id="mainmenu" class="mainmenu">
					<ul>
						<li class="logo-wrapper"><a href="index.html"><img src="resources/img/mPurpose-logo.png" alt="Multipurpose Twitter Bootstrap Template"></a></li>
						<li class="active">
							<a href="<c:url value="/home" />">Home</a>
						</li>
							<li>
							<a href="<c:url value="/CourseServlet" />">Course</a>
						</li>
						<li class="has-submenu">
							<a href="#">Pages +</a>
							<div class="mainmenu-submenu">
								<div class="mainmenu-submenu-inner"> 
									<div>
										<h4>Homepage</h4>
										<ul>
											<li><a href="<c:url value="/home" />">Homepage (Sample 1)</a></li>
											<li><a href="page-homepage-sample.html">Homepage (Sample 2)</a></li>
										</ul>
										<h4>Services & Pricing</h4>
										<ul>
											<li><a href="page-services-1-column.html">Services/Features (Rows)</a></li>
											<li><a href="page-services-3-columns.html">Services/Features (3 Columns)</a></li>
											<li><a href="page-services-4-columns.html">Services/Features (4 Columns)</a></li>
											<li><a href="page-pricing.html">Pricing Table</a></li>
										</ul>
										<h4>Team & Open Vacancies</h4>
										<ul>
											<li><a href="page-team.html">Our Team</a></li>
											<li><a href="page-vacancies.html">Open Vacancies (List)</a></li>
											<li><a href="page-job-details.html">Open Vacancies (Job Details)</a></li>
										</ul>
									</div>
									<div>
										<h4>Our Work (Portfolio)</h4>
										<ul>
											<li><a href="<c:url value="/openCreateTask"/>">Create Task</a></li>
																																	<li><a href="page-portfolio-2-columns-2.html">Portfolio (2 Columns, Option 2)</a></li>
											<li><a href="page-portfolio-3-columns-1.html">Portfolio (3 Columns, Option 1)</a></li>
											<li><a href="page-portfolio-3-columns-2.html">Portfolio (3 Columns, Option 2)</a></li>
											<li><a href="page-portfolio-item.html">Portfolio Item (Project) Description</a></li>
										</ul>
										<h4>General Pages</h4>
										<ul>
											<li><a href="page-about-us.html">About Us</a></li>
											<li><a href="page-contact-us.html">Contact Us</a></li>
											<li><a href="page-faq.html">Frequently Asked Questions</a></li>
											<li><a href="page-testimonials-clients.html">Testimonials & Clients</a></li>
											<li><a href="page-events.html">Events</a></li>
											<li><a href="page-404.html">404 Page</a></li>
											<li><a href="page-sitemap.html">Sitemap</a></li>
											<li><a href="#">Login</a></li>
											<li><a href="<c:url value="/registration" />">Register</a></li>
											<li><a href="page-password-reset.html">Password Reset</a></li>
											<li><a href="page-terms-privacy.html">Terms & Privacy</a></li>
											<li><a href="page-coming-soon.html">Coming Soon</a></li>
										</ul>
									</div>
									<div>
										<h4>eShop</h4>
										<ul>
											<li><a href="page-products-3-columns.html">Products listing (3 Columns)</a></li>
											<li><a href="page-products-4-columns.html">Products listing (4 Columns)</a></li>
											<li><a href="page-products-slider.html">Products Slider</a></li>
											<li><a href="page-product-details.html">Product Details</a></li>
											<li><a href="page-shopping-cart.html">Shopping Cart</a></li>
										</ul>
										<h4>Blog</h4>
										<ul>
											<li><a href="page-blog-posts.html">Blog Posts (List)</a></li>
											<li><a href="page-blog-post-right-sidebar.html">Blog Single Post (Right Sidebar)</a></li>
											<li><a href="page-blog-post-left-sidebar.html">Blog Single Post (Left Sidebar)</a></li>
											<li><a href="page-news.html">Latest & Featured News</a></li>
										</ul>
									</div>
								</div><!-- /mainmenu-submenu-inner -->
							</div><!-- /mainmenu-submenu -->
						</li>
						<li>
							<a href="credits.html">Credits</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>