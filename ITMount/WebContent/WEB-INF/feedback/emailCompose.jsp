<%@ page language="java" contentType="text/html;  charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
<title>Feedback</title>
<link rel="stylesheet" href="resources/css/toastr.css" type="text/css">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/font-awesome.css" rel="stylesheet">
    <link href="resources/css/iCheck/custom.css" rel="stylesheet">
     <link href="resources/css/summernote.css" rel="stylesheet">
    <link href="resources/css/summernote-bs3.css" rel="stylesheet">
    <link href="resources/css/animate.css" rel="stylesheet">
    <link href="resources/css/styleEvent.css" rel="stylesheet">
    
    
    <link rel="stylesheet" href="resources/css/icomoon-social.css">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,600,800'
	rel='stylesheet' type='text/css'>


<link rel="stylesheet" href="resources/css/main.css">

<script type="text/javascript" src="resources/js/jquery-1.10.2.js"></script>


<link rel="stylesheet" type="text/css"
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" />


<script src="http://cdn.leafletjs.com/leaflet-0.5.1/leaflet.js"></script>

<script src="resources/js/main-menu.js"></script>
<script src="resources/js/template.js"></script>
<script src="resources/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script src="resources/js/toastr.js"></script>



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
                    
                    <a href="<c:url value='FeedbackServlet?action=refresh' />" class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="top" title="Discard email"><i class="fa fa-times"></i> Discard</a>
                </div>
                <h2>
                    Compse mail
                </h2>
            </div>
                <div class="mail-box">


                <div class="mail-body">

                    <form class="form-horizontal" method="get">
                        <div class="form-group"><label class="col-sm-2 control-label">To:</label>

                            <div class="col-sm-10"><input id="email" type="text" class="form-control" value="${feedback.email }"></div>
                        </div>
                        <div class="form-group"><label class="col-sm-2 control-label">Subject:</label>
								<input type ="hidden" id="name" value="${feedback.name }"></input>
								<input type ="hidden" id="type" value="${feedback.type }"></input> 
                            <div class="col-sm-10"><input id="subject" type="text" class="form-control" value="${subject }"></div>
                        </div>
                        </form>

                </div>

                    <div class="mail-text h-200">

                        <div class="summernote">
                        

                        </div>
<div class="clearfix"></div>
                        </div>
                    <div class="mail-body text-right tooltip-demo">
                        <a id="send" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="top" title="Send"><i class="fa fa-reply"></i> Send</a>
                        
                    </div>
                    <div class="clearfix"></div>





                </div>
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
    <script src="resources/js/icheck.min.js"></script>
     <script src="resources/js/summernote/summernote.min.js"></script>
   <script type="text/javascript">
  
	$("a#send")
			.click(
					function() {
						
						
						$.post("FeedbackServlet?action=submit",
										{
											message : $("div.note-editable").html(),
											email: $("input#email").val(),
											subject: $("input#subject").val(),
											name: $("input#name").val(),
											type: $("input#type").val(),
											
										},
										function(response) {
											if (response.success) {
												
												showToaast(response.success, 1);
												setTimeout(function () {
													window.location.href = '/ITMount/FeedbackServlet?action=inbox';
                                                }, 500);
												
											} else {
												showToaast(response.fail, 0);

											}
											
													
						});
						
					
					});
</script>
   
    <script>
    
    $(document).ready(function(){
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });


        $('.summernote').summernote({
           
            toolbar: [
                      ['color', ['color']],
                      ['style', ['style']],
                      ['font', ['bold', 'italic', 'underline', 'clear']],
                      ['fontname', ['fontname']],
                      ['para', ['ul', 'ol', 'paragraph']],
                      ['height', ['height']],
                      ['table', ['table']],
                      ['insert', ['hr']]
                     
                    ]
           });

    });
    var edit = function() {
        $('.click2edit').summernote({focus: true});
    };
    var save = function() {
        var aHTML = $('.click2edit').code(); //save HTML If you need(aHTML: array).
        $('.click2edit').destroy();
    };
  
  
    </script>
    
    <script type="text/javascript">
		function showToaast(message, issucces) {
			var i = -1;
			var toastCount = 0;
			var $toastlast;

			var shortCutFunction;
			if (issucces == 1) {
				shortCutFunction = "success";
			}

			if (issucces == 0) {
				shortCutFunction = "error";
			}

			var msg = $('#message').val();
			var title = $('#title').val() || '';
			var $showDuration = $('#showDuration');
			var $hideDuration = $('#hideDuration');
			var $timeOut = $('#timeOut');
			var $extendedTimeOut = $('#extendedTimeOut');
			var $showEasing = $('#showEasing');
			var $hideEasing = $('#hideEasing');
			var $showMethod = $('#showMethod');
			var $hideMethod = $('#hideMethod');
			var toastIndex = toastCount++;

			toastr.options = {

				closeButton : true,
				debug : true,
				newestOnTop : false,
				progressBar : false,
				positionClass : "toast-top-right",
				preventDuplicates : false,
				onclick : null,
				timeOut : 10000,
				showDuration : 300,
				hideDuration : 1000,
				extendedTimeOut : 1000,

				showEasing : "swing",
				hideEasing : "linear",
				showMethod : "fadeIn",
				hideMethod : "fadeOut"

			};

			msg = message;

			$('#toastrOptions').text(
					'Command: toastr["' + shortCutFunction + '"]("' + msg
							+ (title ? '", "' + title : '')
							+ '")\n\ntoastr.options = '
							+ JSON.stringify(toastr.options, null, 2));

			var $toast = toastr[shortCutFunction](msg, title); // Wire up an event handler to a button in the toast, if it exists
			$toastlast = $toast;

			if (typeof $toast === 'undefined') {
				return;
			}

		}
		
		
	</script>
    
	<jsp:include page="../page/footer.jsp" />

</body>
</html>