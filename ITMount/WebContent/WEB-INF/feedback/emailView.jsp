<%@ page language="java" contentType="text/html;  charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
<title>Feedback</title>

<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/font-awesome.css" rel="stylesheet">
    <link href="resources/css/iCheck/custom.css" rel="stylesheet">
    <link href="resources/css/animate.css" rel="stylesheet">
    <link href="resources/css/styleEvent.css" rel="stylesheet">
    
    <link rel="stylesheet" href="resources/css/icomoon-social.css">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,600,800'
	rel='stylesheet' type='text/css'>


<!--[if lte IE 8]>
      <link rel="stylesheet" href="resources/css/leaflet.ie.css" />
  <![endif]-->
<link rel="stylesheet" href="resources/css/main.css">


<!-- block for autokomplite -->
<link rel="stylesheet" type="text/css"
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" />


<script src="http://cdn.leafletjs.com/leaflet-0.5.1/leaflet.js"></script>

<script src="resources/js/main-menu.js"></script>
<script src="resources/js/template.js"></script>
<script src="resources/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
</head>
<body>
	<jsp:include page="../page/header.jsp" />
<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					


				</div>
			</div>
		</div>
	</div>
    <div id="wrapper">

        <div id="page" >
       

        <div class="wrapper wrapper-content">
        <div class="row">
              <jsp:include page="menu.jsp"/>
            <div class="col-lg-9 animated fadeInRight">
            <div class="mail-box-header">
                <div class="pull-right tooltip-demo">
                    <a href="<c:url value='FeedbackServlet?action=reply&id=${feedback.getId() }' />" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Reply"><i class="fa fa-reply"></i> Reply</a>
                  
                    <a href="<c:url value='FeedbackServlet?action=moveTrash&id_trash=${feedback.getId() }' />" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Move to trash"><i class="fa fa-trash-o"></i> </a>
                </div>
                <h2>
                    View Message
                </h2>
                <div class="mail-tools tooltip-demo m-t-md">


                    <h3>
                        <span class="font-noraml">Subject: </span>${feedback.type}
                    </h3>
                    <h5>
                        <span class="pull-right font-noraml">${feedback.timeMessage}</span>
                        <span class="font-noraml">From: </span>${feedback.email}
                        
                    </h5>
                </div>
            </div>
                <div class="mail-box">


                <div class="mail-body">
                    ${feedback.content}
                </div>
                    <div class="mail-attachment">
 
                        <div class="attachment">
                           
                          
                            <div class="clearfix"></div>
                        </div>
                        </div>
                        <div class="mail-body text-right tooltip-demo">
                                <a class="btn btn-sm btn-white" href="<c:url value='FeedbackServlet?action=reply&id=${feedback.getId() }' />"><i class="fa fa-reply"></i> Reply</a>
                                 <a class="btn btn-sm btn-white" href="<c:url value='FeedbackServlet?action=moveTrash&id_trash=${feedback.getId() }' />"><i class="fa fa-trash-o"></i> Remove</a>
                               
                        </div>
                        <div class="clearfix"></div>


                </div>
            </div>
        </div>
        </div>
       
        </div>
        </div>

         
         
   

 

 <!-- Mainly scripts -->
    <script src="resources/js/jquery-2.1.1.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/jquery.metisMenu.js"></script>
    <script src="resources/js/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="resources/js/inspinia.js"></script>
    <script src="resources/js/pace.min.js"></script>

    <!-- iCheck -->
    <script src="resources/js/iCheck/icheck.min.js"></script>
    <script>
        $(document).ready(function(){
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
    </script>
	<jsp:include page="../page/footer.jsp" />

</body>
</html>